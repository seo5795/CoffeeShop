package com.seo.app.coffee.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.seo.app.coffee.CoffeeVO;
import com.seo.app.common.JDBCUtil;


@Repository("CoffeeDAO2")
public class CoffeeDAO2 {

	@Autowired//(타입을 보고 움직임)
	private JdbcTemplate jdbcTemplate;


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
			+" WHERE CPRICE>=? AND CPRICE<=? ORDER BY CID DESC";

	public boolean insertCoffee(CoffeeVO vo) {//커피 상품 추가
		System.out.println("CoffeeDAO2: insertCoffee() 호출됨");
		Object[] args= {vo.getCname(),vo.getCcountry(),vo.getCnum(),vo.getCprice(),vo.getCcontent(),vo.getCpic()};
		if(jdbcTemplate.update(COFFEE_INSERT, args)>0) {
			return true;
		}else{
			return false;
		}
		
	}

	public CoffeeVO getCoffee(CoffeeVO vo) {//커피상세보기
		System.out.println("CoffeeDAO2: getCoffee() 호출됨");
		Object[] args= {vo.getCid()};
		return jdbcTemplate.queryForObject(COFFEE_SELECTONE, args,new CoffeeRowMapper());
	}

	public List<CoffeeVO> getCoffeeList(CoffeeVO vo) {//커피 검색리스트
		System.out.println("CoffeeDAO2: getCoffeeList() 호출됨");

		if(vo.getCcategory()==null){//전체 검색
			System.out.println("COFEEDAO2-Coffee_SelectAll");
			return jdbcTemplate.query(COFFEE_SELECTALL, new CoffeeRowMapper());
		}
		else if(vo.getCcategory().equals("cname")) {//이름검색				
			System.out.println("COFEEDAO2-Coffee_SelectAllName");
			Object[] args= {vo.getKeyword()};
			return jdbcTemplate.query(COFFEE_SELECTALLNAME,args, new CoffeeRowMapper());
		}
		else if(vo.getCcategory().equals("ccountry")){//나라검색
			System.out.println("COFEEDAO2-Coffee_SelectAllCountry");
			Object[] args= {vo.getKeyword()};
			return jdbcTemplate.query(COFFEE_SELECTALLCOUNTRY,args, new CoffeeRowMapper());
		}
		else if(vo.getCcategory().equals("cprice")) {//가격설정
			System.out.println("COFEEDAO2-Coffee_SelectAllPrice");
			Object[] args= {vo.getKeyword()};
			return jdbcTemplate.query(COFFEE_SELECTALLPRICE,args, new CoffeeRowMapper());
		}
		else {
			System.out.println("else");
			return null;
		}
	}
}

class CoffeeRowMapper implements RowMapper<CoffeeVO>{

	@Override
	public CoffeeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CoffeeVO data = new CoffeeVO();
		data.setCid(rs.getInt("CID"));
		data.setCname(rs.getString("CNAME"));
		data.setCcountry(rs.getString("CCOUNTRY"));
		data.setCnum(rs.getInt("CNUM"));
		data.setCprice(rs.getInt("CPRICE"));
		data.setCcontent(rs.getString("CCONTENT"));
		data.setCpic(rs.getString("CPIC"));
		return data;
	}

}

