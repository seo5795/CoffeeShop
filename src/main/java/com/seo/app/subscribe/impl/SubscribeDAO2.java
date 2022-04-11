package com.seo.app.subscribe.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.seo.app.common.JDBCUtil;
import com.seo.app.subscribe.SubscribeVO;

@Repository("SubscribeDAO2")
public class SubscribeDAO2 {
	
	@Autowired//(타입을 보고 움직임)
	private JdbcTemplate jdbcTemplate;

	//구독 추가
	private final String SUBSCRIBE_INSERT="INSERT INTO SUBSCRIBE (SID,MID,CID)"
			+" VALUES ((SELECT NVL(MAX(SID),1000)+1 from SUBSCRIBE),?,?)";
	//구독취소
	private final String SUBSCRIBE_DELETE="DELETE FROM SUBSCRIBE WHERE MID=? and CID=?";
	//회원 구독리스트 보기
	private final String SUBSCRIBE_SELECTALL="SLECT * FROM SUBSCRIBE WHERE MID = ? ORDER BY SID DESC";
	//회원 구독여부 보기
	private final String SUBSCRIBE_SELECTONE="SELECT * FROM SUBSCRIBE WHERE MID =? AND CID=?";
	
	public boolean insertSubscribe(SubscribeVO vo) {//구독추가
		Object[] args = {vo.getMid(),vo.getCid()};
		if(jdbcTemplate.update(SUBSCRIBE_INSERT,args)>0) {
			return true;
		}
		return false;
	}

	public boolean deleteSubscribe(SubscribeVO vo) {//구독삭제
		Object[] args = {vo.getMid(),vo.getCid()};
		if(jdbcTemplate.update(SUBSCRIBE_DELETE,args)>0) {
			return true;
		}
		return false;
	}

	public List<SubscribeVO> getSubscribeList(SubscribeVO vo) {
		Object[] args= {vo.getMid()};
		return jdbcTemplate.query(SUBSCRIBE_SELECTALL,args, new SubscribeRowMapper());
	}

	public SubscribeVO getSubscribe(SubscribeVO vo) {
		Object[] args= {vo.getMid(), vo.getCid()};
		return jdbcTemplate.queryForObject(SUBSCRIBE_SELECTONE, args,new SubscribeRowMapper());
	}		
}

class SubscribeRowMapper implements RowMapper<SubscribeVO>{

	@Override
	public SubscribeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SubscribeVO data=new SubscribeVO();
		data.setSid(rs.getInt("SID"));
		data.setMid(rs.getString("MID"));
		data.setCid(rs.getInt("CID"));
		return data;
	}


	
}
