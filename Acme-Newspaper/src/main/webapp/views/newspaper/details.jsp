<%--
 * action-2.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<jstl:if test="${!(newspaper.picture eq null)}">
	<div align="center">
		<img src="${newspaper.picture }" alt="${picture.title }" ></img>
	</div>
</jstl:if>
<b> <spring:message code="general.title"/>:</b> ${newspaper.title} - (${newspaper.publicationDate})
<br>
<b> <spring:message code="general.description"/>:</b> ${newspaper.description }
<br>

<display:table name="articles" id="row" requestURI="${requestURI}"
	pagesize="10" class="displaytag">

	<display:column titleKey="general.title" >
		<a href="article/details.do?articleId=${row.id}">${row.title}</a>
	</display:column>
	<display:column titleKey="article.writer" >
		<a href="user/details.do?userId=${row.user.id}">${row.user.userAccount.username}</a>
	</display:column>
	<display:column property="summary" titleKey="article.summary" />
	
</display:table>