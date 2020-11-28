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
<title>My Club�</title>
</head>
<body class="pt-5">
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#"></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="#">클럽
							목록 보기 <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="createClub.html">새로운 클럽
							만들기</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<!-- Blog Entries Column -->
			<div class="col-md-8">

				<h6 class="my-4">내 클럽 목록</h6>

				<!-- Blog Post -->
				<div class="card mb-4">
					<img class="card-img-top"
						src="resources/img/book.JPG"
						alt="book.JPG">
					<div class="card-body">
						<h2 class="card-title">[독서] 독세바</h2>
						<p class="card-text">자유 독서 모임, 매주 월목 8시, 월회비 없음</p>
						<ul>
							<li>9.21 월</li>
							<li>9.24 목</li>
							<li>9.28 월</li>
						</ul>
						<a href="clubPage.html" class="btn btn-primary">세부 일정 확인</a>
					</div>
					<div class="card-footer text-muted">
						독서/토론 | 시작일 2020.01.24 | 회원 수 80명 | <a href="#">모임 탈퇴하기</a>
					</div>
				</div>

				<!-- Blog Post -->
				<div class="card mb-4">
					<img class="card-img-top"
						src="resources/img/ball.JPG"
						alt="ball.JPG">
					<div class="card-body">
						<h2 class="card-title">[러닝] 두런두런</h2>
						<p class="card-text">중급자 이상, 월 3회, 월회비 5000원</p>
						<ul>
							<li>9.12 토</li>
							<li>9.19 토</li>
							<li>9.26 토</li>
						</ul>
						<a href="#" class="btn btn-primary">세부 일정 확인</a>
					</div>
					<div class="card-footer text-muted">
						운동/조깅/러닝 | 시작일 2020.03.01 | 회원 수 15명 | <a href="#">모임 탈퇴하기</a>
					</div>
				</div>

				<!-- Blog Post -->
				<div class="card mb-4">
					<img class="card-img-top"
						src="resources/img/run.JPG"
						alt="run.JPG">
					<div class="card-body">
						<h2 class="card-title">[농구] 플레이볼</h2>
						<p class="card-text">초급자 환영, 매주 금요일 밤, 월회비 20000원
						<ul>
							<li>9.11 금</li>
							<li>9.18 금</li>
							<li>9.25 금</li>
							<li>10.2 금</li>
						</ul>
						</p>
						<a href="#" class="btn btn-primary">세부 일정 확인</a>
					</div>
					<div class="card-footer text-muted">
						운동/농구 | 시작일 2020.07.17 | 회원 수 20명 | <a href="#">모임 탈퇴하기</a>
					</div>
				</div>

				<!-- Pagination -->
				<ul class="pagination justify-content-center mb-4">
					<li class="page-item disabled"><a class="page-link" href="#">다음
							&rarr;</a></li>
				</ul>

			</div>

			<!-- Sidebar Widgets Column -->
			<div class="col-md-4">

				<!-- Search Widget -->
				<div class="card my-4">
					<h5 class="card-header">클럽 검색</h5>
					<div class="card-body">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="찾고 싶은 클럽을 검색하세요."> <span
								class="input-group-append">
								<button class="btn btn-secondary" type="button">검색</button>
							</span>
						</div>
					</div>
				</div>

				<div class="card my-4">
					<h5 class="card-header">지역 클럽 검색</h5>
					<div class="card-body">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="찾고 싶은 지역을 입력하세요."> <span
								class="input-group-append">
								<button class="btn btn-secondary" type="button">검색</button>
							</span>
						</div>
					</div>
				</div>

				<!-- Categories Widget -->
				<div class="card my-4">
					<h5 class="card-header">인기 카테고리</h5>
					<div class="card-body">
						<div class="row">
							<div class="col-lg-6">
								<ul class="list-unstyled mb-0">
									<li><a href="#">운동</a></li>
									<li><a href="#">독서</a></li>
									<li><a href="#">음악</a></li>
								</ul>
							</div>
							<div class="col-lg-6">
								<ul class="list-unstyled mb-0">
									<li><a href="#">요리</a></li>
									<li><a href="#">영상</a></li>
									<li><a href="#">영어</a></li>
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
	<script
		src="resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>
