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
	<h1>All events</h1>
	
	<table class="table table-hover">
	    <thead>
		    <tr class="bg-dark text-light">
				<th scope="col">Name</th>
				<th scope="col">Date</th>
				<th scope="col">Location</th>
				<th scope="col">Host</th>
				<th scope="col" class="text-center">Action</th>
		    </tr>
	    </thead>
		<tbody class="table-sm">
			<c:forEach items="${events}" var="event">
			<tr>
				<td><a href="/events/${event.id}"><c:out value="${event.eventname}"/></a></td>
				<td><fmt:formatDate pattern="MMMM dd, yyyy" value="${event.date}" /></td>
				<td><c:out value="${event.location}"/></td>
				<td><c:out value="${event.host.name}"/></td>
				
				<td class="text-center">
					<c:choose>
						<c:when test="${loginUser.id == event.host.id}">
							<a class="btn btn-sm btn-outline-primary" href="/events/${event.id}/edit"><i class="far fa-edit"></i> Edit</a> 
							<a class="btn btn-sm btn-outline-danger" href="/events/${event.id}/delete"><i class="far fa-trash-alt"></i> Delete</a>
						</c:when>
						
						<c:when test="${event.attendees.contains(loginUser)}">
							Joined <a class="btn btn-sm btn-outline-danger" href="/events/${event.id}/cancel"><i class="fas fa-user-minus"></i> Cancel</a>
						</c:when>
						
						<c:otherwise>
							<a class="btn btn-sm btn-outline-success" href="/events/${event.id}/join"><i class="fas fa-user-plus"></i> Join</a>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a href="/events/new" class="btn btn-outline-primary"><i class="far fa-calendar-alt"></i> Create Event</a>
	</div>
</div>
<%@include file="footer.jsp"%>