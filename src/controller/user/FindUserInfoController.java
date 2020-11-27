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
	 
			
		   //ID 찾기
	    	if (request.getParameter("userId").equals("")) {	//?
	    		
	    		String email = request.getParameter("email"); //email로 id찾기
	    		
	    		UserManager manager = UserManager.getInstance();
				String userId = manager.findId(email);	//  사용자id 정보 검색
				request.setAttribute("user", userId);			

				HttpSession session = request.getSession(); 
				
				// else (수정 불가능한 경우) 사용자 보기 화면으로 오류 메세지를 전달
				request.setAttribute("Failed", true);
				request.setAttribute("exception", 
						new IllegalStateException("아이디를 찾을 수 없습니다."));            
				return "/user/findUseInfo/findId/success";	// 사용자 보기 화면으로 이동 (forwarding)
		    }	
	    	
	    	// PW 찾기
	    	String userId = request.getParameter("userId"); //id로 pw 찾기
    		
    		UserManager manager = UserManager.getInstance();
			String userPw = manager.findPw(userId);	//  사용자id 정보 검색
			request.setAttribute("user", userPw);			

			HttpSession session = request.getSession(); 
			
			// else (수정 불가능한 경우) 사용자 보기 화면으로 오류 메세지를 전달
			request.setAttribute("Failed", true);
			request.setAttribute("exception", 
					new IllegalStateException("비밀번호를 찾을 수 없습니다."));            
			return "/user/findUseInfo/findPw/success";	// 사용자 보기 화면으로 이동 (forwarding)	
	    }
	
}
