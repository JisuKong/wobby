package persistence;

import persistence.dao.*;
import persistence.dao.impl.*;

public class DAOFactory {
	public BoardDAO getBoardDAO() {
		return new BoardDAOImpl();
	}
	
	public ClubDAO getClubDAO() {
		return new ClubDAOImpl();
	}
	
	public CommunityDAO getCommunityDAO() {
		return new CommunityDAOImpl();
	}
	
	public PostDAO getPostDAO() {
		return new PostDAOImpl();
	}
	
	public CommentDAO getCommentDAO() {
		return new CommentDAOImpl();
	}
	
	// StudentDAO 를 위한 RDB 용 DAO Implementation 객체를 반환
		public UserDAO getUserDAO() {
			return new UserDAOImpl();		 
		}
		
		// DeptDAO 를 위한 RDB 용 DAO Implementation 객체를 반환
		public MsgDAO getMsgDAO() {
			return new MsgDAOImpl();		
		}
		
		// ProfDAO 를 위한 RDB 용 DAO Implementation 객체를 반환
		public ScrapBoxDAO getScrapBoxDAO() {
			return new ScrapBoxDAOImpl();		
		}
		

}
