package com.seo.app.subscribe.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.seo.app.common.JDBCUtil;
import com.seo.app.subscribe.SubscribeVO;

@Repository("SubscribeDAO")
public class SubscribeDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	//구독 추가
	private final String SUBSCRIBE_INSERT="INSERT INTO SUBSCRIBE (SID,MID,CID)"
			+" VALUES ((SELECT NVL(MAX(SID),1000)+1 from SUBSCRIBE),?,?)";
	//구독취소
	private final String SUBSCRIBE_DELETE="DELETE FROM SUBSCRIBE WHERE MID=? and CID=?";
	//회원 구독리스트 보기
	private final String SUBSCRIBE_SELECTALL="SLECT * FROM SUBSCRIBE WHERE MID = ? ORDER BY SID DESC";

	public boolean insertSubscribe(SubscribeVO vo) {//구독추가
		conn=JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(SUBSCRIBE_INSERT);
			pstmt.setString(1, vo.getMid());
			pstmt.setInt(2, vo.getCid());
			
			pstmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public boolean deleteSubscribe(SubscribeVO vo) {//구독삭제
		conn=JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(SUBSCRIBE_DELETE);
			pstmt.setString(1, vo.getMid());	
			pstmt.setInt(2, vo.getCid());	
			pstmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public List<SubscribeVO> getSubscribeList(SubscribeVO vo) {
		List<SubscribeVO> datas=new ArrayList<SubscribeVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SUBSCRIBE_SELECTALL);
			pstmt.setInt(1, vo.getSid());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				SubscribeVO data=new SubscribeVO();
				data.setSid(rs.getInt("SID"));
				data.setMid(rs.getString("MID"));
				data.setCid(rs.getInt("CID"));
				
				datas.add(data);
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
