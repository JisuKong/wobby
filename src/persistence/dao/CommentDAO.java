package persistence.dao;

import service.dto.CommentDTO;

public interface CommentDAO {
	public CommentDTO create(CommentDTO comm);
	public int update(CommentDTO comm);
	public int delete(int commId);
}
