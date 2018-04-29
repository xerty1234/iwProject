package com.iw.news.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iw.news.board.dto.BoardDTO;
import com.webjjang.util.DBUtil;
import com.webjjang.util.PageObject2;

public class NewsBoardDAO {

	// 오라클에 접속할때 필요한 정보들
	// DBUtil에 다 선언함.
	boolean bCount = false;

	// 글리스트를 가져오는 메서드
	public List<BoardDTO> list(PageObject2 pageObject) {
		System.out.println("BoardDAO.list()");
		List<BoardDTO> list = null;
		// RDBMS에서 데이터를 가져 오는 프로그램 작성

		// 필요한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		ResultSet rs = null; // 결과 객체

		ArrayList<BoardDTO> boardAr = new ArrayList<>();
		NewsCrawlerDAO crawler = new NewsCrawlerDAO();

		if (bCount == false) {
			try {
				boardAr = crawler.excute();

				con = DBUtil.getConnection();

				for (int i = 0; i < boardAr.size(); i++) {
					String sql = "insert into news(no, title, article, offerer, writedate, image_link) "
							+ " values(news_seq.nextval, ?, ?, ?, ?, ?) ";

					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, boardAr.get(i).getTitle());
					pstmt.setString(2, boardAr.get(i).getArticle());
					pstmt.setString(3, boardAr.get(i).getOfferer());
					pstmt.setString(4, boardAr.get(i).getWriteDate());
					pstmt.setString(5, boardAr.get(i).getImageLink());
					

					pstmt.executeUpdate();
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//
		bCount = true;
		try {
			// 1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			// ** 검색 처리 문구를 만든다. pageObject.searchWord가 있는 경우 검색을 해야한다.
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
			String sql = "select no, title, offerer, " + " writedate, hit from news " + search + " order by no desc ";
			// 2. 순서에 맞게 가져온 데이터에 rownum rnum 을 붙인다.
			sql = " select rownum rnum, no, title, offerer, " + " writedate, hit from (" + sql + ")";
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
				BoardDTO boardDTO = new BoardDTO();
				// 데이터를 rs에서 꺼내서 boardDTO에 담는다.
				boardDTO.setNo(rs.getInt("no"));
				boardDTO.setTitle(rs.getString("title"));
				boardDTO.setOfferer(rs.getString("offerer"));
				boardDTO.setWriteDate(rs.getString("writedate"));
				boardDTO.setHit(rs.getInt("hit"));
				// list에 boardDTO를 담는다.
				list.add(boardDTO);
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
	public BoardDTO view(int no) {
		System.out.println("BoardDAO.view()");
		BoardDTO boardDTO = null;
		// 오라클에서 데이터를 가져와서 채우는 프로그램 작성(생략)
		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		ResultSet rs = null; // 결과 객체
		try {
			// 1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			// 3. sql문 작성
			String sql = "select no, title, article, offerer, writedate, image_link, hit " + " from news " + " where no = ? "; // 변하는  데이터  대신? 사용
			// 4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no); // 첫번째 ?에 no를 int로 셋팅
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 rs에서 꺼내서 BoardDTO에 담는다.
			if (rs.next()) {
				// 생성자가 만들어져 있어야 한다.
				boardDTO = new BoardDTO(rs.getInt("no"), rs.getString("title"), rs.getString("article"),
						rs.getString("offerer"), rs.getString("writedate"), rs.getString("image_link"),
						rs.getInt("hit"));
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
		return boardDTO;
	}

	// 게시판 글쓰기 처리.
	public void write(BoardDTO boardDTO) {
		System.out.println("BoardDAO.write()");
		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		try {
			// 1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			// 3. sql문 작성
			String sql = "insert into board(no,title," + " article, offerer) " + " values(board_seq.nextval,"
					+ " ?, ?, ?) "; // 변하는 데이터 대신 ? 사용
			// 4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getTitle());
			pstmt.setString(2, boardDTO.getArticle());
			pstmt.setString(3, boardDTO.getOfferer());
			// 5. 실행 -> select: executeQuery()
			// insert, update, delete:executeUpdate()
			pstmt.executeUpdate();
			// 6. 표시 -> 오류가 없으면 정상처리
		} catch (Exception e) {
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

	// 조회수를 1증가 시키는 메서드 -> 글번호를 받아서 글번호에 맞는 조회수 증가.
	public void increase(int no) {
		System.out.println("BoardDAO.increase()");
		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		try {
			// 1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			// 3. sql문 작성
			String sql = "update news set hit = hit + 1 " + " where no = ? "; // 변하는 데이터 대신 ? 사용
			// 4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no); // 첫번째 ?에 no를 int로 셋팅
			// 5. 실행 -> select: executeQuery()
			// insert, update, delete:executeUpdate()
			pstmt.executeUpdate();
			// 6. 표시 -> 오류가 없으면 정상처리
		} catch (Exception e) {
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

	// 게시판 글수정 처리.
	public void update(BoardDTO boardDTO) {
		System.out.println("BoardDAO.update()");
		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		try {
			// 1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			// 3. sql문 작성
			String sql = "update news set " + " title = ?, content = ?, offerer = ? " + " where no = ? "; // 변하는 데이터 대신
																											// ? 사용
			// 4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getTitle());
			pstmt.setString(2, boardDTO.getArticle());
			pstmt.setString(3, boardDTO.getOfferer());
			pstmt.setInt(4, boardDTO.getNo());
			// 5. 실행 -> select: executeQuery()
			// insert, update, delete:executeUpdate()
			pstmt.executeUpdate();
			// 6. 표시 -> 오류가 없으면 정상처리
		} catch (Exception e) {
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
		System.out.println("BoardDAO.delete()");
		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		try {
			// 1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			// 3. sql문 작성
			String sql = "delete from news " + " where no = ?"; // 변하는 데이터 대신 ? 사용
			// 4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			// 5. 실행 -> select: executeQuery()
			// insert, update, delete:executeUpdate()
			pstmt.executeUpdate();
			// 6. 표시 -> 오류가 없으면 정상처리
		} catch (Exception e) {
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
