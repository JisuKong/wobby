package service;

/**
 * 커뮤니티 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO/CommunityDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
 
import java.sql.SQLException;
import java.util.List;


import persistence.dao.UserDAO;
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
    private UserDAO userDAO;
    private MsgDAO msgDAO;
    private ScrapBoxDAO scrapBoxDAO;
    private ClubDAO clubDAO;

    /*
    private UserManager() {
		try {
			userDAO = new UserDAO();
			//commDAO = new CommunityDAO();
			userAanlysis = new UserAnalysis(userDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}*/
    
    //UserDTO에 matchPassword 추가해야
    /*
    public boolean login(String userId, String password)
    		throws SQLException, UserNotFoundException, PasswordMismatchException {
    		UserDTO user = findUser(userId);
    		
    		if (!user.matchPassword(password)) {
    			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
    		}
    	return true;
    }
    */

    
    public boolean login(String userId, String password)
            throws SQLException, UserNotFoundException, PasswordMismatchException {
             UserDTO user = findUser(userId);
            //String uId = user.getUserId();
            //String uPw = user.getPassword();
            
            if (!userId.equals("admin") || !password.equals("system")) {
                //throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
            }
            return true;
        }
    
    public static UserManager getInstance() {
        return userMan;
    }
    
    public int create(UserDTO user) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(user.getUserId()) == true) {
			throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
		}
		return userDAO.create(user);
	}
/*
    //UserDTO에 clubId넣기
	public int update(UserDTO user) throws SQLException, UserNotFoundException {
		String oldClubId = findUser(user.getUserId()).getClubId();
		if (user.getClubId() != oldClubId) { 	// 소속 커뮤티니가 변경됨
			ClubDTO club = clubDAO.findClub(oldClubId);  // 기존 소속 커뮤니티
			if (club != null && user.getUserId().equals(club.getChairId())) {
				// 사용자가 기존 소속 커뮤니티의 회장인 경우 -> 그 커뮤니티의 회장을 null로 변경 및 저장
				club.setChairId(null);
				clubDAO.updateChair(club);
			}
		}
		return userDAO.update(user);
	}	

	public int remove(String userId) throws SQLException, UserNotFoundException {
		String clubId = findUser(userId).getClubId();
		ClubDTO club = clubDAO.findClub(clubId);  // 소속 커뮤니티
		if (club != null && userId.equals(club.getChairId())) {
			// 사용자가 소속 커뮤니티의 회장인 경우 -> 그 커뮤니티의 회장을 null로 변경 및 저장
			club.setChairId(null);
			clubDAO.updateChair(club);
		}
		return userDAO.remove(userId);
	}
   */
    
	public UserDTO findUser(String userId)
			throws SQLException, UserNotFoundException {
			UserDTO user = userDAO.findUser(userId);
			
			if (user == null) {
				throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
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
				throw new UserNotFoundException(email + "는 존재하지 않는 이메일입니다.");
			}		
			return user.getUserId();
	}
	//findPw 수정해야
	public String findPw(String userId, String name, String email)
			throws SQLException, UserNotFoundException {
			UserDTO user = userDAO.findUser(userId);
			
			if (user == null) {
				throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
			}		
			return user.getUserId();
	}
	
	public List<ScrapBoxDTO> findScrapList(String userId) throws SQLException {
        return ScrapBoxDAO.getScrapList(userId);
    }
	
	public List<MsgDTO> findMessageList(String userId) throws SQLException {
        return MsgDAO.ReceiveMsgList(userId);		//ReceiveMsgList static으로 바꿈
    }
	
	public ClubDTO findMyClub(int clubId) throws SQLException {
		ClubDTO club = clubDAO.findCommunity(clubId); 
		
		List<UserDTO> memberList = userDAO.findUsersInCommunity(clubId);	//UserDAO에  추가
		club.setMemberList(memberList);
		
		int numOfMembers = userDAO.getNumberOfUsersInCommunity(clubId);		//UserDAO에  추가
		club.setNumOfMembers(numOfMembers);
		return club;
	}
	
	public List<ClubDTO> findClubList() throws SQLException {
		return clubDAO.findClubList();
	}
	/*
	 * public int removePost(int postId) throws SQLException, PostNotFoundException{
        if (findPost(postId) == null) {
            throw new PostNotFoundException(postId + "는 존재하지 않는 게시글입니다.");
        }
        return postDAO.delete(postId);
    }
	 
	public int deleteScrapList(String scrapId) throws SQLException, PostNotFoundException{
        if (findScrap(scrapId) == null) {
            throw new PostNotFoundException(scrapId + "는 존재하지 않는 스크랩입니다.");
        }
        return scrapBoxDAO.deleteScrap(scrapId);
    }
	
	public int deleteMessageList(String msgNo) throws SQLException, PostNotFoundException{
        if (findMsg(msgNo) == null) {
            throw new PostNotFoundException(msgNo + "는 존재하지 않는 메시지입니다.");
        }
        return msgDAO.deleteMsg(msgNo);
    }
	*/
	
	//sendMessage(), insertMessage(), receivedMessage() ?
	
	
	
	//
    public int outClub(String userId, int clubId) {
        UserDTO user = userDAO.findUser(userId);
        return 0;
    }
    
    public int registerClub(String userId, int clubId) {
        UserDTO user = userDAO.findUser(userId);
        return 0;
    }
    
    public UserDAO getUserDAO() {
        return this.userDAO;
    }
}