import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.User;

public class ListReceivedMessageController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// 로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
		UserManager manager = UserManager.getInstance();
		String message_no = request.getParameter("message_no");

		List<Message> MessageList = null;
    	try {
    		MessageList = manager.findScrap(message_no);	// 스크랩 검색
		} catch (UserNotFoundException e) {				
	        return "/user/myPage/message/list.jsp";
		}	
    }
}


