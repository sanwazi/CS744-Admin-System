<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>EMR Administrative Login Page</title>

	<!-- Bootstrap Core CSS -->
    <link href="/resources/css/theme/bootstrap-simplex.min.css" rel="stylesheet">
    <link href="/EMR_Admin/views/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/EMR_Admin/views/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/EMR_Admin/views/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/EMR_Admin/views/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script
	src="//netdna.bootstrapcdn.com/bootstrap/2.3.2/js/bootstrap.min.js"></script>


<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #333;
}

.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	margin-top: 100px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	background-color: #fff;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"], .form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}

#footer {
	text-align: center;
	color: #FFFFFF;
	margin-top: 100px;
}
</style>


<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<div class="container">
		<form class="form-signin"
			action="<c:url value="/j_spring_security_check"/>" method="post">
			<h2 class="form-signin-heading">Log in</h2>
			<input type="text" class="form-control" placeholder="Email address"
				autofocus name="j_username" /> <input type="password"
				class="form-control" placeholder="Password" name="j_password" />
			<c:if test="${error}">
				<div class="alert alert-danger">login failed.</div>
			</c:if>
			<button id="sub" class="btn btn-lg btn-primary btn-block"
				type="submit">Log in</button>
		</form>

	</div>
	<!-- /container -->
	<div class="footer" id="footer">
		<p>
			EMR by <a href="https://github.com/alvinvinvinvin"> @Han,</a> <a
				href="http://robin-xueyufan.com/"> @Robin,</a> <a
				href="https://github.com/sanwazi"> @Bo</a>
		</p>

	</div>
	
	<!-- Bootstrap core JavaScript
    ================================================== -->
        <!-- jQuery -->
    <script src="/EMR_Admin/views/bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/EMR_Admin/views/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/EMR_Admin/views/bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/EMR_Admin/views/dist/js/sb-admin-2.js"></script>
	<!-- Placed at the end of the document so the pages load faster -->
	<!--  -->
    <!-- jQuery -->
	<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="../resources/jquery-cookie/jc/jquery.cookie.js"></script>
	<!-- Bootstrap -->
	
	<script src="//netdna.bootstrapcdn.com/bootstrap/2.3.2/js/bootstrap.min.js"></script>
    

    
</body>
</html>

