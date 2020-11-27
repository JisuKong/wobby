package persistence.dao;

import service.dto.CommunityDTO;

public interface CommunityDAO {
	public CommunityDTO create(CommunityDTO comm);
	
	public int update(CommunityDTO comm);
	
	public int insertAdmin(String userId);
	
	public int deleteAdmin(String userId);
	
	public int updateChair(CommunityDTO comm);
	
	public int delete(int communityId);
	
	public CommunityDTO findCommunity(int communityId);
	
	public boolean existingCommunity(int communityId);
}
