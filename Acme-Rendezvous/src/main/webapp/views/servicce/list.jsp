<%--
 * action-2.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="servicces" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<security:authorize access="isAuthenticated()">
		<security:authentication property="principal.username" var="prin" />
		<jstl:if
			test="${row.manager.userAccount.username eq prin && not row.cancelled}">
			<display:column>
				<input type="button" name="edit"
					value="<spring:message code="template.edit" />"
					onclick="document.location.href='servicce/manager/edit.do?servicceId='+ ${row.id};" />
			</display:column>
		</jstl:if>
	</security:authorize>
	<spring:message code="servicce.name" var="name"></spring:message>
	<display:column property="name" title="${name}" />


	<spring:message code="servicce.description" var="description"></spring:message>
	<display:column property="description" title="${description}" />

	<%-- 	<spring:message code="servicce.cancelled" var="cancelled"></spring:message> --%>
	<%-- 	<display:column property="cancelled" title="${cancelled}" /> --%>

	<display:column titleKey="servicce.cancelled">
		<jstl:if test="${row.cancelled}">
			<img
				src="http://www.clker.com/cliparts/2/k/n/l/C/Q/transparent-green-checkmark-hi.png"
				height="30px" />
			<span class="rojo"></span>
		</jstl:if>
		<jstl:if test="${!row.cancelled}">
			<img
				src="https://vignette.wikia.nocookie.net/house-of-cards/images/a/a5/X.png/revision/latest?cb=20161128021903"
				height="30px" />
		</jstl:if>
	</display:column>

	<display:column>
		<img style="max-height: 100px; max-width: 100px;" src="${row.picture}" />
	</display:column>

	<security:authorize access="hasRole('ADMIN')">

		<jstl:if test="${row.cancelled eq false}">
			<display:column>
				<spring:url value="/servicce/administrator/cancell.do" var="editURL">
					<spring:param name="servicceId" value="${row.id}" />
				</spring:url>
				<a href="${editURL}"><spring:message
						code="servicce.admin.cancell" /></a>
			</display:column>
		</jstl:if>

	</security:authorize>

</display:table>

<script>
	var name = "rojo";
	var items = document.getElementsByClassName(name);
	for ( var i = 0; i < items.length; i++) {
		document.getElementsByClassName(name)[i].parentNode.parentNode.style.backgroundColor = "indianred";
	}
</script>