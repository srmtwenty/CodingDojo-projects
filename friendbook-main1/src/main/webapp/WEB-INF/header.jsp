<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pt" uri="http://ocpsoft.org/prettytime/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${title}" default="Friendbook"/></title>
<link rel="stylesheet" href="/css/bootstrap.css" />
<link rel="stylesheet" href="/css/fontawesome-all.css" />
<link rel="stylesheet" href="/css/style.css" />

</head>
<body>
	<div id="wrapper" class="d-flex flex-column h-100">
		<header class="sticky-top">
		<nav class="navbar navbar-expand-md navbar-dark bg-primary p-2">
			<a class="navbar-brand p-0" href="/home"><img src="/img/logo-icon-sm.png" alt="" /></a>
			
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
			    <span class="navbar-toggler-icon"></span>
			  </button>				
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="/profile"><b>Welcome <c:out value="${loginUser.name}" /></b></a></li>
					<li class="nav-item active"><a class="nav-link" href="/home">Home</a></li>
					<li class="nav-item active"><a class="nav-link" href="/events">Events</a></li>
					<li class="nav-item active"><a class="nav-link" href="/logout">Logout</a></li>
				</ul>
			</div>
		</nav>
		</header>
		<main role="main" class="flex-shrink-0">
			<div class="container-fluid">
