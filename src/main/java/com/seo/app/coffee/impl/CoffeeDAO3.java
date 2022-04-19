package com.seo.app.coffee.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seo.app.coffee.CartVO;
import com.seo.app.coffee.CoffeeVO;
import com.seo.app.coffee.PageVO;


@Repository("CoffeeDAO3")
public class CoffeeDAO3 {
	@Autowired
	private SqlSessionTemplate mybatis;


	public int getCoffeeListCnt(CoffeeVO vo) {//검색 데이터 수
		System.out.println("getCoffeeList() 호출됨");
		vo = mybatis.selectOne("CoffeeDAO.getCoffeeListCnt", vo);
		int result=vo.getCnum();
		return result;
	}


	public boolean insertCoffee(CoffeeVO vo) {//커피 상품 추가
		System.out.println("insertCoffee()호출됨");
		if(mybatis.insert("CoffeeDAO.insertCoffee", vo)>0) {
			return true;
		}else{
			return false;
		}
	}

	public CoffeeVO getCoffee(CoffeeVO vo) {//커피상세보기
		System.out.println("getCoffee()호출됨");
		return (CoffeeVO)mybatis.selectOne("CoffeeDAO.getCoffee", vo);
	}
	
	public List<CoffeeVO> getCoffeeList(CoffeeVO vo,PageVO pvo) {//커피 검색리스트
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("vo", vo);
		int amount = pvo.getAmount();
		int pageNum = pvo.getPageNum();
		map.put("first", amount * pageNum);//이하
		map.put("second", (pageNum-1)*amount);//이상
		

		System.out.println("dao3-getCoffeeList: "+vo);
		System.out.println("dao3-getCoffeeList: "+pvo);
		//System.out.println("CoffeeDAO-getCoffeList()호출됨");
		return mybatis.selectList("CoffeeDAO.getCoffeeList", map);
		
		//mybatis의 메서드에 인자를 두개보내는 방법?
		//1.pageVO에 coffeeVO의 keyword를 추가한다 -> 뭔가 난잡.....응집도 down....
		//-> 페이징을 하는 페이지 마다 VO생성??...똑같은데....
		//2.resultType을 통해 vo를 받고, parameterType="Map"을 통해 인자를 2개 받는 방법
		//https://heewon26.tistory.com/26
		//질문사항: 인자를 2개사용해도 coffee-mapping.xml에서 #{searchKeyword}등의 값이 mapping될까?
	}

	public boolean checkCnum(CartVO vo) {
		//커피 수량체크
		System.out.println("coffeeDAO3-checkCnum");
		CoffeeVO cvo= new CoffeeVO();
		
		cvo= (CoffeeVO)mybatis.selectOne("CoffeeDAO.checkCnum", vo);
		if( cvo.getCnum() - vo.getNumber()<0) {
			System.out.println("로그: DAO: 수량 부족");
			return false;
		}
		return true;	
	}
	
	public boolean updateCoffee(CartVO vo) {
		//커피수량 업데이트
		System.out.println("coffeeDAO3-updateCoffee");
		if(mybatis.update("CoffeeDAO.updateBoard", vo)>0) {
			return true;
		}
		return false;
	}

			

}
