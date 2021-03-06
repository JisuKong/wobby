package controller.club;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.CommunityManager;
import service.dto.ClubDTO;

public class ListTopClubController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
        CommunityManager commManager = CommunityManager.getInstance();
        List<ClubDTO> clubList = null;   
        
        String category = request.getParameter("category");
        clubList = commManager.findClubListbyCategory(category);
        
		// commList 객체를 request에 저장하여 커뮤니티 리스트 화면으로 이동(forwarding)
		request.setAttribute("clubList", clubList);				
		return "/community/club.jsp";        
    }
}
