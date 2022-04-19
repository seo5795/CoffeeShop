package com.seo.app.member;

public interface MemberService {
	boolean insertMember(MemberVO vo);
	MemberVO getMember(MemberVO vo);
	boolean pay(MemberVO vo);
}
