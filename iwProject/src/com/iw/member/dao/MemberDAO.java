package com.iw.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.iw.member.dto.MemberDTO;
import com.webjjang.util.DBUtil;

public class MemberDAO
{

	// 오라클에 접속할때 필요한 정보들
	// DBUtil에 다 선언함.

	// 글리스트를 가져오는 메서드
	public List<MemberDTO> list()
	{
		System.out.println("sdsd.list()");
		List<MemberDTO> list = null;
		// RDBMS에서 데이터를 가져 오는 프로그램 작성

		// 필요한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		ResultSet rs = null; // 결과 객체

		try
		{
			// 1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			// 3. sql
			String sql = "select no, id, nickname, grade"
					+" from member_board"
					+" order by no desc";
			// 4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			// 5. 실행 -- select ->rs이 나온다.
			rs = pstmt.executeQuery();
			// 6. 표시 --> 데이터 담기
			while (rs.next())
			{
				// 데이터가 있는데 list가 null이면 생성한다.
				if (list == null)
					list = new ArrayList<>();
				// 데이터 하나를 담을 수 있는 BoardDTO 객체를 생성한다.
				MemberDTO MemberDTO = new MemberDTO();
				// 데이터를 rs에서 꺼내서 boardDTO에 담는다.
				MemberDTO.setNo(rs.getInt("no"));
				MemberDTO.setId(rs.getString("id"));
				MemberDTO.setNickname(rs.getString("nickname"));
				MemberDTO.setGrade(rs.getString("grade"));
				// boardDTO.setWritedate(rs.getString("writedate"));
				// boardDTO.setConnectiondate(rs.getString("connectiondate"));
				// list에 boardDTO를 담는다.
				list.add(MemberDTO);

			}
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		} finally
		{
			try
			{
				// 7. 객체 닫기
				DBUtil.close(con, pstmt, rs);
			} catch (Exception e)
			{
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return list;
	}

	// 글번호에 맞는 글보기 데이터를 가져오는 메서드
	public MemberDTO view(int no)
	{
		System.out.println("BoardDAO.view()");
		MemberDTO boardDTO = null;
		// 오라클에서 데이터를 가져와서 채우는 프로그램 작성(생략)
		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		ResultSet rs = null; // 결과 객체
		
		System.out.println("int= "+no);
		try
		{
			// 1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			// 3. sql문 작성
			String sql = "select no, id, password, nickname, grade, writedate, connectiondate"
					+" from member_board"
					+" where no=?";

			// 4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no); // 첫번째 ?에 no를 int로 셋팅
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 rs에서 꺼내서 BoardDTO에 담는다.
			if (rs.next())
			{
				boardDTO = new MemberDTO(
						rs.getInt("no"),
						rs.getString("id"),
						rs.getString("password"),
						rs.getString("nickname"),
						rs.getString("writedate"),
						rs.getString("connectiondate"),
						rs.getString("grade")
						);

			}

		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		} finally
		{
			try
			{
				// 7. 닫기
				DBUtil.close(con, pstmt, rs);
			} catch (Exception e)
			{
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return boardDTO;
	}

	// 게시판 글쓰기 처리.
	public void write(MemberDTO boardDTO)
	{
		// System.out.println("BoardDAO.write()");
		// // 사용한 객체 선언
		// Connection con = null; // 연결 객체
		// PreparedStatement pstmt = null; // 처리문 객체
		// try
		// {
		// // 1. 드라이버 확인 //2. 연결
		// con = DBUtil.getConnection();
		// // 3. sql문 작성
		// String sql = "insert into board(no,title," + " content, writer) " + "
		// values(board_seq.nextval,"
		// + " ?, ?, ?) "; // 변하는 데이터 대신 ? 사용
		// // 4. 처리문 객체
		// pstmt = con.prepareStatement(sql);
		// pstmt.setString(1, boardDTO.getTitle());
		// pstmt.setString(2, boardDTO.getContent());
		// pstmt.setString(3, boardDTO.getWriter());
		// // 5. 실행 -> select: executeQuery()
		// // insert, update, delete:executeUpdate()
		// pstmt.executeUpdate();
		// // 6. 표시 -> 오류가 없으면 정상처리
		// } catch (Exception e)
		// {
		// // TODO: handle exception
		// e.printStackTrace();
		// } finally
		// {
		// try
		// {
		// // 7. 닫기
		// DBUtil.close(con, pstmt);
		// } catch (Exception e)
		// {
		// // TODO: handle exception
		// e.printStackTrace();
		// }
		// }
	}

	public void update(MemberDTO boardDTO)
	{
		System.out.println("BoardDAO.writes()");
		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		System.out.println(boardDTO);
		
		try
		{
			// 1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			// 3. sql문 작성
			String sql = "update member_board set" + " password=?, nickname=?, grade=?" + " where no=?";
			// 4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getPassword());
			pstmt.setString(2, boardDTO.getNickname());
			pstmt.setString(3, boardDTO.getGrade());
			pstmt.setInt(4, boardDTO.getNo());
			// 5. 실행 -> select: executeQuery()
			// insert, update, delete:executeUpdate()
			pstmt.executeUpdate();
			// 6. 표시 -> 오류가 없으면 정상처리
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		} finally
		{
			try
			{
				// 7. 닫기
				DBUtil.close(con, pstmt);
			} catch (Exception e)
			{
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	// 조회수를 1증가 시키는 메서드 -> 글번호를 받아서 글번호에 맞는 조회수 증가.
	public void increase(int no)
	{
		// System.out.println("BoardDAO.increase()");
		// // 사용한 객체 선언
		// Connection con = null; // 연결 객체
		// PreparedStatement pstmt = null; // 처리문 객체
		// try
		// {
		// // 1. 드라이버 확인 //2. 연결
		// con = DBUtil.getConnection();
		// // 3. sql문 작성
		// String sql = "update board set hit = hit + 1 " + " where no = ? "; // 변하는 데이터
		// 대신 ? 사용
		// // 4. 처리문 객체
		// pstmt = con.prepareStatement(sql);
		// pstmt.setInt(1, no); // 첫번째 ?에 no를 int로 셋팅
		// // 5. 실행 -> select: executeQuery()
		// // insert, update, delete:executeUpdate()
		// pstmt.executeUpdate();
		// // 6. 표시 -> 오류가 없으면 정상처리
		// } catch (Exception e)
		// {
		// // TODO: handle exception
		// e.printStackTrace();
		// } finally
		// {
		// try
		// {
		// // 7. 닫기
		// DBUtil.close(con, pstmt);
		// } catch (Exception e)
		// {
		// // TODO: handle exception
		// e.printStackTrace();
		// }
		// }
	}

	public void delete(int no)
	{
		System.out.println("BoardDAO.increase()");
		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		try
		{
			// 1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			// 3. sql문 작성
			String sql = "delete member_board " + " where no = ? "; // 변하는 데이터 대신 ? 사용
			// 4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no); // 첫번째 ?에 no를 int로 셋팅
			// 5. 실행 -> select: executeQuery()
			// insert, update, delete:executeUpdate()
			pstmt.executeUpdate();
			// 6. 표시 -> 오류가 없으면 정상처리
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		} finally
		{
			try
			{
				// 7. 닫기
				DBUtil.close(con, pstmt);
			} catch (Exception e)
			{
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}


	
	public MemberDTO login(MemberDTO boardDTO) throws SQLException
	{
		System.out.println("MemberDAO.login()");
		// 결과를 저장해서 리턴하는 객체 - Controller에서 생성해서 전달해준다. - return을 하지 않아도 된다.
		// 사용할 객체를 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			// 1. 확인, 2.연결
			con = DBUtil.getConnection();
			// 3. sql 작성
			String sql = "select no,id, nickname, grade " + " from member_board where id = ? and password = ?";
			// 4. 처리객체
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getId());
			pstmt.setString(2, boardDTO.getPassword());

			// 5. 실행 - select : executeQuery() - resultSet이 결과로 나온다.
			// - insert, update, delete : executeUpdate() - int 가 나온다.
			rs = pstmt.executeQuery();

			// 6. 표시 -> jsp : 데이터를 담아서 넘긴다.
			if (rs.next())
			{ // 데이터가 있으면 로그인 처리를 위한 정보를 담는다.
				boardDTO.setNo(Integer.parseInt(rs.getString("no")));
				boardDTO.setNickname(rs.getString("nickname"));
				boardDTO.setGrade(rs.getString("grade"));
				System.out.println(boardDTO);
			} else
			{
				boardDTO = null;
				System.out.println("MemberDAO.login().else.memberDTO:" + boardDTO);
			}
			return boardDTO;
		} catch (Exception e)
		{
			// TODO: handle exception
			throw new SQLException("회원 정보를 불러오는 중 DB 오류");
		} finally
		{
			DBUtil.close(con, pstmt, rs);
		}
	}

	public void join(MemberDTO boardDTO)
	{
		System.out.println("MemberDAO.join()");
		// 사용할 객체를 선언
		// Connection con = null;
		// PreparedStatement pstmt = null;
		// 3. sql 작성
		System.out.println(boardDTO);
		
		String sql = "insert into member_board(no,id, password, nickname, grade, writedate, connectiondate) "
				+ " values(MEMBER_BOARD_SEQ.NEXTVAL,?,?,?,?,sysdate, sysdate)";
		try (Connection con = DBUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql))
		{
			// 4. 처리객체
			pstmt.setString(1, boardDTO.getId());
			pstmt.setString(2, boardDTO.getPassword());
			pstmt.setString(3, boardDTO.getNickname());
			pstmt.setString(4, boardDTO.getGrade());

			// 5. 실행
			pstmt.executeUpdate();

			// 6. 표시 -> jsp : 데이터를 담아서 넘긴다.
		} catch (Exception e)
		{
			// TODO: handle exception
			// e.printStackTrace();
			System.out.println(e);
			//throw new SQLException("회원 가입 처리 중 DB 오류");

		}
	}



	public MemberDTO mypage(String id)
	{
		System.out.println("MemberDAO.login()");
		// 결과를 저장해서 리턴하는 객체 - Controller에서 생성해서 전달해준다. - return을 하지 않아도 된다.
		// 사용할 객체를 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO memberDTO = new MemberDTO();
		try
		{
			// 1. 확인, 2.연결
			con = DBUtil.getConnection();
			// 3. sql 작성
			String sql = "select no, id, password, nickname, grade, writedate, connectiondate"
					+ " from member_board"
					+ " where id=?";
				
			// 4. 처리객체
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			

			// 5. 실행 - select : executeQuery() - resultSet이 결과로 나온다.
			// - insert, update, delete : executeUpdate() - int 가 나온다.
			rs = pstmt.executeQuery();

			// 6. 표시 -> jsp : 데이터를 담아서 넘긴다.
			if (rs.next())
			{ // 데이터가 있으면 로그인 처리를 위한 정보를 담는다.
				memberDTO.setId(rs.getString("id"));
				memberDTO.setNo(rs.getInt("no"));
				memberDTO.setPassword(rs.getString("password"));
				memberDTO.setNickname(rs.getString("nickname"));
				memberDTO.setGrade(rs.getString("grade"));
				memberDTO.setWritedate(rs.getString("writedate"));
				memberDTO.setConnectiondate(rs.getString("connectiondate"));
				System.out.println(memberDTO);
			} else
			{
				memberDTO = null;
				System.out.println("MemberDAO.login().else.memberDTO:" + memberDTO);
			}
			return memberDTO;
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			try
			{
				throw new SQLException("회원 정보를 불러오는 중 DB 오류");
				
			} catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally
		{
			try
			{
				DBUtil.close(con, pstmt, rs);
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return null;
	}

}
