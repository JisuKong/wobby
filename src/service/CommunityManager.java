package service;

import java.sql.SQLException;
import java.util.List;

import service.BoardNotFoundException;
import service.PostNotFoundException;
import service.dto.ClubDTO;
import service.dto.BoardDTO;
import service.dto.PostDTO;
import persistence.dao.impl.ClubDAOImpl;
import persistence.dao.impl.BoardDAOImpl;
import persistence.dao.impl.PostDAOImpl;
//import service.dto.UserDTO;
//import service.dto.CommunityDTO;
//import persistence.DAOImpl.UserDAOImpl;
//import persistence.DAOImpl.CommunityDAOImpl;

public class CommunityManager {
    private static CommunityManager commMan = new CommunityManager();
//    private UserDAOImpl userDAOImpl;
//    private CommunityDAOImpl commDAOImpl;
    private ClubDAOImpl clubDAOImpl = new ClubDAOImpl();
    private BoardDAOImpl boardDAOImpl = new BoardDAOImpl();
    private PostDAOImpl postDAOImpl = new PostDAOImpl();
//    private UserAnalysis userAanlysis;

    /*
     * private CommunityManager() { try { userDAOImpl = new UserDAOImpl(); commDAOImpl = new
     * CommunityDAOImpl(); userAanlysis = new UserAnalysis(userDAOImpl); } catch (Exception
     * e) { e.printStackTrace(); } }
     */

    public static CommunityManager getInstance() {
        return commMan;
    }

    //-------------CLUB-------------
    public ClubDTO findClub(String clubId) throws SQLException {
        return clubDAOImpl.findClub(clubId);
    }
    
    public int createClub(ClubDTO club) throws SQLException, ExistingClubException{
        if (clubDAOImpl.existingClub(club.getName()) == true) {
            throw new ExistingClubException(club.getName() + "占쎈뮉 鈺곕똻�삺占쎈릭占쎈뮉 占쎄깻占쎌쓦筌뤿굞�뿯占쎈빍占쎈뼄.");
        }
        return clubDAOImpl.createClub(club);
    }

    public int removeClub(String clubId) throws SQLException, ClubNotFoundException{
        if (findClub(clubId) == null) {
            throw new ClubNotFoundException(clubId + "占쎈뮉 鈺곕똻�삺占쎈릭筌욑옙 占쎈륫占쎈뮉 占쎄깻占쎌쓦占쎌뿯占쎈빍占쎈뼄.");
        }
        return clubDAOImpl.delete(clubId);
    }

    public int removeMember(String userId, String clubId) throws SQLException, ClubNotFoundException{
        if (findClub(clubId) == null) {
            throw new ClubNotFoundException(clubId + "占쎈뮉 鈺곕똻�삺占쎈릭筌욑옙 占쎈륫占쎈뮉 占쎄깻占쎌쓦占쎌뿯占쎈빍占쎈뼄.");
        }
        return clubDAOImpl.removeUser(userId, clubId);
    }

    public int newMember(String userId, String clubId) throws SQLException, ClubNotFoundException{
        if (findClub(clubId) == null) {
            throw new ClubNotFoundException(clubId + "占쎈뮉 鈺곕똻�삺占쎈릭筌욑옙 占쎈륫占쎈뮉 占쎄깻占쎌쓦占쎌뿯占쎈빍占쎈뼄.");
        }
        return clubDAOImpl.insertUser(userId, clubId);
    }
    
    public int updateClub(ClubDTO club) throws SQLException, PostNotFoundException {
        return clubDAOImpl.update(club);
    }   
    
    public List<ClubDTO> findClubList() throws SQLException {
        return clubDAOImpl.findClubList();
    }
    
    public List<ClubDTO> searchClubList(String keyword) throws SQLException {
        return clubDAOImpl.searchClubList(keyword);
    }
   /* 
    public List<ClubDTO> findClubListbyCategory(String category) throws SQLException {
        return clubDAOImpl.findClubListbyCategory(category);
    }*/

    public List<ClubDTO> findClubListbyMBTI(String mbti) throws SQLException {
        System.out.println("CommMan");
        return clubDAOImpl.findClubListbyMBTI(mbti);
    }

    public List<ClubDTO> findClubListbyRegion(String region) throws SQLException {
        return clubDAOImpl.findClubListbyRegion(region);
    }

    //-------------BOARD-------------
    public BoardDTO findBoard(int boardId) throws SQLException {
        return boardDAOImpl.findBoard(boardId);
    }
    
    public BoardDTO createBoard(BoardDTO board) throws SQLException{
        return boardDAOImpl.create(board);
    }

    //-------------POST-------------
    public PostDTO findPost(int postId) throws SQLException {
        return postDAOImpl.findPost(postId);
    }
    
    public int createPost(PostDTO post, int boardId) throws SQLException, BoardNotFoundException{
        if (findBoard(boardId) == null) {
            throw new BoardNotFoundException(boardId + "占쎈뮉 鈺곕똻�삺占쎈릭筌욑옙 占쎈륫占쎈뮉 野껊슣�뻻占쎈솇占쎌뿯占쎈빍占쎈뼄.");
        }
        post = postDAOImpl.create(post, boardId);
        return post.getPostId();
    }

    public int removePost(int postId) throws SQLException, PostNotFoundException{
        if (findPost(postId) == null) {
            throw new PostNotFoundException(postId + "占쎈뮉 鈺곕똻�삺占쎈릭筌욑옙 占쎈륫占쎈뮉 野껊슣�뻻疫뀐옙占쎌뿯占쎈빍占쎈뼄.");
        }
        return postDAOImpl.delete(postId);
    }

    public int updatePost(PostDTO post) throws SQLException, PostNotFoundException {
        return postDAOImpl.update(post);
    }   
    
    public int increasePostViewCnt(int postId) throws SQLException, PostNotFoundException{
        if (findPost(postId) == null) {
            throw new PostNotFoundException(postId + "占쎈뮉 鈺곕똻�삺占쎈릭筌욑옙 占쎈륫占쎈뮉 野껊슣�뻻疫뀐옙占쎌뿯占쎈빍占쎈뼄.");
        }
        return postDAOImpl.increasePostViewCnt(postId);
    }
    
    public List<PostDTO> searchPostList(String keyword) throws SQLException {
        return postDAOImpl.searchPostList(keyword);
    }
    
    public List<PostDTO> findPostList(int boardId) throws SQLException {
        return postDAOImpl.findPostList(boardId);
    }

    public List<PostDTO> findPopularPostList(int boardId) throws SQLException {
        return postDAOImpl.findPopularPostList(boardId);
    }
}