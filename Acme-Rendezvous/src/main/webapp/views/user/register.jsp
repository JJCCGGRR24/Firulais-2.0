<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<form:form action="register/user.do" modelAttribute="registerForm">



	<spring:message code="user.account" var="account" />
	<fieldset>
		<legend>
			<b>${account }</b>
		</legend>
		<acme:textbox code="user.username" path="username" />
		<acme:password code="user.password" path="password" />
		<acme:password code="user.password.confirm" path="passwordConfirm" />
		<br />
	</fieldset>

	<spring:message code="user.personal" var="personal" />
	<fieldset>
		<legend>
			<b>${personal }</b>
		</legend>
		<acme:textbox code="user.name" path="name" />
		<acme:textbox code="user.surname" path="surname" />
		<acme:textbox code="user.phone" path="phone" />
		<acme:textbox code="user.email" path="email" />
		<acme:date code="user.birthdate" path="birthdate" placeholder="dd/MM/yyyy" />
		<acme:textbox code="user.postalAddress" path="postalAddress" />
	</fieldset>


	<br />


		
	
	<form:label path="check">
	</form:label>	
	<form:checkbox path="check"/>
	<spring:message code="register.check" />
	<form:errors path="check" cssClass="error" />
	
	<acme:cancel2 url=""/>
	
	<acme:submit name="save" code="general.save" />

</form:form>



