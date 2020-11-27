<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<!-- Optional JavaScript -->
<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
	integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<!-- for Icon -->
<script src="https://kit.fontawesome.com/cfb4151af4.js"
	crossorigin="anonymous"></script>
<title>WOBBY</title>
</head>
<body class="pt-5">
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="WobbyHome.jsp">WOBBY</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link"
						href="WobbyHome.jsp">Home<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="user/login.jsp">Login</a></li>
					<li class="nav-item"><a class="nav-link"
						href="user/MyPage.jsp">My page</a></li>
					<li class="nav-item"><a class="nav-link"
						href="club/ClubHome.jsp">Club</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="card-body">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="검색어를 입력하세요.">
				<span class="input-group-append">
					<button class="btn btn-secondary" type="button">Search</button>
				</span>
			</div>
		</div>
	</div>


	<!-- Page Content -->
	<div class="container">

		<!-- Jumbotron Header -->
		<header class="jumbotron my-4">
			<h1 class="display-3">WOBBY</h1>
			<h6>
				<p>여성들을 위한 취미 공유 플랫폼</p>
			</h6>
			<a href="login.jsp" class="btn btn-primary btn-lg">로그인</a> <a
				href="SignUp.jsp" class="btn btn-primary btn-lg">가입하기</a>
		</header>
	</div>

	<div class="container">
		<h5 class="card-header">게시판</h5>
		<div class="card-body">
			<div class="input-group">
				<span class="input-group-append">
					<button class="btn btn-secondary" type="button">그림</button> &nbsp;
					&nbsp;
				</span> <span class="input-group-append">
					<button class="btn btn-secondary" type="button">운동</button>
					&nbsp;&nbsp;
				</span> <span class="input-group-append">
					<button class="btn btn-secondary" type="button">요리</button>
					&nbsp;&nbsp;
				</span> </span> <span class="input-group-append">
					<button class="btn btn-secondary" type="button">독서</button>
					&nbsp;&nbsp;
				</span> </span> <span class="input-group-append">
					<button class="btn btn-secondary" type="button">캠핑</button> &nbsp;
					&nbsp;
				</span> </span> <span class="input-group-append">
					<button class="btn btn-secondary" type="button">IT</button> &nbsp;
					&nbsp;
				</span> <span class="input-group-append">
					<button class="btn btn-secondary" type="button">더보기</button>
					&nbsp; &nbsp;
				</span>
			</div>
		</div>
	</div>

	<!-- Page Features -->
	<div class="container">
		<div class="row text-center">

			<div class="col-lg-3 col-md-6 mb-4">
				<div class="card h-100">
					<div class="card-body">
						<h4 class="card-title">
							<b>당신의 지역을 찾아보세요.</b>
						</h4>
						<img class="card-img-top" src="img/map.JPG"
							alt="지도.JPG">
					</div>
					<div class="card-footer">
						<input type="text" class="form-control" placeholder="시/도">
						<input type="text" class="form-control" placeholder="시/군/구">
						<input type="text" class="form-control" placeholder="읍/면/동">
						<a href="#" class="btn btn-primary">검색</a>
					</div>
				</div>
			</div>

			<div class="col-lg-3 col-md-6 mb-4">
				<div class="card h-100">
					<div class="card-body">
						<h4 class="card-title">
							<b>실시간 인기클럽</b>
						</h4>
						<p class="card-text">실시간 인기 클럽을 알아보세요.</p>
						<img class="card-img-top" src="img/club.JPG"
							alt="img/club.JPG">
					</div>
					<div class="card-footer">
						<a href="club/ClubHome.jsp" class="btn btn-primary">클럽 더보기</a>
					</div>
				</div>
			</div>

			<div class="col-lg-3 col-md-6 mb-4">
				<div class="card h-100">
					<div class="card-body">
						<h4 class="card-title">
							<b>실시간 인기글</b>
						</h4>
						<p class="card-text">실시간 인기글을 알아보세요.</p>
						<img class="card-img-top" src="img/popularPosts.JPG"
							alt="img/popularPosts.JPG">
					</div>
					<div class="card-footer">
						<a href="#" class="btn btn-primary">실시간 인기글 더보기</a>
					</div>
				</div>
			</div>


		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->

	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; WOBBY 2020</p>
		</div>
		<!-- /.container -->
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>
