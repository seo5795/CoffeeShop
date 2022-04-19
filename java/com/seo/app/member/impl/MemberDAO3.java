package com.seo.app.member.impl;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seo.app.member.MemberVO;

@Repository("MemberDAO3")
public class MemberDAO3 {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public MemberVO getMember(MemberVO vo) {//로그인
		System.out.println("getMember() 호출됨");
		return (MemberVO)mybatis.selectOne("MemberDAO.getMember",vo);
	}

	public boolean insertMember(MemberVO vo) {//회원가입
		if(mybatis.insert("MemberDAO.insertMember",vo)>0) {
			return true;
		}
		return false;
		
	}
	
	public boolean pay(MemberVO vo) {//회원가입
		if( mybatis.update("MemberDAO.pay", vo)>0) {
			return true;
		}
		return false;
	}
	
}
