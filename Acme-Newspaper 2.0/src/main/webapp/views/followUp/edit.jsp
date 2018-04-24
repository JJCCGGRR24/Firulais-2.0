<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	
<form:form action="followUp/user/create.do" modelAttribute="followUp">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="publicationMoment"/>
	
<%-- 	<form:label path="newspaper"> --%>
<%-- 		<spring:message code="followUp.newspaper"/>: --%>
<%-- 	</form:label> --%>
<%-- 	<form:select path="newspaper" cssStyle="width:500px;"> --%>
<%-- 		<form:option value="0" label="-"/> --%>
<%-- 		<jstl:forEach items="${newspapers}" var="cat"> --%>
<%-- 			<form:option value="${cat.id}" label="${cat.title} (${cat.publicationDate})"/> --%>
<%-- 		</jstl:forEach> --%>
<%-- 	</form:select> --%>
<%-- 	<form:errors path="newspaper" cssClass="error" /> --%>
<!-- 	<br><br> -->

	<acme:select items="${articles}" itemLabel="title" code="followUp.article" path="article" estilo="width:400px;"/><br>
	
	<acme:textbox code="followUp.title" path="title" size="100"/><br>
	
	<acme:textarea code="followUp.summary" path="summary"/><br>
	
	<acme:textarea code="followUp.text" path="text"/><br>
	
	<acme:textarea code="followUp.pictures" path="pictures"/><br>
	
	<input type="submit" name="save" value="<spring:message code="template.save"/>" />
	<input type="button" name="cancel" value="<spring:message code="template.cancel"/>" 
	onclick="javascript: relativeRedir('/followUp/user/myList.do');"/>

</form:form>
