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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<security:authorize access="hasRole('USER')">
	<spring:message code="register.name" var="name" />
	<spring:message code="register.surname" var="surname" />
	<spring:message code="register.email" var="email" />
	<spring:message code="register.phone" var="phone" />
	<spring:message code="register.postalAddress" var="postalAddress" />
</security:authorize>

<display:table name="chirps" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag" sort="list" defaultsort="1"
	defaultorder="descending">
	<display:column sortProperty="moment">
		<div style="justify-content: space-between; display: flex;">
			<b>${row.title}</b><a href="user/details.do?userId=${row.user.id}">${row.user.name}</a>
			<div style="display: inline-block;">
				<fmt:formatDate value="${row.moment}" pattern="dd/MM/yy" />
				<b><fmt:formatDate value="${row.moment}" pattern="HH:mm:ss" /></b>
			</div>
		</div>
		<div style="justify-content: space-between; display: flex;">
			${row.description}
			<security:authorize access="hasRole('ADMIN')">
				<div style="display: inline-block">
					<div>
						<input type="button" name="cancel"
							value="<spring:message code="template.delete"/>"
							onclick="javascript: relativeRedir('chirp/admin/delete.do?chirpId='+${row.id});" />
					</div>
				</div>
			</security:authorize>
		</div>
	</display:column>

</display:table>
<br>
<spring:message code="register.back" var="back" />
<input type="button" name="back" value="${back}"
	onclick="javascript:relativeRedir('user/list.do')" />


