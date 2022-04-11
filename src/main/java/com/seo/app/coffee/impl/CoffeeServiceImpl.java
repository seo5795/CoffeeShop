package com.seo.app.coffee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seo.app.coffee.CoffeeService;
import com.seo.app.coffee.CoffeeVO;

@Service("coffeeService")
public class CoffeeServiceImpl implements CoffeeService{
	
	@Autowired
	private CoffeeDAO2 coffeeDAO;
	@Override
	public boolean insertCoffee(CoffeeVO vo) {
		return coffeeDAO.insertCoffee(vo);
	}

	@Override
	public CoffeeVO getCoffee(CoffeeVO vo) {
		return coffeeDAO.getCoffee(vo);
	}

	@Override
	public List<CoffeeVO> getCoffeeList(CoffeeVO vo) {
		return coffeeDAO.getCoffeeList(vo);
	}

}
