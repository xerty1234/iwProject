package com.iw.trend.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.iw.trendboard.dto.TrendBoardDTO;

public class StockCrawler
{
	// 주식 시가총액 게시판의 1~50페이지의 모든 주식을 가져온다. 총 1442개 항목

	public static List<TrendBoardDTO> crawler() 
	{

		ArrayList<String> temp = new ArrayList<>();
		ArrayList <TrendBoardDTO> list = new ArrayList<TrendBoardDTO>(); 
		for (int i = 1; i <= 50; i++)
		{

			final String url = "http://finance.daum.net/quote/marketvalue.daum?stype=P&page=" + i
					+ "&col=listprice&order=desc";
			Document doc;
			try
			{
				doc = Jsoup.connect(url).get();
				Elements els = doc.select("#tabSBody1 td");

				for (Element es : els)
				{
					if (es.text().equals(""))
					{
						continue;
					}
					temp.add(es.text());
				}
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		for (int i = 0; i < temp.size(); i += 8)
		{
			TrendBoardDTO tempDTO = new TrendBoardDTO();
			tempDTO.setNo(Integer.parseInt(temp.get(i)));
			tempDTO.setTitle(temp.get(i + 1));
			tempDTO.setPresent(temp.get(i + 2));
			tempDTO.setContent(temp.get(i + 3));
			tempDTO.setContent2(temp.get(i + 4));
			tempDTO.setContent3(temp.get(i + 5));
			tempDTO.setContent4(temp.get(i + 6));
			tempDTO.setContent5(temp.get(i + 7));

			//System.out.println(tempDTO);
			list.add(tempDTO);
		}
		
		return list;
		
	}



}