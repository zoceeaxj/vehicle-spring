<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style type="text/css">
.tcenter {
	display: block;
	margin-left: auto;
	margin-right: auto;
}
</style>

<script>
	function clearMsg() {
		document.getElementById("emessage").innerHTML = "";
		document.getElementById("studentImage").src = "img/student.png";
	}

	//function 
	function validateLogin() {
		//string
		var uname = document.getElementById("username").value;
		if (uname.length == 0) {
			//document.getElementById("emessage") - span object
			document.getElementById("emessage").innerHTML = "Username cannot be empty";
			//document.getElementById("studentImage") - image object
			document.getElementById("validateImage").src = "img/validate.jpg";
			return;
		}
		var pass = document.getElementById("password").value;
		if (pass.length == 0) {
			document.getElementById("emessage").innerHTML = "Password cannot be empty";
			document.getElementById("validateImage").src = "img/validate.jpg";
			return;
		}
		//submitting form manually using JavaScript
		document.authform.submit();
	}
</script>

<title>Login</title>
</head>
<body>
	<header style="background-color: #03a9f4; height: 5px;"> </header>

	<div class="container">
		<img
			src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSzz2uNXJavS9gzGLjSmaT4Yq5984QYjPRTBu2yUVq9S9jYGdtB&usqp=CAU.jpg"
			class="left" style="height: 150px;">

		<div class="tcenter" style="width: 50%">

			${hmmmm}

			<form class="text-center border border-light p-5" name="authform"
				action="auth" method="post">

				<p class="h4 mb-4">Login</p>

				<div class="tcenter" style="width: 50%">

					<span id="emessage"
						style="color: blue; font-size: 16px; font-weight: bold;"> </span>


					<!-- user name -->
					<input type="text" class="form-control" placeholder="username"
						name="username" id="username" onkeyup="clearMsg();"> <br />

					<!-- Password -->

					<input type="password" class="form-control" placeholder="password"
						name="password" id="password" onkeyup="clearMsg();"> <br />

					<!-- Sign in button -->
					<button class="btn btn-info" type="button"
						onClick="validateLogin();">Sign in</button>

					<!-- Sign up button -->
					<a href="${pageContext.request.contextPath}/signup"><button
							type="button" class="btn btn-info">Sign Up</button></a> <br /> <br />

					<!-- Forget password button -->
					<a href="${pageContext.request.contextPath}/fpassword">
						<button class="btn btn-danger" type="button">Forget
							Password</button>
					</a> <br />
					<br />

					<!-- Sign up button -->
					<a href="${pageContext.request.contextPath}/isignup"><button
							type="button" class="btn btn-info">Sign Up Image</button></a> <br />
					<br />

				</div>
			</form>
		</div>
	</div>

</body>
</html>