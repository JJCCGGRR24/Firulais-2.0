
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<spring:message code="dashboard.queryC1" var="q1"/>
<spring:message code="dashboard.queryC2" var="q2"/>
<spring:message code="dashboard.queryC3" var="q3"/>
<spring:message code="dashboard.queryC4" var="q4"/>
<spring:message code="dashboard.queryC5" var="q5"/>
<spring:message code="dashboard.queryB1" var="q6"/>
<spring:message code="dashboard.queryB2" var="q7"/>
<spring:message code="dashboard.queryB3" var="q8"/>
<spring:message code="dashboard.queryA1" var="q9"/>
<spring:message code="dashboard.queryA2" var="q10"/>
<spring:message code="dashboard.queryA3" var="q11"/>
<spring:message code="dashboard.queryNewC2" var="q13"/>
<spring:message code="dashboard.queryNewC3" var="q14"/>
<spring:message code="dashboard.queryNewC1B4" var="q15"/>
<spring:message code="dashboard.queryNewB2" var="q16"/>
<spring:message code="dashboard.queryNewB3" var="q17"/>
<spring:message code="dashboard.queryNewB1" var="q18"/>





<jstl:out value="${q1}:"/><br/><b>
	<spring:message code="dashboard.avg"/> <fmt:formatNumber value="${queryC1[0]}" maxFractionDigits="2"/>
	<jstl:if test="${queryC1[0] eq null}">
		<spring:message code="dashboard.nodata"/>
	</jstl:if><br/>
	<spring:message code="dashboard.stdev"/> <fmt:formatNumber value="${queryC1[1]}" maxFractionDigits="2"/>
	<jstl:if test="${queryC1[1] eq null}">
		<spring:message code="dashboard.nodata"/>
	</jstl:if><br/>
</b><br/><br/>

<jstl:out value="${q2}:"/><br/><b>
<fmt:formatNumber value="${queryC2}" maxFractionDigits="2"/><jstl:if test="${queryC2 eq null}">
		<spring:message code="dashboard.nodata"/>
	</jstl:if></b><br/><br/>

<jstl:out value="${q3}:"/><br/><b>
	<spring:message code="dashboard.avg"/> <fmt:formatNumber value="${queryC3[0]}" maxFractionDigits="2"/><jstl:if test="${queryC3[0] eq null}">
		<spring:message code="dashboard.nodata"/>
	</jstl:if><br/>
	<spring:message code="dashboard.stdev"/> <fmt:formatNumber value="${queryC3[1]}" maxFractionDigits="2"/><jstl:if test="${queryC3[1] eq null}">
		<spring:message code="dashboard.nodata"/>
	</jstl:if><br/>
</b><br/><br/>

<jstl:out value="${q4}:"/><br/><b>
	<spring:message code="dashboard.avg"/> <fmt:formatNumber value="${queryC4[0]}" maxFractionDigits="2"/><jstl:if test="${queryC4[0] eq null}">
		<spring:message code="dashboard.nodata"/>
	</jstl:if><br/>
	<spring:message code="dashboard.stdev"/> <fmt:formatNumber value="${queryC4[1]}" maxFractionDigits="2"/><jstl:if test="${queryC4[1] eq null}">
		<spring:message code="dashboard.nodata"/>
	</jstl:if><br/>
</b><br/><br/>

<jstl:out value="${q5}:"/><br/>
<display:table name="${queryC5}" id="row" class="displaytag">
	<acme:column code="dashboard.rendezvous.name" property ="name"/>
	<acme:column code="dashboard.rendezvous.description" property="description"/>
	<acme:column code="dashboard.rendezvous.moment" property="moment"/>
	<display:column titleKey="dashboard.rendezvous.coordinates">
		<jstl:if test="${row.coordinates != null}">
			<spring:message code="dashboard.rendezvous.coordinates.latitude" />: <jstl:out
				value="${row.coordinates.latitude }" />
			<br />
			<spring:message code="dashboard.rendezvous.coordinates.longitude" />: <jstl:out
				value="${row.coordinates.longitude}" />
			<br />
		</jstl:if>
	</display:column>
	<display:column titleKey="dashboard.rendezvous.attendants">
		<a href="user/rendezvous/list.do?rendezvousId=${row.id}"><spring:message
				code="user.view" /></a>
	</display:column>
	<display:column titleKey="dashboard.rendezvous.announcements">
		<spring:url value="/announcement/list.do" var="editURL">
			<spring:param name="rendezvousId" value="${row.id}"/>
		</spring:url>
		<a href="${editURL}"><spring:message code="user.view" /></a>
	</display:column>
</display:table><br/><br/><br/>


<jstl:out value="${q6}:"/><br/><b>
	<spring:message code="dashboard.avg"/> <fmt:formatNumber value="${queryB1[0]}" maxFractionDigits="2"/><jstl:if test="${queryB1[0] eq null}">
		<spring:message code="dashboard.nodata"/>
	</jstl:if><br/>
	<spring:message code="dashboard.stdev"/> <fmt:formatNumber value="${queryB1[1]}" maxFractionDigits="2"/><jstl:if test="${queryB1[1] eq null}">
		<spring:message code="dashboard.nodata"/>
	</jstl:if><br/>
</b><br/><br/>


<jstl:out value="${q7}:"/><br/>
<display:table name="${queryB2}" id="row" class="displaytag">
	<acme:column code="dashboard.rendezvous.name" property ="name"/>
	<acme:column code="dashboard.rendezvous.description" property="description"/>
	<acme:column code="dashboard.rendezvous.moment" property="moment"/>
	<display:column titleKey="dashboard.rendezvous.coordinates">
		<jstl:if test="${row.coordinates != null}">
			<spring:message code="dashboard.rendezvous.coordinates.latitude" />: <jstl:out
				value="${row.coordinates.latitude }" />
			<br />
			<spring:message code="dashboard.rendezvous.coordinates.longitude" />: <jstl:out
				value="${row.coordinates.longitude}" />
			<br />
		</jstl:if>
	</display:column>
	<display:column titleKey="dashboard.rendezvous.attendants">
		<a href="user/rendezvous/list.do?rendezvousId=${row.id}"><spring:message
				code="user.view" /></a>
	</display:column>
	<display:column titleKey="dashboard.rendezvous.announcements">
		<spring:url value="/announcement/list.do" var="editURL">
			<spring:param name="rendezvousId" value="${row.id}"/>
		</spring:url>
		<a href="${editURL}"><spring:message code="user.view" /></a>
	</display:column>
</display:table><br/><br/><br/>
 

<jstl:out value="${q8}:"/><br/>
<display:table name="${queryB3}" id="row" class="displaytag">
	<acme:column code="dashboard.rendezvous.name" property ="name"/>
	<acme:column code="dashboard.rendezvous.description" property="description"/>
	<acme:column code="dashboard.rendezvous.moment" property="moment"/>
	<display:column titleKey="dashboard.rendezvous.coordinates">
		<jstl:if test="${row.coordinates != null}">
			<spring:message code="dashboard.rendezvous.coordinates.latitude" />: <jstl:out
				value="${row.coordinates.latitude }" />
			<br />
			<spring:message code="dashboard.rendezvous.coordinates.longitude" />: <jstl:out
				value="${row.coordinates.longitude}" />
			<br />
		</jstl:if>
	</display:column>
	
	<display:column titleKey="dashboard.rendezvous.attendants">
		<a href="user/rendezvous/list.do?rendezvousId=${row.id}"><spring:message
				code="user.view" /></a>
	</display:column>
	<display:column titleKey="dashboard.rendezvous.announcements">
		<spring:url value="/announcement/list.do" var="editURL">
			<spring:param name="rendezvousId" value="${row.id}"/>
		</spring:url>
		<a href="${editURL}"><spring:message code="user.view" /></a>
	</display:column>
</display:table><br/><br/><br/>


<jstl:out value="${q9}:"/><br/><b>
	<spring:message code="dashboard.avg"/> <fmt:formatNumber value="${queryA1[0]}" maxFractionDigits="2"/><jstl:if test="${queryA1[0] eq null}">
		<spring:message code="dashboard.nodata"/>
	</jstl:if><br/>
	<spring:message code="dashboard.stdev"/> <fmt:formatNumber value="${queryA1[1]}" maxFractionDigits="2"/><jstl:if test="${queryA1[1] eq null}">
		<spring:message code="dashboard.nodata"/>
	</jstl:if><br/>
</b><br/><br/>

<jstl:out value="${q10}:"/><br/><b>
	<spring:message code="dashboard.avg"/> <fmt:formatNumber value="${queryA2[0]}" maxFractionDigits="2"/><jstl:if test="${queryA2[0] eq null}">
		<spring:message code="dashboard.nodata"/>
	</jstl:if><br/>
	<spring:message code="dashboard.stdev"/> <fmt:formatNumber value="${queryA2[1]}" maxFractionDigits="2"/><jstl:if test="${queryA2[1] eq null}">
		<spring:message code="dashboard.nodata"/>
	</jstl:if><br/>
</b><br/><br/>

<jstl:out value="${q11}:"/><br/><b>
	<spring:message code="dashboard.avg"/> <fmt:formatNumber value="${queryA3[0]}" maxFractionDigits="2"/><jstl:if test="${queryA3[0] eq null}">
		<spring:message code="dashboard.nodata"/>
	</jstl:if><br/>
	<spring:message code="dashboard.stdev"/> <fmt:formatNumber value="${queryA3[1]}" maxFractionDigits="2"/><jstl:if test="${queryA3[1] eq null}">
		<spring:message code="dashboard.nodata"/>
	</jstl:if><br/>
</b><br/><br/> 


<jstl:out value="${q13}:"/><br/>
<display:table name="${queryNewC2}" id="row" class="displaytag">
	<acme:column code="dashboard.manager.name" property ="name"/>
	<acme:column code="dashboard.manager.surname" property ="surname"/>
	<acme:column code="dashboard.manager.email" property ="email"/>
	<acme:column code="dashboard.manager.phone" property ="phone"/>
	<acme:column code="dashboard.manager.postalAddress" property ="postalAddress"/>
	<acme:column code="dashboard.manager.vat" property ="vat"/>
</display:table><br/><br/><br/>

<jstl:out value="${q14}:"/><br/>
<display:table name="${queryNewC3}" id="row" class="displaytag">
	
	<acme:column code="dashboard.manager.name" property ="name"/>
	<acme:column code="dashboard.manager.surname" property ="surname"/>
	<acme:column code="dashboard.manager.email" property ="email"/>
	<acme:column code="dashboard.manager.phone" property ="phone"/>
	<acme:column code="dashboard.manager.postalAddress" property ="postalAddress"/>
	<acme:column code="dashboard.manager.vat" property ="vat"/>
</display:table><br/><br/><br/>

<jstl:out value="${q15}:"/><br/>
<display:table name="${queryNewC1B4}" id="row" class="displaytag">
	
	<acme:column code="servicce.name" property ="name"/>
	<acme:column code="servicce.description" property ="description"/>
	<spring:message code="servicce.picture" var="pictureS"/>
	<display:column title="${pictureS}">
		<img src="picture"/>
	</display:column>
	<acme:column code="servicce.category" property ="category.name"/>
</display:table><br/><br/><br/>
 
<jstl:out value="${q16}:"/><br/><b>
	<fmt:formatNumber value="${queryNewB2}" maxFractionDigits="2"/><jstl:if test="${queryNewB2 eq null}">
	<spring:message code="dashboard.nodata"/>
	</jstl:if>
</b><br/><br/><br/>

 
<jstl:out value="${q17}:"/><br/><b>
	<spring:message code="dashboard.avg"/> <fmt:formatNumber value="${queryNewB3[0]}" maxFractionDigits="2"/><jstl:if test="${queryNewB3[0] eq null}">
		<spring:message code="dashboard.nodata"/>
	</jstl:if><br/>
	<spring:message code="dashboard.min"/> <fmt:formatNumber value="${queryNewB3[1]}" maxFractionDigits="2"/><jstl:if test="${queryNewB3[1] eq null}">
		<spring:message code="dashboard.nodata"/>
		</jstl:if><br/>
	<spring:message code="dashboard.max"/> <fmt:formatNumber value="${queryNewB3[2]}" maxFractionDigits="2"/><jstl:if test="${queryNewB3[2] eq null}">
		<spring:message code="dashboard.nodata"/>
	</jstl:if><br/>
	<spring:message code="dashboard.stdev"/> <fmt:formatNumber value="${queryNewB3[3]}" maxFractionDigits="2"/><jstl:if test="${queryNewB3[3] eq null}">
		<spring:message code="dashboard.nodata"/>
		</jstl:if>
</b><br/><br/>  

<jstl:out value="${q18}:"/><br/><b>
	<fmt:formatNumber value="${queryNewB1}" maxFractionDigits="2"/><jstl:if test="${queryNewB1 eq null}">
	<spring:message code="dashboard.nodata"/>
	</jstl:if>
</b><br/><br/><br/>