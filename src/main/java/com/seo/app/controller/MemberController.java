package com.seo.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seo.app.member.MemberVO;
import com.seo.app.member.impl.MemberDAO;

@Controller
public class MemberController {

	
	@RequestMapping(value="/login.do")
	public String login(MemberVO mvo,MemberDAO mdao,HttpSession session) {
		//로그인
		System.out.println("FC:MemberController-login.do");
		mvo=mdao.getMember(mvo);
		if(mvo==null){
			return "redirect:login.jsp";
		}
		
		session.setAttribute("mRank", mvo.getMrank());
		session.setAttribute("mName", mvo.getMname());
		session.setAttribute("mPoint", mvo.getMpoint());
		return "redirect:main.do";
	}
	@RequestMapping(value="/logout.do")
	public String register(HttpSession session) {
		//로그아웃
		session.invalidate();
		System.out.println("FC:MemberController- logout.do");
		
		return "redirect:main.do";
	}
	@RequestMapping(value="/register.do")
	public String register(MemberVO mvo,MemberDAO mdao,ModelAndView mav) {
		//회원가입
		System.out.println("FC:MemberController-register.do");
		
		if(!mdao.insertMember(mvo)){
			return "redirect:register.jsp";
		}		
		return "redirect:login.jsp";
	}
}
