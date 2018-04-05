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


<spring:message code="register.name" var="name"/>
<spring:message code="register.surname" var="surname"/>
<spring:message code="register.email" var="email"/>
<spring:message code="register.phone" var="phone"/>
<spring:message code="register.postalAddress" var="postalAddress"/>

<fieldset> 
<b><jstl:out value="${name}"/> : </b> <jstl:out value="${user.name}"/></br>
<b><jstl:out value="${surname}"/> : </b> <jstl:out value="${user.surname}"/></br>
<b><jstl:out value="${email}"/> : </b> <jstl:out value="${user.email}"/></br>
<b><jstl:out value="${phone}"/> : </b> <jstl:out value="${user.phone}"/></br>
<b><jstl:out value="${postalAddress}"/> : </b> <jstl:out value="${user.postalAddress}"/></br>

</fieldset>
</br>


<spring:message code="register.back" var="back"/>
<input type="button" name="back" value="${back}" onclick="javascript:relativeRedir('user/list.do')"/>


