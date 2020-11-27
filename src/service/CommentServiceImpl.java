package service;

import persistence.DAOFactory;
import persistence.dao.CommentDAO;
import service.dto.CommentDTO;

public class CommentServiceImpl {
private CommentDAO dao = null;
	
	public CommentServiceImpl() {
		DAOFactory factory = new DAOFactory();
		dao = factory.getCommentDAO();
	}
	public CommentDTO create(CommentDTO comm) {
		return dao.create(comm);
	}
	public int update(CommentDTO comm) {
		return dao.update(comm);
	}
	public int delete(int commId) {
		return dao.delete(commId);
	}
}
