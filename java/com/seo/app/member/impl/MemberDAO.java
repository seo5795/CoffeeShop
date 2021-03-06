package com.seo.app.member.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.seo.app.coffee.CartVO;
import com.seo.app.common.JDBCUtil;
import com.seo.app.member.MemberVO;

//@Repository("MemberDAO1")
public class MemberDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//회원 로그인
	private final String MEMBER_INSERT="INSERT INTO MEMBER (MID,MPW,MNAME)"
			+" VALUES (?,?,?)";
	//회원 가입	
	private final String MEMBER_SELECTONE="SELECT * FROM MEMBER WHERE MID=?";
	
	
	//회원포인트 업데이트
	private final String MEMBER_UPDATE="UPDATE MEMBER SET MPOINT=? WHERE MID=?";
	
	public MemberVO getMember(MemberVO vo) {//로그인
		MemberVO data = null;
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(MEMBER_SELECTONE);
			pstmt.setString(1, vo.getMid());
			rs = pstmt.executeQuery();
			if (rs.next()) {//로그인
				if (rs.getString("MPW").equals(vo.getMpw())) {
					data = new MemberVO();
					data.setMid(rs.getString("MID"));
					data.setMpw(rs.getString("MPW"));
					data.setMname(rs.getString("MNAME"));
					data.setMpoint(rs.getInt("MPOINT"));
					data.setMrank(rs.getInt("MRANK"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return data;
	}

	public boolean insertMember(MemberVO vo) {//회원가입
		conn=JDBCUtil.connect();
		try {
			//INSERT INTO MEMBER (MID,MPW,MNAME,MPOINT,MRANK)
			pstmt = conn.prepareStatement(MEMBER_INSERT);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMpw());
			pstmt.setString(3, vo.getMname());
			
			
			pstmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(pstmt, conn);
		return true;
	}
	
	public boolean pay(MemberVO vo) {//결제
		conn=JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(MEMBER_UPDATE);
			pstmt.setInt(1, vo.getMpoint());
			pstmt.setString(2, vo.getMid());
			pstmt.executeUpdate();	
		} catch (SQLException e) {			
				e.printStackTrace();
				return false;
		}
		JDBCUtil.disconnect(pstmt, conn);
		return true;
	}
	
	

	
}
