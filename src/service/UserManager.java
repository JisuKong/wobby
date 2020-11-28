package service;

/**
 * 커占승댐옙티 占쏙옙占쏙옙 API占쏙옙 占쏙옙占쏙옙求占� 占쏙옙占쏙옙占쌘듸옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占싹곤옙 占실댐옙 클占쏙옙占쏙옙.
 * UserDAO/CommunityDAO占쏙옙 占싱울옙占싹울옙 占쏙옙占쏙옙占싶븝옙占싱쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쌜억옙占쏙옙 占쏙옙占쏙옙占싹듸옙占쏙옙 占싹몌옙,
 * 占쏙옙占쏙옙占싶븝옙占싱쏙옙占쏙옙 占쏙옙占쏙옙占싶듸옙占쏙옙 占싱울옙占싹울옙 占쏙옙占쏙옙占싹쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹댐옙 占쏙옙占쏙옙占쏙옙 占싼댐옙.
 * 占쏙옙占쏙옙占싹쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙荑∽옙占� 占쏙옙占쏙옙占싹쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹댐옙 클占쏙옙占쏙옙占쏙옙 
 * 占쏙옙占쏙옙占쏙옙 占쏙옙 占쏙옙 占쌍댐옙.
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

    //UserDTO占쏙옙 matchPassword 占쌩곤옙占쌔억옙
    public boolean login(String userId, String password)
    		throws SQLException, UserNotFoundException, PasswordMismatchException {
    		System.out.println("UserManager login call success");
    		UserDTO user = findUser(userId);
    	
    		if (!user.matchPassword(password)) {
    			System.out.println("matchPassword success");
    			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다");
    		}
    		 
    		return true;
    	}

    
    public int create(UserDTO user) throws SQLException, ExistingUserException {
		if (userDAOImpl.existingUser(user.getUserId()) == true) {
			throw new ExistingUserException(user.getUserId() + "占쏙옙 占쏙옙占쏙옙占싹댐옙 占쏙옙占싱듸옙占쌉니댐옙.");
		}
		return userDAOImpl.create(user);
	}
/*
    //UserDTO占쏙옙 clubId占쌍깍옙
	public int update(UserDTO user) throws SQLException, UserNotFoundException {
		String oldClubId = findUser(user.getUserId()).getClubId();
		if (user.getClubId() != oldClubId) { 	// 占쌀쇽옙 커占쏙옙티占싹곤옙 占쏙옙占쏙옙占�
			ClubDTO club = clubDAO.findClub(oldClubId);  // 占쏙옙占쏙옙 占쌀쇽옙 커占승댐옙티
			if (club != null && user.getUserId().equals(club.getChairId())) {
				// 占쏙옙占쏙옙微占� 占쏙옙占쏙옙 占쌀쇽옙 커占승댐옙티占쏙옙 회占쏙옙占쏙옙 占쏙옙占� -> 占쏙옙 커占승댐옙티占쏙옙 회占쏙옙占쏙옙 null占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙
				club.setChairId(null);
				clubDAO.updateChair(club);
			}
		}
		return userDAO.update(user);
	}	

	public int remove(String userId) throws SQLException, UserNotFoundException {
		String clubId = findUser(userId).getClubId();
		ClubDTO club = clubDAO.findClub(clubId);  // 占쌀쇽옙 커占승댐옙티
		if (club != null && userId.equals(club.getChairId())) {
			// 占쏙옙占쏙옙微占� 占쌀쇽옙 커占승댐옙티占쏙옙 회占쏙옙占쏙옙 占쏙옙占� -> 占쏙옙 커占승댐옙티占쏙옙 회占쏙옙占쏙옙 null占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙
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
				throw new UserNotFoundException("사용자(" + userId + ")를 찾을 수 없습니다.");
			}		
			return user;
	}
	
	public List<UserDTO> findUserList() throws SQLException {
				return userDAOImpl.findUserList();
	}
	
	public String findId(String name, String email)
			throws SQLException, UserNotFoundException {
	        String userId = null;
            userId = userDAOImpl.findId(email);
            
            if (userId == null) {
				throw new UserNotFoundException("이메일 (" + email + ")이 존재하지 않습니다.");
			}	/*
            else if (!user.getName().equals(name)) {
                throw new UserNotFoundException("올바르지 않은 정보입니다.");
            }*/
			return userId;
	}
	
	public String findPw(String userId, String inputName, String inputEmail)
			throws SQLException, UserNotFoundException {
	        UserDTO user = null;
			user = userDAOImpl.findUser(userId);
			
			if (user == null) {
				throw new UserNotFoundException("아이디 (" + userId + ")가 존재하지 않습니다.");
			}
			else{
			    String name = user.getName();
                String email = user.getEmail();
                System.out.println(userId + " / " + inputName + " / " + inputEmail);
                System.out.println(userId + " / " + name + " / " + email);
                
                if(!name.equals(inputName) || !email.equals(inputEmail)) {
                    throw new UserNotFoundException("정보가 일치하지 않습니다.");
                }
			}
			return user.getUserId();
	}

    public String updatePw(String userId, String password)
            throws SQLException, UserNotFoundException {
            UserDTO user = null;
            user = userDAOImpl.findUser(userId);
        
            if (user == null) {
                throw new UserNotFoundException("아이디 (" + userId + "가 존재하지 않습니다.");
            }
            userDAOImpl.updatePw(userId, password);
            return user.getUserId();
    }
/*
	public List<ScrapBoxDTO> findScrapList(String userId) throws SQLException {
        return ScrapBoxDAO.getScrapList(userId);
    }
	
	public List<MsgDTO> findMessageList(String userId) throws SQLException {
        return MsgDAO.ReceiveMsgList(userId);		//ReceiveMsgList static占쏙옙占쏙옙 占쌕뀐옙
    }
	
	public ClubDTO findMyClub(int clubId) throws SQLException {
		ClubDTO club = clubDAO.findCommunity(clubId); 
		
		List<UserDTO> memberList = userDAO.findUsersInCommunity(clubId);	//UserDAO占쏙옙  占쌩곤옙
		club.setMemberList(memberList);
		
		int numOfMembers = userDAO.getNumberOfUsersInCommunity(clubId);		//UserDAO占쏙옙  占쌩곤옙
		club.setNumOfMembers(numOfMembers);
		return club;
	}
	
	public List<ClubDTO> findClubList() throws SQLException {
		return clubDAO.findClubList();
	}
	/*
	 * public int removePost(int postId) throws SQLException, PostNotFoundException{
        if (findPost(postId) == null) {
            throw new PostNotFoundException(postId + "占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占십댐옙 占쌉시깍옙占쌉니댐옙.");
        }
        return postDAO.delete(postId);
    }
	 
	public int deleteScrapList(String scrapId) throws SQLException, PostNotFoundException{
        if (findScrap(scrapId) == null) {
            throw new PostNotFoundException(scrapId + "占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占십댐옙 占쏙옙크占쏙옙占쌉니댐옙.");
        }
        return scrapBoxDAO.deleteScrap(scrapId);
    }
	
	public int deleteMessageList(String msgNo) throws SQLException, PostNotFoundException{
        if (findMsg(msgNo) == null) {
            throw new PostNotFoundException(msgNo + "占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占십댐옙 占쌨쏙옙占쏙옙占쌉니댐옙.");
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