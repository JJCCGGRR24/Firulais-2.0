<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
	
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	

<form:form action="${requestURI}" modelAttribute="comment">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="moment"/>
	<form:hidden path="user"/>
	<form:hidden path="rendezvous"/>
	<form:hidden path="replies"/>
	
	
	
	<acme:textbox code="comment.text" path="text" />
	
	

	
	
	<acme:textbox code="comment.picture" path="picture" />
	

	
	

	
	
	<security:authorize access="hasRole('USER')">
	<input type="submit" name="save" onclick="javascript:relativeRedir('comment/user/edit.do');" value="<spring:message code="save"/>" />
	</security:authorize>
		

<security:authorize access="hasRole('USER')">
	<jstl:if test="${comment.id != 0}">	
	<input type="submit" name="delete" 
	onclick="javascript: relativeRedir('comment/user/edit.do');"
		value="<spring:message code="template.delete" />" />&nbsp; 
	</jstl:if>
	</security:authorize>
	
	<security:authorize access="hasRole('USER')">
	<input type="button" name="cancel" value="<spring:message code="template.cancel"/>" 
	onclick="javascript: relativeRedir('comment/user/list.do?rendezvousId='+${comment.rendezvous.id});"/>
	</security:authorize>
	
	


</form:form>
