<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- this is to use JSTL  -->
<!DOCTYPE html>
<html>
<head>
<title>Profiles</title>
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
<script src="https://www.w3schools.com/lib/w3.js"></script>
<script
	src="https://www.kryogenix.org/code/browser/sorttable/sorttable.js"></script>

<!-- JavaScript -->

<script type="text/javascript">
	//jQuery Ready Hander
	$(document).ready(function() {
		$("#imgInp").change(function() {
			readURL(this);
		});
	});

	//Code which is used to preview the image 
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#ppimage').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]); // convert to base64 string
		}
	}

	function openModal(username, email) {
		var imgURL = "${pageContext.request.contextPath}/load/image?username="
				+ username;
		
		//This line is showing image on the fly on modal
		$("#pimage").attr("src", imgURL);
		
		//Setting email id of the user on modal as label
		$("#pemail").html(email);

		//This I need to edit the image when data is sent to the server from the client 
		//Setting email id as a hidden field inside modal form
		$("#username").val(username);
		//Open modal as per it;s ID
		$("#changeImageModel").modal("show");

	}
</script>


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
	<header style="height: 30px; background-color: #21c9ff;"> </header>

	<div class="container">
		<img
			src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQu-Iy1-YbY4zzESjDdhd-eE5XFMdHaF-VDgqw-bDipibdZfiTi&usqp=CAU.png"
			style="height: 150px"> <br />

		<div class="row">
			<div class="col text-center">
				<div class="col text-center">


					<button type="button" class="btn btn-primary">Manage Users</button>

					<button type="reset" class="btn btn-danger">Email</button>

					<a href=Profiles>
						<button type="button" class="btn btn-success">Profiles</button> <a
						href=iprofiles>
							<button type="button" class="btn btn-success">IProfiles</button>
					</a>

					</a> <a href="loggedUser">
						<button type="button" class="btn btn-primary">Logged in
							User</button>
					</a> </a> <a href="LogOut">
						<button type="button" class="btn btn-warning">Logout</button>
					</a>

					<hr />
					<form action="searchProfile">
						<input type="text" name="search" class="form-control mb-4"
							placeholder="Search" style="width: 25%; display: inline;" />
						<button type="submit" class="btn btn-primary">Go</button>
						<a href="Profiles">
							<button type="button" class="btn btn-primary">Clear</button>
						</a>
					</form>

					<form action="filterProfile">
						<select class="form-control" placeholder="Filter"
							name="filterText" style="width: 25%; display: inline;">
							<option>Select Filter</option>

							<c:forEach items="${listoptions}" var="toption">
								<option>${toption}</option>
							</c:forEach>
						</select>
						<button type="submit" class="btn btn-primary"
							style="margin-top: -4px;">Go</button>


					</form>
					<hr />


					<table id="profile"
						class="table table-bordered, table table-striped">

						<thead>

							<tr>
								<th>S.NO</th>
								<th>Username</th>
								<th>Password</th>
								<th>Name</th>
								<th>Email <a href="sortProfile?sort=asc"> <img
										src="img/asc1.1.png" style="width: 15px; height: 15px;"></a>
									<a href="sortProfile?sort=desc"> <img src="img/des.png"
										style="width: 15px; height: 15px;"></a>
								</th>
								<th>Qualification</th>
								<th>Mobile</th>
								<th>Photo</th>
							<!-- 	<th>Action</th> -->
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${profileDTOs}" var="profileDTO" varStatus="cc">
								<tr class="item">
									<td>${cc.count}</td>
									<td>${profileDTO.username}</td>
									<td>${profileDTO.password}</td>
									<td>${profileDTO.name}</td>
									<td>${profileDTO.email}</td>
									<td>${profileDTO.qualification}</td>
									<td>${profileDTO.mobile}</td>
									<td><img
										src="${pageContext.request.contextPath}/load/image?username=${profileDTO.username}"
										style="width: 120px; height: 128px;" class="zoom" /> 
										<a
										href="javascript:openModal('${profileDTO.username}','${profileDTO.email}');">
											<img src="${pageContext.request.contextPath}/img/edit1.png"width="30" height="30">
									</a></td>
									
								<%-- 	<a href="deleteProfile?username=${profileDTO.username}">
															<img src="img/Delete.png" style="height: 30px;" /> <a
															href="editProfile?username=${profileDTO.username}"> <img
																src="img/Edit.png" style="height: 30px;" />

														</a>
														</a> --%>

									<td>
										<!-- The Modal Code -->
										<div class="modal" id="changeImageModel">
											<div class="modal-dialog">
												<div class="modal-content">
													<form
														action="${pageContext.request.contextPath}/changeImage"
														method="post" enctype="multipart/form-data">
														<input type="hidden" name="username" id="username" />
														

														<!-- Modal Header -->
														<div class="modal-header">
															<h4 class="modal-title">Edit Profile Image</h4>
															<button type="button" class="close" data-dismiss="modal">&times;</button>
														</div>

														<!-- Modal body -->
														<div class="modal-body">
															<label>Email : <span id="pemail"
																style="font-size: 18px; font-weight: bold;"></span></label>
															<hr />
															<img src="" id="pimage" style="height: 100px;"> <img
																src="" id="ppimage" style="height: 100px;"> 
																<br/><br/>
																<input
																type="file" name="file" id="imgInp" class="form-control" />
														</div>

														<!-- Modal footer -->
														<div class="modal-footer">
															<button type="submit" class="btn btn-primary">Change
																Photo</button>
															<button type="button" class="btn btn-danger"
																data-dismiss="modal">Close</button>
														</div>
</form></div></div></div>
														
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>


				</div>

			</div>
			<br /> <br />

		</div>
	</div>

</body>
</html>