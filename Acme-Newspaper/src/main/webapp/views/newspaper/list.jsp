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



<display:table name="newspapers" id="row" requestURI="${requestURI}"
	pagesize="10" class="displaytag">

	<display:column property="publicationDate" titleKey="newspaper.publicationDate" />
	<display:column property="title" titleKey="general.title" />

	<input type="button"  value="<spring:message code="general.details"/>" 
	onclick="javascript: relativeRedir('newspaper/details.do?newspaperId='+${row.id});"/>
	
</display:table>