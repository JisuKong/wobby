package controller.board;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;

public class DeletePostController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
        CommunityManager commManager = CommunityManager.getInstance();        
        HttpSession session = request.getSession(); 
        
        int postId = Integer.parseInt(request.getParameter("postId"));
        int boardId = Integer.parseInt(request.getParameter("boardId"));
        String userId = request.getParameter("userId");
    
        if ((UserSessionUtils.isLoginUserCommAdmin(session)) //로그인한 사용자가 커뮤니티 관리자인 경우
               ||                                               // 또는 
//            (UserSessionUtils.isLoginUserChair(boardId, session)) //로그인한 사용자가 해당 커뮤니티 운영자인 경우
//               ||                                               // 또는 
            (!UserSessionUtils.isLoginUserCommAdmin(session) &&  //로그인한 사용자가 커뮤니티 관리자가 아니고 
              UserSessionUtils.isLoginUser(userId, session))) { // 글 작성자인 경우
                
            commManager.removePost(postId);               // 글 삭제
            return "redirect:/community/board.jsp?boardId=" + boardId;     //글 리스트로 이동
        }
        
        /* 삭제가 불가능한 경우 */
        request.setAttribute("postId", postId);                     
        request.setAttribute("deleteFailed", true); 
        request.setAttribute("exception", new IllegalStateException("관리자 혹은 글 작성자가 아닌 경우 글을 지울 수 없습니다."));            
        return "/community/post.jsp?boardId=" + boardId + "?postIdo=" + postId;
    }
}
