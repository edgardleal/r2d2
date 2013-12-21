<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-danger">
	<div class="panel-haeding">
		<div class="title">
			<h3>Registrando o repositório</h3>
		</div>
	</div>
	<div class="panel-body">
		<label class="label label-danger">Registre a seguinte url:</label> <a
			href="<c:url value="/repository/"/>"> <c:url value="/repository/" /></a>
	</div>

</div>