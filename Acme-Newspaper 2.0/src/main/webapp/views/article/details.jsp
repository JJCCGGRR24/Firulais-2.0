<%--
 * action-2.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div align="center">
	<img SRC="${banner}" width="400" height="200" />
</div>

<div style="margin: 0 20% 0 20%">
	<h1 style="text-decoration: underline;">${article.title}</h1>
	<h2>${article.summary}</h2>
	<b> (<fmt:formatDate value="${article.moment}"
			pattern="dd/MM/yy HH:mm" />)
	</b> <br> <br>
	<jstl:if test="${!(article.pictures eq null)}">
		<jstl:forEach items="${article.pictures}" var="pic">
			<div align="center">
				<img src="${pic}" alt="${article.title}" style="max-height: 350px;" />
			</div>
		</jstl:forEach>
	</jstl:if>
	<br>
	<p style="text-align: justify;">${fn:replace(article.body,'\\n',"<br>")}</p>
	<%-- 	<p style="text-align:justify;">${article.body}</p> --%>
	<br>
	<jstl:forEach items="${article.followUps}" var="fus" varStatus="i">
		<jstl:if test="${i.index<fn:length(article.followUps)}">
			<hr>
		</jstl:if>
		<h2 style="margin-bottom: 0; text-decoration: underline;">${fus.title}</h2>
		<h3 style="margin-bottom: 0;">${fus.summary}</h3>
	(<fmt:formatDate value="${fus.publicationMoment}"
			pattern="dd/MM/yy HH:mm" />)
	<jstl:if test="${!(fus.pictures eq null)}">
			<jstl:forEach items="${fus.pictures}" var="pic">
				<div align="center">
					<img src="${pic}" alt="${fus.title}" style="max-height: 350px;" />
				</div>
			</jstl:forEach>
		</jstl:if>
		<br>
		<p style="text-align: justify;">${fus.text}</p>
	</jstl:forEach>
</div>