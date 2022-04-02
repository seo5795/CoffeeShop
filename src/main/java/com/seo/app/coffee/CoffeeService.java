package com.seo.app.coffee;

import java.util.List;

public interface CoffeeService {
	boolean insertCoffee(CoffeeVO vo);
	CoffeeVO getCoffee(CoffeeVO vo);
	List<CoffeeVO> getCoffeeList(CoffeeVO vo);
}
