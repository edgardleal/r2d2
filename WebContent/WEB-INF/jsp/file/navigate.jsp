<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-default">
	<div class="heading">
		<div class="title">
			<ol class="breadcrumb">
				<li><a href="<c:url value="/file" />">root</a></li>
				<c:forEach items="${paths }" var="p">

					<li <c:if test="${current==folder }" >
				   class="active"
				</c:if>><a
						href="<c:url value="/file"/>${current}/${p}/">${p }</a></li>
					<c:set var="current" value="${current }${p }/" />
				</c:forEach>
			</ol>

		</div>
	</div>
</div>
<br>
<table>

	<c:forEach items="${files }" var="f">
		<tr>
			<td><c:if test="${not f.isDirectory() }">
					<span class="glyphicon glyphicon-file"></span>
					<a
						<%-- Se form um arquivo, direciona para a tela de detalhes do artifact --%>
						href="<c:url value="/artifact" />${folder}/">${f.name }</a>
				</c:if> <c:if test="${f.isDirectory() }">
					<span class="glyphicon glyphicon-folder-open"></span>
					<a href="<c:url value="/file" />${folder}/${f.name}">${f.name }
					</a>
				</c:if></td>
		</tr>
	</c:forEach>
</table>
<br>
