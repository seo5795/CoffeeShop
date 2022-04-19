package com.seo.app.coffee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seo.app.coffee.CartVO;
import com.seo.app.coffee.CoffeeService;
import com.seo.app.coffee.CoffeeVO;
import com.seo.app.coffee.PageVO;

@Service("coffeeService")
public class CoffeeServiceImpl implements CoffeeService{
	
	@Autowired
	private CoffeeDAO3 coffeeDAO;
	@Override
	public boolean insertCoffee(CoffeeVO vo) {
		return coffeeDAO.insertCoffee(vo);
	}



	@Override
	public List<CoffeeVO> getCoffeeList(CoffeeVO vo,PageVO pvo) {
		return coffeeDAO.getCoffeeList(vo,pvo);
	}

	@Override
	public boolean updateCoffeeList(List<CartVO> cartlist) {
//		for (int i = 0; i < cartlist.size(); i++) {
//		if(coffeeDAO.getCart(cartlist.get(i)));
//		}
		return false;
	}//결제시 수량 체크-> 오류 발생을 어떻게 시켜야하는지에 대한 고민

	@Override
	public int getBoardListCnt(CoffeeVO vo) {
		// TODO Auto-generated method stub
		return coffeeDAO.getCoffeeListCnt(vo);
	}



	@Override
	public CoffeeVO getCoffee(CoffeeVO vo) {
		// TODO Auto-generated method stub
		return coffeeDAO.getCoffee(vo);
	}



	@Override
	public boolean insertBoard(CoffeeVO cvo) {
		// TODO Auto-generated method stub
		return coffeeDAO.insertCoffee(cvo);
	}



	@Override
	public boolean checkCnum(CartVO vo) {
		// TODO Auto-generated method stub
		return coffeeDAO.checkCnum(vo);
	}



	@Override
	public boolean updateCoffee(CartVO vo) {
		// TODO Auto-generated method stub
		return coffeeDAO.updateCoffee(vo);
	}



}
