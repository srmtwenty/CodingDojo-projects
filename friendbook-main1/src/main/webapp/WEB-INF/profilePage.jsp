<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file="header.jsp"%>

<div class="row">
	<div class="col-4 mt-3">
		<img src='<c:out value="${user.profile.profilePicPath}" />'  
			onerror="javascript:this.src='/img/avatar.png'" class="rounded img-fluid" />
	</div>


	<div class="col mt-3">
		<h1><c:out value="${user.name}" /></h1>
		
		
		<h5>Intro</h5>
		<p>
			<c:out value="${user.profile.bio}" />
		</p>
		<p><i class="ico fas fa-graduation-cap"></i> Studied at <c:out value="${user.profile.education}" /></p>
		<p><i class="ico fas fa-map-marker-alt"></i> Lives in <c:out value="${user.profile.currentCity}" /></p>
		
		
		<div class="form-inline">
			<c:if test="${loginUser.id == user.id}">
			<a href="/profile/edit" class="btn btn-secondary"><i class="fas fa-user-edit"></i> Edit</a>
			</c:if>
		</div>		
	</div>
	
</div>

<%@include file="footer.jsp"%>