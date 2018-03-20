<%--
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


<script>
	function preguntar(rendezvousId) {
		eliminar = confirm('<spring:message code="announcement.confirmDelete"/>');
		if (eliminar)
			//Redireccionamos si das a aceptar
			window.location.href = "rendezvous/admin/delete.do?rendezvousId=" + rendezvousId; //página web a la que te redirecciona si confirmas la eliminación
		else
			//Y aquí pon cualquier cosa que quieras que salga si le diste al boton de cancelar
			alert('<spring:message code="announcement.negativeDelete"/>');
	}
</script>

<!-- List Rendezvous Attended -->
<h2>
	<spring:message code="rendezvous.past" />
</h2>

<display:table name="rendezvous1" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<security:authorize access="hasRole('USER')">
		<display:column>
			<jstl:if test="${row.user == principal && row.finalMode == false}">
				<a href="rendezvous/user/edit.do?rendezvousId=${row.id}"><spring:message
						code="rendezvous.edit" /></a>
			</jstl:if>
		</display:column>
	</security:authorize>

	<display:column titleKey="rendezvous.creator">
		<a href="user/view.do?userId=${row.user.id}"><spring:message
				code="user.view" /></a>
	</display:column>
	<display:column property="name" titleKey="rendezvous.name" />
	<display:column property="description"
		titleKey="rendezvous.description" />
	<display:column property="moment" titleKey="rendezvous.moment" />

	<display:column titleKey="rendezvous.picture">
		<a target="_blank" href="${row.picture}"><img SRC="${row.picture}"
			width="40" /></a>
	</display:column>
	<display:column titleKey="rendezvous.coordinates">
		<jstl:if test="${row.coordinates != null}">
			<spring:message code="rendezvous.coordinates.latitude" />: <jstl:out
				value="${row.coordinates.latitude }" />
			<br />
			<spring:message code="rendezvous.coordinates.longitude" />: <jstl:out
				value="${row.coordinates.longitude}" />
			<br />
		</jstl:if>
	</display:column>
	<display:column titleKey="rendezvous.attendants">
		<a href="user/rendezvous/list.do?rendezvousId=${row.id}"><spring:message
				code="user.view" /></a>
	</display:column>

	<security:authorize access="isAnonymous() or hasRole('ADMIN')">
		<spring:message code="rendezvous.announcements" var="var1" />
		<display:column title="${var1}">
			<spring:url value="/announcement/list.do" var="editURL">
				<spring:param name="rendezvousId" value="${row.id}" />
			</spring:url>
			<a href="${editURL}"><spring:message code="user.view" /></a>
		</display:column>
	</security:authorize>



	<security:authorize access="hasRole('USER')">
		<spring:message code="rendezvous.announcements" var="var1" />
		<display:column title="${var1}">
			<jstl:if test="${rsvp.contains(row)}">
				<spring:url value="/announcement/user/list.do" var="editURL">
					<spring:param name="rendezvousId" value="${row.id}" />
				</spring:url>
				<a href="${editURL}"><spring:message code="user.view" /></a>
			</jstl:if>
		</display:column>
	</security:authorize>


	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="javascript:preguntar(${row.id})"><spring:message
					code="rendezvous.delete" /></a>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('USER')">
		<display:column>
			<input type="button"
				value="<spring:message code="rendezvous.comments" />"
				onclick="document.location.href='comment/user/list.do?rendezvousId='+ ${row.id};" />
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<input type="button"
				value="<spring:message code="rendezvous.comments" />"
				onclick="document.location.href='comment/administrator/list.do?rendezvousId='+ ${row.id};" />
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('USER')">
		<display:column>
			<input type="button"
				value="<spring:message code="rendezvous.links" />"
				onclick="document.location.href='rendezvous/user/links.do?rendezvousId='+ ${row.id};" />
		</display:column>
	</security:authorize>
	<security:authorize access="!hasRole('USER')">
		<display:column>
			<input type="button"
				value="<spring:message code="rendezvous.links" />"
				onclick="document.location.href='rendezvous/all/links.do?rendezvousId='+ ${row.id};" />
		</display:column>
	</security:authorize>

	<display:column>
		<security:authorize access="hasRole('USER')">
			<input type="button"
				value="<spring:message code="rendezvous.questions" />"
				onclick="document.location.href='question/user/list.do?rendezvousId='+ ${row.id};" />
		</security:authorize>
		<security:authorize access="not hasRole('USER')">
			<input type="button"
				value="<spring:message code="rendezvous.questions" />"
				onclick="document.location.href='question/all/list.do?rendezvousId='+ ${row.id};" />
		</security:authorize>
	</display:column>

	<display:column titleKey="rendezvous.finalMode">
		<jstl:if test="${row.finalMode}">
			<img
				src="http://www.clker.com/cliparts/2/k/n/l/C/Q/transparent-green-checkmark-hi.png"
				height="30px" />
		</jstl:if>
		<jstl:if test="${!row.finalMode}">
			<img
				src="https://vignette.wikia.nocookie.net/house-of-cards/images/a/a5/X.png/revision/latest?cb=20161128021903"
				height="30px" />
		</jstl:if>
	</display:column>

	<display:column titleKey="rendezvous.adult">
		<jstl:if test="${row.adultsOnly}">
			<img
				src="https://upload.wikimedia.org/wikipedia/commons/c/c5/Russia_18%2B.svg"
				height="30px" />
		</jstl:if>
		<jstl:if test="${!row.adultsOnly}">
			<img
				src="http://www.pngall.com/wp-content/uploads/2017/03/Family-Free-Download-PNG.png"
				height="40px" />
		</jstl:if>
	</display:column>

	<security:authorize access="hasRole('USER')">
		<display:column titleKey="rendezvous.deleted">
			<jstl:if test="${row.deleted}">
				<spring:message code="template.yes" />
				<span class="rojo"></span>
			</jstl:if>
			<jstl:if test="${!row.deleted}">
				<spring:message code="template.no" />
			</jstl:if>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('USER')">
	<security:authentication property="principal.username" var="prin" />
	 <display:column>
  <jstl:if
    test="${row.user.userAccount.username eq prin }">
   
    
    <input type="button"
				value="<spring:message code="rendezvous.servicces" />"
				onclick="document.location.href='request/user/list.do?rendezvousId='+ ${row.id};" />
    
    
  </jstl:if>
  </display:column>
  </security:authorize>
	
</display:table>

<!-- List Rendezvous Going to Attend-->

<script>
	function preguntar(rendezvousId) {
		eliminar = confirm('<spring:message code="announcement.confirmDelete"/>');
		if (eliminar)
			//Redireccionamos si das a aceptar
			window.location.href = "rendezvous/admin/delete.do?rendezvousId=" + rendezvousId; //página web a la que te redirecciona si confirmas la eliminación
		else
			//Y aquí pon cualquier cosa que quieras que salga si le diste al boton de cancelar
			alert('<spring:message code="announcement.negativeDelete"/>');
	}
</script>

<h2>
	<spring:message code="rendezvous.future" />
</h2>

<display:table name="rendezvous2" id="row" requestURI="${requestURI}"
	class="displaytag">

	<security:authorize access="hasRole('USER')">
		<display:column>
			<jstl:if
				test="${row.user == principal && row.finalMode == false && row.deleted == false}">
				<a href="rendezvous/user/edit.do?rendezvousId=${row.id}"><spring:message
						code="rendezvous.edit" /></a>
			</jstl:if>
		</display:column>
	</security:authorize>

	<display:column titleKey="rendezvous.creator">
		<a href="user/view.do?userId=${row.user.id}"><spring:message
				code="user.view" /></a>
	</display:column>
	<display:column property="name" titleKey="rendezvous.name" />
	<display:column property="description"
		titleKey="rendezvous.description" />
	<display:column property="moment" titleKey="rendezvous.moment" />

	<display:column titleKey="rendezvous.picture">
		<a target="_blank" href="${row.picture}"><img SRC="${row.picture}"
			width="40" /></a>
	</display:column>

	<display:column titleKey="rendezvous.coordinates">
		<jstl:if test="${row.coordinates != null}">
			<spring:message code="rendezvous.coordinates.latitude" />: <jstl:out
				value="${row.coordinates.latitude }" />
			<br />
			<spring:message code="rendezvous.coordinates.longitude" />: <jstl:out
				value="${row.coordinates.longitude}" />
			<br />
		</jstl:if>
	</display:column>

	<display:column titleKey="rendezvous.attendants">
		<a href="user/rendezvous/list.do?rendezvousId=${row.id}"><spring:message
				code="user.view" /></a>
	</display:column>

	<security:authorize access="hasRole('USER')">
		<spring:message code="rendezvous.announcements" var="var1" />
		<display:column title="${var1}">
			<jstl:if test="${rsvp.contains(row)}">
				<spring:url value="/announcement/user/list.do" var="editURL">
					<spring:param name="rendezvousId" value="${row.id}" />
				</spring:url>
				<a href="${editURL}"><spring:message code="user.view" /></a>
			</jstl:if>
		</display:column>
	</security:authorize>

	<security:authorize access="isAnonymous() or hasRole('ADMIN')">
		<spring:message code="rendezvous.announcements" var="var1" />
		<display:column title="${var1}">
			<spring:url value="/announcement/list.do" var="editURL">
				<spring:param name="rendezvousId" value="${row.id}" />
			</spring:url>
			<a href="${editURL}"><spring:message code="user.view" /></a>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="javascript:preguntar(${row.id})"><spring:message
					code="rendezvous.delete" /></a>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('USER')">
		<display:column>
			<input type="button"
				value="<spring:message code="rendezvous.comments" />"
				onclick="document.location.href='comment/user/list.do?rendezvousId='+ ${row.id};" />
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('USER')">
		<display:column>
			<input type="button"
				value="<spring:message code="rendezvous.links" />"
				onclick="document.location.href='rendezvous/user/links.do?rendezvousId='+ ${row.id};" />
		</display:column>
	</security:authorize>
	<security:authorize access="!hasRole('USER')">
		<display:column>
			<input type="button"
				value="<spring:message code="rendezvous.links" />"
				onclick="document.location.href='rendezvous/all/links.do?rendezvousId='+ ${row.id};" />
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<input type="button"
				value="<spring:message code="rendezvous.comments" />"
				onclick="document.location.href='comment/administrator/list.do?rendezvousId='+ ${row.id};" />
		</display:column>
	</security:authorize>

	<display:column>
		<security:authorize access="hasRole('USER')">
			<input type="button"
				value="<spring:message code="rendezvous.questions" />"
				onclick="document.location.href='question/user/list.do?rendezvousId='+ ${row.id};" />
		</security:authorize>
		<security:authorize access="not hasRole('USER')">
			<input type="button"
				value="<spring:message code="rendezvous.questions" />"
				onclick="document.location.href='question/all/list.do?rendezvousId='+ ${row.id};" />
		</security:authorize>
	</display:column>

	<display:column titleKey="rendezvous.finalMode">
		<jstl:if test="${row.finalMode}">
			<img
				src="http://www.clker.com/cliparts/2/k/n/l/C/Q/transparent-green-checkmark-hi.png"
				height="30px" />
		</jstl:if>
		<jstl:if test="${!row.finalMode}">
			<img
				src="https://vignette.wikia.nocookie.net/house-of-cards/images/a/a5/X.png/revision/latest?cb=20161128021903"
				height="30px" />
		</jstl:if>
	</display:column>

	<display:column titleKey="rendezvous.adult">
		<jstl:if test="${row.adultsOnly}">
			<img
				src="https://upload.wikimedia.org/wikipedia/commons/c/c5/Russia_18%2B.svg"
				height="30px" />
		</jstl:if>
		<jstl:if test="${!row.adultsOnly}">
			<img
				src="http://www.pngall.com/wp-content/uploads/2017/03/Family-Free-Download-PNG.png"
				height="40px" />
		</jstl:if>
	</display:column>

	<security:authorize access="hasRole('USER')">
		<display:column>
			<jstl:if test="${myRs.contains(row)}">
				<input type="button"
					value="<spring:message code="rendezvous.drop" />"
					onclick="document.location.href='rendezvous/user/unrsvp.do?rendezvousId='+ ${row.id};" />
			</jstl:if>
			<jstl:if test="${!myRs.contains(row)}">
				<input type="button"
					value="<spring:message code="rendezvous.attend" />"
					onclick="document.location.href='rendezvous/user/rsvp.do?rendezvousId='+ ${row.id};" />
			</jstl:if>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('USER')">
		<display:column titleKey="rendezvous.deleted">
			<jstl:if test="${row.deleted}">
				<spring:message code="template.yes" />
				<span class="rojo"></span>
			</jstl:if>
			<jstl:if test="${!row.deleted}">
				<spring:message code="template.no" />
			</jstl:if>
		</display:column>
	</security:authorize>
	
	
	
	
		<security:authorize access="hasRole('USER')">
	<security:authentication property="principal.username" var="prin" />
	 <display:column>
  <jstl:if
    test="${row.user.userAccount.username eq prin }">
   
    
    <input type="button"
				value="<spring:message code="rendezvous.servicces" />"
				onclick="document.location.href='request/user/list.do?rendezvousId='+ ${row.id};" />
    
     
  </jstl:if>
  </display:column>
  </security:authorize>
	
</display:table>

<br />
<br />
<security:authorize access="hasRole('USER')">
	<a href="rendezvous/user/create.do"><spring:message
			code="rendezvous.create" /></a>
</security:authorize>
<!-- <br /> -->
<!-- <br /> -->
<!-- <br /> -->
<%-- <a href="user/list.do"><spring:message code="user.back" /></a> --%>

<script>
	var name = "pagebanner";
	var items = document.getElementsByClassName(name);
	for (var i = 0; i < items.length; i++) {
		document.getElementsByClassName(name)[i].style.width = "94%";
	}
	var name = "pagelinks";
	var items = document.getElementsByClassName(name);
	for (var i = 0; i < items.length; i++) {
		document.getElementsByClassName(name)[i].style.width = "94%";
	}
	var name = "displaytag";
	var items = document.getElementsByClassName(name);
	for (var i = 0; i < items.length; i++) {
		document.getElementsByClassName(name)[i].style.width = "95%";
	}
	var name = "rojo";
	var items = document.getElementsByClassName(name);
	for (var i = 0; i < items.length; i++) {
		document.getElementsByClassName(name)[i].parentNode.parentNode.style.backgroundColor = "indianred";
	}
</script>
