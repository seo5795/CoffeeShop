package com.seo.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.seo.app.coffee.CartVO;
import com.seo.app.coffee.CoffeeService;
import com.seo.app.coffee.CoffeeVO;
import com.seo.app.coffee.PageVO;
import com.seo.app.coffee.impl.CoffeeDAO;
import com.seo.app.subscribe.SubscribeService;
import com.seo.app.subscribe.SubscribeVO;
import com.seo.app.subscribe.impl.SubscribeDAO;


@Controller // ★x100
// 1. <bean> -> @Controller
// 2. implements Controller 필요없음 -> 제거
// 3. 메서드 강제 사라짐!!!
// 4. 완전한 POJO가 되었다!!!!!
// 		-> req,res,...가 존재하지않음 => 경량의 객체
@SessionAttributes("data")
public class CoffeeController {
	
	@Autowired
	private CoffeeService coffeeService;
	@Autowired
	private SubscribeService subscribeService;
	
	   @ModelAttribute("conMap") // @RequestMapping이 설정된 메서드보다 먼저 수행됨
	   public Map<String,String> searchConditionMap() {
	      Map<String,String> conMap=new HashMap<String,String>();
	      conMap.put("커피명", "cname");
	      conMap.put("나라명", "ccountry");
	      return conMap; // 반환값은 자동으로 Model에 저장 == (model.attribute("conMap",conMap)
	   }
	   
	//---------------------제품목록--------------------------
	@RequestMapping(value="/main.do")
	public String getCoffeeList(CoffeeVO cvo,PageVO pvo,Model model) {
		System.out.println("FC:CoffeeController-main.do");
		
		pvo.setPageNum(1);
		pvo.setAmount(6);
		
		List<CoffeeVO> datas=coffeeService.getCoffeeList(cvo, pvo);
		model.addAttribute("datas", datas); // Model을 이용하여 전달할 정보를 저장!
		return "main.jsp";
	}
	@RequestMapping(value="/insertCoffee.do")
	public String insertCoffee(CoffeeVO cvo) throws IllegalStateException, IOException {
		System.out.println("FC:CoffeeController-insertCoffee.do");
		
		// [파일업로드 로직]
				MultipartFile uploadFile = cvo.getUploadFile();
				if(!uploadFile.isEmpty()) { //정보가 들어있다면
					String name = uploadFile.getOriginalFilename();
					System.out.println("파일명: " + name);
					uploadFile.transferTo(new File("C:\\SEO\\workspace3\\coffee\\src\\main\\webapp\\assets\\coffee\\"+name));
					cvo.setCpic("assets/coffee/"+name);																			// 주의▲
					//File -> io 임포트
				}
				else { // 정보가 비어있다면(null)
					cvo.setCpic("assets/coffee/default.jpg");
				}
				coffeeService.insertBoard(cvo);
				return "main.do"; 


	}
	@RequestMapping(value="/singleProduct.do")
	public String singleProduct(SubscribeVO svo, CoffeeVO cvo,CoffeeVO vo,PageVO pvo,Model model, HttpSession session) {
		System.out.println("FC:CoffeeController-singleProduct.do");
		//커피 정보
		cvo=coffeeService.getCoffee(cvo);
		model.addAttribute("data", cvo); // Model을 이용하여 전달할 정보를 저장!
		
		//동일나라 커피 리스트
		vo.setCcategory("ccountry");
		vo.setKeyword(cvo.getCcountry());
		ArrayList<CoffeeVO> datas=(ArrayList<CoffeeVO>) coffeeService.getCoffeeList(cvo,pvo);
		model.addAttribute("datas", datas);
		
		//구독여부
		svo.setMid((String)session.getAttribute("mId"));
		System.out.println("1"+svo);
		svo=subscribeService.getSubscribe(svo);	
		model.addAttribute("sdata",svo);
		System.out.println("2"+svo);
		
		return "single-product.jsp";
	}

	@RequestMapping(value="/shop.do")
	public String shop(CoffeeVO cvo,Model model,PageVO pvo) {
		System.out.println("FC:CoffeeController-shop.do");
		
				// 1. 화면전환 시에 조회하는 페이지번호 and 화면에 그려질 데이터개수 2개를 전달받음
				
				if(pvo.getPageNum() == 0 || pvo.getAmount() == 0) {
					pvo.setPageNum(1);
					pvo.setAmount(3);
				}
		
				System.out.println("shop.do:"+cvo.getKeyword());
				System.out.println("shop.do:"+cvo.getCcategory());
				
				if(cvo.getCprice()>cvo.getCprice2()) {
					//가격순서를 잘못 입력받았을 때
					int a=0;
					a=cvo.getCprice2();
					cvo.setCprice2(cvo.getCprice());
					cvo.setCprice(a);	
				}
				
				// 2. pageVO생성
				List<CoffeeVO> list = coffeeService.getCoffeeList(cvo,pvo);
				int total = coffeeService.getBoardListCnt(cvo); // 전체게시글수
				PageVO pageVO = new PageVO(pvo.getPageNum(), pvo.getAmount(), total);
				
				// 3. 페이지네이션을 화면에 전달
				model.addAttribute("pageVO", pageVO);
				//검색정보를 통한 페이징처리를 위해 coffee객체 저장
				model.addAttribute("cvo",cvo);
				// 화면에 가지고 나갈 list를 request에 저장 !!
				model.addAttribute("list", list);
	

		return "shop.jsp";
	}
	//---------------------제품목록끝--------------------------
	//---------------------장바구니추가-------------------------
	@RequestMapping(value="/cart.do")
	public String cart(CartVO cartvo,CoffeeVO cvo,Model model,HttpSession session) {
		System.out.println("FC:CoffeeController-cart.do");

		ArrayList<CartVO> cartlist = (ArrayList<CartVO>)session.getAttribute("cartlist");
		
		if(cartlist == null) {//1. 장바구니 리스트가 없다면 arrayList생성
			cartlist=new ArrayList<CartVO>();
		}
		System.out.println("배열사이즈1:"+cartlist.size());
		System.out.println("cid2: "+cvo.getCid());
		for (int i = 0; i < cartlist.size(); i++) {
			if(cartlist.get(i).getCid()==cvo.getCid()) {
				cartlist.remove(i);
				//장바구니에 중복값이 있다면 기존값 삭제
			}
		}

		if(cvo.getCid()!=0) {
			//cid값을 받아온다면 			
			cvo=coffeeService.getCoffee(cvo);//장바구니에 추가할 커피 객체
			cartvo.setCid(cvo.getCid());
			cartvo.setCname(cvo.getCname());
			cartvo.setCcountry(cvo.getCcountry());
			cartvo.setCnum(cvo.getCnum());
			cartvo.setCprice(cvo.getCprice());
			cartvo.setSubtotal(cvo.getCprice()*cartvo.getNumber());//장바구니 커피당 가격합
			cartvo.setCpic(cvo.getCpic());

			cartlist.add(0,cartvo);
		}


		int total=0;
		System.out.println("배열사이즈2:"+cartlist.size());
		for (int i = 0; i < cartlist.size(); i++) {
			total+=(cartlist.get(i).getCprice()*cartlist.get(i).getNumber());//장바구니의 총가격					
		}
		System.out.println("총가격: "+total);

		// Model을 이용하여 전달할 정보를 저장!
		session.setAttribute("cartlist", cartlist); 
		session.setAttribute("total", total);
		return "cart.jsp";
	}
	@RequestMapping(value="/cartRemove.do")
	public String cartRemove(CoffeeVO cvo,HttpSession session) {
		//장바구니 상품 제거
		ArrayList<CartVO> cartlist = (ArrayList<CartVO>)session.getAttribute("cartlist");
		System.out.println("removec-id: "+cvo.getCid());
		for (int i = 0; i < cartlist.size(); i++) {
			if(cartlist.get(i).getCid()==cvo.getCid()) {
				cartlist.remove(i);
				//검색목록리스트에 중복 검색항목이 있다면 true
			}
		}
			
		//selectAll로 해당 나라의 리스트를 보내야함
		return "redirect:cart.do";
	}
	
	@RequestMapping(value="/cartupdate.do")
	public String cartUpdate(CartVO cartvo,CoffeeVO cvo,HttpSession session) {
		//장바구니 수령 변경
		ArrayList<CartVO> cartlist = (ArrayList<CartVO>)session.getAttribute("cartlist");
		System.out.println("cartvo: "+cartvo.getNumber());
		System.out.println("cid: "+cvo.getCid());
		for (int i = 0; i < cartlist.size(); i++) {
			if(cartlist.get(i).getCid()==cvo.getCid()) {
				cartlist.get(i).setNumber(cartvo.getNumber());
				cartlist.get(i).setSubtotal(cartvo.getNumber()*cartlist.get(i).getCprice());
			}
		}
		System.out.println("cartvo2: "+cartvo.getNumber());
		//selectAll로 해당 나라의 리스트를 보내야함
		return "cart.do";
	}
	//----------------------장바구니 끝------------------

		//----------------------구독---------------------------
		@RequestMapping(value="/subscribe.do")
		public String subscribe(@ModelAttribute("data")CoffeeVO cvo,SubscribeVO svo,Model model) {
			//구독하기
			System.out.println("FC:MemberController-Subscribe.do");
			
			subscribeService.insertSubscribe(svo);		
			
			int cid = cvo.getCid();
			model.addAttribute("cid", cid);
			return "singleProduct.do";
		}
		
		@RequestMapping(value="/delsubscribe.do")
		public String delsubscribe(@ModelAttribute("data")CoffeeVO cvo, SubscribeVO svo, Model model) {
			//구독취소
			System.out.println("FC:MemberController-delSubscribe.do");
			
			subscribeService.deleteSubscribe(svo);	
			
			
			//@SessionAttributes("data")에 저장해둔 커피 객체사용
			int cid = cvo.getCid();
			model.addAttribute("cid", cid);
			return "singleProduct.do";
		}
	
	
	//----------------------모든 페이지 검색을 위한 get방식 처리-----------
	@RequestMapping(value="/checkout.do", method=RequestMethod.GET)
	public String checkout() {
		return "checkout.jsp";
	}



}
