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
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<script >
function preguntar(commentId){
   eliminar=confirm('<spring:message code="comment.confirmDelete"/>');
   if (eliminar)
   //Redireccionamos si das a aceptar
     window.location.href = "comment/administrator/delete.do?commentId=" + commentId; //página web a la que te redirecciona si confirmas la eliminación
	else
  //Y aquí pon cualquier cosa que quieras que salga si le diste al boton de cancelar
    alert('<spring:message code="comment.negativeDelete"/>');
}
</script>

<display:table name="comments" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<spring:message code="comment.user" var="user"></spring:message>
	<display:column property="user.userAccount.username" title="${user}" />
	
	<spring:message code="comment.moment" var="moment"></spring:message>
	<display:column property="moment" title="${moment}" />
	
	<spring:message code="comment.text" var="text"></spring:message>
	<display:column property="text" title="${text}" />
		
	<display:column><img style="max-height: 100px;max-width: 100px;" src="${row.picture}"/></display:column>
	
	<security:authorize access="hasRole('ADMIN')">
	<spring:message code="comment.edit" var="edit" />
	<display:column title="${edit}" >
	<input type="button" class="btn btn-primary" value="${edit}"	
			onclick="javascript:preguntar(${row.id});" /></display:column>
	</security:authorize>
	
	
	<security:authorize access="hasRole('USER')">
	<spring:message code="comment.reply" var="reply" />
	<display:column title="${reply}" >
	<input type="button" class="btn btn-primary" value="${reply}"	
			onclick="javascript: window.location.href = './reply/user/list.do?commentId=${row.id}';" /></display:column>
			</security:authorize>
			
			<security:authorize access="hasRole('ADMIN')">
	<spring:message code="comment.reply" var="reply" />
	<display:column title="${reply}" >
	<input type="button" class="btn btn-primary" value="${reply}"	
			onclick="javascript: window.location.href = './reply/administrator/list.do?commentId=${row.id}';" /></display:column>
			</security:authorize>
		


	
	
	
	
	
	
	
</display:table>

<security:authorize access="hasRole('USER')">
<jstl:if test="${comentable}">
<spring:message code="create" var="create" />
	<div align="left">
	<input type="button" value="${create}"
		onclick="javascript: window.location.href = './comment/user/create.do?rendezvousId=${rendId}';" /></div>
		</jstl:if>
</security:authorize>



	
