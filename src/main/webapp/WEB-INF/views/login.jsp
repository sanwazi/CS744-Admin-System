<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>EMR Administrative System</title>

<!-- Bootstrap Core CSS -->
<link
	href="/EMR_Admin/views/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="/EMR_Admin/views/bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="/EMR_Admin/views/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="/EMR_Admin/views/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Please Sign In</h3>
					</div>
					<div class="panel-body">
						<form class="form-signin"
							action="<c:url value="/j_spring_security_check"/>" method="post">
							<h2 class="form-signin-heading">Sign in</h2>
							<input type="text" class="form-control"
								placeholder="User Name" autofocus name="j_username" /> 
							<input type="password" class="form-control" placeholder="Password"
								name="j_password" />
							<c:if test="${error}">
								<div class="alert alert-danger">login failed.</div>
							</c:if>
							<button id="sub" class="btn btn-lg btn-primary btn-block"
								type="submit">Sign in</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script
		src="/EMR_Admin/views/bower_components/jquery/dist/jquery.min.js"></script>
	<script 
		src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script 
		src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script 
		src="/EMR_Admin/views/js/ourjs/jquery.cookie.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="/EMR_Admin/views/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="/EMR_Admin/views/bower_components/metisMenu/dist/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script 
		src="/EMR_Admin/views/dist/js/sb-admin-2.js"></script>
	<script 
		src="//netdna.bootstrapcdn.com/bootstrap/2.3.2/js/bootstrap.min.js"></script>
	

</body>
<!-- 
	<form role="form">
		<fieldset>
			<div class="form-group">
				<input class="form-control" placeholder="E-mail"
				name="admin_account" type="email" autofocus>
			</div>
			<div class="form-group">
				<input class="form-control" placeholder="Password"
				name="admin_password" type="password" value="">
			</div>
			<button id="sub" type="submit"
			class="btn btn-lg btn-success btn-block">Login</button>
		</fieldset>
	</form>
     <a href="/EMR_Admin/views/pages/index.html" class="btn btn-lg btn-success btn-block">Login</a>
     <button>Log in</button>
-->
</html>
