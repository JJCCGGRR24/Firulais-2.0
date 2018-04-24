<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	
<form:form action="article/user/edit.do" modelAttribute="article">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="moment"/>
	<form:hidden path="user"/>
	<form:hidden path="tabooWord"/>
	<form:hidden path="finalMode"/>
	<form:hidden path="followUps"/>
	<form:hidden path="user"/>
	
<%-- 	<form:label path="newspaper"> --%>
<%-- 		<spring:message code="article.newspaper"/>: --%>
<%-- 	</form:label> --%>
<%-- 	<form:select path="newspaper" cssStyle="width:500px;"> --%>
<%-- 		<form:option value="0" label="-"/> --%>
<%-- 		<jstl:forEach items="${newspapers}" var="cat"> --%>
<%-- 			<form:option value="${cat.id}" label="${cat.title} (${cat.publicationDate})"/> --%>
<%-- 		</jstl:forEach> --%>
<%-- 	</form:select> --%>
<%-- 	<form:errors path="newspaper" cssClass="error" /> --%>
<!-- 	<br><br> -->

	<acme:select items="${newspapers}" itemLabel="title" code="article.newspaper" path="newspaper" estilo="width:400px;"/><br>
	
	<acme:textbox code="article.title" path="title" size="100"/><br>
	
	<acme:textarea code="article.summary" path="summary"/><br>
	
	<acme:textarea code="article.body" path="body"/><br>
	
	<acme:textarea code="article.pictures" path="pictures"/><br>
	
	<input type="submit" name="save" value="<spring:message code="template.save"/>" />
	<input type="submit" name="publish" value="<spring:message code="template.publish"/>" />
	<jstl:if test="${!(article.id eq 0)}">
		<input type="submit" name="delete" value="<spring:message code="template.delete"/>" />
	</jstl:if>
	<input type="button" name="cancel" value="<spring:message code="template.cancel"/>" 
	onclick="javascript: relativeRedir('/article/user/myList.do');"/>

</form:form>
