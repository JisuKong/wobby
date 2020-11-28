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
<title>클럽 생성</title>
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
		</div>
	</nav>
	<div class="col-xs-6 col-sm-4 py-5 container">
		<form class="form-signin">
			<h2 class="pb-2 text-center form-signin-heading">클럽 만들기</h2>
			<div class="form-group">
				<label for="inputName">클럽 이름</label> <input type="text"
					class="form-control" id="clubTitle">
			</div>
			<div class="form-group">
				<label for="inputName">클럽 최대 인원 수</label>
				<div class="row">

					<select name="maxOfmembers">
						<option value="10">10</option>
						<option value="20">20</option>
						<option value="30" selected="selected">30</option>
						<option value="40">40</option>
						<option value="50">50</option>
					</select>
				</div>

			</div>
			<div class="pt-2 form-group">
				<label for="selectHobby"><i class="fab fa-diaspora"></i> 클럽
					카테고리 <small> <br>클럽 카테고리를 선택하세요. <br></small></label>
				<div class="btn-group btn-group-justified pt-1 btn-block"
					data-toggle="buttons-checkbox">
					<button type="button" class="btn btn-light">
						<i class="fas fa-futbol"></i><br> <small>운동</small>
					</button>
					<button type="button" class="btn btn-light">
						<i class="fas fa-book-reader"></i><br> <small>독서</small>
					</button>
					<button type="button" class="btn btn-light">
						<i class="fas fa-palette"></i><br> <small>미술</small>
					</button>
					<button type="button" class="btn btn-light">
						<i class="fas fa-language"></i><br> <small>어학</small>
					</button>
					<button type="button" class="btn btn-light">
						<i class="fas fa-pizza-slice"></i><br> <small>요리</small>
					</button>
					<button type="button" class="btn btn-light">
						<i class="fas fa-gamepad"></i><br> <small>게임</small>
					</button>
					<button type="button" class="btn btn-light">
						<i class="fas fa-music"></i><br> <small>음악</small>
					</button>
					<button type="button" class="btn btn-light">
						<i class="fas fa-chart-line"></i><br> <small>재테크</small>
					</button>
					<button type="button" class="btn btn-light">
						<i class="fas fa-icons"></i><br> <small>기타</small>
					</button>
				</div>
			</div>
			<div class="pt-2 form-group">
				<label for="selectHobby"><i class="fab fa-diaspora"></i> 지역
					선택<small> <br>클럽의 활동 지역을 선택하세요. <br>
				</small></label>
				<div class="row">
					<div class="col-md-4">
						<input type="text" class="form-control" id="InputYear"
							placeholder="시도">
					</div>
					<div class="col-md-4">
						<input type="text" class="form-control" id="InputYear"
							placeholder="시군구">
					</div>
					<div class="col-md-4">
						<input type="text" class="form-control" id="InputYear"
							placeholder="읍면동">
					</div>
				</div>
			</div>
			<div class="pt-2 pb-5">
				<button class="btn btn-lg btn-info btn-block" type="submit">생성</button>
			</div>
		</form>
	</div>
</body>
</html>
0bg61gb296