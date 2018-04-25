<%--
 * action-2.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script>
	function preguntar(id) {
		eliminar = confirm('<spring:message code="newspaper.confirmDelete"/>');
		if (eliminar)
			//Redireccionamos si das a aceptar
			window.location.href = "advertisement/admin/delete.do?advertisementId=" + id; //página web a la que te redirecciona si confirmas la eliminación
		else
			//Y aquí pon cualquier cosa que quieras que salga si le diste al boton de cancelar
			alert('<spring:message code="newspaper.negativeDelete"/>');
	}
	
	
</script>

<display:table name="advertisements" id="row" requestURI="${requestURI}"
	pagesize="10" class="displaytag" sort="list" defaultsort="1"
	defaultorder="descending">

	<display:column property="agent" titleKey="advertisement.agent" />
	<display:column property="title" titleKey="advertisement.title" />
	<display:column property="banner" titleKey="advertisement.banner" />
	<display:column property="targetPage"
		titleKey="advertisement.targetPage" />

	<security:authorize access="hasRole('ADMIN')">
		<display:column property="tabooWord"
			titleKey="advertisement.tabooWord" />
		
		<display:column>
			<a href="javascript:preguntar(${row.id})"> <spring:message code="chirp.delete"/> </a>
		</display:column>
	</security:authorize>

</display:table>