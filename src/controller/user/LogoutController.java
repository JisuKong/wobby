package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller; 

public class LogoutController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        try {
			// 모델에 로그아웃 처리를 위임
			UserManager manager = UserManager.getInstance();
			manager.logout(userId, password);
	
			//세션에 저장된 사용자 이이디를 삭제하고 세션을 무효화 함 
			HttpSession session = request.getSession();
			session.removeAttribute(UserSessionUtils.USER_SESSION_KEY);
			session.invalidate();		        
            
            return "redirect:/home";			
		} catch (Exception e) {
			request.setAttribute("exception", e);
        		
		}	
    }
}

