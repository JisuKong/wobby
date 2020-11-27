package service.dto;

import java.util.Date;
import java.util.List;

public class CommunityDTO {
	private int communityId; //커뮤니티는 한개라 따로 String Id를 사용하지 않음.
	private String type;
	private String name;
	private String category;
	private Date startDate;
	private String keeperId;
	private int numOfMembers;
	private int maxNumOfMembers;
	private int amountOfPost;
	private int amountOfBoard;
	private boolean popularity;
	private List<UserDTO> memberList;
	private List<BoardDTO> boadrList;

	public CommunityDTO() { }		
	
	
	public CommunityDTO(int communityId, String type, String name, String category, Date startDate, String keeperId, int numOfMembers,
			int maxNumOfMembers, int amountOfPost, boolean popularity, List<UserDTO> memberList, List<BoardDTO> boadrList) {
		super();
		this.communityId = communityId;
		this.type = type;
		this.name = name;
		this.category = category;
		this.startDate = startDate;
		this.keeperId = keeperId;
		this.numOfMembers = numOfMembers;
		this.maxNumOfMembers = maxNumOfMembers;
		this.amountOfPost = amountOfPost;
		this.popularity = popularity;
		this.memberList = memberList;
		this.boadrList = boadrList;
	}


	public CommunityDTO(int communityId, String name, String category, int numOfMembers) {
		super();
		this.communityId = communityId;
		this.name = name;
		this.category = category;
		this.numOfMembers = numOfMembers;
	}

	
	public CommunityDTO(int communityId, String name,   Date startDate, String keeperId) {
		super();
		this.communityId = communityId;
		this.name = name;
		this.startDate = startDate;
		this.keeperId = keeperId;
	}


	public int getCommunityId() {
		return communityId;
	}

	public void setCommunityId(int generatedKey) {
		this.communityId = generatedKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getKeeperId() {
		return keeperId;
	}


	public void setKeeperId(String keeperId) {
		this.keeperId = keeperId;
	}


	public int getAmountOfPost() {
		return amountOfPost;
	}


	public void setAmountOfPost(int amountOfPost) {
		this.amountOfPost = amountOfPost;
	}


	public boolean isPopularity() {
		return popularity;
	}


	public void setPopularity(boolean popularity) {
		this.popularity = popularity;
	}


	public int getNumOfMembers() {
		return numOfMembers;
	}

	public void setNumOfMembers(int numOfMembers) {
		this.numOfMembers = numOfMembers;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMaxNumOfMembers() {
		return maxNumOfMembers;
	}

	public void setMaxNumOfMembers(int maxNumOfMembers) {
		this.maxNumOfMembers = maxNumOfMembers;
	}

	public List<UserDTO> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<UserDTO> memberList) {
		this.memberList = memberList;
	}

	public List<BoardDTO> getBoadrList() {
		return boadrList;
	}

	public void setBoadrList(List<BoardDTO> boadrList) {
		this.boadrList = boadrList;
	}

	
	public int getAmountOfBoard() {
		return amountOfBoard;
	}


	public void setAmountOfBoard(int amountOfBoard) {
		this.amountOfBoard = amountOfBoard;
	}


	@Override
	public String toString() {
		return "Community [communityId=" + communityId + ", type=" + type + ", name=" + name + ", category=" + category + ", startDate="
				+ startDate + ", keeperId=" + keeperId + ", numOfMembers=" + numOfMembers + ", maxNumOfMembers="
				+ maxNumOfMembers + ", amountOfPost=" + amountOfPost + ", popularity=" + popularity + ", memberList="
				+ memberList + ", boadrList=" + boadrList + "]";
	}

}
