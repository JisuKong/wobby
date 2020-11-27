package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.User;

public class FindUserInfoController implements Controller {

	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
	 
			
		   //ID ã��
	    	if (request.getParameter("userId").equals("")) {	//?
	    		
	    		String email = request.getParameter("email"); //email�� idã��
	    		
	    		UserManager manager = UserManager.getInstance();
				String userId = manager.findId(email);	//  �����id ���� �˻�
				request.setAttribute("user", userId);			

				HttpSession session = request.getSession(); 
				
				// else (���� �Ұ����� ���) ����� ���� ȭ������ ���� �޼����� ����
				request.setAttribute("Failed", true);
				request.setAttribute("exception", 
						new IllegalStateException("���̵� ã�� �� �����ϴ�."));            
				return "/user/findUseInfo/findId/success";	// ����� ���� ȭ������ �̵� (forwarding)
		    }	
	    	
	    	// PW ã��
	    	String userId = request.getParameter("userId"); //id�� pw ã��
    		
    		UserManager manager = UserManager.getInstance();
			String userPw = manager.findPw(userId);	//  �����id ���� �˻�
			request.setAttribute("user", userPw);			

			HttpSession session = request.getSession(); 
			
			// else (���� �Ұ����� ���) ����� ���� ȭ������ ���� �޼����� ����
			request.setAttribute("Failed", true);
			request.setAttribute("exception", 
					new IllegalStateException("��й�ȣ�� ã�� �� �����ϴ�."));            
			return "/user/findUseInfo/findPw/success";	// ����� ���� ȭ������ �̵� (forwarding)	
	    }
	
}
