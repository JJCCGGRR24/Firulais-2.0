<?xml version="1.0" encoding="UTF-8" ?>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<security:authorize access="hasRole('ADMIN')">
<spring:message code="taboo.create" var="create" />
	<div align="left">
	<input type="button" value="${create}"
		onclick="javascript: window.location.href = './taboo/admin/create.do';" /></div>
</security:authorize>



<display:table  name="taboos" id="row"  pagesize="10" requestURI="${requestURI}" class="displaytag" > 

	<spring:message code="taboo.word" var="word"></spring:message>
	<display:column title="${word}" property ="word" />
	
	<security:authorize access="hasRole('ADMIN')">
	<spring:message code="taboo.edit" var="edit" />
	<display:column title="${edit}" >
	<input type="button" class="btn btn-primary" value="${edit}"	
			onclick="javascript: window.location.href = './taboo/admin/edit.do?tabooId=${row.id}';" /></display:column>
	</security:authorize>
	
	
	

</display:table>
