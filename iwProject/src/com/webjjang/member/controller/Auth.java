package com.webjjang.member.controller;

import java.util.HashMap;
import java.util.Map;

public class Auth {

	
	// uri와 허용 권한을 넣어 두는 map 객체
	private static Map<String, Integer> authMap = new HashMap<>();
	// uri에 대해 권한을 셋팅하는 메서드
	public static void addAuth(String uri, Integer grade) {authMap.put(uri, grade);}
	// uri의 등급과 사용자 등급을 비교헤서 사용자 등급이 허용되는 등급이면 true를 리턴한다.
	public static boolean isAuth(String uri, Integer grade) {
		Integer pageGrade = authMap.get(uri);
		if (pageGrade == null || pageGrade == 0) return true; // page 등급이 없다. - 권한이 필요 없다.
		else { // pageGrade 가 존재하는데 1이상이면 계산해서 리턴한다.
			if(grade == null || grade == 0) return false; // 사용자가 로그인을 하지 않았다.
			return authMap.get(uri) <= grade;}
		}
	public static boolean isGoLogin(String uri, Integer grade) {
		Integer pageGrade = authMap.get(uri);
		if(pageGrade == null || pageGrade == 0) return false; // 로그인이 필요하지 않는 경우
		return pageGrade > 0 && (grade == null || grade == 0);
	}
}
