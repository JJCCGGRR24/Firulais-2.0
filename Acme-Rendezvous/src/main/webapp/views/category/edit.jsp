<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<form:form action="category/admin/edit.do" modelAttribute="category">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="categoriesChildren"/>
	<form:hidden path="servicces"/>
	
	<form:label path="name">
		<spring:message code="category.name"/>:
	</form:label>
	<form:input path="name" type="name"/>
	<form:errors cssClass="error" path="name"/>
	<br />
	
	<form:label path="description">
		<spring:message code="category.description"/>:
	</form:label>
	<form:input path="description" type="description"/>
	<form:errors cssClass="error" path="description"/>
	<br />
	
	<form:label path="categoryFather">
		<spring:message code="category.father" />:
	</form:label>
	<form:select path="categoryFather">
		<form:option value="0" label="-"/>
		<form:options items="${categories}" itemLabel="name" itemValue="id"/>
	</form:select>
	<form:errors cssClass="error" path="categoryFather" />
	<br />
	<br />
	
	<input type="submit" name="save" value='<spring:message code="template.save"/>'>
	
	<jstl:if test="${category.id != 0}">
		<input type="submit" name="delete"
				value="<spring:message code="template.delete" />"
				onclick="return confirm('<spring:message code="category.alert" />')" />&nbsp;
	</jstl:if>
	
	<input type="button" name="cancel" value='<spring:message code="template.cancel"/>' onclick="document.location.href='category/all/list.do';">
	
	<form:errors cssClass="error" path="servicces" />
	<form:errors cssClass="error" path="categoriesChildren" />

</form:form>

