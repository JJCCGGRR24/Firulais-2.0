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
function preguntar(id){
   eliminar=confirm('<spring:message code="announcement.confirmDelete"/>');
   if (eliminar)
   //Redireccionamos si das a aceptar
     window.location.href = "servicce/administrator/cancell.do?servicceId=" + id; //página web a la que te redirecciona si confirmas la eliminación
	else
  //Y aquí pon cualquier cosa que quieras que salga si le diste al boton de cancelar
    alert('<spring:message code="announcement.negativeDelete"/>');
}
</script>



<security:authorize access="hasRole('MANAGER')">
	<a href="servicce/manager/create.do"><spring:message
			code="rendezvous.create" /></a>
</security:authorize>

<br>

<display:table name="servicces" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<security:authorize access="isAuthenticated()">
		<security:authentication property="principal.username" var="prin" />
		
			<display:column>
			<jstl:if
			test="${row.manager.userAccount.username eq prin && not row.cancelled}">
				<input type="button" name="edit"
					value="<spring:message code="template.edit" />"
					onclick="document.location.href='servicce/manager/edit.do?servicceId='+ ${row.id};" />
			</jstl:if>
			</display:column>
		
	</security:authorize>
	<spring:message code="servicce.name" var="name"></spring:message>
	<display:column property="name" title="${name}" />


	<spring:message code="servicce.description" var="description"></spring:message>
	<display:column property="description" title="${description}" />

	<%-- 	<spring:message code="servicce.cancelled" var="cancelled"></spring:message> --%>
	<%-- 	<display:column property="cancelled" title="${cancelled}" /> --%>

	<display:column titleKey="servicce.cancelled">
		<jstl:if test="${row.cancelled}">
			<img
				src="http://www.clker.com/cliparts/2/k/n/l/C/Q/transparent-green-checkmark-hi.png"
				height="30px" />
			<span class="rojo"></span>
		</jstl:if>
		<jstl:if test="${!row.cancelled}">
			<img
				src="https://vignette.wikia.nocookie.net/house-of-cards/images/a/a5/X.png/revision/latest?cb=20161128021903"
				height="30px" />
		</jstl:if>
	</display:column>

	<display:column>
		<img style="max-height: 100px; max-width: 100px;" src="${row.picture}" />
	</display:column>

	<security:authorize access="hasRole('ADMIN')">

		
			<display:column>
				<jstl:if test="${row.cancelled eq false}">
				<a href="javascript:preguntar(${row.id})"><spring:message
						code="servicce.admin.cancell" /></a>
				</jstl:if>
			</display:column>
		

	</security:authorize>

</display:table>

<script>
	var name = "rojo";
	var items = document.getElementsByClassName(name);
	for ( var i = 0; i < items.length; i++) {
		document.getElementsByClassName(name)[i].parentNode.parentNode.style.backgroundColor = "indianred";
	}
</script>