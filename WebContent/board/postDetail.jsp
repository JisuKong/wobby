<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.*, persistence.dao.impl.PostDAOImpl" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 부트스트랩 css 사용 -->
    
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 부트스트랩 css 사용 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"/>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	
<script>
function userRemove() {
	return confirm("정말 삭제하시겠습니까?");		
}
</script>

<title>게시글 상세 확인</title>
</head>

<body>


<nav class="navbar navbar-default">

		<div class="navbar-header">

			<button type="button" class="navbar-toggle collapsed"

				data-toggle="collapse" data-target="bs-example-navbar-collapse-1"

				aria-expaned="false">

				<span class="icon-bar"></span> <span class="icon-bar"></span> <span

					class="icon-bar"></span>

			</button>
			<a class="navbar-brand" href="<c:url value='/community/board' />">게시판</a>

		</div>

		<div class="collapse navbar-collapse"

			id="#bs-example-navbar-collapse-1">

			<ul class="nav navbar-nav">

				<li><a href="main.jsp">메인</a></li>

				<li class="active"><a href="<c:url value='/community/board' />">게시판</a></li>

			</ul>
	
			<ul class="nav navbar-nav navbar-right">
			
				<li class="dropdown"><a href="#" class="dropdown-toggle"
				
					data-toggle="dropdown" role="button" aria-haspopup="true"
					
					aria-expanded="false">접속하기<span class="caret"></span></a>
					
					<ul class="dropdown-menu">
						<li><a href="<c:url value='/user/login/form' />">로그인</a></li>
						
						<li><a href="<c:url value='/user/register/form' />">회원가입</a></li>
					</ul>
				</li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
			
				<li class="dropdown"><a href="#" class="dropdown-toggle"
				
					data-toggle="dropdown" role="button" aria-haspopup="true"
					
					aria-expanded="false">회원관리<span class="caret"></span></a>
					
					<ul class="dropdown-menu">
					
						<li><a href="<c:url value='/user/logout' />">로그아웃</a></li>
					</ul>
				</li>
			</ul>

		</div>
</nav>
<div class="container">
	<div class="row">
	<form action="readAction" method="post">
	<table class="table table-striped"
					style="text-align: center; border: 1px solid #dddddd">
		<thead>
			<tr>
				<th colspan="2" style="background-color: #eeeeee; text-align: center;">게시글 상세 확인 </th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td> 
					title ${post.title} 
	 			</td>
			</tr>	
			<tr>
				<td>
					nickname : ${post.nicname}  postDate : ${post.postDate}  
					numOfView : ${post.numOfView} numOfScraps: ${post.numOfScraps} 								    
				</td>
			</tr>
			<tr>
				<td>
					<textarea class="form-control" placeholder="contents" name="bbsContent" maxlength="2048"
					 style="height: 350px;" readonly> contents ${post.contents} 	</textarea>
				</td>
			</tr>

		</tbody>
	</table>
	</form>
	</div>
	</div>
	
	<br>
	
  <a class="btn btn-primary pull-right"

   	href="<c:url value='/community/post/updateForm' >

    		     <c:param name='postId' value='${post.postId}'/>

		 	  </c:url>">수정</a>

   <a class="btn btn-primary pull-right"

  		href="<c:url value='/community/post/delete'>

		     	 <c:param name='postId' value='${post.postId}'/>

	 	      </c:url>" onclick="return userRemove();">삭제</a>

  	   
   
   <br>	  

	<!-- 수정 또는 삭제가  실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
	<c:if test="${updateFailed || deleteFailed}">
		<h6 class="text-danger"><c:out value="${exception.getMessage()}"/></h6>
   </c:if> 

	<br>
	
	 <!-- 댓글 부분 -->
    <div id="comment">
        <table class="table table-striped"
					style="text-align: center; border: 1px solid #dddddd">
    <!-- 댓글 목록 -->    
    <c:if test="${requestScope.commentList != null}">
        <c:forEach var="comment" items="${requestScope.commentList}">
            <tr>
                <!-- 아이디, 작성날짜 -->
                <td width="150">
                    <div>
                        <c:if test="${comment.comment_level > 1}">
                            &nbsp;&nbsp;&nbsp;&nbsp; <!-- 답변글일경우 아이디 앞에 공백을 준다. -->
                            <img src="img/reply_icon.gif">
                        </c:if>
                    
                        ${comment.comment_id}<br>
                        <font size="2" color="lightgray">${comment.comment_date}</font>
                    </div>
                </td>
                <!-- 본문내용 -->
                <td width="550">
                    <div class="text_wrapper">
                        ${comment.comment_content}
                    </div>
                </td>
                <!-- 버튼 -->
                <td width="100">
                    <div id="btn" style="text-align:center;">
                        <a href="#" onclick="cmReplyOpen(${comment.comment_num})">[답변]</a><br>
                    <!-- 댓글 작성자만 수정, 삭제 가능하도록 -->    
                    <c:if test="${comment.comment_id == sessionScope.sessionID}">
                        <a href="#" onclick="cmUpdateOpen(${comment.comment_num})">[수정]</a><br>    
                        <a href="#" onclick="cmDeleteOpen(${comment.comment_num})">[삭제]</a>
                    </c:if>        
                    </div>
                </td>
            </tr>
            
        </c:forEach>
    </c:if>
           
            <!-- 로그인 했을 경우만 댓글 작성가능 -->
            <c:if test="${sessionScope.sessionID !=null}">
            <tr bgcolor="#F5F5F5">
            <form id="writeCommentForm">
                <input type="hidden" name="comment_board" value="${board.board_num}">
                <input type="hidden" name="comment_id" value="${sessionScope.sessionID}">
                <!-- 아이디-->
                <td width="150">
                    <div>
                        ${sessionScope.sessionID}
                    </div>
                </td>
                <!-- 본문 작성-->
                <td width="550">
                    <div>
                        <textarea name="comment_content" rows="4" cols="70" ></textarea>
                    </div>
                </td>
                <!-- 댓글 등록 버튼 -->
                <td width="100">
                    <div id="btn" style="text-align:center;">
                        <p><a href="#" onclick="writeCmt()">[댓글등록]</a></p>    
                    </div>
                </td>
            </form>
            </tr>
            </c:if>
    
        </table>
    </div>
    
	
	<a href = "/community/board" class="btn btn-primary pull-right">목록</a>

<!--  부트스트랩 js 사용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.js"></script>

</body>
</html>
