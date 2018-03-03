<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<img src="${rendezvous.picture}" style="height: 100px"/>
<h2><jstl:out value="${rendezvous.name}"/></h2>

<display:table name="questions" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<display:column property="text" titleKey="question.text"></display:column>
	
	<jstl:if test="${editable}">
		<display:column>
			<input type="button"
			value="<spring:message code="template.edit" />"
			onclick="document.location.href='question/user/edit.do?questionId='+ ${row.id};" />
		</display:column>
	</jstl:if>	
	
	
	<display:column>
		<security:authorize access="hasRole('USER')">
			<input type="button"
			value="<spring:message code="question.answers" />"
			onclick="document.location.href='answer/user/list.do?questionId='+ ${row.id};" />
		</security:authorize>
		<security:authorize access="not hasRole('USER')">
			<input type="button"
			value="<spring:message code="question.answers" />"
			onclick="document.location.href='answer/all/list.do?questionId='+ ${row.id};" />
		</security:authorize>
	</display:column>
</display:table>

<jstl:if test="${editable}">
	<input type="button"
	value="<spring:message code="template.create" />"
	onclick="document.location.href='question/user/create.do?rendezvousId='+ ${rendezvous.id};" />
</jstl:if>	
