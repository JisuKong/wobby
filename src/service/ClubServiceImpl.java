package service;

import java.util.List;

import persistence.DAOFactory;
import persistence.dao.ClubDAO;
import service.dto.ClubDTO;
import service.dto.UserDTO;

public class ClubServiceImpl {
	private ClubDAO dao = null;
	
	public ClubServiceImpl() {
		DAOFactory factory = new DAOFactory();
		dao = factory.getClubDAO();
	}
	public int createClub(ClubDTO club) {
		return dao.createClub(club);
	}
	
	public int insertUser(String userId, String clubId) {
		return dao.insertUser(userId, clubId);
	}
	
	public int removeUser(String userId, String clubId) {
		return dao.removeUser(userId, clubId);
	}
	
	public int update(ClubDTO club) {
		return dao.update(club);
	}
	
	public int updateChair(ClubDTO club) {
		return dao.updateChair(club);
	}
	
	public int delete(String clubId) {
		return dao.delete(clubId);
	}
	
	public ClubDTO regionMatching(UserDTO user) {
		return dao.regionMatching(user);
	}
	public ClubDTO findClub(String clubId) {
		return dao.findClub(clubId);
	}
	
	public List<ClubDTO> findClubList() {
		return dao.findClubList();
	}
	
	public boolean existingClub(String clubId) {
		return dao.existingClub(clubId);
	}
}
