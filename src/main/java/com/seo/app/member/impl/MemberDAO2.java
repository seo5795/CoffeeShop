package com.seo.app.member.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.seo.app.coffee.CartVO;
import com.seo.app.coffee.CoffeeVO;
import com.seo.app.common.JDBCUtil;
import com.seo.app.member.MemberVO;

@Repository("MemberDAO2")
public class MemberDAO2 {
	
	@Autowired//(타입을 보고 움직임)
	private JdbcTemplate jdbcTemplate;

	//회원 로그인
	private final String MEMBER_INSERT="INSERT INTO MEMBER (MID,MPW,MNAME)"
			+" VALUES (?,?,?)";
	//회원 가입	
	private final String MEMBER_SELECTONE="SELECT * FROM MEMBER WHERE MID=? AND MPW=?";

	public MemberVO getMember(MemberVO vo) {//로그인
		Object[] args= {vo.getMid(), vo.getMpw()};
		return jdbcTemplate.queryForObject(MEMBER_SELECTONE, args,new MemberRowMapper());
	}

	public boolean insertMember(MemberVO vo) {//회원가입
		Object[] args = {vo.getMid(),vo.getMpw(),vo.getMname()};
		if(jdbcTemplate.update(MEMBER_INSERT,args)>0) {
			return true;
		}
		return false;
	}

	public boolean pay(MemberVO vo, List<CartVO> cartlist) {
		// TODO Auto-generated method stub
		return false;
	}

}

class MemberRowMapper implements RowMapper<MemberVO>{

	@Override
	public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberVO data = new MemberVO();
		data.setMid(rs.getString("MID"));
		data.setMpw(rs.getString("MPW"));
		data.setMname(rs.getString("MNAME"));
		data.setMpoint(rs.getInt("MPOINT"));
		data.setMrank(rs.getInt("MRANK"));
		return data;
	}



}
