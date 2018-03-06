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




<display:table name="requests" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<spring:message code="rquest.comment" var="comment"></spring:message>
	<display:column property="comment" title="${comment}" />
	
	<spring:message code="rquest.servicce" var="servicce"></spring:message>
	<display:column property="servicce.name" title="${servicce}" />
	
	
	<spring:message code="request.creditCard.holderName" var="holderName"></spring:message>
	<display:column property="creditCard.holderName" title="${holderName}" />
	
	<spring:message code="request.creditCard.brandName" var="brandName"></spring:message>
	<display:column property="creditCard.brandName" title="${brandName}" />
	
	<spring:message code="request.creditCard.expirationYear" var="expirationYear"></spring:message>
	<display:column property="creditCard.expirationYear" title="${expirationYear}" />
	
	<spring:message code="request.creditCard.expirationMonth" var="expirationMonth"></spring:message>
	<display:column property="creditCard.expirationMonth" title="${expirationMonth}" />
	
	<spring:message code="request.creditCard.CVV" var="CVV"></spring:message>
	<display:column property="creditCard.CVV" title="${CVV}" />
	
	<spring:message code="request.creditCard.number" var="number"></spring:message>
	<display:column property="creditCard.number" title="${number}" />
	
	
	
		
		
	
</display:table>

<security:authorize access="hasRole('USER')">
<%-- <jstl:if test="${comentable}"> --%>
<spring:message code="request.create" var="create" />
	<div align="left">
	<input type="button" value="${create}"
		onclick="javascript: window.location.href = './request/user/create.do?rendezvousId=${rendezvousId}';" /></div>
<%-- 		</jstl:if> --%>
</security:authorize>



	
