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

<title>Sign Up</title>
</head>
<body>
	<header style="background-color: #03a9f4; height: 5px;"> </header>

	<div class="container">
		<img src="img/Signup.jpg" class="left" style="height: 145px;">

		<div class="tcenter" style="width: 50%">

			<form class="text-center border border-light p-5" action="usignup"
				method="post">

				<p class="h4 mb-4">Sign up</p>

				<div class="tcenter" style="width: 50%">

					<!-- name  -->
					<input type="text" class="form-control" placeholder="Name"
						name="name"> <br />

					<!-- E-mail -->
					<input type="email" class="form-control mb-4" placeholder="Email"
						name="email">

					<!-- Degree -->
					<select type="text" class="form-control"
						placeholder="Qualification" name="qualification">
						<option>Qualification</option>
						<option>High School</option>
						<option>Bachelors</option>
						<option>Masters</option>
						<option>PhD</option>
					</select> <br />

					<!-- Mobile -->
					<input type="number" class="form-control" placeholder="Mobile"
						name="mobile"> <br />

					<!-- Image -->
					<input type="text" class="form-control" placeholder="Photo"
						name="photo"> <br />
					<!-- Gender -->

					<select type="text" class="form-control" placeholder="Gender"
						name="gender">
						<option>Gender</option>
						<option>Male</option>
						<option>Female</option>
					</select> <br />

					<!-- Sign up button -->
					<button class="btn btn-info" type="submit">Register</button>

					<a href="${pageContext.request.contextPath}/auth">
						<button type="button" class="btn btn-info">Sign In</button>
					</a> <br /> <br />



				</div>


			</form>
		</div>
</body>
</html>