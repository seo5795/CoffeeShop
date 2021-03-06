package com.seo.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seo.app.coffee.CartVO;
import com.seo.app.coffee.CoffeeService;
import com.seo.app.member.MemberService;
import com.seo.app.member.MemberVO;
import com.seo.app.member.impl.MemberDAO;
import com.seo.app.subscribe.SubscribeService;
import com.seo.app.subscribe.SubscribeVO;
import com.seo.app.subscribe.impl.SubscribeDAO;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private CoffeeService coffeeService;

	@ModelAttribute("conMap") // @RequestMapping이 설정된 메서드보다 먼저 수행됨
	public Map<String,String> searchConditionMap() {
		Map<String,String> conMap=new HashMap<String,String>();
		conMap.put("커피명", "cname");
		conMap.put("나라명", "ccountry");
		return conMap; // 반환값은 자동으로 Model에 저장 == (model.attribute("conMap",conMap)
	}

	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public String login(MemberVO mvo,HttpSession session) {
		//로그인
		System.out.println("FC:MemberController-login.do");
		mvo=memberService.getMember(mvo);
		if(mvo==null){
			return "login.jsp";
		}

		session.setAttribute("mId", mvo.getMid());
		session.setAttribute("mRank", mvo.getMrank());
		session.setAttribute("mName", mvo.getMname());
		session.setAttribute("mPoint", mvo.getMpoint());
		return "redirect:main.do";
	}

	@RequestMapping(value="/logout.do")
	public String logout(HttpSession session) {
		//로그아웃
		session.invalidate();
		System.out.println("FC:MemberController- logout.do");

		return "redirect:main.do";
	}
	@RequestMapping(value="/register.do", method=RequestMethod.POST)
	public String register(MemberVO mvo) {
		//회원가입
		System.out.println("FC:MemberController-register.do");

		if(!memberService.insertMember(mvo)){
			return "register.jsp";
		}		
		return "redirect:login.do";
	}

	@RequestMapping(value="/pay.do", method=RequestMethod.POST)
	public String pay(MemberVO mvo,HttpSession session) {
		//결제
		System.out.println("FC:MemberController-pay.do");
		//제고 update

		ArrayList<CartVO> cartlist = (ArrayList<CartVO>) session.getAttribute("cartlist");
		mvo.setMid((String)session.getAttribute("mId"));		


		//==============장바구니 상품 수량체크=============
		boolean flag=true;
		for (int i = 0; i < cartlist.size(); i++) {
			//
			if(!coffeeService.checkCnum(cartlist.get(i))){
				flag=false;
				break;
			}
		}

		if(flag==false) {
			System.out.println("수량부족!");
			return "cart.do";
		}
		
		
		//==================수량업데이트===============
		for (int i = 0; i < cartlist.size(); i++) {
			coffeeService.updateCoffee(cartlist.get(i));
		}

		//==================결제===============
		int mPoint = (Integer)session.getAttribute("mPoint")  - (Integer) session.getAttribute("total");
		if(mPoint<0){
			System.out.println("포인트부족!");
			return "cart.do";
		}	
		mvo.setMpoint(mPoint);
		memberService.pay(mvo);



		session.setAttribute("mPoint", mPoint);
		session.removeAttribute("cartlist");
		session.removeAttribute("total");
		return "main.do";


	}



	//----------------------모든 페이지 검색을 위한 get방식 처리------------

	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login() {
		return "login.jsp";
	}
	@RequestMapping(value="/register.do", method=RequestMethod.GET)
	public String register() {
		return "register.jsp";
	}
}
