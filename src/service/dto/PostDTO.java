package service.dto;

import java.sql.Date;

public class PostDTO {
	private int postId;
	private int boardId;
	private String userId;
	private String nickname;
	private String title;
	private int numOfView;
	private int numOfScraps;
	private Date postDate;
	private Date updateDate;
	private String contents;
	
	public PostDTO() {}

	public PostDTO(int postId, String userId, String title, int numOfView, int numOfScraps, Date postDate,
			Date updateDate) {
		super();
		this.userId = userId;
		this.postId = postId;
		this.title = title;
		this.numOfView = numOfView;
		this.numOfScraps = numOfScraps;
		this.postDate = postDate;
		this.updateDate = updateDate;
	}
	
	public PostDTO(int postId,  String title, String contents,Date postDate,String nickname) {
		super();
		this.postId = postId;
		this.nickname = nickname;
		this.title = title;
		this.contents = contents;
		this.postDate = postDate;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	
	
	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public int getNumOfView() {
		return numOfView;
	}

	public void setNumOfView(int numOfView) {
		this.numOfView = numOfView;
	}

	public int getNumOfScraps() {
		return numOfScraps;
	}

	public void setNumOfScraps(int numOfScraps) {
		this.numOfScraps = numOfScraps;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "PostDTO [postId=" + postId + ", boardId=" + boardId + ", userId=" + userId + ", title=" + title
				+ ", numOfView=" + numOfView + ", numOfScraps=" + numOfScraps + ", postDate=" + postDate
				+ ", updateDate=" + updateDate + "]";
	}

	
	
}
