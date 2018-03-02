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




<display:table name="replies" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<spring:message code="reply.user" var="user"></spring:message>
	<display:column property="user.userAccount.username" title="${user}" />
	
	
	<spring:message code="reply.text" var="text"></spring:message>
	<display:column property="text" title="${text}" />
		
		
	
</display:table>

<security:authorize access="hasRole('USER')">
<%-- <jstl:if test="${comentable}"> --%>
<spring:message code="reply.create" var="create" />
	<div align="left">
	<input type="button" value="${create}"
		onclick="javascript: window.location.href = './reply/user/create.do?commentId=${cmmntId}';" /></div>
<%-- 		</jstl:if> --%>
</security:authorize>



	
