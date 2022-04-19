package com.seo.app.member;

public class MemberVO {
	private String mid;//회원id
	private String mpw;//회원 패스워드
	private String mname;//회원
	private int mpoint;//회원포인트
	private int mrank;
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	
	
	public int getMpoint() {
		return mpoint;
	}
	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}
	public int getMrank() {
		return mrank;
	}
	public void setMrank(int mrank) {
		this.mrank = mrank;
	}
	@Override
	public String toString() {
		return "MemberVO [mid=" + mid + ", mpw=" + mpw + ", mname=" + mname + ", mpoint=" + mpoint + "]";
	}

	
	
}
