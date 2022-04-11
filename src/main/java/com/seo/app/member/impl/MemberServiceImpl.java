package com.seo.app.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seo.app.coffee.CartVO;
import com.seo.app.member.MemberService;
import com.seo.app.member.MemberVO;
@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO2 memberDAO;
	@Override
	public boolean insertMember(MemberVO vo) {
		return memberDAO.insertMember(vo);
	}

	@Override
	public MemberVO getMember(MemberVO vo) {
		return memberDAO.getMember(vo);
	}

	@Override
	public boolean pay(MemberVO vo, List<CartVO> cartlist) {
		// TODO Auto-generated method stub
		return memberDAO.pay(vo, cartlist);
	}

}
