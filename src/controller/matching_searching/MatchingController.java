package controller.matching_searching;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.UserNotFoundException;
import service.CommunityManager;
import service.UserManager;
import service.dto.ClubDTO;
import service.dto.UserDTO;

public class MatchingController implements Controller{

	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {		
	        HttpSession session = request.getSession(); 	
	    	// 로그인 여부 확인
	    	if (!UserSessionUtils.hasLogined(session)) {
	            return "redirect:/user/login/form";		// login form 요청으로 redirect
	        }
            String userId = UserSessionUtils.getLoginUserId(session);
	    	String type = request.getParameter("type");

            CommunityManager commMan = CommunityManager.getInstance();
            UserManager userMan = UserManager.getInstance();
            UserDTO user = userMan.findUser(userId);
	    	
	    	if (type.equals("mbti")) {
	        	String mbti = user.getMbti();
	        	List<ClubDTO> clubList = (List<ClubDTO>) commMan.findClubListbyMBTI(mbti); //입력받은 keyword로 club 검색
	        	request.setAttribute("clubList", clubList);	
	        	
	            return "/matching/result.jsp";
	    	}
	    	else if (type.equals("region")) {
                String region = user.getRegion();
                List<ClubDTO> clubList = (List<ClubDTO>) commMan.findClubListbyRegion(region); //입력받은 keyword로 club 검색
                request.setAttribute("clubList", clubList); 

                return "/matching/result.jsp";
            }
	    	else {
	            return "/matching.jsp";
	    	}
	    }
}
