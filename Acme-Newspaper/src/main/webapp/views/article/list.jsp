<?xml version="1.0" encoding="UTF-8" ?>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<script>
	function preguntar(rendezvousId) {
		eliminar = confirm('<spring:message code="newspaper.confirmDelete"/>');
		if (eliminar)
			//Redireccionamos si das a aceptar
			window.location.href = "article/admin/delete.do?articleId=" + rendezvousId; //página web a la que te redirecciona si confirmas la eliminación
		else
			//Y aquí pon cualquier cosa que quieras que salga si le diste al boton de cancelar
			alert('<spring:message code="newspaper.negativeDelete"/>');
	}
	
	
</script>

<input type="text" id="textSearch" value="">
<input type="button" id="buttonSearch"
	value="<spring:message code="newspaper.search"/>" />

<script type="text/javascript">
	$(document).ready(function() {
		$("#buttonSearch").click(function() {
			if ($("#textSearch").val()!="")
				window.location.replace('article/all/list.do?search='+ $("#textSearch").val());
		});
		$("#textSearch").on('keyup',function(e) {
			if (e.keyCode === 13 && $("#textSearch").val()!="")
				window.location.replace('article/all/list.do?search='+ $("#textSearch").val());
			e.preventDefault();
		});
	});
</script>

<display:table  name="articles" id="row"  pagesize="10" requestURI="${requestURI}" class="displaytag" > 

	<spring:message code="article.title" var="title"></spring:message>
	<display:column titleKey="article.title">
		<a href="article/details.do?articleId=${row.id}">${row.title}</a>
	</display:column>
	
	<display:column titleKey="article.summary">
		<jstl:if test="${fn:length(row.summary) > 100}">
			<span class="teaser">${fn:substring(row.summary, 0, 100)}</span>
			<span class="complete">${row.summary}</span>
			<span class="more">${template.more}...</span>
		</jstl:if>
		<jstl:if test="${!(fn:length(row.summary) > 100)}">
			${row.summary}
		</jstl:if>
	</display:column>
	
	<display:column titleKey="article.body">
		<jstl:if test="${fn:length(row.body) > 100}">
			<span class="teaser">${fn:substring(row.body, 0, 100)}</span>
			<span class="complete">${row.body}</span>
			<span class="more">${template.more}...</span>
		</jstl:if>
		<jstl:if test="${!(fn:length(row.body) > 100)}">
			${row.body}
		</jstl:if>
	</display:column>
	
	<display:column titleKey="article.moment" property ="moment" format="{0,date,dd/MM/yy HH:mm}"/>
	
	<jstl:if test="${requestURI eq 'article/user/myList.do'}">
		<display:column titleKey="template.edit">
			<jstl:if test="${row.finalMode eq false and row.moment eq null and row.newspaper.publicationDate eq null}">
				<input type="button" name="edit" value="<spring:message code="template.edit"/>" 
				onclick="javascript: relativeRedir('article/user/edit.do?articleId=${row.id}');"/>
			</jstl:if>
			<jstl:if test="${!(row.finalMode eq false and row.moment eq null and row.newspaper.publicationDate eq null)}">
				<spring:message code="article.finalMode"/>
			</jstl:if>
		</display:column>
	</jstl:if>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="javascript:preguntar(${row.id})"><spring:message
					code="newspaper.delete" /></a>
		</display:column>
	</security:authorize>
	

</display:table>

<script>
	$(".more").toggle(function(){
	    $(this).text("<spring:message code="template.less"/>...").siblings(".teaser").hide();  
	    $(this).text("<spring:message code="template.less"/>...").siblings(".complete").show();    
	}, function(){
		$(this).text("<spring:message code="template.more"/>...").siblings(".teaser").show();
		$(this).text("<spring:message code="template.more"/>...").siblings(".complete").hide();    
	});
</script>