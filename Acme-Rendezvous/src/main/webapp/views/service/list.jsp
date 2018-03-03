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




<display:table name="services" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<spring:message code="service.name" var="name"></spring:message>
	<display:column property="name" title="${name}" />
	
	
	<spring:message code="service.description" var="description"></spring:message>
	<display:column property="description" title="${description}" />
	
<%-- 	<spring:message code="service.cancelled" var="cancelled"></spring:message> --%>
<%-- 	<display:column property="cancelled" title="${cancelled}" /> --%>

	<display:column titleKey="service.cancelled">
		<jstl:if test="${row.cancelled}">
			<img
				src="http://www.clker.com/cliparts/2/k/n/l/C/Q/transparent-green-checkmark-hi.png"
				height="30px" />
		</jstl:if>
		<jstl:if test="${!row.cancelled}">
			<img
				src="https://vignette.wikia.nocookie.net/house-of-cards/images/a/a5/X.png/revision/latest?cb=20161128021903"
				height="30px" />
		</jstl:if>
	</display:column>
	
	<display:column><img style="max-height: 100px;max-width: 100px;" src="${row.picture}"/></display:column>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column> 
		<jstl:if test="${row.cancelled eq false}">
			<spring:url value="/servicce/administrator/cancell.do" var="editURL">
				<spring:param name="servicceId" value="${row.id}"/>
			</spring:url>
			<a href="${editURL}"><spring:message code="servicce.admin.cancell"/></a>
		</jstl:if>
		</display:column>
	</security:authorize>
	
	
		
		
	
</display:table>




	
