<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
	

<!--//BLOQUE COOKIES-->
<div id="barraaceptacion">
	<div class="inner">
		<spring:message code="aviso.cookies" />
		<a href="javascript:void(0);" class="ok" onclick="PonerCookie();"><b>OK</b></a>
		| <a href="law/politicaCookies.do" target="_blank" class="info"><spring:message
				code="aviso.cookies.information" /></a>
	</div>
</div>

<script>
	function getCookie(c_name) {
		var c_value = document.cookie;
		var c_start = c_value.indexOf(" " + c_name + "=");
		if (c_start == -1) {
			c_start = c_value.indexOf(c_name + "=");
		}
		if (c_start == -1) {
			c_value = null;
		} else {
			c_start = c_value.indexOf("=", c_start) + 1;
			var c_end = c_value.indexOf(";", c_start);
			if (c_end == -1) {
				c_end = c_value.length;
			}
			c_value = unescape(c_value.substring(c_start, c_end));
		}
		return c_value;
	}

	function setCookie(c_name, value, exdays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays);
		var c_value = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toUTCString());
		document.cookie = c_name + "=" + c_value;
	}

	if (getCookie('tiendaaviso') != "1") {
		document.getElementById("barraaceptacion").style.display = "block";
	} else {
		document.getElementById("barraaceptacion").style.display = "none";
	}

	function PonerCookie() {
		setCookie('tiendaaviso', '1', 365);
		document.getElementById("barraaceptacion").style.display = "none";
	}
</script>
<!--//FIN BLOQUE COOKIES-->

<div align="center">
	<a href="">
		<img src="${banner}" alt="${nameBusiness}" />
	</a><br/>
	<h1><jstl:out value="${nameBusiness}"/></h1>
</div>

<div align="center">
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message
						code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a  href="dashboard/administrator/display.do"><spring:message
						code="master.page.administrator.dashboard" /></a></li>
					<li><a  href="servicce/administrator/list.do"><spring:message
						code="master.page.servicces" /></a></li>
					<li><a  href="configuration/admin/edit.do"><spring:message
						code="master.page.configuration" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
<%-- 		<security:authorize access="!hasRole('ADMIN')"> --%>
			<li><a class="fNiv"><spring:message
						code="master.page.categories" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a  href="category/all/flat.do"><spring:message
						code="master.page.categories.flat" /></a></li>
					<li><a  href="category/all/list.do"><spring:message
						code="master.page.categories.dinamic" /></a></li>
				</ul>
			</li>
<%-- 		</security:authorize> --%>
		
		<security:authorize access="hasRole('USER')">
			<li><a class="fNiv" href="servicce/user/list.do"><spring:message
						code="master.page.servicces" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('MANAGER')">
			<li><a class="fNiv"><spring:message
						code="master.page.servicces" /></a>
					<ul><li class="arrow"></li>
						<li><a href="servicce/manager/allServicces.do"><spring:message
							code="master.page.allServicces" /></a></li>
						<li><a href="servicce/manager/myservicces.do"><spring:message
							code="master.page.myservicces" /></a></li>
					</ul></li>
		</security:authorize>
		
		<security:authorize access="permitAll">
			<li><a class="fNiv" href="user/list.do"><spring:message
						code="master.page.users" /></a></li>
			<li><a class="fNiv" href="rendezvous/list.do"><spring:message
						code="master.page.rendezvous" /></a> 
				<security:authorize
					access="hasRole('USER')">
					<ul>
						<li class="arrow"></li>
						<li><a href="rendezvous/user/RSVPd.do"><spring:message
									code="master.page.RSVPd" /></a></li>
						<li><a href="rendezvous/user/mylist.do"><spring:message
						code="master.page.user.rendezvous" /></a></li>
						
					</ul>
				</security:authorize></li>


		</security:authorize>

		<security:authorize access="isAnonymous()">

			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
					
		
			
			<li><a class="fNiv"><spring:message
						code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a  href="register/user.do"><spring:message
						code="master.page.registerUser" /></a></li>
					<li><a  href="register/manager.do"><spring:message
						code="master.page.registerManager" /></a></li>
				</ul>
			</li>
						
		</security:authorize>




		<security:authorize access="isAuthenticated()">

			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>

					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>

				</ul></li>
		</security:authorize>
	</ul>
</div>

<div align="center">
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

