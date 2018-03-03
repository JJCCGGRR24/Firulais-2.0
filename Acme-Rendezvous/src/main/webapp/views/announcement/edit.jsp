
<%--
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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>




<form:form action="${requestURI}" modelAttribute="announcement">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="rendezvous"/>
	<form:hidden path="moment"/>

	<acme:textbox code="announcement.title" path="title" />
	<br />
	<br />
	<acme:textarea code="announcement.description" path="description" />
	<br />

	<acme:submit name="save" code="announcement.save" />
	<input type="button" name="cancel" value='<spring:message code="template.cancel"/>' onclick="document.location.href='announcement/user/list.do?rendezvousId=${announcement.rendezvous.id}';">

</form:form>