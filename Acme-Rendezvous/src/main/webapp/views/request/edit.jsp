<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
	
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	
<script>
	function doSomething() {
		setCookie('creditCard.holderName', document.getElementById('creditCard.holderName').value, 365);
		setCookie('creditCard.brandName', document.getElementById('creditCard.brandName').value, 365);
		setCookie('creditCard.number', document.getElementById('creditCard.number').value, 365);
		setCookie('creditCard.expirationYear', document.getElementById('creditCard.expirationYear').value, 365);
		setCookie('creditCard.expirationMonth', document.getElementById('creditCard.expirationMonth').value, 365);
		setCookie('creditCard.CVV', document.getElementById('creditCard.CVV').value, 365);
	    return true;
	}
</script>

<form:form action="${requestURI}" modelAttribute="request" onsubmit="return doSomething();">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="rendezvous"/>
	
	<acme:textarea code="rquest.comment" path="comment" />
	
	<form:label path="servicce">
    <spring:message code="rquest.servicce" />:
  </form:label>
  <form:select path="servicce">
    <form:option value="0" label="-"/>
    <form:options items="${serviccesVisibles}" itemLabel="name" itemValue="id"/>
  </form:select>
  <form:errors cssClass="error" path="servicce" />
  <br/><br/>
  
  <form:label path="creditCard.holderName">
		<spring:message code="request.creditCard.holderName" />:
	</form:label>
	<form:input path="creditCard.holderName" type="text"/>
	<form:errors cssClass="error" path="creditCard.holderName"/>
	<br />
	
	<spring:message code="request.creditCard.brandName"/>
	<form:select path="creditCard.brandName" >
		<form:option value="VISA" label="VISA"/>
		<form:option value="MASTERCARD" label="MASTERCARD"/>
		<form:option value="DISCOVER" label="DISCOVER"/>
		<form:option value="DINNERS" label="DINNERS"/>
		<form:option value="AMEX" label="AMEX"/>
	
	</form:select>
	<form:errors cssClass="error" path="creditCard.brandName"/>
	<br />
	<br />
	
	<form:label path="creditCard.number">
		<spring:message code="request.creditCard.number" />:
	</form:label>
	<form:input path="creditCard.number" type="text"/>
	<form:errors cssClass="error" path="creditCard.number"/>
	<br />
	
	<form:label path="creditCard.expirationYear">
		<spring:message code="request.creditCard.expirationYear" />:
	</form:label>
	<form:input path="creditCard.expirationYear" type="number" min="2018" max="3000"/>
	<form:errors cssClass="error" path="creditCard.expirationYear"/>
	<br />
	
	<form:label path="creditCard.expirationMonth">
		<spring:message code="request.creditCard.expirationMonth" />:
	</form:label>
	<form:input path="creditCard.expirationMonth" type="number" min="1" max="12"/>
	<form:errors cssClass="error" path="creditCard.expirationMonth"/>
	<br />
	
	<form:label path="creditCard.CVV">
		<spring:message code="request.creditCard.CVV" />:
	</form:label>
	<form:input path="creditCard.CVV" type="number" min="100" max="999"/>
	<form:errors cssClass="error" path="creditCard.CVV"/>
	<br />

	<security:authorize access="hasRole('USER')">
	<input type="submit" name="save"  value="<spring:message code="save"/>" />
	</security:authorize>
		
	<security:authorize access="hasRole('USER')">
	<input type="button" name="cancel" value="<spring:message code="template.cancel"/>" 
	onclick="javascript: relativeRedir('request/user/list.do?rendezvousId='+${request.rendezvous.id});"/>
	</security:authorize>
	
</form:form>
<script>
	document.getElementById('creditCard.holderName').value = getCookie('creditCard.holderName');
	document.getElementById('creditCard.brandName').value = getCookie('creditCard.brandName');
	document.getElementById('creditCard.number').value = getCookie('creditCard.number');
	document.getElementById('creditCard.expirationYear').value = getCookie('creditCard.expirationYear');
	document.getElementById('creditCard.expirationMonth').value = getCookie('creditCard.expirationMonth');
	document.getElementById('creditCard.CVV').value = getCookie('creditCard.CVV');
</script>