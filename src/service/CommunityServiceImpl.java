package service;

import persistence.DAOFactory;
import persistence.dao.CommunityDAO;

import service.dto.CommunityDTO;

public class CommunityServiceImpl {
	
	private CommunityDAO dao = null;
	public CommunityServiceImpl() {								
		DAOFactory factory = new DAOFactory();
		dao = factory.getCommunityDAO();
	}
	public CommunityDTO create(CommunityDTO comm) {
		return dao.create(comm);
	}
	
	public int update(CommunityDTO comm) {
		return dao.update(comm);
	}
	public int insertAdmin(String userId) {
		return dao.insertAdmin(userId);
	}
	public int deleteAdmin(String userId) {
		return dao.deleteAdmin(userId);
	}
	public int updateChair(CommunityDTO comm) {
		return dao.updateChair(comm);
	}
	
	public int delete(int communityId) {
		return dao.delete(communityId);
	}
	
	public CommunityDTO findCommunity(int communityId) {
		return dao.findCommunity(communityId);
	}
	
	public boolean existingCommunity(int communityId) {
		return dao.existingCommunity(communityId);
	}
}
