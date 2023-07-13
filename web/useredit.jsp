<%-- 
    Document   : login
    Created on : Apr 11, 2023, 6:34:16 PM
    Author     : Matic
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="head.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <section class="d-flex login container-sm justify-content-center align-items-center col-md-6">
            <form class="col-8" method="post" onsubmit="return userformValidation()" action="${pageContext.request.contextPath}/user/edit/submit">
                <h1 class="border-bottom">Edit</h1>

                <div class="form-outline mb-2 row">
                    <c:forEach items="${userDetails}" var="user">
                        <div class="">
                            <label class="form-label" for="UID2">User ID</label>
                            <input type="text" id="UID2" class="form-control1" name="userID" placeholder="5 Characters." value="${user.userID}"/>
                        </div>
                        <div class="">
                            <label class="form-label" for="UFN2">First Name</label>
                            <input type="text" id="UFN2" class="form-control1" placeholder="Characters only." name="userFN" value="${user.userFirstName}"/>
                        </div>
                        <div class="">
                            <label class="form-label" for="UMN2">Middle Name</label>
                            <input type="text" id="UMN2" class="form-control1" placeholder="Characters only." name="userMN" value="${user.userMiddleName}"/>
                        </div>
                        <div class="">
                            <label class="form-label" for="ULN2">Last Name</label>
                            <input type="text" id="ULN2" class="form-control1" name="userLN" placeholder="Characters only." value="${user.userLastName}"/>
                        </div>
                        <div class="">
                            <label class="form-label" for="userR2">User Role</label>
                            <input type="text" id="userR2" class="form-control1" placeholder="" name="userRole" value="${user.userRole}"/>
                        </div>
                        <div class="">
                            <input type="hidden" name="user_ID" value="${user.userID}"/>
                        </div>
                        <div class="">
                            <button type="submit" class="btn btn-primary btn-block mt-4 col-12">EDIT</button>
                        </div>
                    </c:forEach>    
                </div>  

                <script>
                    var charactersonly = /^[a-zA-Z\s]+$/;

                    function userformValidation() {
                        var UID2 = $("#UID2").val();
                        console.log("User ID: " + UID2);
                        if (UID2.length < 1) {
                            alert("User ID can't be empty.");
                        } else if (UID2.length < 5 || UID2.length > 5) {
                            alert("User ID must be exactly 5 characters.");
                            return false;
                        }

                        var UFN2 = $("#UFN2").val();
                        console.log("First Name" + UFN2);
                        if (UFN2.length < 1) {
                            alert("First Name must be 1 or more characters.");
                            return false;
                        } else if (!UFN2.match(charactersonly)) {
                            alert("First Name must contain characters only.");
                            return false;
                        }

                        var UMN2 = $("#UMN2").val();
                        console.log("Middle Name: " + UMN2);
                        if (UMN2.length > 0 && !UMN2.match(charactersonly)) {
                            alert("Middle Name must contain characters only.");
                            return false;
                        }

                        var ULN2 = $("#ULN2").val();
                        console.log("Last Name" + ULN2);
                        if (ULN2.length < 1) {
                            alert("Last Name must be 1 or more characters.");
                            return false;
                        } else if (!ULN2.match(charactersonly)) {
                            alert("Last Name must contain characters only.");
                            return false;
                        }

                        var UR = $("#userR2").val();
                        console.log("User Role: " + UR);
                        if (UR.length < 1) {
                            alert("User Role can't be empty.");
                            return false;
                        }

                        return true;
                    }
                </script>
        </section>

        <jsp:include page="footer.jsp"/>
        <script src="js/bootstrap.bundle.min.js"></script>
    </body>
</html>