<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fieldset>
	<legend> Registro </legend>

	<form class="form" action="<c:url value="/artifact/register" />"
		method="post" enctype="multipart/form-data">
		<div class="row">
			<label>GroupId</label>
			<div class="cols-3">
				<input type="text" class="form-control" />
			</div>
		</div>
		<div class="row">
			<label>ArtifactId</label>
			<div class="cols-3">
				<input type="text" class="form-control" />
			</div>
		</div>
		<div class="row">
			<label>Version</label>
			<div class="cols-3">
				<input type="text" class="form-control" />
			</div>
		</div>

		<div class="row">
			<label>File</label>
			<div class="cols-3">
				<input type="text" class="form-control" />
			</div>
		</div>


	</form>
</fieldset>