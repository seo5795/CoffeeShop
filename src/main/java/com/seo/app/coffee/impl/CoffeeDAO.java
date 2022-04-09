package com.seo.app.coffee.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.seo.app.coffee.CoffeeVO;
import com.seo.app.coffee.Pagination;
import com.seo.app.common.JDBCUtil;

@Repository("CoffeeDAO1")
public class CoffeeDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;


	//커피상품 추가
	private final String COFFEE_INSERT="INSERT INTO COFFEE (CID,CNAME,CCOUNTRY,CNUM,CPRICE,CCONTENT,CPIC)"
			+" VALUES ((SELECT NVL(MAX(CID),1000)+1 from COFFEE),?,?,?,?,?,?)";
	//커피 상세보기
	private final String COFFEE_SELECTONE="SELECT * FROM COFFEE WHERE CID=?";
	//커피 검색
	private final String COFFEE_SELECTALL="SELECT * FROM COFFEE ORDER BY CID DESC";
	//커피 이름 포함검색
	private final String COFFEE_SELECTALLNAME="SELECT * FROM COFFEE"
			+" WHERE CNAME LIKE '%'||?||'%' ORDER BY CID DESC";
	//커피 원산지 검색
	private final String COFFEE_SELECTALLCOUNTRY = "SELECT * FROM COFFEE" 
			+" WHERE CCOUNTRY LIKE '%'||?||'%' ORDER BY CID DESC";
	//커피 가격 검색
	private final String COFFEE_SELECTALLPRICE = "SELECT * FROM COFFEE"
			+" WHERE CPRICE>=? AND CPRICE<=? ORDER BY CPRICE DESC";
	//구독 커피 검색
	private final String COFFEE_SELECTJOIN = "SELECT A.CID, A.CNAME, A.CCOUNTRY,A.CNUM,A.CPRICE,A.CCONTENT,A.CPIC" 
			+" FROM COFFEE A, SUBSCRIBE B WHERE A.CID = B.CID AND B.MID = ?";

	//=============================================================
	//페이징
	private final String COFFEE_PAGINATION= "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* FROM COFFEE A)"
			+" WHERE RNUM BETWEEN ? AND ?";
	private final String COFFEELIST_CNT ="SELECT count(*) as listCnt FROM COFFEE";
	
	
	
	
	public int getBoardListCnt() {
		//총 게시글 개수 확인
		conn=JDBCUtil.connect();
		int listCnt=0;
		try {
			pstmt=conn.prepareStatement(COFFEELIST_CNT);
			ResultSet rs =pstmt.executeQuery();
			rs.next();
			listCnt=rs.getInt("CID");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCnt;
	}
	
	public List<CoffeeVO> getCoffeePage(Pagination vo) {//커피 검색리스트
		System.out.println("CoffeeDAO:CoffeeDAO-getCoffeePage");
		List<CoffeeVO> datas=new ArrayList<CoffeeVO>();
		conn=JDBCUtil.connect();
		try {	
			pstmt=conn.prepareStatement(COFFEE_PAGINATION);
			pstmt.setInt(1, vo.getStartList());
			pstmt.setInt(2, vo.getListSize());

			ResultSet rs =pstmt.executeQuery();
			while(rs.next()) {
				CoffeeVO data=new CoffeeVO();
				data.setCid(rs.getInt("CID"));
				data.setCname(rs.getString("CNAME"));
				data.setCcountry(rs.getString("CCOUNTRY"));
				data.setCnum(rs.getInt("CNUM"));
				data.setCprice(rs.getInt("CPRICE"));
				data.setCcontent(rs.getString("CCONTENT"));
				data.setCpic(rs.getString("CPIC"));
				datas.add(data);
				System.out.println(data);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;
	}
	//======================페이징 끝=============================

	public boolean insertCoffee(CoffeeVO vo) {//커피 상품 추가
		conn=JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(COFFEE_INSERT);
			pstmt.setString(1, vo.getCname());
			pstmt.setString(2, vo.getCcountry());
			pstmt.setInt(3, vo.getCnum());
			pstmt.setInt(4, vo.getCprice());
			pstmt.setString(5, vo.getCcontent());
			pstmt.setString(6, vo.getCpic());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(pstmt, conn);
		return true;
	}

	public CoffeeVO getCoffee(CoffeeVO vo) {//커피상세보기
		CoffeeVO data = null;
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(COFFEE_SELECTONE);
			pstmt.setInt(1, vo.getCid());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data = new CoffeeVO();
				data.setCid(rs.getInt("CID"));
				data.setCname(rs.getString("CNAME"));
				data.setCcountry(rs.getString("CCOUNTRY"));
				data.setCnum(rs.getInt("CNUM"));
				data.setCprice(rs.getInt("Cprice"));
				data.setCcontent(rs.getString("CCONTENT"));
				data.setCpic(rs.getString("CPIC"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}		
		return data;
	}

	public List<CoffeeVO> getCoffeeList(CoffeeVO vo) {//커피 검색리스트
		System.out.println("CoffeeDAO:CoffeeDAO-getCoffeList");
		System.out.println("vo: "+vo);
		List<CoffeeVO> datas=new ArrayList<CoffeeVO>();
		conn=JDBCUtil.connect();
		try {
			if(vo.getCcategory()==null){
				System.out.println("COFEEDAO-Coffee_SelectAll");
				pstmt=conn.prepareStatement(COFFEE_SELECTALL);
			}else if(vo.getCcategory().equals("cname")) {//이름검색
				System.out.println("name");
				pstmt=conn.prepareStatement(COFFEE_SELECTALLNAME);
				pstmt.setString(1, vo.getKeyword());
			}else if(vo.getCcategory().equals("ccountry")){
				System.out.println("country");
				pstmt=conn.prepareStatement(COFFEE_SELECTALLCOUNTRY);
				pstmt.setString(1, vo.getKeyword());
			}else if(vo.getCcategory().equals("cprice")) {
				System.out.println("price");

				if(vo.getCprice()>vo.getCprice2()) {

					int a=0;
					a=vo.getCprice2();
					vo.setCprice2(vo.getCprice());
					vo.setCprice(a);
				}

				pstmt=conn.prepareStatement(COFFEE_SELECTALLPRICE);
				pstmt.setInt(1, vo.getCprice());
				pstmt.setInt(2, vo.getCprice2());
			}else if(vo.getCcategory().equals("join")) {
				System.out.println("join");
				System.out.println(vo.getKeyword());
				pstmt=conn.prepareStatement(COFFEE_SELECTJOIN);
				pstmt.setString(1, vo.getKeyword());
			}else {
				System.out.println("else");
			}
			ResultSet rs =pstmt.executeQuery();
			while(rs.next()) {
				CoffeeVO data=new CoffeeVO();
				data.setCid(rs.getInt("CID"));
				data.setCname(rs.getString("CNAME"));
				data.setCcountry(rs.getString("CCOUNTRY"));
				data.setCnum(rs.getInt("CNUM"));
				data.setCprice(rs.getInt("CPRICE"));
				data.setCcontent(rs.getString("CCONTENT"));
				data.setCpic(rs.getString("CPIC"));
				datas.add(data);
				System.out.println(data);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;
	}

}
