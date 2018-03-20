<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="question/user/edit.do" modelAttribute="question">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="rendezvous"/>
	<form:hidden path="answers"/>

<%-- 	<h2><jstl:out value="${question.rendezvous.name}"/></h2> --%>
	
	<form:label path="text">
		<spring:message code="question.text"/>:
	</form:label>
	<form:input path="text" type="text"/>
	<form:errors cssClass="error" path="text"/>
	<br /><br />
	
	<input type="submit" name="save" value='<spring:message code="template.save"/>'>
	<jstl:if test="${question.id != 0}">
		<input type="submit" name="delete" value='<spring:message code="template.delete"/>'>
	</jstl:if>
	<input type="button" name="cancel" value='<spring:message code="template.cancel"/>' onclick="document.location.href='question/user/list.do?rendezvousId=${question.rendezvous.id}';">
</form:form>


</body>
</html>