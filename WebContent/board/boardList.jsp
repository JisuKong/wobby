<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 부트스트랩 css 사용 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"/>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	
	<title>게시판 UI</title>
		
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
<table class="table">
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
			<th>스크랩 수</th>
		</tr>
	</thead>
	
	<tbody>
	
	<c:forEach var="post" items="${postList}">
		<tr>
			<td>
			 <a href="<c:url value='/community/post'>
			 			<c:param name='postId' value='${post.postId}' />
			 		 </c:url>"> 
			 </a>		
			 ${post.title} 
			</td>
		</tr>
		<tr>
			<td>
			${post.nickname}
			</td>
		</tr>
		<tr>
			<td>
			${post.postDate}
			</td>
		</tr>
		<tr>
			<td>
			${post.numOfView}
			</td>
		</tr>
		<tr>
			<td>
			${post.numOfScraps}
			</td>
		</tr>
	</c:forEach>
 	<tr>
			<td>1</td>
			<td>게시판 UI입니다.</td>
			<td>커뮤잉즈 </td>
			<td>2020.10.05</td>
			<td>100</td>
			<td>11</td>							
		</tr>
		<tr>
			<td>2</td>
			<td>게시판 UI입니다.</td>
			<td>커뮤잉즈 </td>
			<td>2020.10.1</td>
			<td>10</td>
			<td>1</td>							
		</tr>
		<tr>
			<td>3</td>
			<td>게시판 UI입니다.</td>
			<td>커뮤잉즈 </td>
			<td>2020.10.05</td>
			<td>1000</td>
			<td>210</td>							
		</tr>
		<tr>
			<td>4</td>
			<td>게시판 UI입니다.</td>
			<td>커뮤잉즈 </td>
			<td>2020.10.07</td>
			<td>800</td>
			<td>11</td>							
		</tr>

	</tbody>
</table>

	<hr/>
	<a href = "<c:url value='/community/post/create/form'/>" class="btn btn-primary pull-right">글쓰기</a>

	<div class="text-center">
		<ul class="pagination">
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
		</ul>
	</div>
</div>

<!--  부트스트랩 js 사용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.js"></script>

</body>
</html>