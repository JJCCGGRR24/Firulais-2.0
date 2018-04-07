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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI }" modelAttribute="newspaper">

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="publicationDate" />
		<form:hidden path="deprived" />
		<form:hidden path="tabooWord" />
		<form:hidden path="articles" />
		<form:hidden path="user" />
		<form:hidden path="subscribes" />

	<acme:textbox code="chirp.title" path="title" size="100"/><br>
	<acme:textbox code="general.picture" path="picture" size="100"/><br>
	<acme:textarea code="chirp.description" path="description"/>
	<br>
	<input type="submit" name="save" value="<spring:message code="template.save"/>" />
	
	<input type="button" name="cancel" value="<spring:message code="template.cancel"/>" 
	onclick="javascript: relativeRedir('newspaper/user/myList.do');"/>
	
</form:form>


