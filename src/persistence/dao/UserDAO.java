package persistence.dao;

import java.sql.SQLException;
import java.util.List;
import service.dto.UserDTO;

public interface UserDAO {
	public int create(UserDTO user);			// 유저정보 추가
	public int update(UserDTO dept);		// 유저정보 수정
	public int remove(String userId);			// 유저정보 삭제
	public UserDTO findUser(String userId);   	//유저 정보 검색
	public List<UserDTO> findUserList();				// 전체 유저 정보를 획득 
	public List<UserDTO> findUsersInClub(int clubId);//특정 클럽에 속한 유저 정보 획득
	public boolean existingUser(String userId); 	//존재하는 유저 여부 확인
    public String findId(String email);
    public int updatePw(String userId, String password);
}
