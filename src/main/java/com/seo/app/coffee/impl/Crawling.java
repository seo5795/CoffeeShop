package com.seo.app.coffee.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.seo.app.coffee.CoffeeVO;
import com.seo.app.common.JDBCUtil;

public class Crawling {



	static Connection conn;
	static PreparedStatement pstmt;

	// 초기 데이터 유무 체크
	static final String DATACHECK = "SELECT CID FROM COFFEE";
	//
	// 식당 커피
	static final String COFFEE_INSERT = "INSERT INTO COFFEE (CID,CNAME,CCOUNTRY,CNUM,CPRICE,CCONTENT,CPIC)"
			+" VALUES ((SELECT NVL(MAX(CID),1000)+1 FROM COFFEE),?,?,?,?,?,?)";


	public static void start() throws IOException {

		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(DATACHECK);
			ResultSet rs=pstmt.executeQuery(DATACHECK);
			if(rs.next()!=false) {
				System.out.println("초기 데이터 존재");
				System.out.println("데이터 크롤링 미실행");
			}
			else {
				System.out.println("초기 데이터 미존재");



				String URL = "https://coffeecg.com/product/list.html?cate_no=88";
				Document doc = Jsoup.connect(URL).get();
				//커피이름
				Iterator<Element> name = doc.select("div > div.description > strong > a > span:nth-child(2)").iterator();
				//커피 가격
				Iterator<Element> price =  doc.select("div > div.description > ul > li > span:nth-child(2)").iterator();
				//커피 설명
				Iterator<Element> content =  doc.select("div > div.description > ul > li:nth-child(2) > span").iterator();
				//커피 이미지
				Iterator<Element> pic =  doc.select("div > div.thumbnail > a > img").iterator();

				for(int i=0;i<20;i++) {
					//커피이름
					String cname=name.next().text();
					//커피가격
					String priceall=price.next().text();
					String cprice_1=priceall.substring(0,priceall.length()-1);	
					cprice_1 = cprice_1.replace(",", ""); 
					int cprice=Integer.parseInt(cprice_1);
					//커피 설명
					String ccontent = content.next().text();
					//커피사진
					String cpic = pic.next().attr("src");

					System.out.println(cname);
					System.out.println("가격 : "+cprice);
					System.out.println("설명: "+ccontent);
					System.out.println("사진 경로: "+cpic);
					System.out.println("======================");


					// 커피 DB에 삽입
					conn=JDBCUtil.connect();
					CoffeeVO cvo=new CoffeeVO();

					cvo.setCname(cname);//이름
					cvo.setCnum(10);//재고 기본 10개
					cvo.setCprice(cprice);
					cvo.setCcontent(ccontent);
					cvo.setCpic(cpic);
					ArrayList<String> al = new ArrayList<String>();
					al.add("콜롬비아");
					al.add("에티오피아");
					al.add("브라질");
					al.add("과테말라");
					al.add("코스타리카");
					al.add("인도네시아");
					al.add("파나마");
					al.add("인도");
					al.add("케냐");

					int flag=0;
					for (int j = 0; j < al.size(); j++) {
						if(cname.contains(al.get(j))) {
							cvo.setCcountry(al.get(j));
							flag=1;
						}
					}
					if(flag==0) {
						cvo.setCcountry("제주도");
					}

					try {
						//(CID,CNAME,CCOUNTRY,CNUM,CPRICE,CCONTENT,CPIC)
						pstmt=conn.prepareStatement(COFFEE_INSERT);
						pstmt.setString(1, cvo.getCname());
						pstmt.setString(2, cvo.getCcountry());
						pstmt.setInt(3, cvo.getCnum());
						pstmt.setInt(4, cvo.getCprice());
						pstmt.setString(5, cvo.getCcontent());
						pstmt.setString(6, cvo.getCpic());
						pstmt.executeUpdate();

						System.out.println("Log: 식당 데이터를 추가하였습니다.");
						price.next();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							pstmt.close();
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}



					System.out.println("===========================================");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

