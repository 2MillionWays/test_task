<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Events List</title>
    <style>
        tr:first-child{
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>
</head>

<body>
<div class="generic-container">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">List of Events</span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>Location</th>
                <th>Date From</th>
                <th>Date To</th>
                <th>Routers</th>
                <th width="100"></th>
                <th width="100"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${events}" var="event">
                <tr>
                    <td>${event.name}</td>
                    <td>${event.location}</td>
                    <td>${event.dateFrom}</td>
                    <td>${event.dateTo}</td>
                    <td>${event.routerList}</td>
                    <td><a href="<c:url value='/edit/event/${event.name}' />" class="btn btn-success

custom-width">edit</a></td>
                    <td><a href="<c:url value='/delete/event/${event.name}' />" class="btn btn-danger

custom-width">delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="well">
        <a href="<c:url value='//newevent' />">Add New User</a>
    </div>
</div>
</body>
</html>
