package controller.scrap;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.User;

public class ListScrapController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// 로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
		UserManager manager = UserManager.getInstance();
		String scrap_no = request.getParameter("scrap_no");

		List<Scrap> ScrapList = null;
    	try {
    		ScrapList = manager.findScrap(scrap_no);	// 스크랩 검색
		} catch (UserNotFoundException e) {				
	        return "/user/myPage/scrapList.jsp";
		}	
    }
}


