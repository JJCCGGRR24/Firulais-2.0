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

<form:form action="rendezvous/user/link.do" modelAttribute="linkForm">

	<form:hidden path="id" />
	
	<form:select path="r">
		<form:options  items="${rendezvouses}" itemValue="id" itemLabel="name"/>
	</form:select>
	
	<acme:submit name="save" code="rendezvous.link" />
	<acme:cancel url="rendezvous/user/links.do?rendezvousId=${linkForm.id}" code="rendezvous.cancel" />

</form:form>

