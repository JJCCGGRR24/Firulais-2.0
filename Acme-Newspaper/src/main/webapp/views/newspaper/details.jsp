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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<spring:message code="newspaper.private.permiss" var="permiss"/>


<jstl:if test="${!(newspaper.picture eq null)}">
	<div align="center">
		<img src="${newspaper.picture }" alt="${picture.title }" ></img>
	</div>
</jstl:if>
<b> <spring:message code="general.title"/>:</b> ${newspaper.title} - (${newspaper.publicationDate})
<br>
<b> <spring:message code="general.description"/>:</b> ${newspaper.description }
<br>


<jstl:choose>

	<jstl:when test="${newspaper.deprived eq false }">
		<display:table name="articles" id="row" requestURI="${requestURI}"
			pagesize="10" class="displaytag">
			<display:column titleKey="general.title" >
			<a href="article/details.do?articleId=${row.id}">${row.title}</a>
			</display:column>
			<display:column titleKey="article.writer" >
			<a href="user/details.do?userId=${row.user.id}">${row.user.userAccount.username}</a>
			</display:column>
			<display:column titleKey="article.summary">
			<jstl:if test="${fn:length(row.summary) > 100}">
			<span class="teaser">${fn:substring(row.summary, 0, 100)}</span>
			<span class="complete">${row.summary}</span>
			<span class="more">${template.more}...</span>
			</jstl:if>
			<jstl:if test="${!(fn:length(row.summary) > 100)}">
			${row.summary}
			</jstl:if>
			</display:column>
		</display:table>
	</jstl:when>
	
	<jstl:otherwise>
		<security:authorize access="isAnonymous()" >
			<div align="center">
				<h2> <jstl:out  value="${permiss}"></jstl:out></h2>
			</div>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')" >
			<display:table name="articles" id="row" requestURI="${requestURI}"
			pagesize="10" class="displaytag">
			<display:column titleKey="general.title" >
			<a href="article/details.do?articleId=${row.id}">${row.title}</a>
			</display:column>
			<display:column titleKey="article.writer" >
			<a href="user/details.do?userId=${row.user.id}">${row.user.userAccount.username}</a>
			</display:column>
			<display:column titleKey="article.summary">
			<jstl:if test="${fn:length(row.summary) > 100}">
			<span class="teaser">${fn:substring(row.summary, 0, 100)}</span>
			<span class="complete">${row.summary}</span>
			<span class="more">${template.more}...</span>
			</jstl:if>
			<jstl:if test="${!(fn:length(row.summary) > 100)}">
			${row.summary}
			</jstl:if>
			</display:column>
			</display:table>
		</security:authorize>
		
		<security:authorize access="hasRole('USER')" >
			<security:authentication property="principal.username" var="username"/>
			<jstl:choose>
				<jstl:when test="${newspaper.user.userAccount.username eq username }">
					<display:table name="articles" id="row" requestURI="${requestURI}"
					pagesize="10" class="displaytag">
					<display:column titleKey="general.title" >
					<a href="article/details.do?articleId=${row.id}">${row.title}</a>
					</display:column>
					<display:column titleKey="article.writer" >
					<a href="user/details.do?userId=${row.user.id}">${row.user.userAccount.username}</a>
					</display:column>
					<display:column titleKey="article.summary">
					<jstl:if test="${fn:length(row.summary) > 100}">
					<span class="teaser">${fn:substring(row.summary, 0, 100)}</span>
					<span class="complete">${row.summary}</span>
					<span class="more">${template.more}...</span>
					</jstl:if>
					<jstl:if test="${!(fn:length(row.summary) > 100)}">
					${row.summary}
					</jstl:if>
					</display:column>
					</display:table>
				
				</jstl:when>
				
				<jstl:otherwise>
					<div align="center">
						<h2> <jstl:out  value="${permiss}"></jstl:out></h2>
					</div>
				</jstl:otherwise>
			
			</jstl:choose>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')" >
			<jstl:choose>
				<jstl:when test="${customerEstaSubscrito}">
					<display:table name="articles" id="row" requestURI="${requestURI}"
					pagesize="10" class="displaytag">
					<display:column titleKey="general.title" >
					<a href="article/details.do?articleId=${row.id}">${row.title}</a>
					</display:column>
					<display:column titleKey="article.writer" >
					<a href="user/details.do?userId=${row.user.id}">${row.user.userAccount.username}</a>
					</display:column>
					<display:column titleKey="article.summary">
					<jstl:if test="${fn:length(row.summary) > 100}">
					<span class="teaser">${fn:substring(row.summary, 0, 100)}</span>
					<span class="complete">${row.summary}</span>
					<span class="more">${template.more}...</span>
					</jstl:if>
					<jstl:if test="${!(fn:length(row.summary) > 100)}">
					${row.summary}
					</jstl:if>
					</display:column>
					</display:table>
				</jstl:when>
				
				<jstl:otherwise>
					<div align="center">
						<h2> <jstl:out  value="${permiss}"></jstl:out></h2>
					</div>
				</jstl:otherwise>
			</jstl:choose>
		</security:authorize>
	
	</jstl:otherwise>
	
</jstl:choose>








<script>
	$(".more").toggle(function(){
	    $(this).text("<spring:message code="template.less"/>...").siblings(".teaser").hide();  
	    $(this).text("<spring:message code="template.less"/>...").siblings(".complete").show();    
	}, function(){
		$(this).text("<spring:message code="template.more"/>...").siblings(".teaser").show();
		$(this).text("<spring:message code="template.more"/>...").siblings(".complete").hide();    
	});
</script>