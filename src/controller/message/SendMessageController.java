package controller.message;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.User;

public class SendMessageController implements Controller{
	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
	    	// 로그인 여부 확인
	    	if (!UserSessionUtils.hasLogined(request.getSession())) {
	            return "redirect:/user/login/form";		// login form 요청으로 redirect
	        }
	    	
	 int selectedMessage = request.getParameter("message_no");
		
		try {
			// 모델에 로그인 처리를 위임
			UserManager manager = UserManager.getInstance();
			manager.sendMessage(selectedMessage);
         
			return "redirect:/user/myPage/message/list";			
		} catch (Exception e) {
			/* 전송 안되면 
			 * 다시 messagelist로 fowarding하고 오류 메세지도 출력
			 */
         request.setAttribute("Failed", true);
			request.setAttribute("exception", e);
         return "/user/myPage/message/list.jsp";			
		}	
	 }
}
