package persistence.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import persistence.dao.BoardDAO;
import persistence.dao.impl.JDBCUtil;
import service.dto.BoardDTO;
import service.dto.ClubDTO;

import java.sql.Connection;

public class BoardDAOImpl implements BoardDAO {
private JDBCUtil jdbcUtil = null;
static Connection conn = null;

	public BoardDAOImpl() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	/**
	 * Board 테이블에 새로운 행 생성 (PK 값은 Sequence를 이용하여 자동 생성)
	 */
	public BoardDTO create(BoardDTO board) {
		
		String sql = "INSERT INTO Board (BOARD_ID, HB_ID, CLUB_ID, NAME) VALUES (BOARD_SEQ.nextval,?,?,?) ";
		
			try {
				Object[] param = new Object[] { board.getHobbyId(), board.getClubId(), board.getName()};				
				jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
									
				String key[] = {"BOARD_ID"};	// PK 컬럼명
				
				if(jdbcUtil.executeUpdate(key) != 1) { // insert 문 실행
					throw new AppException();
				}
				
			   	ResultSet rs = jdbcUtil.getGeneratedKeys();
			   	if(rs.next()) {
			   		int generatedKey = rs.getInt(1);   // 생성된 PK 값
			   		board.setBoardId(generatedKey); 	// id필드에 저장  
			   	}
			   	jdbcUtil.commit();
			   	return board;
			   	
			} catch (Exception ex ) {
				if (ex instanceof AppException) {
					jdbcUtil.rollback();
					System.out.println("트랜잭션이 rollback 되었습니다.");	
				}
				ex.printStackTrace();
			}
			finally {
				if (jdbcUtil != null) {
					jdbcUtil.close();
				}
			}		
			return null;
	}
	
	/**
	 * 기존의 게시판 정보를 수정
	 */
	public int update(BoardDTO board) {
		String sql = "UPDATE Board "
				+ "SET HB_ID=?, NAME=? "
				+ "WHERE BOARD_ID=?" ;
		int boardId = board.getBoardId();
	
//		System.out.println(board.getHobbyId() + " " + board.getName() + " " + board.getClubId() + " "+ boardId);

		Object[] param = new Object[] {board.getHobbyId(), board.getName(), boardId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
		
		try {	
			int result = jdbcUtil.executeUpdate();	// update 문 실행
//			if (result != 1) {
//				throw new AppException();
//			}
		 	jdbcUtil.commit();
			return result;
			
		}   catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	/**
	 * 주어진 ID에 해당하는 게시판 정보를 삭제.
	 */
	public int delete(int boardId)  {
		String sql = "DELETE FROM Board WHERE BOARD_ID=? ";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {boardId});	// JDBCUtil에 delete문과 매개 변수 설정

		try {
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			
			if (result != 1) {
				throw new AppException();
			}
		 	jdbcUtil.commit();
			return result;
		}  catch (Exception ex ) {
			if (ex instanceof AppException) {
				jdbcUtil.rollback();
				System.out.println("트랜잭션이 rollback 되었습니다.");	
			}
			ex.printStackTrace();
		}
		finally {
			if (jdbcUtil != null) {
				jdbcUtil.close();
			}
		}		
		return 0;
	}

	/**
	 * 주어진  ID에 해당하는 board 정보를 데이터베이스에서 찾아 Board 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public BoardDTO findBoard(int boardId)  {
		String sql = "SELECT NAME, HB_ID, CLUB_ID,STARTDATE, NUMOFVIEW, NUMOFPOST "
    			+ "FROM Board "
    			+ "WHERE BOARD_ID=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {boardId});	// JDBCUtil에 query문과 매개 변수 설정
		BoardDTO board = new BoardDTO();
		try {
			
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			
			if (rs.next()) {					
				board.setBoardId(boardId);
				board.setName(rs.getString("NAME"));
				board.setHobbyId(rs.getString("HB_ID"));
				board.setClubId(rs.getString("CLUB_ID"));
				board.setStartDate(rs.getDate("STARTDATE"));
				board.setNumOfView(rs.getInt("NUMOFVIEW"));
				board.setNumOfPost(rs.getInt("NUMOFPOST"));
			}
			jdbcUtil.commit();
			return board;
		} catch (Exception ex ) {
			if (ex instanceof AppException) {
				jdbcUtil.rollback();
				System.out.println("트랜잭션이 rollback 되었습니다.");	
			}
			ex.printStackTrace();
		}
		finally {	
			if (jdbcUtil != null) {
				jdbcUtil.close();
			}
		}
		return null;
	}
	
	/**
	 * 전체 게시판 정보를 검색하여 List에 저장 및 반환
	 */
	public List<BoardDTO> findBoardList()  {
		String sql = "SELECT BOARD_ID, NAME, HB_ID, CLUB_ID, STARTDATE,NUMOFVIEW, NUMOFPOST "
    			+ "FROM Board "
		   		+ "ORDER BY name ";        
				jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
							
				try {
					ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
					List<BoardDTO> boardList = new ArrayList<BoardDTO>();	// Community들의 리스트 생성
					BoardDTO board = null;
					while (rs.next()) {
						board = new BoardDTO();			// Community 객체를 생성하여 현재 행의 정보를 저장

						board.setBoardId(rs.getInt("BOARD_ID"));
						board.setName(rs.getString("NAME"));
						board.setHobbyId(rs.getString("HB_ID"));
						board.setClubId(rs.getString("CLUB_ID"));
						board.setStartDate(rs.getDate("STARTDATE"));
						board.setNumOfView(rs.getInt("NUMOFVIEW"));
						board.setNumOfPost(rs.getInt("NUMOFPOST"));
						
						boardList.add(board);				// List에 Community 객체 저장
					}	
					jdbcUtil.commit();
					return boardList;					
					
				} catch (Exception ex ) {
					if (ex instanceof AppException) {
						jdbcUtil.rollback();
						System.out.println("트랜잭션이 rollback 되었습니다.");	
					}
					ex.printStackTrace();
				}
				finally {	
					if (jdbcUtil != null) {
						jdbcUtil.close();
					}
				}
				return null;
	}
	

	
	/**
	 * 주어진 게시판ID에 해당하는 커뮤니티가 존재하는지 검사 
	 */
	public boolean existingBoard(int boardId) {
		String sql = "SELECT count(*) FROM Board WHERE BOARD_ID=? ";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {boardId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				jdbcUtil.commit();
				return (count == 1 ? true : false);
			}
		
			
		} catch (Exception ex ) {
			if (ex instanceof AppException) {
				jdbcUtil.rollback();
				System.out.println("트랜잭션이 rollback 되었습니다.");	
			}
			ex.printStackTrace();
		}
		finally {	
			if (jdbcUtil != null) {
				jdbcUtil.close();
			}
		}
		return false;
	}
	
}
