package com.seo.app.member;

import java.util.List;

import com.seo.app.member.MemberVO;

public interface MemberService {
	boolean insertMember(MemberVO vo);
	MemberVO getMember(MemberVO vo);
}
