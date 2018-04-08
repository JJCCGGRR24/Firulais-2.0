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

<h1> ${article.title} </h1> 
<b> (${article.moment}) </b>
<br>
${article.summary }
<br>
<jstl:if test="${!(article.pictures eq null)}">
<jstl:forEach items="${ article.pictures}" var="pic">
	<div align="center">
		<img src="${pic}" alt="${article.title }" width="50%" height="50%" ></img>
	</div>
</jstl:forEach>
</jstl:if>
<br>
${article.body }

