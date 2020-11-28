package controller.user;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import service.UserManager;

public class HomeController implements Controller {
    
     @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
         // �α��� ���� Ȯ��
         if (!UserSessionUtils.hasLogined(request.getSession())) {
             return "/user/wobbyHome.jsp";
         }
         else {
             request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));   
             return "/user/wobbyHome_login.jsp";
         }
        }
}