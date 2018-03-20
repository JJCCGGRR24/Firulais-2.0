<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="rendezvous/user/rsvp.do" modelAttribute="answersForm">
	
	
	<form:hidden path="rid"/>
<%-- 	<form:hidden path="las"/> --%>


	<img src="${rendezvous.picture}" style="height: 100px"/>
	<h2><jstl:out value="${rendezvous.name}"/></h2>
	
	
	<jstl:if test="${empty answersForm.las}">
		<spring:message code="answer.none"/><br/><br/>
	</jstl:if>
	
	<jstl:forEach items="${answersForm.las}" var="i" varStatus="loop">
<%-- 		<form:hidden path="las[${loop.index}].id"/> --%>
		<h3><jstl:out value="${i.question.text}"/></h3>
		<form:label path="las[${loop.index}].text">
			<spring:message code="answer.text"/>:
		</form:label>
		<form:input path="las[${loop.index}].text" type="text"/>
		<form:errors cssClass="error" path="las[${loop.index}].text"/>
		<br/><br/>
	</jstl:forEach>

	<input type="submit" name="save" value='<spring:message code="answer.attend"/>'>
	<input type="button" name="cancel" value='<spring:message code="template.cancel"/>' onclick="document.location.href='rendezvous/list.do?rendezvousId=${answer.question.id}';">
</form:form>



</body>
</html>