package controller.club;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.UserManager;

public class RegisterClubController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        CommunityManager commManager = CommunityManager.getInstance();
        UserManager userManager = UserManager.getInstance();    

        String clubId =request.getParameter("clubId");
        String userId = UserSessionUtils.getLoginUserId(session);
    
        userManager.registerClub(userId, clubId); // ���� �������� Ŭ�� ���� �߰�
        commManager.insertUser(userId, clubId); //Ŭ�� �������� ���� ���� �߰�
        
        return "redirect:/community/club/clubHome.jsp?clubId=" + clubId;     //Ŭ�� ����Ʈ�� �̵�
    }
}
