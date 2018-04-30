package com.iw.free.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iw.free.dto.FreeDTO;
import com.webjjang.util.DBUtil;
import com.webjjang.util.PageObject2;

public class FreeDAO {

	// 오라클에 접속할때 필요한 정보들
	// DBUtil에 다 선언함.

	// 글리스트를 가져오는 메서드
	public List<FreeDTO> list(PageObject2 pageObject) {
		System.out.println("FreeDAO.list()");
		List<FreeDTO> list = null;
		// RDBMS에서 데이터를 가져 오는 프로그램 작성

		// 필요한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		ResultSet rs = null; // 결과 객체

		try {
			// 1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			// ** 검색 처리 문구를 만든다. pageObject.searchWord가 있는 경우 검색을 해야한다
			String search = "";
			String searchWord = pageObject.getSearchWord();
			if (searchWord != null && !searchWord.equals("")) {
				search += " where 1=0 ";
				// title -> where 1=0 title like ?
				// title, content -> where 1=0 or title like ? or content like ?
				for (String field : pageObject.getSearchKeys())
					search += " or " + field + " like ? ";
			}
			// 3. sql
			// 1. 원래 데이터를 순서에 맞게 다가져온다.
			String sql = "select no, title, writer, " + " writedate, hit from FREE_BOARD " + search + " order by no desc ";
			// 2. 순서에 맞게 가져온 데이터에 rownum rnum 을 붙인다.
			sql = " select rownum rnum, no, title, writer, " + " writedate, hit from (" + sql + ")";
			sql = "select * from (" + sql + ")" + " where rnum between ? and ? ";
			System.out.println(sql);
			// 4. 처리문 객체
			int idx = 1;
			pstmt = con.prepareStatement(sql);
			System.out.println("pstmt setDate : searchWord:" + searchWord);
			// System.out.println(pageObject.getSearchKeys().length);
			if (searchWord != null && !searchWord.equals("")) {
				for (String field : pageObject.getSearchKeys()) {
					// System.out.println(idx);
					pstmt.setString(idx++, "%" + searchWord + "%");
				}
			}
			// System.out.println(idx);
			pstmt.setInt(idx++, pageObject.getStartRow());
			pstmt.setInt(idx++, pageObject.getEndRow());
			// 5. 실행 -- select ->rs이 나온다.
			rs = pstmt.executeQuery();
			// 6. 표시 --> 데이터 담기
			while (rs.next()) {
				// 데이터가 있는데 list가 null이면 생성한다.
				if (list == null)
					list = new ArrayList<>();
				// 데이터 하나를 담을 수 있는 BoardDTO 객체를 생성한다.
				FreeDTO freeDTO = new FreeDTO();
				// 데이터를 rs에서 꺼내서 boardDTO에 담는다.
				freeDTO.setNo(rs.getInt("no"));
				freeDTO.setTitle(rs.getString("title"));
				freeDTO.setWriter(rs.getString("writer"));
				freeDTO.setWriteDate(rs.getString("writedate"));
				freeDTO.setHit(rs.getInt("hit"));
				// list에 boardDTO를 담는다.
				list.add(freeDTO);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// 7. 객체 닫기
				DBUtil.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return list;
	}

	// 글번호에 맞는 글보기 데이터를 가져오는 메서드
	public FreeDTO view(int no) {
		System.out.println("FreeDAO.view()");
		FreeDTO freeDTO = null;
		// 오라클에서 데이터를 가져와서 채우는 프로그램 작성(생략)
		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		ResultSet rs = null; // 결과 객체
		try {
			// 1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			// 3. sql문 작성
			String sql = "select no, title, content, " + " writer, writedate, hit " + " from FREE_BOARD " + " where no = ? "; // 변하는
																															// 데이터
																															// 대신
																															// ?
																															// 사용
			// 4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no); // 첫번째 ?에 no를 int로 셋팅
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 rs에서 꺼내서 BoardDTO에 담는다.
			if (rs.next()) {
				// 생성자가 만들어져 있어야 한다.
				freeDTO = new FreeDTO(rs.getInt("no"), rs.getString("title"), rs.getString("content"),
						rs.getString("writer"), rs.getString("writeDate"), rs.getInt("hit"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// 7. 닫기
				DBUtil.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return freeDTO;
	}

	// 게시판 글쓰기 처리.
	public void write(FreeDTO freeDTO) {
		System.out.println("FreeDAO.write()");
		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		try {
			//1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			//3. sql문 작성
			String sql = "insert into FREE_BOARD(no,title,"
					+ " content, writer) "
					+ " values(FREE_BOARD_seq.nextval,"
					+ " ?, ?, ?) "; //변하는 데이터 대신 ? 사용
			//4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, freeDTO.getTitle());
			pstmt.setString(2, freeDTO.getContent());
			pstmt.setString(3, freeDTO.getWriter());
			//5. 실행 -> select: executeQuery()
			//  insert, update, delete:executeUpdate()
			pstmt.executeUpdate();
			//6. 표시 -> 오류가 없으면 정상처리
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}  finally {
			try {
				// 7. 닫기
				DBUtil.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	// 조회수를 1증가 시키는 메서드 -> 글번호를 받아서 글번호에 맞는 조회수 증가.
	public void increase(int no) {
		System.out.println("FreeDAO.increase()");
		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		try {
			//1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			//3. sql문 작성
			String sql = "update FREE_BOARD set hit = hit + 1 "
					+ " where no = ? "; //변하는 데이터 대신 ? 사용
			//4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no); // 첫번째 ?에 no를 int로 셋팅
			//5. 실행 -> select: executeQuery()
			//  insert, update, delete:executeUpdate()
			pstmt.executeUpdate();
			//6. 표시 -> 오류가 없으면 정상처리
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}  finally {
			try {
				// 7. 닫기
				DBUtil.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	// 게시판 글수정 처리.
	public void update(FreeDTO freeDTO) {
		System.out.println("FreeDAO.update()");
		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		try {
			//1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			//3. sql문 작성
			String sql = "update FREE_BOARD set "
					+ " title = ?, content = ?, writer = ? "
					+ " where no = ? "; //변하는 데이터 대신 ? 사용
			//4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, freeDTO.getTitle());
			pstmt.setString(2, freeDTO.getContent());
			pstmt.setString(3, freeDTO.getWriter());
			pstmt.setInt(4, freeDTO.getNo());
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
	public void delete(int no) {
		System.out.println("FreeDAO.delete()");
		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		try {
			//1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			//3. sql문 작성
			String sql = "delete from FREE_BOARD"
					+ " where no = ?"; //변하는 데이터 대신 ? 사용
			//4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
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
