<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="Icons/favicon.ico"/>
<title>Login</title>
<script src="jQuery/jquery-3.1.1.min.js"></script>

<link rel="stylesheet" type="text/css" href="BootStrap/css/bootstrap.min.css" />
<!-- <link rel="stylesheet" type="text/css" href="AppCSS/Login.css" /> -->
<link rel="stylesheet" type="text/css" href="AppCSS/Login.css" />

<script src="BootStrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<form class="form-signin" id="LoginForm" action="LoginAction" method="post">
			<h2 class="form-signin-heading">Please sign in</h2>
			<label for="inputEmail" class="sr-only">Email address</label> 
			<input type="email" id="Email" name="Email" class="form-control" placeholder="Email address" required autofocus> 
			<label for="inputPassword" class="sr-only">Password</label> 
			<input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me"> Remember me</label>
				<img src='kaptcha/getKaptchaImage.do' id="kaptchaImage" />
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		</form>

	</div>
</body>
</html>