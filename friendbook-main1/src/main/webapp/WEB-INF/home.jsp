<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pt" uri="http://ocpsoft.org/prettytime/tags" %>

<%@include file="header.jsp"%>

<div class="row bg-light">
	<div class="col-sm-12">

		<div class="card my-3">
			<form action="/home" method="post" class="card-body">
				<h5 class="card-title">Create a post ...</h5>
				<div class="input-group">
					<textarea name="post" class="form-control"></textarea>
					<div class="input-group-append">
						<button class="btn btn-outline-primary" type="submit"><i class="fas fa-paper-plane"></i> Post</button>
					</div>
				</div>
			</form>
		</div>

		<c:forEach items="${allPosts}" var="post">
		<div class="card my-3">
			<div class="row no-gutters">
			    <div class="col-md-1">
			      <img src='<c:out value="${post.postedBy.profile.profilePicPath}" />'  
			onerror="javascript:this.src='/img/avatar.png'" class="card-img" >
			    </div>
			    <div class="col-md-11">
				  <div class="card-body">
					<h6 class="card-subtitle mb-2 text-muted">Posted by <a href="/profile/user/${post.postedBy.id}">
							<c:out value="${post.postedBy.name}" /></a>,
						<pt:prettytime date="${post.createdAt}" />
						<c:if test="${post.postedBy.id == loginUser.id}">
						<span class="float-right">
							<a href="/delete/${post.id}" class="text-danger"><i class="far fa-trash-alt"></i> Delete</a>
						</span>
						</c:if>
					</h6>
					<p class="card-text">
						<c:out value="${post.message}" />
					</p>

					<div class="card-text">
						<form action="/like/${post.id}" method="post" class="form-inline">
							<button type="submit" class="btn btn-outline-primary btn-sm rounded-circle" name="button"
									value="like"><i class="far fa-thumbs-up"></i></button>
							<c:out value="${likeButton}"></c:out>
							<c:if test="${likes[post.id] > 0}">
								<span class="ml-2">${likes[post.id]} people liked it!</span>
							</c:if>
						</form>
					</div>
				  </div>
				</div>
			</div>
			<div class="row no-gutters">
			    <div class="col">

				<c:forEach items="${post.comments}" var="comment">
					<div class="card-body py-0 mb-2">
						<div class="card px-2">
							<h6 class="card-subtitle my-1 text-muted">Commented by <a
								   href="/profile/user/${comment.commentedBy.id}">
									<c:out value="${comment.commentedBy.name}" /></a>
								<pt:prettytime date="${comment.createdAt}" />
							</h6>
							<p class="card-text">
								<c:out value="${comment.message}" />
							</p>
						</div>
					</div>
				</c:forEach>
				<div class="card-footer">
					<form action="/comment/${post.id}" method="post">
						<div class="input-group">
							<input name="message" class="form-control" />
							<div class="input-group-append">
								<button class="btn btn-outline-secondary" type="submit"><i class="far fa-comment"></i> Comment</button>
							</div>
						</div>
					</form>
				</div>
				</div>
			</div>
		 </div>
		</c:forEach>



	</div>
</div>



	


	<%@include file="footer.jsp"%>
