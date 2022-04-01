package com.seo.app.subscribe.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seo.app.subscribe.SubscribeService;
import com.seo.app.subscribe.SubscribeVO;

@Service("subscribeService")
public class SubscribeServiceImpl implements SubscribeService{
	@Autowired
	private SubscribeDAO subscribeDAO;
	
	@Override
	public boolean insertSubscribe(SubscribeVO vo) {//구독추가
		return subscribeDAO.insertSubscribe(vo);
	}


	@Override
	public boolean deleteSubscribe(SubscribeVO vo) {//구독해제
		return subscribeDAO.deleteSubscribe(vo);
	}


	@Override
	public List<SubscribeVO> getSubscribeList(SubscribeVO vo) {//구독리스트보기
		return subscribeDAO.getSubscribeList(vo);
	}

}
