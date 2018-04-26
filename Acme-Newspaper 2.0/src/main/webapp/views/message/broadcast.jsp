<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<form:form action="${requestURI}" modelAttribute="broadcastForm">
	
	<acme:textbox code="message.subject" path="subject"/>
	<acme:textarea code="message.body" path="body"/>
	<div>
		<form:label path="priority">
			<spring:message code="message.priority" />
		</form:label>	
		<form:select path="priority">
			<form:option value="0" label="----" />		
			<form:options items="${ps}"/>
		</form:select>
		<form:errors path="priority" cssClass="error" />
	</div>	
	<input type="submit" name="save" value="<spring:message code="template.send"/>" />

</form:form>
