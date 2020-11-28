 package controller.club;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.CommunityManager;
import service.dto.BoardDTO;
import service.dto.ClubDTO;

public class CreateClubController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String userId = UserSessionUtils.getLoginUserId(session);
        
        //�޾ƿ� ������ ���� club ��ü ����        
    	ClubDTO club = new ClubDTO(
    	        request.getParameter("name"), 
//    	        request.getParameter("category"),
                request.getParameter("region"),
    	        Integer.parseInt(request.getParameter("maxNumOfMembers"))
    	        );
    	club.setChairId(userId);
        
		try {
		    //ClubManager�� create ó�� ����
			CommunityManager commManager = CommunityManager.getInstance();
			int result = commManager.createClub(club); //clubId��ȯ�ϵ���
			
			if (result > 0) {
				BoardDTO board = new BoardDTO(club.getClubId());//���� ����� ���ÿ� ����
				commManager.createBoard(board); 
			}
			
//			//or
//			commManager.createBoard();
//			commManager.connectClub(clubId);
			
			/*//UserManager�� clubAdmin ó�� ���� > ȸ�ǰ�� ���?
            UserManager userManager = UserManager.getInstance();
            userManager.updateChair(clubId, );*/
	        return "redirect:/community/club/clubHome";	// ���� �� Ŀ�´�Ƽ ����Ʈ ȭ������ redirect
	        
		} catch (Exception e) {		// ���� �߻� �� �Է� form���� forwarding
            request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			return "/community/club/create.jsp";
		}
    }
}
