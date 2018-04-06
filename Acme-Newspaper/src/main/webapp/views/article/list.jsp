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

<display:table  name="articles" id="row"  pagesize="10" requestURI="${requestURI}" class="displaytag" > 

	<spring:message code="article.title" var="title"></spring:message>
	<display:column title="${title}" property ="title" />
	
	<spring:message code="article.summary" var="summary"></spring:message>
	<display:column title="${summary}" property ="summary" />
	
	<spring:message code="article.body" var="body"></spring:message>
	<display:column title="${body}" property ="body" />
	
	<spring:message code="article.moment" var="moment"></spring:message>
	<display:column title="${moment}" property ="moment" />
	
		<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="javascript:preguntar(${row.id})"><spring:message
					code="newspaper.delete" /></a>
		</display:column>
	</security:authorize>
	

</display:table>
