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
	    	// 로그인 여부 확인
	    	if (!UserSessionUtils.hasLogined(request.getSession())) {
	            return "redirect:/user/login/form";		// login form 요청으로 redirect
	        }
	    	
	    	/* 모델에 로그인 처리를 위임
	    				UserManager manager = UserManager.getInstance();
	    				manager.login(userId, password);
	    				*/
	    	
			UserManager manager = UserManager.getInstance();
			String userId = request.getParameter("userId");

	    	User user = null;
	    	try {
				user = manager.findUser(userId);	// 사용자 정보 검색
			} catch (UserNotFoundException e) {	
				
		        return "redirect:/user/myPage/Alarm/list";	//5개의 반환처리 
		        return "redirect:/user/myPage/message/list";
		        return "redirect:/user/myPage/scrap/list";
		        return "redirect:/user/myPage/club/list";
		        return "redirect:/user/myPage/post/list";
		       
			}	
			
	    	request.setAttribute("user", user);		// 사용자 정보 저장				
			return "/user/myPage.jsp";				// 사용자 보기 화면으로 이동
	    }
}
