package service;

import service.dto.CommentDTO;

public interface CommentService {
	public CommentDTO create(CommentDTO comm);
	public int update(CommentDTO comm);
	public int delete(int commId);
}
