package com.seo.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seo.app.coffee.CartVO;
import com.seo.app.coffee.CoffeeVO;
import com.seo.app.coffee.impl.CoffeeDAO;


@Controller // ★x100
// 1. <bean> -> @Controller
// 2. implements Controller 필요없음 -> 제거
// 3. 메서드 강제 사라짐!!!
// 4. 완전한 POJO가 되었다!!!!!
// 		-> req,res,...가 존재하지않음 => 경량의 객체
public class CoffeeController {
	// 1. Controller 교체
	// 2. 반환타입의 변경 -> ModelAndView(무엇을 보낼지_정보_datas,data,member,... + 어디로 가야하는지_경로)
	@RequestMapping(value="/main.do")
	public ModelAndView getCoffeeList(CoffeeVO cvo,CoffeeDAO cdao,ModelAndView mav) {
		System.out.println("FC:CoffeeController-main.do");
		List<CoffeeVO> datas=cdao.getCoffeeList(cvo);
		mav.addObject("datas", datas); // Model을 이용하여 전달할 정보를 저장!
		mav.setViewName("main.jsp");
		return mav;
	}
	@RequestMapping(value="/singleProduct.do")
	public ModelAndView singleProduct(CoffeeVO cvo,CoffeeDAO cdao,ModelAndView mav) {
		System.out.println("FC:CoffeeController-singleProduct.do");
		CoffeeVO data=cdao.getCoffee(cvo);
		mav.addObject("data", data); // Model을 이용하여 전달할 정보를 저장!
		mav.setViewName("single-product.jsp");
		return mav;
	}

	@RequestMapping(value="/shop.do")
	public ModelAndView shop(CoffeeVO cvo,CoffeeDAO cdao,ModelAndView mav) {
		System.out.println("FC:CoffeeController-shop.do");
		List<CoffeeVO> datas=cdao.getCoffeeList(cvo);
		mav.addObject("datas", datas); // Model을 이용하여 전달할 정보를 저장!
		mav.setViewName("shop.jsp");
		//selectAll로 해당 나라의 리스트를 보내야함

		return mav;
	}

	@RequestMapping(value="/cart.do")
	public ModelAndView cart(CartVO cartvo,CoffeeVO cvo,CoffeeDAO cdao,ModelAndView mav,HttpSession session) {
		System.out.println("FC:CoffeeController-cart.do");

		ArrayList<CartVO> cartlist = (ArrayList<CartVO>)session.getAttribute("cartlist");
		
		if(cartlist == null) {//검색리스트가 없다면 arrayList생성
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
			cvo=cdao.getCoffee(cvo);//장바구니에 추가할 커피 객체
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
		mav.setViewName("cart.jsp");
		return mav;
	}
	@RequestMapping(value="/cartRemove.do")
	public String cartRemove(CoffeeVO cvo,CoffeeDAO cdao,HttpSession session) {
		
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
	public String cartUpdate(CartVO cartvo,CoffeeVO cvo,CoffeeDAO cdao,ModelAndView mav,HttpSession session) {
		
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



}
