<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title></title>
</head>
<body class="pt-5">
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">Wobby</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link"
						href="ClubHome.html">Club<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="WobbyHome.html">홈</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">매칭</a></li>
					<li class="nav-item"><a class="nav-link" href="MyPage.html">마이페이지</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Main jumbotron for a primary marketing message or call to action -->

	<div class="jumbotron">
		<div class="container">
			<h2>
				<b>${club.title}</b>
			</h2>
		</div>
	</div>
	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<!-- Blog Entries Column -->
			<div class="col-md-8">
				<!-- Blog Post -->
				<div class="card mb-4">

					<div class="card-body">
						<h2 class="card-title">게시판</h2>

						<table class="table">
							<thread>
							<tr>
								<th>ë²í¸</th>
								<th>ì ëª©</th>
								<th>ìì±ì</th>
								<th>ë ì§</th>
								<th>ì¡°íì</th>
								<th>ì¤í¬ë© ì</th>
							</tr>
							</thread>
							<tbody>
								<tr>
									<td>1</td>
									<td>ê²ìí UIìëë¤.</td>
									<td>ì»¤ë®¤ìì¦</td>
									<td>2020.10.05</td>
									<td>100</td>
									<td>11</td>
								</tr>
								<tr>
									<td>2</td>
									<td>ê²ìí UIìëë¤.</td>
									<td>ì»¤ë®¤ìì¦</td>
									<td>2020.10.1</td>
									<td>10</td>
									<td>1</td>
								</tr>
								<tr>
									<td>3</td>
									<td>ê²ìí UIìëë¤.</td>
									<td>ì»¤ë®¤ìì¦</td>
									<td>2020.10.05</td>
									<td>1000</td>
									<td>210</td>
								</tr>
								<tr>
									<td>4</td>
									<td>ê²ìí UIìëë¤.</td>
									<td>ì»¤ë®¤ìì¦</td>
									<td>2020.10.07</td>
									<td>800</td>
									<td>11</td>
								</tr>
							</tbody>
						</table>

					</div>

				</div>

				<!-- Pagination -->
				<ul class="pagination justify-content-center mb-4">
					<li class="page-item disabled"><a class="page-link" href="#">í´ë½
							íí´íê¸°</a></li>
				</ul>
			</div>

			<!-- Sidebar Widgets Column -->
			<div class="col-md-4">

				<!-- Search Widget -->
				<div class="card my-4">
				<h5 class="card-header">${club.title}</h5>
					<div class="card-body">
						<div class="input-group">
							<ul class="list-unstyled mb-0">
								<li>í´ë½ì¥: ì±ë²ë </li>
								<li>ì¹´íê³ ë¦¬: ëì/í ë¡ </li>
								<li>ììì¼: 2020.01.24</li>
								<li>íì ì: 20ëª</li>
								<li>ì§ì­: ê²½ê¸°ë ìì ë¶ì</li>
							</ul>
						</div>
					</div>
				</div>

				<!-- Categories Widget -->
				<div class="card my-4">
					<h5 class="card-header">ê²ìí</h5>
					<div class="card-body">
						<div class="row">
							<div class="col-lg-6">
								<ul class="list-unstyled mb-0">
									<li><a href="#">ê³µì§ì¬í­</a></li>
									<li><a href="#">ìì ê²ìí</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>

				<!-- Side Widget -->


			</div>

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->

	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; WOBY 2020</p>
		</div>
		<!-- /.container -->
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="resources/vendor/jquery/jquery.min.js"></script>
	<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
