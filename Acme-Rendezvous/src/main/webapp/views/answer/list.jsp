<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h2><jstl:out value="${question.text}"/></h2>
<display:table name="answers" id="row" requestURI="${requestURI}" pagesize="0" class="displaytag">
	
	<display:column property="text" titleKey="answer.text"></display:column>

	<display:column titleKey="answer.user">
		<jstl:out value="${row.user.userAccount.username}"/>
	</display:column>
	
</display:table>

<%-- <jstl:if test="${creable}"> --%>
<!-- 	<input type="button" -->
<%-- 	value="<spring:message code="answer.create" />" --%>
<%-- 	onclick="document.location.href='answer/user/create.do?questionId='+ ${question.id};" /> --%>
<%-- </jstl:if>	 --%>
