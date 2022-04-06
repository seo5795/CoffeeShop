package com.seo.app.coffee;

public class CartVO {
	//카트담기 전용 VO
	private int cid;//pk
	private String cname;//이름
	private String ccountry;//원산지
	private int cnum;//수량
	private int cprice;//커피가격
	private int cprice2;//커피 검색용
	private String ccontent;//커피설명
	private String cpic;//커피사진
	private int number;//카트에 넣을 수량
	private int subtotal;//각 커피가격 총 합
	public int getCid() {
		return cid;
	}
	public String getCname() {
		return cname;
	}
	public String getCcountry() {
		return ccountry;
	}
	public int getCnum() {
		return cnum;
	}
	public int getCprice() {
		return cprice;
	}
	public int getCprice2() {
		return cprice2;
	}
	public String getCcontent() {
		return ccontent;
	}
	public String getCpic() {
		return cpic;
	}
	public int getNumber() {
		return number;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public void setCcountry(String ccountry) {
		this.ccountry = ccountry;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public void setCprice(int cprice) {
		this.cprice = cprice;
	}
	public void setCprice2(int cprice2) {
		this.cprice2 = cprice2;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public void setCpic(String cpic) {
		this.cpic = cpic;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
	@Override
	public String toString() {
		return "CartVO [cid=" + cid + ", cname=" + cname + ", ccountry=" + ccountry + ", cnum=" + cnum + ", cprice="
				+ cprice + ", cprice2=" + cprice2 + ", ccontent=" + ccontent + ", cpic=" + cpic + ", number=" + number
				+ "]";
	}
	
	
}
