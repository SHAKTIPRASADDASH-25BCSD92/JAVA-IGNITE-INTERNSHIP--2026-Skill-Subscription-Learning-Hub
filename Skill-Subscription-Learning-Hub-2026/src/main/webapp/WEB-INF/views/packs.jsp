<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Skill Packs</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="header">
    <img src="/images/logo.png">
    <h2>Available Skill Packs</h2>
    <!-- Display logged-in user's name from session -->
    <p>Welcome, ${sessionScope.loggedUser.name}!</p>
    <!-- Logout button linked to controller -->
    <form action="/logout" method="post">
        <button type="submit">Logout</button>
    </form>
</div>

<div class="container">
    <h3>All Courses</h3>

   
    <form action="/packs" method="get">
        <input type="text" name="search" placeholder="Search packs...">
        <button type="submit">Search</button>
    </form>

    <!-- Show error message if duplicate subscription is attempted -->
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>

    <!-- Loop through skill packs -->
    <c:forEach var="pack" items="${packs}">
        <div class="card">
           
            <h4>${pack.title}</h4>

            <!-- Show description -->
            <p>${pack.description}</p>

            <!-- Show price -->
            <b>₹ ${pack.price}</b>
            <br><br>

            <!-- Subscribe action -->
            <a href="/subscribe?userId=${sessionScope.loggedUser.id}&packId=${pack.id}">Subscribe</a>
        </div>
    </c:forEach>

    <!-- Show subscription count for the current user -->
    <p>Your Total Subscriptions: ${subscriptionCount}</p>
</div>
</body>
</html>
