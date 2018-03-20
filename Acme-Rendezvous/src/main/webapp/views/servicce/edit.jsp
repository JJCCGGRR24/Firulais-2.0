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
	

<form:form action="${requestURI}" modelAttribute="servicce">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="cancelled"/>
	<form:hidden path="manager"/>
	<form:hidden path="requests"/>
	
	<acme:textbox code="servicce.name" path="name" />
	<acme:textarea code="servicce.description" path="description" />
	<acme:textbox code="servicce.picture" path="picture" />
	
	<form:label path="category">
		<spring:message code="servicce.category" />:
	</form:label>
	<form:select path="category">
		<form:option value="0" label="-"/>
		<form:options items="${categories}" itemLabel="name" itemValue="id"/>
	</form:select>
	<form:errors cssClass="error" path="category" />
	<br/><br/>
	
	<input type="submit" name="save" onclick="javascript:relativeRedir('servicce/manager/myservicces.do');" value="<spring:message code="save"/>" />
	
	<jstl:if test="${servicce.id != 0}">	
		<input type="submit" name="delete" 
		onclick="javascript: relativeRedir('servicce/manager/myservicces.do');"
			value="<spring:message code="template.delete" />" />&nbsp; 
	</jstl:if>
	
	<input type="button" name="cancel" value="<spring:message code="template.cancel"/>" 
	onclick="javascript: relativeRedir('servicce/manager/myservicces.do');"/>

</form:form>