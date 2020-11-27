package controller.matching_searching;

import java.util.List;  

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.dto.ClubDTO;
import service.dto.PostDTO;

public class SearchController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// 로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	String searchWord = request.getParameter("keyword");

        CommunityManager manager = CommunityManager.getInstance();
        List<ClubDTO> clubList = (List<ClubDTO>) manager.searchClubList(searchWord); //입력받은 keyword로 club 검색
        request.setAttribute("clubList", clubList); 
        List<PostDTO> postList = (List<PostDTO>) manager.searchPostList(searchWord); //입력받은 keyword로 post 검색
        request.setAttribute("postList", postList); 
            
        return "/search/result.jsp";
    }
}

