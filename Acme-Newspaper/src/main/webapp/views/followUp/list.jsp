<?xml version="1.0" encoding="UTF-8" ?>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<display:table  name="followUps" id="row"  pagesize="10" requestURI="${requestURI}" class="displaytag" > 

	<spring:message code="followUp.title" var="title"></spring:message>
	<display:column titleKey="followUp.title">
		${row.title}
	</display:column>
	
	<display:column titleKey="followUp.summary">
		<jstl:if test="${fn:length(row.summary) > 100}">
			<span class="teaser">${fn:substring(row.summary, 0, 100)}</span>
			<span class="complete">${row.summary}</span>
			<span class="more">${template.more}...</span>
		</jstl:if>
		<jstl:if test="${!(fn:length(row.summary) > 100)}">
			${row.summary}
		</jstl:if>
	</display:column>
	
	<display:column titleKey="followUp.text">
		<jstl:if test="${fn:length(row.text) > 100}">
			<span class="teaser">${fn:substring(row.text, 0, 100)}</span>
			<span class="complete">${row.text}</span>
			<span class="more">${template.more}...</span>
		</jstl:if>
		<jstl:if test="${!(fn:length(row.text) > 100)}">
			${row.text}
		</jstl:if>
	</display:column>
	
	<display:column titleKey="followUp.article">
		<spring:url value="/article/details.do" var="editURL">
			<spring:param name="articleId" value="${row.article.id}"/>
		</spring:url>
		<a href="${editURL}"><spring:message code="register.view"></spring:message></a>
			
	</display:column>
	
		<spring:message code="event.format.date" var="pattern"/>
	
	<display:column titleKey="followUp.moment" property ="publicationMoment" format="${pattern}"/>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="javascript:preguntar(${row.id})"><spring:message
					code="newspaper.delete" /></a>
		</display:column>
	</security:authorize>
	

</display:table>

<script>
	$(".more").toggle(function(){
	    $(this).text("<spring:message code="template.less"/>...").siblings(".teaser").hide();  
	    $(this).text("<spring:message code="template.less"/>...").siblings(".complete").show();    
	}, function(){
		$(this).text("<spring:message code="template.more"/>...").siblings(".teaser").show();
		$(this).text("<spring:message code="template.more"/>...").siblings(".complete").hide();    
	});
</script>