<%--
 * list announcement
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<script >
function preguntar(announcementId){
   eliminar=confirm('<spring:message code="announcement.confirmDelete"/>');
   if (eliminar)
   //Redireccionamos si das a aceptar
     window.location.href = "announcement/administrator/delete.do?announcementId=" + announcementId; //página web a la que te redirecciona si confirmas la eliminación
	else
  //Y aquí pon cualquier cosa que quieras que salga si le diste al boton de cancelar
    alert('<spring:message code="announcement.negativeDelete"/>');
}
</script>

<display:table name="announcements" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag"> 
	
	
	<acme:column  code="announcement.title"  property = "title"/>
	<acme:column  code="announcement.description"  property = "description"/>
	<acme:column  code="announcement.moment"  property = "moment"/>
	
		
	<security:authorize access="hasRole('ADMIN')">
	<display:column>
		<a href="javascript:preguntar(${row.id})" > <spring:message code="announcement.delete" /></a>
	</display:column>
	</security:authorize>
	
</display:table>

<security:authorize access="hasRole('USER')">
<security:authentication property="principal.username" var="username"/>
  <jstl:if test="${rendezvous.user.userAccount.username eq username}">
	<acme:createByUser code="announcement.create" url="/announcement/user/create.do?rendezvousId=${rendezvous.id}"/>
  </jstl:if>
 </security:authorize> 