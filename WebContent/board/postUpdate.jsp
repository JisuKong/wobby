<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.*, persistence.dao.impl.PostDAOImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
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
	
<title>게시글 수정</title>

<script>
	function updatePost() {
		if(form.bdTitle.value == "") {
			alert("Enter the title!");
			form.bdTitle.focus();
			return false;
		}
		
		form.submit();
	}
</script>
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
	<form name="form" method="post" action="<c:url value='/community/post/update'/>">
	
	<table class="table table-striped"
					style="text-align: center; border: 1px solid #dddddd">
		<thead>
			<tr>
				<th colspan="2" style="background-color: #eeeeee; text-align: center;">게시글 수정</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td> <input type="text" name="bdTitle" class="form-control mt-4 mb-2"
	 					placeholder="제목을 입력해주세요." value="${post.title}" required>	
	 			</td>
			</tr>	
			<tr>
				<td>
						<div class="form-group row">  
				        <label for="commId" class="col-lg-2 col-form-label">커뮤니티 or 게시판 목록?</label>
				        <!-- 화면 로드 시 서버로부터 커뮤니티 목록을 가져와 commSelect 메뉴 생성  -->
				        <div class="col-lg-10">
				        	<select id="commSelect" name="commId" class="form-control"> 
								<option value="0">없음</option>
								<c:forEach var="club" items="${clubList}">
									<option value="${club.name}"
										<c:if test="${club.clubId eq user.clubId}">selected="selected"</c:if>
										>${club.name}</option>
								</c:forEach>
							</select>
				        </div>
				  	  </div> 
				</td>
			</tr>
			<tr>
				<td>
					<textarea class="form-control" placeholder="글 내용" name="bbsContent" 
					maxlength="2048" style="height: 350px;"> ${post.contents} </textarea>
				</td>
			</tr>

		</tbody>
	</table>
	
	<a href = "<c:url value='/community/post' />" class="btn btn-primary pull-right">취소</a>
	<input type="button"  class="btn btn-primary pull-right" onClick="updatePost()" value="수정">
	</form>
	</div>
</div>
<!--  부트스트랩 js 사용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.js"></script>

</body>
</html>