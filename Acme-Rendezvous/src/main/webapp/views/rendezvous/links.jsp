<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<img src="${rendezvous.picture}" style="height: 100px"/>
<h2><jstl:out value="${rendezvous.name}"/></h2>

<display:table name="links" id="row" requestURI="rendezvous/user/links.do" pagesize="5" class="displaytag">
	
	<display:column titleKey="rendezvous.name">
		<security:authorize access="!hasRole('USER')">
			<a href="rendezvous/all/links.do?rendezvousId=${row.id}"><jstl:out value="${row.name}"/></a>
		</security:authorize>
		<security:authorize access="hasRole('USER')">
			<a href="rendezvous/user/links.do?rendezvousId=${row.id}"><jstl:out value="${row.name}"/></a>
		</security:authorize>
	</display:column>
	
	<jstl:if test="${editable}">
		<display:column>
			<input type="button"
			value="<spring:message code="template.delete" />"
			onclick="document.location.href='rendezvous/user/deleteLink.do?rendezvousId='+ ${rendezvous.id}+'&linkedId='+${row.id};" />
		</display:column>
	</jstl:if>
	
</display:table>
<br/>
<jstl:if test="${!linkable}">
	<spring:message code="rendezvous.nolinks"/>
</jstl:if>
<jstl:if test="${editable && linkable}">
	<input type="button"
	value="<spring:message code="rendezvous.link" />"
	onclick="document.location.href='rendezvous/user/link.do?rendezvousId='+ ${rendezvous.id};" />
</jstl:if>	<br/><br/>
