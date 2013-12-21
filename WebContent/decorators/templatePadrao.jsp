<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<html>
<head>
<script>
	var callBackList = new Array(0);
	onload = function(callBack) {
		callBackList[callBackList.length] = callBack;
	};
</script>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/bootstrap.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/bootstrap-theme.css" />" />

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/menu.css" />" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><decorator:title default="...:::: R2D2 ::::..."></decorator:title></title>
<%-- Layout

+---------------------------------------------------+
|													|
|___________________________________________________|
|  Menu |	      Mensagens  						|
|		|	formulário								|
|		|											|
|		|											|
|		|											|
|		|											|
|		|											|
|		|											|
|		|											|
+-------+-------------------------------------------+
|													|
|													|
+-------+-------------------------------------------+

 --%>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="/" />">R2D2</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="<c:url value="/file" />">Pastas</a></li>
		<%--
				<li><a href="#">Link</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Dropdown <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li class="divider"></li>
						<li><a href="#">Separated link</a></li>
						<li class="divider"></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>
					--%>
			</ul>
			<%--
			<form class="navbar-form navbar-left" role="search" method="get"
				action="<c:url value="/file/search" />">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search"
						name="word">
				</div>
				<button type="submit" class="btn btn-default">
					<span class="glyphicon .glyphicon-search"></span>
				</button>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">Link</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Dropdown <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li class="divider"></li>
						<li><a href="#">Separated link</a></li>
					</ul></li>
			</ul>
			 --%>
		</div>
		<!-- /.navbar-collapse -->
	</nav>



	<div id="content">
		<div id="leftBar" style="float: left; width: 22%">
			<!-- 	MENU ALTERNATIVO -->
			<img style="width: 100px; height: 130px;"
				src="<c:url value="/img/r2d2.jpg" />" />
		</div>
		<div id="content" style="width: 78%; float: left">
			<fieldset>
				<decorator:body />
			</fieldset>
		</div>
	</div>


	<script type="text/javascript" src="/r2d2/js/jquery.min.js">
		
	</script>
	<script type="text/javascript" src="/r2d2/js/bootstrap.js">
		
	</script>
	<script>
		for (c in callBackList)
			try {
				callBackList[c].call();
			} catch (e) {

			}
	</script>

</body>
</html>