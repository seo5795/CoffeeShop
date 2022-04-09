package com.seo.app.coffee;



public class Pagination {

	private int listSize = 6;   //한 페이지 목록의 개수    //초기값으로 목록개수를 10으로 셋팅
	private int rangeSize = 10;  //한 페이지 범위의 개수    //초기값으로 페이지범위를 10으로 셋팅
	private int page;			//현재 페이지 번호	
	private int range;			//각 페이지 범위 시작 번호 
	private int listCnt; 		// 총 게시물의 개수
	private int pageCnt;		//총 페이지 범위의 개수
	private int startPage;		//각페이지 시작번호
	private int startList;		//게시판의 시작번호
	private int endPage;		//각페이지 끝 번호
	private boolean prev;
	private boolean next;
	

	public int getRangeSize() {
		return rangeSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getListCnt() {
		return listCnt;
	}

	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}
	
	public int getStartList() {
		return startList;
	}

	public void pageInfo(int page, int range, int listCnt) {

		this.page = page;//현제 페이지 정보
		this.range = range;//현재 페이지 범위 정보
		this.listCnt = listCnt;//게시물의 총 개수

		//전체 페이지수 
		this.pageCnt = (int) Math.ceil((double)listCnt/listSize);

		//시작 페이지
		this.startPage = (range - 1) * rangeSize + 1 ;

		//끝 페이지
		this.endPage = range * rangeSize;				

		//게시판 시작번호
		this.startList = (page - 1) * listSize;

		

		//이전 버튼 상태
		this.prev = range == 1 ? false : true;		

		//다음 버튼 상태
		this.next = pageCnt > endPage ? true : false;

		if (this.endPage > this.pageCnt) {
			this.endPage = this.pageCnt;
			this.next = false;
		}
	}
}



