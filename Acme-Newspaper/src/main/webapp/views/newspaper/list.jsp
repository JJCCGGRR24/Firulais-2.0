<%--
 * action-2.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<script>
	function preguntar(Id) {
		eliminar = confirm('<spring:message code="newspaper.confirmDelete"/>');
		if (eliminar)
			//Redireccionamos si das a aceptar
			window.location.href = "newspaper/admin/delete.do?newspaperId=" + Id; //página web a la que te redirecciona si confirmas la eliminación
		else
			//Y aquí pon cualquier cosa que quieras que salga si le diste al boton de cancelar
			alert('<spring:message code="newspaper.negativeDelete"/>');
	}
</script>


<display:table name="newspapers" id="row" requestURI="${requestURI}"
	pagesize="10" class="displaytag">

	<display:column property="publicationDate" titleKey="newspaper.publicationDate" />
	<display:column property="title" titleKey="general.title" />
	
	
	
	
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column>
			<a href="javascript:preguntar(${row.id})"><spring:message
					code="newspaper.delete" /></a>
		</display:column>
	</security:authorize>

	<input type="button"  value="<spring:message code="general.details"/>" 
	onclick="javascript: relativeRedir('newspaper/details.do?newspaperId='+${row.id});"/>
	
</display:table>