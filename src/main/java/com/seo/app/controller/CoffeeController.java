package com.seo.app.controller;

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

import com.seo.app.coffee.CartVO;
import com.seo.app.coffee.CoffeeService;
import com.seo.app.coffee.CoffeeVO;
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
	public String getCoffeeList(CoffeeVO cvo,Model model) {
		System.out.println("FC:CoffeeController-main.do");
		List<CoffeeVO> datas=coffeeService.getCoffeeList(cvo);
		model.addAttribute("datas", datas); // Model을 이용하여 전달할 정보를 저장!
		return "main.jsp";
	}
	@RequestMapping(value="/singleProduct.do")
	public String singleProduct(SubscribeVO svo, CoffeeVO cvo,CoffeeVO vo,Model model, HttpSession session) {
		System.out.println("FC:CoffeeController-singleProduct.do");
		//커피 정보
		cvo=coffeeService.getCoffee(cvo);
		model.addAttribute("data", cvo); // Model을 이용하여 전달할 정보를 저장!
		
		//동일나라 커피 리스트
		vo.setCcategory("ccountry");
		vo.setKeyword(cvo.getCcountry());
		ArrayList<CoffeeVO> datas=(ArrayList<CoffeeVO>) coffeeService.getCoffeeList(vo);
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
	public String shop(CoffeeVO cvo,Model model) {
		System.out.println("FC:CoffeeController-shop.do");
		List<CoffeeVO> datas=coffeeService.getCoffeeList(cvo);
		model.addAttribute("datas", datas); // Model을 이용하여 전달할 정보를 저장!
		//selectAll로 해당 나라의 리스트를 보내야함

		return "shop.jsp";
	}
	//---------------------제품목록끝--------------------------
	//---------------------장바구니-----------------------
	@RequestMapping(value="/cart.do")
	public String cart(CartVO cartvo,CoffeeVO cvo,Model model,HttpSession session) {
		System.out.println("FC:CoffeeController-cart.do");

		ArrayList<CartVO> cartlist = (ArrayList<CartVO>)session.getAttribute("cartlist");
		
		if(cartlist == null) {//장바구니 리스트가 없다면 arrayList생성
			cartlist=new ArrayList<CartVO>();
		}
		System.out.println("배열사이즈1:"+cartlist.size());
		System.out.println("cid2: "+cvo.getCid());
		Boolean flag=false;//분기처리용 flag변수		
		for (int i = 0; i < cartlist.size(); i++) {
			if(cartlist.get(i).getCid()==cvo.getCid()) {
				cartlist.remove(i);
				//검색목록리스트에 중복 검색항목이 있다면 true
				flag=true;
			}
		}

		if(cvo.getCid()!=0) {
			//cid값을 받아온다면 			
			cvo=coffeeService.getCoffee(cvo);//장바구니에 추가할 커피 객체
			cartvo.setCid(cvo.getCid());
			cartvo.setCname(cvo.getCname());//커피객체의 커피재고에 장바구니 커피 갯수 set(바꿔야함)
			cartvo.setCcountry(cvo.getCcountry());
			cartvo.setCnum(cvo.getCnum());
			cartvo.setCprice(cvo.getCprice());
			cartvo.setSubtotal(cvo.getCprice()*cartvo.getNumber());
			cartvo.setCpic(cvo.getCpic());

			cartlist.add(0,cartvo);
		}


		int total=0;
		System.out.println("배열사이즈2:"+cartlist.size());
		for (int i = 0; i < cartlist.size(); i++) {
			total+=(cartlist.get(i).getCprice()*cartlist.get(i).getNumber());					
		}
		System.out.println("총가격: "+total);

		session.setAttribute("cartlist", cartlist); // Model을 이용하여 전달할 정보를 저장!
		session.setAttribute("total", total);
		return "redirect:cart.jsp";
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
		return "redirect:cart.do";
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
		return "redirect:checkout.jsp";
	}



}
