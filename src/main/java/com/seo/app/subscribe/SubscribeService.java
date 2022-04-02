package com.seo.app.subscribe;

import java.util.List;

public interface SubscribeService {
	boolean insertSubscribe(SubscribeVO vo);//구독추가
	boolean deleteSubscribe(SubscribeVO vo);//구독취소
	List<SubscribeVO> getSubscribeList(SubscribeVO vo);//구독리스트보기
	
}
