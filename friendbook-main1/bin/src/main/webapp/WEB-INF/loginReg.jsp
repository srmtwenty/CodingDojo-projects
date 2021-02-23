<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Page</title>
<link rel="stylesheet" href="/css/bootstrap.css" />
<link rel="stylesheet" href="/css/style.css" />

</head>
<body>
	<div id="wrapper">

		<div id="bg-register" class="row">
			<div class="col mx-2">
				<h2 class="text-center">Register</h2>

				<div class="form-group row">
					<p class="col text-danger">
						<form:errors path="registerUser.*" />
					</p>
				</div>
				<form:form method="POST" action="/register" modelAttribute="registerUser">
					<div class="form-group row ">
						<form:label path="name"
							cssClass="col-sm-4 col-form-label">Name:</form:label>
						<div class="col-sm">
							<form:input path="name" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group row">
						<form:label path="email" cssClass="col-sm-4 col-form-label">Email:</form:label>
						<div class="col-sm">
							<form:input type="email" path="email" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group row">
						<form:label path="password"
							cssClass="col-sm-4 col-form-label">Password:</form:label>
						<div class="col-sm">
							<form:password path="password" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group row">
						<form:label path="passwordConfirmation"
							cssClass="col-sm-4 col-form-label">Password Conf:</form:label>
						<div class="col-sm">
							<form:password path="passwordConfirmation"
								cssClass="form-control" />
						</div>
					</div>
					<div class="form-group row">
						<div class="col-sm text-center ">
							<button type="submit" class=" btn-linear  btn-success">Register</button>
						</div>
					</div>
				</form:form>
			</div>
			<div class="col-v-divider"></div>
			<div class="col mx-2">
				<h2 class="text-center">Login</h2>

				<div class="form-group row">
					<p class="col text-danger">
						<form:errors path="loginUser.*" />
					</p>
				</div>
				<form:form method="POST" action="/login" modelAttribute="loginUser">
					<div class="form-group row">
						<form:label path="email" cssClass="col-sm-3 col-form-label">Email:</form:label>
						<div class="col-sm">
							<form:input type="email" path="email" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group row">
						<form:label path="password"
							cssClass="col-sm-3 col-form-label">Password:</form:label>
						<div class="col-sm">
							<form:password path="password" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group row">
						<div class="col-sm text-center ">
							<button type="submit" class=" btn-linear  btn-success">Login</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>