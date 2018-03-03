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
	

<form:form action="${requestURI}" modelAttribute="reply">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<form:hidden path="user"/>
	<form:hidden path="comment"/>
	
	
	
	<acme:textbox code="reply.text" path="text" />
	
	

	
	

	

	
	

	
	
	<security:authorize access="hasRole('USER')">
	<input type="submit" name="save" onclick="javascript:relativeRedir('reply/user/edit.do');" value="<spring:message code="save"/>" />
	</security:authorize>
		


	
	<security:authorize access="hasRole('USER')">
	<input type="button" name="cancel" value="<spring:message code="template.cancel"/>" 
	onclick="javascript: relativeRedir('reply/user/list.do?commentId='+${reply.comment.id});"/>
	</security:authorize>
	
	


</form:form>
