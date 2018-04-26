<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	
<form:form action="${requestURI}" modelAttribute="messageForm">
	
		
	
	
	<acme:textbox code="message.subject" path="subject" size="100"/><br>
	<acme:textarea code="message.body" path="body"/>
	<acme:select items="${actors}" itemLabel="name" code="message.recipient" path="recipient"/>
	<div>
	<form:label path="priority">
		<spring:message code="message.priority" />
	</form:label>	
	<form:select path="priority">
		<form:option value="0" label="----" />
		<form:option value="HIGH" label="HIGH" />			
		<form:option value="NEUTRAL" label="NEUTRAL" />		
		<form:option value="LOW" label="LOW" />		
	</form:select>
	<form:errors path="priority" cssClass="error" />
	</div>
	
	<br>
	<input type="submit" name="save" value="<spring:message code="message.send"/>" />
	
	<input type="button" name="cancel" value="<spring:message code="message.cancel"/>" 
	onclick="javascript: relativeRedir('folder/list.do?actorId='+${actor.id});"/>

</form:form>
