package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.dto.PostDTO;

public class CreatePostController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
        HttpSession session = request.getSession();
        String userId = UserSessionUtils.getLoginUserId(session);
        
        //받아온 정보를 토대로 post 객체 생성
        String title = request.getParameter("title");
        //String content = reuqest.getParameter("content");
        PostDTO post = new PostDTO(0, title, null, null, userId);
        
        try {
            //CommunityManager에 create처리 위임
            CommunityManager commManager = CommunityManager.getInstance();
            int boardId = Integer.parseInt(request.getParameter("boardId"));
            int postId = commManager.createPost(post, boardId);
            
            return "redirect:/community/post.jsp?boardId=" + boardId + "?postId=" + postId; // 성공 시 글로 redirect
            
        } catch (Exception e) {     // 예외 발생 시 입력 form으로 forwarding
            request.setAttribute("creationFailed", true);
            request.setAttribute("exception", e);
            return "/community/post/createForm.jsp";
        }
    }
}
