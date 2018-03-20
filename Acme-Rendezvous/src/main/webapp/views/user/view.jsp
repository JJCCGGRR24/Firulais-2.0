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



<spring:message code="user.name" />
: <jstl:out value="${user.name}"/>
<br />
<spring:message code="user.surname" />
: <jstl:out value="${user.surname}"/>
<br />
<spring:message code="user.email" />
: <jstl:out value="${user.email}"/>
<br />
<spring:message code="user.birthdate" />
: <jstl:out value="${user.birthdate}"/>
<br />
<spring:message code="user.phone" />
: <jstl:out value="${user.phone}"/>
<br />
<spring:message code="user.postalAddress" />
: <jstl:out value="${user.postalAddress}"/>
<br />
<br />
<br />
<a href="user/list.do"><spring:message code="user.back" /></a>
