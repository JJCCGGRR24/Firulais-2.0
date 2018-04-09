<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	
<form:form action="${requestURI}" modelAttribute="subscribe">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="newspaper"/>
	<form:hidden path="customer"/>

	<form:label path="creditCard.holderName">
		<spring:message code="sponsorship.creditCard.holderName" />:
	</form:label>
	<form:input path="creditCard.holderName" type="text"/>
	<form:errors cssClass="error" path="creditCard.holderName"/>
	<br />
	
	<spring:message code="sponsorship.creditCard.brandName"/>
	<form:select path="creditCard.brandName" >
		<form:option value="VISA" label="VISA"/>
		<form:option value="MASTERCARD" label="MASTERCARD"/>
		<form:option value="DISCOVER" label="DISCOVER"/>
		<form:option value="DINNERS" label="DINNERS"/>
		<form:option value="AMEX" label="AMEX"/>
	
	</form:select>
	<br />
	
	<form:label path="creditCard.number">
		<spring:message code="sponsorship.creditCard.number" />:
	</form:label>
	<form:input path="creditCard.number" type="text"/>
	<form:errors cssClass="error" path="creditCard.number"/>
	<br />
	
	<form:label path="creditCard.expirationYear">
		<spring:message code="sponsorship.creditCard.expirationYear" />:
	</form:label>
	<form:input path="creditCard.expirationYear" type="text"/>
	<form:errors cssClass="error" path="creditCard.expirationYear"/>
	<br />
	
	<form:label path="creditCard.expirationMonth">
		<spring:message code="sponsorship.creditCard.expirationMonth" />:
	</form:label>
	<form:input path="creditCard.expirationMonth" type="text"/>
	<form:errors cssClass="error" path="creditCard.expirationMonth"/>
	<br />
	
	<form:label path="creditCard.CVV">
		<spring:message code="sponsorship.creditCard.CVV" />:
	</form:label>
	<form:input path="creditCard.CVV" type="text"/>
	<form:errors cssClass="error" path="creditCard.CVV"/>
	<br />

	<input type="submit" name="save" value="<spring:message code='subscribe.toSubscribe'/>" />

	<input type="button" value="<spring:message code="template.cancel"/>" 
	onclick="document.location.href='newspaper/list.do'"/>

</form:form>
