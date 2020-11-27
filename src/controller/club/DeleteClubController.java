package controller.club;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.dto.ClubDTO;

public class DeleteClubController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {   
        HttpSession session = request.getSession(); 
        CommunityManager commManager = CommunityManager.getInstance();     
        
        
        String clubId = request.getParameter("clubId");
        ClubDTO club = commManager.findClub(clubId);
        String chairId = club.getChairId();
    
        if ((UserSessionUtils.isLoginUserCommAdmin(session)) //�α����� ����ڰ� Ŀ�´�Ƽ �������� ���
               ||                                               // �Ǵ� 
            (!UserSessionUtils.isLoginUserCommAdmin(session) &&  //�α����� ����ڰ� Ŀ�´�Ƽ �����ڰ� �ƴϰ� 
              UserSessionUtils.isLoginUser(chairId, session))) { // Ŀ�´�Ƽ�� ����� ���
                
            commManager.removeClub(clubId);               // Ŭ�� ���� ����
            return "redirect:/community/club/list";     //Ŭ�� ����Ʈ�� �̵�
        }
        
        /* ������ �Ұ����� ��� */
        request.setAttribute("clubId", clubId);                     
        request.setAttribute("deleteFailed", true); 
        request.setAttribute("exception", new IllegalStateException("������ Ȥ�� ��ڰ� �ƴ� ��� Ŭ���� ������ �� �����ϴ�."));            
        return "/community/club/clubHome.jsp?clubId=" + clubId;
    }
}
