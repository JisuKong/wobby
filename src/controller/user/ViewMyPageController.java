package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.User;

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

	    	User user = null;
	    	try {
				user = manager.findUser(userId);	// ����� ���� �˻�
			} catch (UserNotFoundException e) {	
				
		        return "redirect:/user/myPage/Alarm/list";	//5���� ��ȯó�� 
		        return "redirect:/user/myPage/message/list";
		        return "redirect:/user/myPage/scrap/list";
		        return "redirect:/user/myPage/club/list";
		        return "redirect:/user/myPage/post/list";
		       
			}	
			
	    	request.setAttribute("user", user);		// ����� ���� ����				
			return "/user/myPage.jsp";				// ����� ���� ȭ������ �̵�
	    }
}
