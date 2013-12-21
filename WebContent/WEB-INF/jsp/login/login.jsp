<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-info">
	<div class="panel-heading">
		<div class="title">
			<h3>Login</h3>
		</div>
	</div>
	<form action="<c:url value="/login/autenticar"/>" method="post"
		class="form">
		<div class="form-group">
			<label>Login</label> <input type="text" name="userModel.userName"
				class="form-control" />
		</div>
		<div class="form-group">
			<label>Password:</label> <input type="password"
				name="userModel.password" class="form-control" />
		</div>
		<div class="form-group">
			<input type="submit" value="Ok" class="btn btn-success" />
		</div>
	</form>
	<div class="panel-body"></div>
	<div class="panel-footer"></div>
</div>