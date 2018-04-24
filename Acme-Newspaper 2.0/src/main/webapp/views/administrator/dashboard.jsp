<%--
 * action-1.jsp
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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2>
	<spring:message code="administrator.queryC1" />
</h2>
<b> <spring:message code="administrator.avg" /> :
</b>
<fmt:formatNumber value="${queryC1[0]}" maxFractionDigits="2" />
<jstl:if test="${queryC1[0] eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />

<b><spring:message code="administrator.sttdev" /> :</b>
<fmt:formatNumber value="${queryC1[1]}" maxFractionDigits="2" />
<jstl:if test="${queryC1[0] eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />
<br />

<h2>
	<spring:message code="administrator.queryC2" />
</h2>
<b><spring:message code="administrator.avg" /> :</b>
<fmt:formatNumber value="${queryC2[0]}" maxFractionDigits="2" />
<jstl:if test="${queryC2[0] eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />

<b><spring:message code="administrator.sttdev" /> :</b>
<fmt:formatNumber value="${queryC2[1]}" maxFractionDigits="2" />
<jstl:if test="${queryC2[0] eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />
<br />
<h2>
	<spring:message code="administrator.queryC3" />
</h2>
<b><spring:message code="administrator.avg" /> :</b>
<fmt:formatNumber value="${queryC3[0]}" maxFractionDigits="2" />
<jstl:if test="${queryC3[0] eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />

<b><spring:message code="administrator.sttdev" /> :</b>
<fmt:formatNumber value="${queryC3[1]}" maxFractionDigits="2" />
<jstl:if test="${queryC3[0] eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />
<br />
<h2>
	<spring:message code="administrator.queryC4" />
</h2>
<display:table name="queryC4" id="row" class="displaytag" pagesize="10">
	<display:column titleKey="general.title">
		<jstl:out value="${row}" />
	</display:column>
</display:table>
<br />
<br />
<h2>
	<spring:message code="administrator.queryC5" />
</h2>
<display:table name="queryC5" id="row" class="displaytag" pagesize="10">
	<display:column titleKey="general.title">
		<jstl:out value="${row}" />
	</display:column>
</display:table>
<br />
<br />
<h2>
	<spring:message code="administrator.queryC6" />
</h2>
<b> <spring:message code="administrator.ratio" /> :
</b>
<fmt:formatNumber value="${queryC6}" maxFractionDigits="2" />
<jstl:if test="${queryC6 eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />
<br />
<h2>
	<spring:message code="administrator.queryC7" />
</h2>
<b> <spring:message code="administrator.ratio" /> :
</b>
<fmt:formatNumber value="${queryC7}" maxFractionDigits="2" />
<jstl:if test="${queryC7 eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />
<br />
<h2>
	<spring:message code="administrator.queryB1" />
</h2>
<b> <spring:message code="administrator.avg" /> :
</b>
<fmt:formatNumber value="${queryB1}" maxFractionDigits="2" />
<jstl:if test="${queryB1 eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />
<br />
<h2>
	<spring:message code="administrator.queryB2" />
</h2>
<b> <spring:message code="administrator.avg" /> :
</b>
<fmt:formatNumber value="${queryB2}" maxFractionDigits="2" />
<jstl:if test="${queryB2 eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />
<br />
<h2>
	<spring:message code="administrator.queryB3" />
</h2>
<b> <spring:message code="administrator.avg" /> :
</b>
<fmt:formatNumber value="${queryB3}" maxFractionDigits="2" />
<jstl:if test="${queryB3 eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />
<br />
<h2>
	<spring:message code="administrator.queryB4" />
</h2>
<b><spring:message code="administrator.avg" /> :</b>
<fmt:formatNumber value="${queryB4[0]}" maxFractionDigits="2" />
<jstl:if test="${queryB4[0] eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />

<b><spring:message code="administrator.sttdev" /> :</b>
<fmt:formatNumber value="${queryB4[1]}" maxFractionDigits="2" />
<jstl:if test="${queryB4[0] eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />
<br />
<h2>
	<spring:message code="administrator.queryB5" />
</h2>
<b> <spring:message code="administrator.ratio" /> :
</b>
<fmt:formatNumber value="${queryB5}" maxFractionDigits="2" />
<jstl:if test="${queryB5 eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />
<br />
<h2>
	<spring:message code="administrator.queryA1" />
</h2>
<b> <spring:message code="administrator.ratio" /> :
</b>
<fmt:formatNumber value="${queryA1}" maxFractionDigits="2" />
<jstl:if test="${queryA1 eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />
<br />
<h2>
	<spring:message code="administrator.queryA2" />
</h2>
<b> <spring:message code="administrator.avg" /> :
</b>
<fmt:formatNumber value="${queryA2}" maxFractionDigits="2" />
<jstl:if test="${queryA2 eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />
<br />
<h2>
	<spring:message code="administrator.queryA3" />
</h2>
<b> <spring:message code="administrator.avg" /> :
</b>
<fmt:formatNumber value="${queryA3}" maxFractionDigits="2" />
<jstl:if test="${queryA3 eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />
<br />
<h2>
	<spring:message code="administrator.queryA4" />
</h2>
<b> <spring:message code="administrator.ratio" /> :
</b>
<fmt:formatNumber value="${queryA4}" maxFractionDigits="2" />
<jstl:if test="${queryA4 eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />
<br />
<h2>
	<spring:message code="administrator.queryA5" />
</h2>
<b> <spring:message code="administrator.ratio" /> :
</b>
<fmt:formatNumber value="${queryA5}" maxFractionDigits="2" />
<jstl:if test="${queryA5 eq null}">
	<spring:message code="administrator.nodata" />
</jstl:if>
<br />
<br />