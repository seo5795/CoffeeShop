package com.seo.app.coffee;

public class CoffeeVO {
	private int cid;//pk
	private String cname;//이름
	private String ccountry;//원산지
	private int cnum;//수량
	private int cprice;//커피가격
	private int cprice2;//커피 검색용
	private String ccontent;//커피설명
	private String cpic;//커피사진
	private String ccategory;//검색용 멤버변수
	private String keyword; //검색어

	public int getCprice() {
		return cprice;
	}
	public void setCprice(int cprice) {
		this.cprice = cprice;
	}
	
	public int getCprice2() {
		return cprice2;
	}
	public void setCprice2(int cprice2) {
		this.cprice2 = cprice2;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	
	public String getCcountry() {
		return ccountry;
	}
	public void setCcountry(String ccountry) {
		this.ccountry = ccountry;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}	
	public String getCcategory() {
		return ccategory;
	}
	public void setCcategory(String ccategory) {
		this.ccategory = ccategory;
	}	
	public String getCpic() {
		return cpic;
	}
	public void setCpic(String cpic) {
		this.cpic = cpic;
	}
	
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
	@Override
	public String toString() {
		return "CoffeeVO [cid=" + cid + ", cname=" + cname + ", ccountry=" + ccountry + ", cnum=" + cnum + ", cprice="
				+ cprice + ", cprice2=" + cprice2 + ", ccontent=" + ccontent + ", cpic=" + cpic + ", ccategory="
				+ ccategory + ", keyword=" + keyword + "]";
	}


	
	
	
	
	



}
