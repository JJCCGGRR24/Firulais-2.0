<%--
 * footer.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:useBean id="date" class="java.util.Date" />

<hr />

<div align="center">

<a href="law/terminosYCondiciones.do"><spring:message code="master.page.terminosYCondiciones" /></a>
<img src="images/padlock.ico" alt="Sample-Project-1.9 Co., Inc." width="15" height="15"/>
<a href="law/politicaCookies.do"><spring:message code="master.page.politicaCookies" /></a>
<img src="images/cookie.ico" alt="Sample-Project-1.9 Co., Inc." width="20" height="20"/>
<a href="law/avisoLegal.do"><spring:message code="master.page.avisoLegal" /></a>
<img src="images/law.ico" alt="Sample-Project-1.9 Co., Inc." width="15" height="15"/>

<br>

<b>Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> Acme-Rendezvous Co., Inc.</b>
</div>