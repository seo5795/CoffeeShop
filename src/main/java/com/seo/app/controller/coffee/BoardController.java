package com.seo.app.controller.coffee;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seo.app.coffee.CoffeeVO;
import com.seo.app.coffee.impl.CoffeeDAO;


@Controller // ★x100
// 1. <bean> -> @Controller
// 2. implements Controller 필요없음 -> 제거
// 3. 메서드 강제 사라짐!!!
// 4. 완전한 POJO가 되었다!!!!!
// 		-> req,res,...가 존재하지않음 => 경량의 객체
public class BoardController {
	// 1. Controller 교체
	// 2. 반환타입의 변경 -> ModelAndView(무엇을 보낼지_정보_datas,data,member,... + 어디로 가야하는지_경로)

	/*
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String bid=request.getParameter("bid");
		BoardVO vo=new BoardVO();
		vo.setBid(Integer.parseInt(bid));
		BoardDAO boardDAO=new BoardDAO();
		vo=boardDAO.getBoard(vo);
		ModelAndView mav=new ModelAndView();
		mav.addObject("data", vo);
		mav.setViewName("board");
		return mav;
	}
	 */
	@RequestMapping(value="/main.do")
	public ModelAndView getCoffeeList(CoffeeVO vo,CoffeeDAO cDAO,ModelAndView mav) {
		System.out.println("FC:Controller-main.do");
		List<CoffeeVO> datas=cDAO.getCoffeeList(vo);
		mav.addObject("datas", datas); // Model을 이용하여 전달할 정보를 저장!
		mav.setViewName("main.jsp");
		return mav;
	}

}
