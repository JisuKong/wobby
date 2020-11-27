package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.dto.PostDTO;

public class UpdatePostController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
        CommunityManager commManager = CommunityManager.getInstance();
        
        HttpSession session = request.getSession();
        String userId = UserSessionUtils.getLoginUserId(session);
        String writerId = request.getParameter("userId");
        
		int postId = Integer.parseInt(request.getParameter("postId"));
		int boardId = Integer.parseInt(request.getParameter("boardId"));

        if (!UserSessionUtils.isLoginUser(writerId, session)) { // 글 작성자가 아닌 경우
            request.setAttribute("exception", new IllegalStateException("글 작성자가 아닌 경우 글을 수정할 수 없습니다."));       
            return "redirect:/community/board.jsp?boardId=" + boardId;     //글 리스트로 이동
        }
        

        // 1) GET request: 글 수정 form 요청    
		if (request.getMethod().equals("GET")) {	
    		PostDTO post = commManager.findPost(postId);	// 수정하려는 글 정보 검색
			request.setAttribute("post", post);			

			return "/community/post/updateForm.jsp";   // 검색한 정보를 update form으로 전송     
	    }	
    	
    	// 2) POST request (글 정보가 parameter로 전송됨)
        String title = request.getParameter("title");
        //String content = reuqest.getParameter("content");
        PostDTO post = new PostDTO(0, title, null, null, userId); 

        commManager.updatePost(post);			
        return "redirect:/community/post.jsp?boardId=" + boardId + "?postId=" + postId;		
    }
}
