<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-info">
	<div class="panel-heading">
		<div class="title">
			<h3>${artifact }</h3>
		</div>
	</div>
	<div class="panel-body">
		<br> <a href="<c:url value="/repository/" />${download }"
			class="btn btn-primary"> <span
			class="glyphicon glyphicon-cloud-download"></span> Download
		</a> <br>
		<ul class="nav nav-tabs">
			<li id="tabMaven"><a href="#">Maven</a></li>
			<li id="tabIvy" class="active"><a href="#">Ivy</a></li>
			<%--
  <li><a href="#">Messages</a></li>
   --%>
		</ul>
		<div id="codeIvy" class="well">
		    &lt;dependency&nbsp;org=&quot;${groupId }&quot;&nbsp;name=&quot;${artifact }&quot;
		     &nbsp;rev=&quot;${version }&quot;/&gt;
		</div>
		<div id="codeMaven" class="well" style="display:none;">
			&lt;dependency&gt;<br> &nbsp;&nbsp; &lt;groupId&gt;${groupId }&lt;/groupId&gt;<br>
			&nbsp;&nbsp; &lt;artifactId&gt;${artifact }&lt;/artifactId&gt;<br>
			&nbsp;&nbsp; &lt;version&gt;${version }&lt;/version&gt;<br>
			&lt;/dependency&gt;<br>
		</div>
	</div>
	<div class="panel-footer"></div>
</div>

<script>
   onload(function(){
      $('#tabMaven').click(function(){
        $('li').removeClass('li');
        $(this).addClass("active");
        $('#codeIvy').hide();
        $('#codeMaven').show();
      });

      $('#tabIvy').click(function(){
          $('li').removeClass('li');
          $(this).addClass("active");
          $('#codeMaven').hide();
          $('#codeIvy').show();
        });             
   });
</script>