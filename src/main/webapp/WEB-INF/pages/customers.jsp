<%-- 
    Document   : customers
    Created on : Feb 19, 2016, 1:53:23 PM
    Author     : Rohan
--%>

 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>
        <!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products JSP Page</title>
      
    </head>
    <body>
         
        <h1>Products JSP Page !</h1>
        <c:if test="${not empty requestScope[product]}">
    <h2>${requestScope.customer.firstName} ${requestScope.customer.lastName}</h2>
</c:if>

<c:if test="${not empty requestScope.violations}">
    <h2>Violations were found in my controller! (and passed back in the request scope)</h2>
    <ul>
        <c:forEach items="${requestScope.violations}" var="violation">
            <li>
                <c:out value="${violation.propertyPath}"/>: ${violation.message}
            </li>
        </c:forEach>
    </ul>
</c:if>

<c:if test="${not empty requestScope.messages}">
    <h2>Messages from controller:</h2>
    <ul>
        <c:forEach items="${requestScope.messages}" var="message">
            <li>
                <c:out value="${message.key}"/>: ${message.value}
            </li>
        </c:forEach>
    </ul>
</c:if>
    
    
    <table class="table table-striped">
    <thead>
        <tr>
            <th>Customer ID</th>
            <th>Customer First Name</th>
            <th>Customer Last Name</th>
            <th>Customer Email</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${requestScope.customers}" var="customer">
            <tr>
                <td>${customer.id}</td>
                <td>${customer.firstName}</td>
                <td>${customer.lastName}</td>
                <td>${customer.email}</td>
                <td>
                    <a href="<c:url value="/customer"><c:param name="customerId" value="${customer.id}"/></c:url>">Details (Read/Only)</a>
                </td>
                <td>
                    <a href="<c:url value="/updatecustomer"><c:param name="customerId" value="${customer.id}"/></c:url>">Update</a>
                </td>
                <td>
                    <a href="<c:url value="/delcustomer"><c:param name="customerId" value="${customer.id}"/></c:url>">Delete</a>
                </td>
            </tr>
        </c:forEach>        
    </tbody>
</table>
    </body>
</html>
