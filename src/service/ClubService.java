package service;

import java.util.List;

import service.dto.ClubDTO;
import service.dto.UserDTO;

public interface ClubService {
	public int createClub(ClubDTO club);
	
	public int insertUser(String userId, String clubId);
	
	public int removeUser(String userId, String clubId);
	
	public int update(ClubDTO club);
	
	public int updateChair(ClubDTO club);
	
	public int delete(String clubId);
	
	public ClubDTO regionMatching(UserDTO user);
	
	public ClubDTO findClub(String clubId);
	
	public List<ClubDTO> findClubList();
	
	public boolean existingClub(String clubId);
}
