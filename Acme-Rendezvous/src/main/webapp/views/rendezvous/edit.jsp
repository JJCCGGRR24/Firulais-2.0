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




<form:form action="rendezvous/user/edit.do" modelAttribute="rendezvous">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="user" />
	<form:hidden path="linkedTo" />
	<form:hidden path="linkedIn" />
	<form:hidden path="users" />
	<form:hidden path="comments" />
	<form:hidden path="questions" />
	<form:hidden path="announcements" />
	<form:hidden path="requests" />

	<acme:textbox code="rendezvous.name" path="name" />
	<br />
	<br />
	<acme:textarea code="rendezvous.description" path="description" />
	<br />
	<br />
	<acme:textbox code="rendezvous.moment" path="moment" />
	(dd/MM/yyyy HH:mm)
	<br />
	<br />
	<acme:textbox code="rendezvous.picture" path="picture" />
	<br />
	<br />

	<form:label path="finalMode">
		<spring:message code="rendezvous.final" />:
	</form:label>
	<form:radiobutton path="finalMode" value="true" />
	<spring:message code="rendezvous.yes" />
	<form:radiobutton path="finalMode" value="false" />
	<spring:message code="rendezvous.no" />
	<br />
	<br />

	<jstl:if test="${edad>=18}">
		<form:label path="adultsOnly">
			<spring:message code="rendezvous.adult" />:
		</form:label>
		<form:radiobutton path="adultsOnly" value="true" />
		<spring:message code="rendezvous.yes" />
		<form:radiobutton path="adultsOnly" value="false" />
		<spring:message code="rendezvous.no" />
		<br />
		<br />
	</jstl:if>

	<jstl:if test="${rendezvous.id !=0}">
		<form:label path="deleted">
			<spring:message code="rendezvous.delete.message" />:
	</form:label>
		<form:radiobutton path="deleted" value="true" />
		<spring:message code="rendezvous.yes" />
		<form:radiobutton path="deleted" value="false" />
		<spring:message code="rendezvous.no" />
		<br />
		<br />
	</jstl:if>

	<fieldset>
		<legend>
			<spring:message code="rendezvous.coordinates" />
		</legend>
		<acme:textbox code="rendezvous.coordinates.longitude"
			path="coordinates.longitude" />
		<br />
		<acme:textbox code="rendezvous.coordinates.latitude"
			path="coordinates.latitude" />
	</fieldset>
	<br />

	<acme:submit name="save" code="rendezvous.save" />
	<acme:cancel url="rendezvous/list.do" code="rendezvous.cancel" />

</form:form>

