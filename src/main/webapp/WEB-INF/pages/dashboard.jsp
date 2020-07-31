<!DOCTYPE html>
<html>
<head>
<title>Dash board</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>


<style>
.zoom {
	transition: transform .5s; /* Animation */
	margin: 0 auto;
}

.zoom:hover {
	transform: scale(3.0);
	/* (200% zoom - Note: if the zoom is too large, it will go outside of the viewport) */
}
</style>
</head>
<body>
	<header style="height: 30px; background-color: #21c9ff;">
		<b style="margin-left: 50px;">Coming soon </b>
	</header>

	<div class="container">
		<img
			src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQu-Iy1-YbY4zzESjDdhd-eE5XFMdHaF-VDgqw-bDipibdZfiTi&usqp=CAU.png"
			style="height: 150px"> <br />
		<div class="row">
			<div class="col text-center">
				<button type="button" class="btn btn-primary">Manage Users</button>

				<button type="reset" class="btn btn-danger">Email</button>

				<a href="Profiles">
					<button type="button" class="btn btn-success">Profiles</button>
					
					</a>
   				  	         <a href="iprofiles">
   				  	        <button type="button" class="btn btn-success">IProfiles</button>
				</a> <a href="loggedUser">
					<button type="button" class="btn btn-primary">Logged in
						User</button>
				</a> <a href="LogOut">
					<button type="button" class="btn btn-warning">Logout</button>
				</a> <br />
				<br>
				<h1>Dash Board</h1>
			</div>
		</div>

		<br />

		<table class="table table-bordered">

			<tr>
				<td>Name</td>
				<td>${userData.name}</td>
				<!-- changed magic to userdata -->
			</tr>

			<tr>
				<td>Email</td>
				<td>${userData.email}</td>
			</tr>

			<tr>
				<td>username</td>
				<td>${userData.username}</td>
			</tr>

			<tr>
				<td>Gender</td>
				<td>${userData.gender}</td>
			</tr>

			<tr>
				<td>Mobile</td>
				<td>${userData.mobile}</td>
			</tr>

			<tr>
				<td>Qualification</td>
				<td>${userData.qualification}</td>
			</tr>

			<tr>
				<td>Photo</td>
				<td><img src="${userData.photo}"
					style="width: 128px; height: 128px;" class="zoom"></td>
			</tr>

		</table>


	</div>



</body>
</html>