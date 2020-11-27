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
            
            System.out.println(userId + " / " + password);
            try {
                // �𵨿� �α��� ó���� ����
                UserManager manager = UserManager.getInstance();
                manager.login(userId, password);
        
                // ���ǿ� ����� ���̵� ����
                HttpSession session = request.getSession();
                session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);

                return "redirect:/home";            
            } catch (Exception e) {
                /* UserNotFoundException�̳� PasswordMismatchException �߻� ��
                 * �ٽ� login form�� ����ڿ��� �����ϰ� ���� �޼����� ���
                 */
                request.setAttribute("loginFailed", true);
                request.setAttribute("exception", e);
                return "/user/login.jsp";           
            }   
        }
}