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



<display:table name="users" id="row" requestURI="${requestURI}"
	pagesize="10" class="displaytag">

	<display:column property="name" titleKey="user.name" />

	<display:column titleKey="user.personalData">
		<a href="user/view.do?userId=${row.id}"><spring:message
				code="user.view" /></a>
	</display:column>
	<display:column titleKey="user.rendezvous">
		<security:authorize access="hasRole('USER')">
			<a href="rendezvous/user/list.do?userId=${row.id}"><spring:message
					code="user.view" /></a>
		</security:authorize>
		<security:authorize access="!hasRole('USER')">
			<a href="rendezvous/all/list.do?userId=${row.id}"><spring:message
					code="user.view" /></a>
		</security:authorize>
	</display:column>
</display:table>