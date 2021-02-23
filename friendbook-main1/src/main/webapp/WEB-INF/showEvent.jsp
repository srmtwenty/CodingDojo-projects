<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pt" uri="http://ocpsoft.org/prettytime/tags" %>

<%@include file="header.jsp"%>

<div class="row">
	<div class="col-sm-12">
	<h1><c:out value="${event.eventname}"/></h1>
	
	<p><b>Posted By:</b> <c:out value="${event.host.name}"/></p>
	<p><b>Date:</b> <c:out value="${event.date}"/></p>
	<p><b>Location:</b> <c:out value="${event.location}"/></p>
	<p><b>Attendee:</b></p>
	<ul>
		<c:forEach items="${event.attendees}" var="attendee">
		<li><c:out value="${attendee.name}"/></li>
		</c:forEach>
	</ul>
	
	<div class="mt-3">
		<a class="btn btn-primary" href="/events/${event.id}/edit"><i class="far fa-edit"></i> Edit</a>
		<a class="btn btn-secondary" href="/events"><i class="fas fa-arrow-left"></i> Back to Event List</a>
	</div>
	
	</div>
</div>
<%@include file="footer.jsp"%>