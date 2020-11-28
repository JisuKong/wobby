package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.alarm.*;
import controller.board.*;
import controller.club.*;
import controller.matching_searching.*;
import controller.message.*;
import controller.scrap.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
        mappings.put("/", new ForwardController("/index.jsp"));
        mappings.put("/home", new HomeController());
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/findUserInfo", new FindUserInfoController());
        mappings.put("/user/updatePw", new UpdatePwController());

        
        //matching & searching
        mappings.put("/matching", new MatchingController());
        mappings.put("/search", new SearchController());
        
        /*mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));
        mappings.put("/user/register", new RegisterUserController());
        mappings.put("/user/update", new UpdateClubController());
        mappings.put("/user/updateForm", new UpdateClubController());
        mappings.put("/user/delete", new DeleteClubController());
        mappings.put("/user/myPage", new ViewMyPageController());
        
        //message
        mappings.put("/user/myPage/message/send/form", new ForwardController("/user/myPage/message/sendForm.jsp"));
        mappings.put("/user/myPage/message/send", new SendMessageController());
        mappings.put("/user/myPage/message/delete", new DeleteMessageController());
        mappings.put("/user/myPage/message/list", new ListReceivedMessageController());
        mappings.put("/user/myPage/message/SentMessagelist", new ListSentMessageController());

        //alarm
        mappings.put("/user/myPage/Alarm/delete", new DeleteAlarmController());
        mappings.put("/user/myPage/Alarm/list", new ListAlarmController());

        //scrap
        mappings.put("/user/myPage/scrap/delete", new DeleteScrapController());
        mappings.put("/user/myPage/scrap/list", new ListScrapController());

        //board
        mappings.put("/community/post/create/form", new ForwardController("/community/post/postWrite.jsp"));
        mappings.put("/community/post/create", new CreatePostController());
        mappings.put("/community/post/delete", new DeletePostController());
        mappings.put("/community/board", new ListPostController());
        mappings.put("/community/postList", new ListPostController());
        mappings.put("/community/post/update", new UpdatePostController());
        mappings.put("/community/post/updateForm", new UpdatePostController());
        mappings.put("/community/post", new ViewPostController());

        //club
        mappings.put("/community/club/create/form", new ForwardController("/community/club/createForm.jsp"));
        mappings.put("/community/club/create", new CreateClubController());
        mappings.put("/community/club/delete", new DeleteClubController());
        mappings.put("/community/club/update", new UpdateClubController());
        mappings.put("/community/club/updateForm", new UpdateClubController());
        mappings.put("/community/club/register", new RegisterClubController());
        mappings.put("/community/club/clubHome", new ViewClubHomeController());
        mappings.put("/community/club/out", new OutClubController());
        mappings.put("/community/club/list", new ListClubController());
        mappings.put("/community/club/topList", new ListTopClubController());
        

        mappings.put("/admin/post", new ViewPostbyAdminController());
        mappings.put("/admin/club/clubHome", new ViewClubbyAdminController());
        */
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
        return mappings.get(uri);
    }
}
