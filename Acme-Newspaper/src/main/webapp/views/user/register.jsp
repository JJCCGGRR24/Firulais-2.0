
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<form:form action="register/user.do" modelAttribute="registerForm">



	<spring:message code="register.account" var="account" />
	<fieldset>
		<legend>
			<b>${account }</b>
		</legend>
		<acme:textbox code="register.username" path="username" />
		<acme:password code="register.password" path="password" />
		<acme:password code="register.password.confirm" path="confirmPassword" />
		<br />
	</fieldset>

	<spring:message code="register.personal" var="personal" />
	<fieldset>
		<legend>
			<b>${personal }</b>
		</legend>
		<acme:textbox code="register.name" path="name" />
		<acme:textbox code="register.surname" path="surname" />
		<acme:textbox code="register.phone" path="phone" />
		<acme:textbox code="register.postalAddress" path="postalAddress" />
	</fieldset>


	<br />


		
	
	<form:label path="check">
	</form:label>	
	<form:checkbox path="check"/>
	<spring:message code="register.check" />	
  	<form:errors path="check" cssClass="error" />
  	</br></br>
  	
	
	
	<acme:submit name="save" code="register.save" />

</form:form>