package service;

/**
 * Ŀ�´�Ƽ ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * UserDAO/CommunityDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
 */
 
import java.sql.SQLException;
import java.util.List;

import service.UserManager;
import persistence.dao.UserDAO;
import persistence.dao.impl.UserDAOImpl;
import persistence.dao.MsgDAO;
import persistence.dao.ScrapBoxDAO;
import persistence.dao.ClubDAO; //regionmathcing
import service.dto.UserDTO;
import service.dto.MsgDTO;
import service.dto.PostDTO;
import service.dto.ScrapBoxDTO;
import service.dto.ClubDTO;
import service.dto.CommunityDTO;


public class UserManager {
    private static UserManager userMan = new UserManager();
    private UserDAOImpl userDAOImpl = new UserDAOImpl();
    private MsgDAO msgDAO;
    private ScrapBoxDAO scrapBoxDAO;
    private ClubDAO clubDAO;

    
    
//    private UserManager() {
//		try {
//			userDAO = new UserDAO();
//			commDAO = new CommunityDAO();
//			userAanlysis = new UserAnalysis(userDAO);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}			
//	}
    
    public UserManager() {
		super();
	}
    
	public static UserManager getInstance() {
		return userMan;
	}

    //UserDTO�� matchPassword �߰��ؾ�
    public boolean login(String userId, String password)
    		throws SQLException, UserNotFoundException, PasswordMismatchException {
    		System.out.println("UserManager login call success");
    		UserDTO user = findUser(userId);
    	
    		if (!user.matchPassword(password)) {
    			System.out.println("matchPassword success");
    			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
    		}
    		 
    		return true;
    	}

    
    public int create(UserDTO user) throws SQLException, ExistingUserException {
		if (userDAOImpl.existingUser(user.getUserId()) == true) {
			throw new ExistingUserException(user.getUserId() + "�� �����ϴ� ���̵��Դϴ�.");
		}
		return userDAOImpl.create(user);
	}
/*
    //UserDTO�� clubId�ֱ�
	public int update(UserDTO user) throws SQLException, UserNotFoundException {
		String oldClubId = findUser(user.getUserId()).getClubId();
		if (user.getClubId() != oldClubId) { 	// �Ҽ� Ŀ��Ƽ�ϰ� �����
			ClubDTO club = clubDAO.findClub(oldClubId);  // ���� �Ҽ� Ŀ�´�Ƽ
			if (club != null && user.getUserId().equals(club.getChairId())) {
				// ����ڰ� ���� �Ҽ� Ŀ�´�Ƽ�� ȸ���� ��� -> �� Ŀ�´�Ƽ�� ȸ���� null�� ���� �� ����
				club.setChairId(null);
				clubDAO.updateChair(club);
			}
		}
		return userDAO.update(user);
	}	

	public int remove(String userId) throws SQLException, UserNotFoundException {
		String clubId = findUser(userId).getClubId();
		ClubDTO club = clubDAO.findClub(clubId);  // �Ҽ� Ŀ�´�Ƽ
		if (club != null && userId.equals(club.getChairId())) {
			// ����ڰ� �Ҽ� Ŀ�´�Ƽ�� ȸ���� ��� -> �� Ŀ�´�Ƽ�� ȸ���� null�� ���� �� ����
			club.setChairId(null);
			clubDAO.updateChair(club);
		}
		return userDAO.remove(userId);
	}
   */
    
	public UserDTO findUser(String userId)
			throws SQLException, UserNotFoundException {
			System.out.println("usermanager finduser");
			UserDTO user = userDAOImpl.findUser(userId);
		
			if (user == null) {
				throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
			}		
			return user;
		}
/*
	public List<UserDTO> findUserList() throws SQLException {
				return userDAO.findUserList();
	}
	
	public String findId(String name, String email)
			throws SQLException, UserNotFoundException {
			UserDTO user = userDAO.findUser(email);
			
			if (user == null) {
				throw new UserNotFoundException(email + "�� �������� �ʴ� �̸����Դϴ�.");
			}		
			return user.getUserId();
	}
	//findPw �����ؾ�
	public String findPw(String userId, String name, String email)
			throws SQLException, UserNotFoundException {
			UserDTO user = userDAO.findUser(userId);
			
			if (user == null) {
				throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
			}		
			return user.getUserId();
	}
	
	public List<ScrapBoxDTO> findScrapList(String userId) throws SQLException {
        return ScrapBoxDAO.getScrapList(userId);
    }
	
	public List<MsgDTO> findMessageList(String userId) throws SQLException {
        return MsgDAO.ReceiveMsgList(userId);		//ReceiveMsgList static���� �ٲ�
    }
	
	public ClubDTO findMyClub(int clubId) throws SQLException {
		ClubDTO club = clubDAO.findCommunity(clubId); 
		
		List<UserDTO> memberList = userDAO.findUsersInCommunity(clubId);	//UserDAO��  �߰�
		club.setMemberList(memberList);
		
		int numOfMembers = userDAO.getNumberOfUsersInCommunity(clubId);		//UserDAO��  �߰�
		club.setNumOfMembers(numOfMembers);
		return club;
	}
	
	public List<ClubDTO> findClubList() throws SQLException {
		return clubDAO.findClubList();
	}
	/*
	 * public int removePost(int postId) throws SQLException, PostNotFoundException{
        if (findPost(postId) == null) {
            throw new PostNotFoundException(postId + "�� �������� �ʴ� �Խñ��Դϴ�.");
        }
        return postDAO.delete(postId);
    }
	 
	public int deleteScrapList(String scrapId) throws SQLException, PostNotFoundException{
        if (findScrap(scrapId) == null) {
            throw new PostNotFoundException(scrapId + "�� �������� �ʴ� ��ũ���Դϴ�.");
        }
        return scrapBoxDAO.deleteScrap(scrapId);
    }
	
	public int deleteMessageList(String msgNo) throws SQLException, PostNotFoundException{
        if (findMsg(msgNo) == null) {
            throw new PostNotFoundException(msgNo + "�� �������� �ʴ� �޽����Դϴ�.");
        }
        return msgDAO.deleteMsg(msgNo);
    }
	*/
	
	//sendMessage(), insertMessage(), receivedMessage() ?
	
	
	
	//
    public int outClub(String userId, int clubId) {
        UserDTO user = userDAOImpl.findUser(userId);
        return 0;
    }
    
    public int registerClub(String userId, int clubId) {
        UserDTO user = userDAOImpl.findUser(userId);
        return 0;
    }
    
    public UserDAO getUserDAO() {
        return this.userDAOImpl;
    }


}