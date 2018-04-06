<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	
<form:form action="${requestURI}" modelAttribute="chirp">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="moment"/>
	<form:hidden path="user"/>
	<form:hidden path="tabooWord"/>

	<acme:textbox code="chirp.title" path="title" size="100"/><br>
	<acme:textarea code="chirp.description" path="description"/>
	<br>
	<input type="submit" name="save" value="<spring:message code="template.publish"/>" />
	
	<input type="button" name="cancel" value="<spring:message code="template.cancel"/>" 
	onclick="javascript: relativeRedir('user/details.do?userId='+${chirp.user.id});"/>

</form:form>
