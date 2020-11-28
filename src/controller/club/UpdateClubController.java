package controller.club;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.dto.ClubDTO;

public class UpdateClubController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
        HttpSession session = request.getSession();
        
        CommunityManager commManager = CommunityManager.getInstance();
        String clubId =request.getParameter("clubId");
        ClubDTO club = commManager.findClub(clubId);// �����Ϸ��� Ŭ�� ���� �˻�
        String chairId = club.getChairId();

        if (!UserSessionUtils.isLoginUserCommAdmin(session) &&  //�α����� ����ڰ� Ŀ�´�Ƽ �����ڰ� �ƴϰ� 
            !UserSessionUtils.isLoginUser(chairId, session)) { // Ŀ�´�Ƽ�� ��ڵ� �ƴ� ���

            request.setAttribute("exception", new IllegalStateException("Ŭ�� ��ڰ� �ƴѰ�� Ŭ�� ������ ������ �� �����ϴ�."));
            return "/community/club/clubHome.jsp?clubId=" + clubId; //Ŭ�� Ȩ���� �̵�
        }
        
        // 1) GET request: Ŭ�� ���� form ��û    
		if (request.getMethod().equals("GET")) {	
			request.setAttribute("club", club);
			return "/community/club/updateForm.jsp";   // �˻��� ������ update form���� ����     
	    }	

		// 2) POST request (Ŭ�� ������ parameter�� ���۵�)
        club = new ClubDTO(
            request.getParameter("name"), 
//            request.getParameter("category"),
            request.getParameter("region"),
            Integer.parseInt(request.getParameter("maxNumOfMembers")));     

		commManager.updateClub(club);			
        return "redirect:/community/club/list";			
    }
}
