<section class="d-flex container justify-content-center align-items-center">

    <!-- form input -->
    <div class="form-outline mb-2 row">
        <div class="">
            <label class="form-label" for="UID">User ID</label>
            <input type="text" id="UID" class="form-control1" name="userID" placeholder="5 Characters."/>
        </div>
        <div class="">
            <label class="form-label" for="UFN">First Name</label>
            <input type="text" id="UFN" class="form-control1" placeholder="Characters only." name="userFN"/>
        </div>
        <div class="">
            <label class="form-label" for="UMN">Middle Name</label>
            <input type="text" id="UMN" class="form-control1" placeholder="Characters only." name="userMN"/>
        </div>
        <div class="">
            <label class="form-label" for="ULN">Last Name</label>
            <input type="text" id="ULN" class="form-control1" name="userLN" placeholder="Characters only."/>
        </div>
        <div class="">
            <label class="form-label" for="userR">User Role</label>
            <input type="text" id="userR" class="form-control1" placeholder="" name="userRole"/>
        </div>
    </div>  

    <script>
        var charactersonly = /^[a-zA-Z\s]+$/;

        function userformValidation() {
            var UID = $("#UID").val();
            console.log("User ID: " + UID);
            if (UID.length < 1) {
                alert("User ID can't be empty.");
            } else if (UID.length < 5 || UID.length > 5) {
                alert("User ID must be exactly 5 characters.");
                return false;
            }

            var UFN = $("#UFN").val();
            console.log("First Name" + UFN);
            if (UFN.length < 1) {
                alert("First Name must be 1 or more characters.");
                return false;
            } else if (!UFN.match(charactersonly)) {
                alert("First Name must contain characters only.");
                return false;
            }

            var UMN = $("#UMN").val();
            console.log("Middle Name: " + UMN);
            if (UMN.length > 0 && !UMN.match(charactersonly)) {
                alert("Middle Name must contain characters only.");
                return false;
            }

            var ULN = $("#ULN").val();
            console.log("Last Name" + ULN);
            if (ULN.length < 1) {
                alert("Last Name must be 1 or more characters.");
                return false;
            } else if (!ULN.match(charactersonly)) {
                alert("Last Name must contain characters only.");
                return false;
            }

            var UR = $("#userR").val();
            console.log("User Role: " + UR);
            if (UR.length < 1) {
                alert("User Role can't be empty.");
                return false;
            }

            return true;
        }
    </script>
</section>