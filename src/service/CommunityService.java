package service;

import service.dto.CommunityDTO;

public interface CommunityService {
	public CommunityDTO create(CommunityDTO comm);
	
	public int update(CommunityDTO comm);
	
	public int updateChair(CommunityDTO comm);
	
	public int delete(int communityId);
	
	public CommunityDTO findCommunity(int communityId);
	
	public boolean existingCommunity(int communityId);
}
