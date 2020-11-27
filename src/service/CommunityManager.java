package service;

/**
 * 而ㅻ�ㅻ땲�떚 愿�由� API瑜� �궗�슜�븯�뒗 媛쒕컻�옄�뱾�씠 吏곸젒 �젒洹쇳븯寃� �릺�뒗 �겢�옒�뒪.
 * UserDAO/CommunityDAO瑜� �씠�슜�븯�뿬 �뜲�씠�꽣踰좎씠�뒪�뿉 �뜲�씠�꽣 議곗옉 �옉�뾽�씠 媛��뒫�븯�룄濡� �븯硫�,
 * �뜲�씠�꽣踰좎씠�뒪�쓽 �뜲�씠�꽣�뱾�쓣 �씠�슜�븯�뿬 鍮꾩��땲�뒪 濡쒖쭅�쓣 �닔�뻾�븯�뒗 �뿭�븷�쓣 �븳�떎.
 * 鍮꾩��땲�뒪 濡쒖쭅�씠 蹂듭옟�븳 寃쎌슦�뿉�뒗 鍮꾩��땲�뒪 濡쒖쭅留뚯쓣 �쟾�떞�븯�뒗 �겢�옒�뒪瑜� 
 * 蹂꾨룄濡� �몮 �닔 �엳�떎.
 */

import java.sql.SQLException;
import java.util.List;

import service.BoardNotFoundException;
import service.PostNotFoundException;
import service.dto.ClubDTO;
import service.dto.BoardDTO;
import service.dto.PostDTO;
import persistence.dao.ClubDAO;
import persistence.dao.BoardDAO;
import persistence.dao.PostDAO;
//import service.dto.UserDTO;
//import service.dto.CommunityDTO;
//import persistence.dao.UserDAO;
//import persistence.dao.CommunityDAO;

public class CommunityManager {
    private static CommunityManager commMan = new CommunityManager();
//    private UserDAO userDAO;
//    private CommunityDAO commDAO;
    private ClubDAO clubDAO;
    private BoardDAO boardDAO;
    private PostDAO postDAO;
//    private UserAnalysis userAanlysis;

    /*
     * private CommunityManager() { try { userDAO = new UserDAO(); commDAO = new
     * CommunityDAO(); userAanlysis = new UserAnalysis(userDAO); } catch (Exception
     * e) { e.printStackTrace(); } }
     */

    public static CommunityManager getInstance() {
        return commMan;
    }

    //-------------CLUB-------------
    public ClubDTO findClub(String clubId) throws SQLException {
        return clubDAO.findClub(clubId);
    }
    
    public int createClub(ClubDTO club) throws SQLException, ExistingClubException{
        if (clubDAO.existingClub(club.getName()) == true) {
            throw new ExistingClubException(club.getName() + "�뒗 議댁옱�븯�뒗 �겢�읇紐낆엯�땲�떎.");
        }
        return clubDAO.createClub(club);
    }

    public int removeClub(String clubId) throws SQLException, ClubNotFoundException{
        if (findClub(clubId) == null) {
            throw new ClubNotFoundException(clubId + "�뒗 議댁옱�븯吏� �븡�뒗 �겢�읇�엯�땲�떎.");
        }
        return clubDAO.delete(clubId);
    }

    public int removeMember(String userId, String clubId) throws SQLException, ClubNotFoundException{
        if (findClub(clubId) == null) {
            throw new ClubNotFoundException(clubId + "�뒗 議댁옱�븯吏� �븡�뒗 �겢�읇�엯�땲�떎.");
        }
        return clubDAO.removeUser(userId, clubId);
    }

    public int newMember(String userId, String clubId) throws SQLException, ClubNotFoundException{
        if (findClub(clubId) == null) {
            throw new ClubNotFoundException(clubId + "�뒗 議댁옱�븯吏� �븡�뒗 �겢�읇�엯�땲�떎.");
        }
        return clubDAO.insertUser(userId, clubId);
    }
    
    public int updateClub(ClubDTO club) throws SQLException, PostNotFoundException {
        return clubDAO.update(club);
    }   
    
    public List<ClubDTO> findClubList() throws SQLException {
        return clubDAO.findClubList();
    }
    
    public List<ClubDTO> searchClubList(String keyword) throws SQLException {
        return clubDAO.searchClubList(keyword);
    }
    
    public List<ClubDTO> findClubListbyCategory(String category) throws SQLException {
        return clubDAO.findClubListbyCategory(category);
    }

    public List<ClubDTO> findClubListbyMBTI(String mbti) throws SQLException {
        return clubDAO.findClubListbyMBTI(mbti);
    }

    public List<ClubDTO> findClubListbyRegion(String region) throws SQLException {
        return clubDAO.findClubListbyRegion(region);
    }

    //-------------BOARD-------------
    public BoardDTO findBoard(int boardId) throws SQLException {
        return boardDAO.findBoard(boardId);
    }
    
    public BoardDTO createBoard(BoardDTO board) throws SQLException{
        return boardDAO.create(board);
    }

    //-------------POST-------------
    public PostDTO findPost(int postId) throws SQLException {
        return postDAO.findPost(postId);
    }
    
    public int createPost(PostDTO post, int boardId) throws SQLException, BoardNotFoundException{
        if (findBoard(boardId) == null) {
            throw new BoardNotFoundException(boardId + "�뒗 議댁옱�븯吏� �븡�뒗 寃뚯떆�뙋�엯�땲�떎.");
        }
        post = postDAO.create(post, boardId);
        return post.getPostId();
    }

    public int removePost(int postId) throws SQLException, PostNotFoundException{
        if (findPost(postId) == null) {
            throw new PostNotFoundException(postId + "�뒗 議댁옱�븯吏� �븡�뒗 寃뚯떆湲��엯�땲�떎.");
        }
        return postDAO.delete(postId);
    }

    public int updatePost(PostDTO post) throws SQLException, PostNotFoundException {
        return postDAO.update(post);
    }   
    
    public int increasePostViewCnt(int postId) throws SQLException, PostNotFoundException{
        if (findPost(postId) == null) {
            throw new PostNotFoundException(postId + "�뒗 議댁옱�븯吏� �븡�뒗 寃뚯떆湲��엯�땲�떎.");
        }
        return postDAO.increasePostViewCnt(postId);
    }
    
    public List<PostDTO> searchPostList(String keyword) throws SQLException {
        return postDAO.searchPostList(keyword);
    }
    
    public List<PostDTO> findPostList(int boardId) throws SQLException {
        return postDAO.findPostList(boardId);
    }

    public List<PostDTO> findPopularPostList(int boardId) throws SQLException {
        return postDAO.findPopularPostList(boardId);
    }
}