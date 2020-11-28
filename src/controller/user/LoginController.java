package controller.user;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import service.UserManager;

public class LoginController implements Controller {
    
     @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
            String userId = request.getParameter("userId");
            String password = request.getParameter("password");
            try {
                UserManager manager = UserManager.getInstance();
                
                System.out.println(userId + " / " + password);
                boolean result = manager.login(userId, password);
                System.out.println(result);
                
                HttpSession session = request.getSession();
                session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);	
              
            	return "redirect:/home";            
            } catch (Exception e) {
                request.setAttribute("loginFailed", true);
                request.setAttribute("exception", new IllegalStateException("가입하지 않은 아이디이거나, 잘못된 비밀번호입니다."));
                System.out.print("falied");
                return "/user/loginForm.jsp";           
            }   
        }
}