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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

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
		document.cookie = c_name + "=" + c_value+"; path=/";
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
	<a href=""><img src="images/banner2.png" alt="Acme-Newspaper Co., Inc." /></a>
</div>

<div align="center">
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<li><a class="fNiv" href="newspaper/list.do"><spring:message
						code="master.page.newspaper.published" /></a></li>
		<security:authorize access="isAnonymous()">

			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>

			<li><a class="fNiv"><spring:message code="master.register" /></a>
				<ul>
					<li><a href="register/user.do"><spring:message
								code="master.register.user" /></a> <a href="register/customer.do"><spring:message
								code="master.register.customer" /></a></li>
				</ul></li>

			<li><a class="fNiv" href="user/list.do"><spring:message
						code="master.users" /></a>
		</security:authorize>

		<security:authorize access="hasRole('ADMIN')">
		
		<li><a href="user/list.do"><spring:message
						code="master.users" /></a>
			<li><a class="fNiv" href="admin/dashboard.do"><spring:message
						code="master.page.administrator.dashboard" /></a></li>
						
			<li><a class="fNiv" href="chirp/admin/list.do"><spring:message
						code="master.page.administrator.chirps" /></a></li>

			<li><a class="fNiv"><spring:message
						code="master.page.administrator.taboowords" /></a>

				<ul>
					<li class="arrow"></li>
					<li><a href="taboo/admin/list.do"><spring:message
								code="master.page.listTaboo" /></a></li>
					<li><a href="taboo/admin/listChirpTabooWord.do"><spring:message
								code="master.page.listChirpTabooWord" /></a></li>
					<li><a href="taboo/admin/listNewspaperTabooWord.do"><spring:message
								code="master.page.listNewspaperTabooWord" /></a></li>
					<li><a href="taboo/admin/listArticleTabooWord.do"><spring:message
								code="master.page.listArticleTabooWord" /></a></li>
				</ul></li>

			<li><a class="fNiv"><spring:message
						code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('CUSTOMER')">
		<li><a href="user/list.do"><spring:message
						code="master.users" /></a>
			<li><a class="fNiv"><spring:message
						code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('USER')">
			<li><a class="fNiv"><spring:message
							code="master.page.newspapers" /></a>
					<ul>
						<li class="arrow"></li>
						
						<li><a href="newspaper/user/create.do"><spring:message
									code="template.new" /></a></li>
						<li><a href="newspaper/user/myList.do"><spring:message
									code="master.page.myNewspapers" /></a></li>
					</ul></li>
			<li><a href="user/user/list.do"><spring:message
						code="master.users" /></a>
			<li><a class="fNiv"><spring:message
						code="master.page.chirps" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="user/myDetails.do"><spring:message
								code="master.page.myChirps" /></a></li>
					<li><a href="chirp/user/create.do"><spring:message
								code="master.page.toChirp" /></a></li>
					<li><a href="chirp/user/followeds.do"><spring:message
								code="master.page.fromFollows" /></a></li>
				</ul></li>
			<li><a class="fNiv"><spring:message
						code="master.page.followUps" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="followUp/user/create.do"><spring:message
								code="template.new" /></a></li>
					<li><a href="followUp/user/myList.do"><spring:message
								code="master.page.myFollowUps" /></a></li>
				</ul></li>
		</security:authorize>

		<li><a class="fNiv"><spring:message
					code="master.page.articles" /></a>
			<ul>
				<li class="arrow"></li>
				<li><a href="article/publicList.do"><spring:message
							code="template.all" /></a></li>
				<security:authorize access="hasRole('USER')">
					<li><a href="article/user/create.do"><spring:message
								code="template.new" /></a></li>
					<li><a href="article/user/myList.do"><spring:message
								code="master.page.myArticles" /></a></li>
				</security:authorize>
			</ul></li>

		<security:authorize access="hasRole('USER')">
			<li><a class="fNiv" href="follower/user/list.do"><spring:message
						code="master.page.administrator.followers" /></a></li>

			<li><a class="fNiv" href="follow/user/list.do"><spring:message
						code="master.page.administrator.follows" /></a></li>
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

