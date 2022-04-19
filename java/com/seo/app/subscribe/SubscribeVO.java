package com.seo.app.subscribe;

public class SubscribeVO {
	private int sid;//구독번호
	private String mid;//회원아이디
	private int cid;//커피아이디
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	@Override
	public String toString() {
		return "SubscribeVO [sid=" + sid + ", mid=" + mid + ", cid=" + cid + "]";
	}
	
	
}
