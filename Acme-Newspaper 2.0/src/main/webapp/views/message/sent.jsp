<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	
<form:form action="${requestURI}" modelAttribute="messageForm">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	
	<acme:textbox code="message.subject" path="subject" size="100"/><br>
	<acme:textarea code="message.body" path="body"/>
	<acme:select items="${actors}" itemLabel="${row.name}" code="message.recipient" path="recipient"/>
	 
	
	<br>
	<input type="submit" name="save" value="<spring:message code="message.send"/>" />
	
	<input type="button" name="cancel" value="<spring:message code="message.cancel"/>" 
	onclick="javascript: relativeRedir('folder/list.do?actorId='+${actor.id});"/>

</form:form>
