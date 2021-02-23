<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file="header.jsp"%>

<div class="row">
	<div class="col-4 mt-3">
		<img src='<c:out value="${loginUser.profile.profilePicPath}" />'
			onerror="javascript:this.src='/img/avatar.png'" class="rounded img-fluid" />

		
		<form action="/photo/profilepic" method="POST"
			enctype="multipart/form-data">
			<div class="input-group mt-3">
				<div class="custom-file">
					<input type="file" class="custom-file-input" id="profilePic"
						name="profilePic" accept="image/png, image/jpeg" /> <label
						class="custom-file-label" for="profilePic"
						aria-describedby="profilePic">Choose photo</label>
				</div>
				<div class="input-group-append">
					<button class="btn btn-outline-secondary" type="submit">Upload</button>
				</div>
			</div>
		</form>
	</div>




	<form:form action="/profile/edit" modelAttribute="editUserProfile"
		method="POST" class="col mt-3 form-row">
		<form:hidden path="id" />
		<form:hidden path="user.id" />
		<div class="col">
			<h1>
				<c:out value="${loginUser.name}" />
			</h1>

			<h5>Intro</h5>
			<div class="input-group input-group-sm mb-3">
				<form:textarea path="bio" cssClass="form-control" cssErrorClass="form-control is-invalid" />
				<form:errors path="bio" element="div" cssClass="invalid-feedback" />
			</div>
			<div class="input-group input-group-sm mb-3">
			  <div class="input-group-prepend">
			    <span class="input-group-text"><i class="ico fas fa-graduation-cap"></i> Studied at</span>
			  </div>
			  <form:input path="education" cssClass="form-control" cssErrorClass="form-control is-invalid" />
			  <form:errors path="education" element="div" cssClass="invalid-feedback" />
			</div>
			<div class="input-group input-group-sm mb-3">
			  <div class="input-group-prepend">
			    <span class="input-group-text"><i class="ico fas fa-map-marker-alt"></i> Lives in</span>
			  </div>
			  <form:input path="currentCity" cssClass="form-control" cssErrorClass="form-control is-invalid" />
			  <form:errors path="currentCity" element="div" cssClass="invalid-feedback" />
			</div>
			<div>
				<button type="submit" class="btn btn-secondary"><i class="fas fa-user-edit"></i> Save</button>
			</div>
		</div>
	</form:form>

</div>

<%@include file="footer.jsp"%>