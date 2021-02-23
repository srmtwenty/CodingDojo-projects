<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pt" uri="http://ocpsoft.org/prettytime/tags" %>

<%@include file="header.jsp"%>

<div class="row">
	<div class="col-sm-8">
	<h1>Create a new Event</h1>
	
	<form:form action="/events" method="POST" modelAttribute="event">
	
		<div class="form-group row">
			<form:label path="eventname" cssClass="col-sm-3 col-form-label">Event Name:</form:label>
			<div class="col-sm">
				<form:input path="eventname" cssClass="form-control" cssErrorClass="form-control is-invalid" />
				<form:errors path="eventname" element="div" cssClass="invalid-feedback" />
			</div>
		</div>
		<div class="form-group row">
			<form:label path="date" cssClass="col-sm-3 col-form-label">Date:</form:label>
			<div class="col-sm">
				<form:input path="date" type="date" cssClass="form-control" cssErrorClass="form-control is-invalid" />
				<form:errors path="date" element="div" cssClass="invalid-feedback" />
			</div>
		</div>
		<div class="form-group row">
			<form:label path="location" cssClass="col-sm-3 col-form-label">Location:</form:label>
			<div class="col-sm">
				<form:input path="location" cssClass="form-control" cssErrorClass="form-control is-invalid" />
				<form:errors path="location" element="div" cssClass="invalid-feedback" />
			</div>
		</div>
		<div class="form-group row">
			<div class="offset-sm-3 col-sm">
				<button type="submit" class="btn btn-primary"><i class="far fa-calendar-alt"></i> Create Event</button>
				<a href="/events" class="btn btn-secondary"><i class="fas fa-arrow-left"></i> Back to Event List</a>
			</div>
		</div>
	</form:form>
	
	</div>
</div>
<%@include file="footer.jsp"%>