package persistence.dao.impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import service.dto.BoardDTO;
import service.dto.UserDTO;
import persistence.dao.BoardDAO;
import persistence.dao.impl.*;


public class projectTest {

	private static BoardDAOImpl bm = new BoardDAOImpl();
	private static UserDAOImpl uI = new UserDAOImpl();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		/*BoardDTO create(BoardDTO board)*/
//		System.out.print("새로 생성할 board의 정보를 입력하세요(hb_id, club_id, name):");
//		String hb = sc.next();
//		String clubId = sc.next();
//		String name = sc.next();
//		
//		BoardDTO board = new BoardDTO();
//		board.setHobbyId(hb);
//		board.setClubId(clubId);
//		board.setName(name);
//		bm.create(board);
		
		/* update(BoardDTO board)*/
		System.out.print("수정할 board의 정보를 입력하세요(board_id,hb_id, name):");
		int id = sc.nextInt();
		String hb = sc.next();
		
		
		BoardDTO board = new BoardDTO();
		board.setBoardId(id);
		board.setHobbyId(hb);
	
		int rs = bm.update(board);
		System.out.println(rs);
		
		
		System.out.print("User의 정보를 입력하세요(UserId):");
		String userId = sc.next();
		
		UserDTO user = new UserDTO();
		user.setUserId(userId);
	
		/*findBoard(boardId)*/
		System.out.print("boardId를 입력하세요:");
		int boardId = sc.nextInt();
//		
		UserDTO us = uI.findUser(userId);	
		if (uI == null) {
			System.out.println("해당 uI가 없습니다.");
			sc.close();
			return;
		}
		System.out.println("<user 정보>");
		System.out.println(" userId: " + us.getUserId());
		System.out.println(" name: " + us.getName());
		System.out.println(" startDate: " + bd.getStartDate());
		System.out.println();
		
		/*findBoard(boardId)*/
		System.out.print("boardId를 입력하세요:");
		int boardId = sc.nextInt();
//		
		BoardDTO bd = bm.findBoard(boardId);	
		if (bd == null) {
			System.out.println("해당 board가 없습니다.");
			sc.close();
			return;
		}
		System.out.println("<board 정보>");
		System.out.println(" boarId: " + bd.getBoardId());
		System.out.println(" clubId: " + bd.getClubId());
		System.out.println(" name: " + bd.getName());
		System.out.println(" startDate: " + bd.getStartDate());
		System.out.println();
		
		/* findBoardList()*/
//		List<BoardDTO> bd = bm.findBoardList();	
//		Iterator<BoardDTO> it = bd.iterator();
//		
//		System.out.println("<board 정보>");
//		// board 객체의 필드 값 출력
//		while(it.hasNext()) {
//			BoardDTO bt = it.next();
//			System.out.println(" boarId: " + bt.getBoardId());
//			System.out.println(" clubId: " + bt.getClubId());
//			System.out.println(" name: " + bt.getName());
//			System.out.println(" startDate: " + bt.getStartDate());
//			System.out.println();
//		}
//		/*existingBoard(int boardId)*/
//		System.out.print("boardId를 입력하세요:");
//		int boardId = sc.nextInt();
//		
//		boolean result = bm.existingBoard(boardId);
//		if (result) {
//			System.out.println("board is exist");
//		}
//		else {System.out.println("Board is not exist");}
						
		sc.close();
	}

}
