<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<form:form action="${requestURI}" modelAttribute="taboo">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	
	
	
	<form:label path="word">
		<spring:message code="taboo.word"/>:
	</form:label>
	<form:input path="word" type="word"/>
	<form:errors cssClass="error" path="word"/>
	<br />
	
	
	
	
	
	
	
	<input type="submit" name="save" onclick="javascript:relativeRedir('taboo/admin/edit.do');" value="<spring:message code="note.save"/>" />


	<jstl:if test="${taboo.id != 0}">	
	<input type="submit" name="delete" 
	onclick="javascript: relativeRedir('taboo/admin/edit.do');"
		value="<spring:message code="template.delete" />" />&nbsp; 
	</jstl:if>
	
	
	<input type="button" name="cancell" value="<spring:message code="template.cancel"/>" 
	onclick="javascript: relativeRedir('taboo/admin/list.do');"/>


</form:form>
