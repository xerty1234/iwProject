package com.webjjang.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.dto.ReplyDTO;
import com.webjjang.util.DBUtil;
import com.webjjang.util.PageObject2;

public class ReplyDAO {

	// 오라클에 접속할때 필요한 정보들
	// DBUtil에 다 선언함.

	// 댓글 리스트를 가져오는 메서드 - 게시판 글보기 화면에서 같이 가져간다.
	public List<ReplyDTO> list(Integer no){
		System.out.println(getClass().getName()+".list()");
		List<ReplyDTO> list = null;
		// RDBMS에서 데이터를 가져 오는 프로그램 작성
		
		// 필요한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		ResultSet rs = null;  // 결과 객체
		
		try {
			//1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			//3. sql
			//   1. 원래 데이터를 순서에 맞게 다가져온다.
			String sql = "select rno, no, content, writer, "
					+ " writedate from board_reply "
					+ " where no = ? "
					+ " order by rno desc ";
			System.out.println(sql);
			//   2. 순서에 맞게 가져온 데이터에 rownum rnum 을 붙인다.
//			sql = " select rownum rnum, no, title, writer, "
//					+" writedate, hit from ("+sql+")";
//			sql = "select * from (" + sql+ ")"
//					+ " where rnum between ? and ? ";
			//4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
//			pstmt.setInt(1, pageObject.getStartRow());
//			pstmt.setInt(2, pageObject.getEndRow());
			//5. 실행 -- select ->rs이 나온다.
			rs = pstmt.executeQuery();
			//6. 표시 --> 데이터 담기
			while(rs.next()) {
				// 데이터가 있는데 list가 null이면 생성한다.
				if(list == null) list = new ArrayList<>();
				// 데이터 하나를 담을 수 있는 ReplyDTO 객체를 생성한다.
				ReplyDTO replyDTO = new ReplyDTO(rs.getInt("rno"),
						rs.getInt("no"),rs.getString("content"),
						rs.getString("writer"), rs.getDate("writedate")
						);
				// list에 replyDTO를 담는다.
				list.add(replyDTO);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				//7. 객체 닫기
				DBUtil.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
				
		return list;
	}
	
	// 댓글 쓰기 처리.
	public void write(ReplyDTO replyDTO) {
		System.out.println(getClass().getName()+".write()");
		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		try {
			//1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			//3. sql문 작성
			String sql = "insert into board_reply(rno, "
					+ " no, content, writer) "
					+ " values(board_reply_seq.nextval, "
					+ " ?, ?, ?) "; //변하는 데이터 대신 ? 사용
			//4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, replyDTO.getNo());
			pstmt.setString(2, replyDTO.getContent());
			pstmt.setString(3, replyDTO.getWriter());
			//5. 실행 -> select: executeQuery()
			//  insert, update, delete:executeUpdate()
			pstmt.executeUpdate();
			//6. 표시 -> 오류가 없으면 정상처리
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// 7. 닫기
				DBUtil.close(con, pstmt);	
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	// 댓글 수정 처리.
	public void update(ReplyDTO replyDTO) {
		System.out.println(getClass().getName()+".update()");
		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		try {
			//1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			//3. sql문 작성
			String sql = "update board_reply set "
					+ " content = ?, writer = ? "
					+ " where rno = ? "; //변하는 데이터 대신 ? 사용
			//4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, replyDTO.getContent());
			pstmt.setString(2, replyDTO.getWriter());
			pstmt.setInt(3, replyDTO.getRno());
			//5. 실행 -> select: executeQuery()
			//  insert, update, delete:executeUpdate()
			pstmt.executeUpdate();
			//6. 표시 -> 오류가 없으면 정상처리
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// 7. 닫기
				DBUtil.close(con, pstmt);	
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	// 게시판 글삭제 처리
	public void delete(int rno) {
		System.out.println(getClass().getName()+".delete()");
		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		try {
			//1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			//3. sql문 작성
			String sql = "delete from board_reply "
					+ " where rno = ?"; //변하는 데이터 대신 ? 사용
			//4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rno);
			//5. 실행 -> select: executeQuery()
			//  insert, update, delete:executeUpdate()
			pstmt.executeUpdate();
			//6. 표시 -> 오류가 없으면 정상처리
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// 7. 닫기
				DBUtil.close(con, pstmt);	
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

}
