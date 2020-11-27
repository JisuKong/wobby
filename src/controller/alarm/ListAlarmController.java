package controller.alarm;

import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import service.UserManager;

public class ListAlarmController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// 로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
		UserManager manager = UserManager.getInstance();
		String alarmlist_no = request.getParameter("alarmlist_no");

		List<Alarm> AlarmList = null;
    	try {
    		AlarmList = manager.findAlarm(alarmlist_no);	// 알람검색
		} catch (UserNotFoundException e) {				
	        return "/user/myPage/AlarmList.jsp";
		}	
    }
}


