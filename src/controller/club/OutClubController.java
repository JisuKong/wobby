package controller.club;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.UserManager;
import service.dto.ClubDTO;

public class OutClubController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CommunityManager commManager = CommunityManager.getInstance();
        UserManager userManager = UserManager.getInstance();    
        HttpSession session = request.getSession(); 
        
        String clubId = request.getParameter("clubId");
        ClubDTO club = commManager.findClub(clubId);
        String chairId = club.getChairId();
        String userId = UserSessionUtils.getLoginUserId(session);
    
        if (!(UserSessionUtils.isLoginUser(chairId, session))){ //�α����� ����ڰ� Ŀ�´�Ƽ ��ڰ� �ƴѰ��
            userManager.outClub(userId, clubId); // ���� �������� Ŭ�� ���� ����
            commManager.removeMember(userId, clubId); //Ŭ�� �������� ���� ���� ����
            return "redirect:/community/club/list";     //Ŭ�� ����Ʈ�� �̵�
        }
        
        /* ������ �Ұ����� ��� */
        request.setAttribute("clubId", clubId);                     
        request.setAttribute("deleteFailed", true); 
        request.setAttribute("exception", new IllegalStateException("����� ��� Ŭ���� Ż���� �� �����ϴ�."));            
        return "/community/club/clubHome.jsp?clubId=" + clubId;
    }
}
