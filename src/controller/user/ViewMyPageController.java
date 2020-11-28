package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.UserManager;

public class ViewMyPageController  implements Controller {

	  @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
	    	// �α��� ���� Ȯ��
	    	if (!UserSessionUtils.hasLogined(request.getSession())) {
	            return "redirect:/user/login/form";		// login form ��û���� redirect
	        }
	    	
	    	/* �𵨿� �α��� ó���� ����
	    				UserManager manager = UserManager.getInstance();
	    				manager.login(userId, password);
	    				*/
	    	
			UserManager manager = UserManager.getInstance();
			
			String userId = request.getParameter("userId");

	    	request.setAttribute("userId", userId);		// ����� ���� ����				
			return "/user/myPage.jsp";				// ����� ���� ȭ������ �̵�
	    }
}
