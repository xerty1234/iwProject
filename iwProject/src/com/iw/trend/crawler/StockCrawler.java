package com.iw.trend.crawler;

import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class StockCrawler {
	// 주식 시가총액 게시판의 1~50페이지의 모든 주식을 가져온다. 총 1442개 항목

	public ArrayList<String> crawler() throws Exception {

		ArrayList<String> ar = new ArrayList<>();
		for (int i = 1; i <= 50; i++) {

			final String url = "http://finance.daum.net/quote/marketvalue.daum?stype=P&page=" + i
					+ "&col=listprice&order=desc";
			Document doc = Jsoup.connect(url).get();
			Elements els = doc.select("#tabSBody1 td");

			for (Element es : els) {
				if (es.text().equals("")) {
					continue;
				}
				ar.add(es.text());
			}
		}
		return ar;
	}
}