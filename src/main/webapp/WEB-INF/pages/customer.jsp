<%-- 
    Document   : product
    Created on : Feb 19, 2016, 1:53:07 PM
    Author     : Rohan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>

        <h1>Hello World!</h1>
        <%-- 
From here we have written the code ffor displaying on html..
        --%>
        
        <c:if test="${not empty requestScope[customer]}">
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
    
    <form method="POST" action="<c:url value="/customer"/>">
    <div>
        <label for="customerID">Customer ID</label>
        <input type="number" name="customerId" id="customer" value="${customer.id}"/>
    </div>
    <div>
        <label for="customerName">Customer First Name</label>
        <input type="text" name="firstName" id="customerName" value="${customer.firstName}"/>
    </div>
    <div>
        <label for="customerId">Customer Last Name</label>
        <input type="text" name="lastName" id="lastName" value="${customer.lastName}"/>
    </div>
    <div>
        <label for="email">Customer Email</label>
        <input type="email" name="email" id="email" value="${customer.email}"/>
    </div>
    <input type="submit" name="submitCustomer" id="submitCustomer"/>
</form>
    <%@include file="/WEB-INF/jspf/footer.jspf" %>
 
