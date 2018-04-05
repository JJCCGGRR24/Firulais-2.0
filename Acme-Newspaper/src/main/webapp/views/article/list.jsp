
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<display:table  name="articles" id="row"  pagesize="10" requestURI="${requestURI}" class="displaytag" > 

	<spring:message code="article.title" var="title"></spring:message>
	<display:column title="${title}" property ="title" />
	
	<spring:message code="article.summary" var="summary"></spring:message>
	<display:column title="${summary}" property ="summary" />
	
	<spring:message code="article.body" var="body"></spring:message>
	<display:column title="${body}" property ="body" />
	
	<spring:message code="article.moment" var="moment"></spring:message>
	<display:column title="${moment}" property ="moment" />
	
	

</display:table>
