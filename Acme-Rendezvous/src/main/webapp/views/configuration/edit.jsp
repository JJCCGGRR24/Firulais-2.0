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


<form:form action="configuration/admin/edit.do" modelAttribute="configuration">

		<form:hidden path="id" />
		<form:hidden path="version" />

	
		<acme:textbox code="configuration.nameBusiness" path="nameBusiness" />
		<acme:textbox code="configuration.banner" path="banner" />
		<acme:textarea code="configuration.messageES" path="messageES" />
		<acme:textarea code="configuration.messageEN" path="messageEN" />
		
	
	<acme:cancel2 url=""/>
	
	<acme:save />

</form:form>



