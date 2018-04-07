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

<div>
	<img src="images/logo.png" alt="Acme-Newspaper Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="isAnonymous()">
		
			<li><a class="fNiv" href="newspaper/list.do"><spring:message
						code="master.page.newspaper.published" /></a></li>
			
			
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
						
		<li><a class="fNiv" ><spring:message code="master.register" /></a>
			<ul>
				<li><a href="register/user.do"><spring:message code="master.register.user"/></a>
				<a href="register/customer.do"><spring:message code="master.register.customer"/></a>
				</li>
			</ul>
		</li>
		
		<li><a class="fNiv" href="user/list.do"><spring:message code="master.users" /></a>
		
										
		</security:authorize>

		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv" href="admin/dashboard.do"><spring:message
						code="master.page.administrator.dashboard" /></a></li>
						
			<li><a class="fNiv" href="taboo/admin/list.do"><spring:message
						code="master.page.administrator.taboowords" /></a></li>
						
			<li><a class="fNiv"><spring:message
						code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message
						code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('USER')">
			<li><a href="newspaper/user/myList.do"><spring:message code="master.page.myNewspapers" /></a>
			<li><a class="fNiv"><spring:message code="master.page.chirps" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="user/myDetails.do"><spring:message
								code="master.page.myChirps" /></a></li>
					<li><a href="chirp/user/create.do"><spring:message
								code="master.page.toChirp" /></a></li>
					<li><a href="chirp/user/followeds.do"><spring:message
								code="master.page.fromFollows" /></a></li>
				</ul></li>
		</security:authorize>

		<li><a class="fNiv"><spring:message code="master.page.articles" /></a>
			<ul>
				<li class="arrow"></li>
				<li><a href="article/publicList.do"><spring:message
							code="template.all" /></a></li>
				<security:authorize access="hasRole('USER')">
					<li><a href="article/user/create.do"><spring:message
								code="template.new" /></a></li>
					<li><a href="article/user/myArticles.do"><spring:message
								code="master.page.myArticles" /></a></li>
				</security:authorize>
			</ul></li>

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

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

