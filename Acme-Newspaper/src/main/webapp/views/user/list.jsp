
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<display:table  name="users" id="row"  pagesize="10" requestURI="${requestURI}" class="displaytag" > 

	<spring:message code="register.name" var="name"></spring:message>
	<display:column title="${name}" > 
		<spring:url value="/user/details.do" var="editURL"><spring:param name="userId" value="${row.id}"/>
	 </spring:url>
	 <a href="${editURL}">${row.name}</a>
	 </display:column>
	 	
	 <spring:message code="register.view" var="view"></spring:message>
	 <spring:message code="register.articles" var="articlesT"></spring:message>
	<display:column title="${articlesT}" > 
		<spring:url value="/article/list.do" var="editURL"><spring:param name="userId" value="${row.id}"/>
	 </spring:url>
	 <a href="${editURL}"><jstl:out value="${view}"></jstl:out></a>
	 </display:column>


</display:table>
