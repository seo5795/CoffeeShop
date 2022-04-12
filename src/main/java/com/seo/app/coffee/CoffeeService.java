package com.seo.app.coffee;

import java.util.List;

public interface CoffeeService {
	boolean insertCoffee(CoffeeVO vo);
	CoffeeVO getCoffee(CoffeeVO vo);
	boolean updateCoffeeList(List<CartVO> cartlist);
	int getBoardListCnt(CoffeeVO vo);
	//List<CoffeeVO> getCoffeePage(int pageNum, int amount);
	List<CoffeeVO> getCoffeeList(CoffeeVO cvo, PageVO pvo);
	boolean insertBoard(CoffeeVO cvo);
}
