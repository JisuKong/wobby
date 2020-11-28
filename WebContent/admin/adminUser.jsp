<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>사용자 관리 페이지</title>

    <!-- Bootstrap core CSS -->
<!--     <link href="../../dist/css/bootstrap.min.css" rel="stylesheet"> -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />
    

    <!-- Custom styles for this template -->
    <link href="dashboard.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          
          <a class="navbar-brand" href="#">사용자 관리자 페이지</a>
        </div>
 
        </div>
    </nav>


    <div class="container-fluid">
      <div class="row">

          <h2 class="sub-header">사용자 관리</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>PW</th>
                  <th>닉네임</th>
                  <th>가입일</th>
                  <th>레벨</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>kuku00</td>
                  <td>1234</td>
                  <td>김크크</td>
                  <td>2020.09.01</td>
                  <td>관리자</td>
                </tr>
              </tbody>
            </table>
            <hr>
            <h5 class="sub-header"> &nbsp 멤버쉽</h5>
             <table class="table table-striped">
              <thead>
                  <tr>
                  	<th>결제 내역</th>
                  	<th>내용</th>
                  	<th>결제액</th>
                  	<th>결제 정보</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>2020.09.01</td>
                  <td>멤버쉽 가입</td>
                  <td>15,000원</td>
                  <td>Master Card 19083*****</td>
                </tr>
                <tr>
                  <td>2020.08.01</td>
                  <td>멤버쉽 가입</td>
                  <td>15,000원</td>
                  <td>Master Card 19083*****</td>
                </tr>
                <tr>
                  <td>2020.07.01</td>
                  <td>멤버쉽 가입</td>
                  <td>15,000원</td>
                  <td>Master Card 19083*****</td>
                </tr>
              </tbody>
            </table>
            
            <hr>
            <h5 class="sub-header"> &nbsp 가입한 클럽</h5>
             <table class="table table-striped">
              <thead>
                  <tr>
                  	<th>클럽명</th>
                  	<th>카테고리</th>
                  	<th>가입일</th>
                  	<th>관리</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>커뮤잉즈</td>
                  <td>IT</td>
                  <td>2020.09.01</td> <td><button type="button" class="btn btn-default">관리</button></td>
                </tr>
                
              </tbody>
            </table>
          </div>
         
        </div>
         <button type="button" class="btn btn-default pull-right">쪽지 보내기</button>
           <button type="button" class="btn btn-default pull-right">사용자 삭제</button>
        <!--    <button type="button" class="btn btn-default pull-right">관리</button> -->
      </div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>