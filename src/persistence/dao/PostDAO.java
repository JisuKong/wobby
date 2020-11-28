package persistence.dao;

import java.util.List;

import service.dto.PostDTO;

public interface PostDAO {	
	public PostDTO create(PostDTO post, int boardId);
	
	public int update(PostDTO post);
	
	public int delete(int postId);
	
	public int increasePostViewCnt(int postId);
	
	public List<PostDTO> findPopularPostList(int boardId);
	
	public PostDTO findPost(int postId);

    public List<PostDTO> searchPostList(String keyword);
    
	public List<PostDTO> findPostList(int boardId);
	
	public boolean existingPost(int postId);
}
