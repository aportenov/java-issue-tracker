<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<br/>
<jsp:include page="${pageContext.request.contextPath}/error.jsp"/>
<div class="container">
    <div class="row">
        <div class="jumbotron">
            <form method="post">
                <div class="form-group">
                    <input name="username" type="text" class="form-control" placeholder="Enter username">
                </div>
                <div class="form-group">
                    <input name="password" type="password" class="form-control" placeholder="Enter password">
                </div>
                <div class="form-group">
                    <input class="btn btn-primary" type="submit" value="Log In">
                    <a href="/" class="btn btn-primary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</div>