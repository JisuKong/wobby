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
    	// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form ��û���� redirect
        }
    	String searchWord = request.getParameter("keyword");

        CommunityManager manager = CommunityManager.getInstance();
        List<ClubDTO> clubList = (List<ClubDTO>) manager.searchClubList(searchWord); //�Է¹��� keyword�� club �˻�
        request.setAttribute("clubList", clubList); 
        List<PostDTO> postList = (List<PostDTO>) manager.searchPostList(searchWord); //�Է¹��� keyword�� post �˻�
        request.setAttribute("postList", postList); 
            
        return "/search/result.jsp";
    }
}

