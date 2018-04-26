package com.webjjang.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.webjjang.member.dto.MemberDTO;
import com.webjjang.util.DBUtil;

/*
 * 회원관리 DB 처리
 */
public class MemberDAO {

	// id를 가지고 회원 정보를 불러오는 메서드
	public MemberDTO selectById(String id) throws SQLException{
		System.out.println("MemberDAO.selectById()");
		// 결과를 저장해서 리턴하는 객체
		MemberDTO memberDTO = null;
		// 사용할 객체를 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//1. 확인, 2.연결
			con = DBUtil.getConnection();
			//3. sql 작성
			String sql = "select memberid, name, password, regdate, grade "
					+ " from member where memberid = ?";
			//4. 처리객체
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			//5. 실행
			rs = pstmt.executeQuery();
			
			//6. 표시 -> jsp : 데이터를 담아서 넘긴다.
			if(rs.next()) { // 데이터가 있으면
				memberDTO = new MemberDTO(rs.getString("memberid"),
						rs.getString("name"),
						rs.getString("password"),
						rs.getDate("regdate"),
						rs.getInt("grade")
						);
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new SQLException("회원 정보를 불러오는 중 DB 오류");
		} 
		finally {
			DBUtil.close(con, pstmt, rs);
		}
		// 처리된 결과를 리턴한다.
		return memberDTO;
	} // end of selectById()
	
	// ========== 로그인 처리 =========================================/
	// 참조형 변수를 전달 받으면 밖에 있는 객체와 안에 있는 객체가 같은 주소를 가지게 된다. call by reference-그래서 return이 필요 없다.
	public MemberDTO login(MemberDTO memberDTO) throws SQLException{
		System.out.println("MemberDAO.login()");
		// 결과를 저장해서 리턴하는 객체 - Controller에서 생성해서 전달해준다. - return을 하지 않아도 된다.
		// 사용할 객체를 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//1. 확인, 2.연결
			con = DBUtil.getConnection();
			//3. sql 작성
			String sql = "select memberid, name, grade "
					+ " from member where memberid = ? and password = ?";
			//4. 처리객체
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPassword());
			
			//5. 실행  - select : executeQuery() - resultSet이 결과로 나온다.
			//      - insert, update, delete : executeUpdate() - int 가 나온다.
			rs = pstmt.executeQuery();
			
			//6. 표시 -> jsp : 데이터를 담아서 넘긴다.
			if(rs.next()) { // 데이터가 있으면 로그인 처리를 위한 정보를 담는다.
				memberDTO.setName(rs.getString("name"));
				memberDTO.setGrade(rs.getInt("grade"));
				System.out.println(memberDTO);
			}else { memberDTO = null; System.out.println("MemberDAO.login().else.memberDTO:"+memberDTO);}
			return memberDTO;
		}catch (Exception e) {
			// TODO: handle exception
			throw new SQLException("회원 정보를 불러오는 중 DB 오류");
		} 
		finally {
			DBUtil.close(con, pstmt, rs);
		}
		// 처리된 결과를 리턴한다. -> 참조형 변수를 받았으므로 리턴하지 않아도 되게 작성
//		return memberDTO;
	} // end of selectById()
	
	// ========== 아이디 중복 체크 처리 =========================================/
	// 참조형 변수를 전달 받으면 밖에 있는 객체와 안에 있는 객체가 같은 주소를 가지게 된다. call by reference-그래서 return이 필요 없다.
	public Boolean checkId(String id) throws SQLException{
		System.out.println("MemberDAO.checkId()");
		// 사용할 객체를 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//1. 확인, 2.연결
			con = DBUtil.getConnection();
			//3. sql 작성
			String sql = "select memberid "
					+ " from member where memberid = ?";
			//4. 처리객체
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			//5. 실행  - select : executeQuery() - resultSet이 결과로 나온다.
			//      - insert, update, delete : executeUpdate() - int 가 나온다.
			rs = pstmt.executeQuery();
			
			//6. 표시 -> 데이터를 넘긴다.
			if(rs.next()) { // 아이디가 존재하므로 리턴 false
				return false;
			}else { return true;} // 아이디가 존재하지 않으면 리턴 true
		}catch (Exception e) {
			// TODO: handle exception
			throw new SQLException("회원 정보를 불러오는 중 DB 오류");
		} 
		finally {
			DBUtil.close(con, pstmt, rs);
		}
	} // end of checkId()
	
	
	
	// 회원가입 메서드
	public void insert(MemberDTO memberDTO) throws SQLException{
		System.out.println("MemberDAO.insert()");
		// 사용할 객체를 선언
//		Connection con = null;
//		PreparedStatement pstmt = null;
		//3. sql 작성
		String sql = "insert into member(memberid, name, password) "
				+ " values(?,?,?)";
		try(Connection con = DBUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			//4. 처리객체
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getName());
			pstmt.setString(3, memberDTO.getPassword());
			
			//5. 실행
			pstmt.executeUpdate();
			
			//6. 표시 -> jsp : 데이터를 담아서 넘긴다.
		}catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			throw new SQLException("회원 가입 처리 중 DB 오류");
		} 
	} // end of selectById()
	
	
	
}
