package controller.user;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import service.UserManager;
import service.dto.UserDTO;

public class LoginController implements Controller {
    
     @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
            String userId = request.getParameter("userId");
            String password = request.getParameter("password");
            try {
           
            
                // �𵨿� �α��� ó���� ����
                UserManager manager = UserManager.getInstance();
                
                System.out.println(userId + " / " + password);
                boolean result = manager.login(userId, password);
                System.out.println(result);
                
                HttpSession session = request.getSession();
                session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
                
            	request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));		
              
            	return "/user/wobbyHome_login.jsp";            
            } catch (Exception e) {
                /* UserNotFoundException�̳� PasswordMismatchException �߻� ��
                 * �ٽ� login form�� ����ڿ��� �����ϰ� ���� �޼����� ���
                 */
            	//System.out.println(userId + " / " + password);
                request.setAttribute("loginFailed", true);
                request.setAttribute("exception", e);
                System.out.print("falied");
                return "/user/loginForm.jsp";           
            }   
        }
}