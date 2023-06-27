<section class="d-flex container justify-content-center align-items-center">

    <!-- form input -->
    <div class="form-outline mb-2 row">
        <div class="">
            <label class="form-label" for="PID">Product ID</label>
            <input type="number" id="PID" class="form-control1" placeholder="Must be greater than 0." name="productID"/>
        </div>
        <div class="">
            <label class="form-label" for="PN">Product Name</label>
            <input type="text" id="PN" class="form-control1" placeholder="Characters only, min. of 3." name="productName"/>
        </div>
        <div class="">
            <label class="form-label" for="DSCRP">Description</label>
            <input type="text" id="DSCRP" class="form-control1" placeholder="Characters only, min. of 3." name="productDescription"/>
        </div>
        <div class="">
            <label class="form-label" for="pfsize">Size</label>
            <input type="text" id="pfsize" class="form-control1" name="productSize"/>
        </div>
        <div class="">
            <label class="form-label" for="pprice">Price</label>
            <input type="number" id="pprice" step="0.01" class="form-control1" placeholder="Must be greater than 0.00." name="productPrice"/>
        </div>                
        <div class="">
            <label class="form-label" for="pquantity">Quantity</label>
            <input type="number" id="pquantity" class="form-control1" placeholder="Must be greater than 0." name="productQuantity"/>
        </div>
    </div>

    <script>
        var charactersonly = /^[A-Za-z\s]+$/;

        function prdformValidation() {
            var PID = $("#PID").val();
            console.log("Product ID " + PID);
            if (PID <= 0) {
                alert("Product ID must be greater than 0.");
                return false;
            }

            var PN = $("#PN").val();
            console.log("Product Name: " + PN);
            if (PN.length < 3) {
                alert("Product Name must be 3 or more characters.");
                return false;
            } else if (!PN.match(charactersonly)) {
                alert("Product Name must contain characters only.");
                return false;
            }

            var DSCRP = $("#DSCRP").val();
            console.log("Description: " + DSCRP);
            if (DSCRP.length < 3) {
                alert("Description must be 3 or more characters.");
                return false;
            } else if (!DSCRP.match(charactersonly)) {
                alert("Description must contain characters only.");
                return false;
            }

            var pprice = $("#pprice").val();
            console.log("Price: " + pprice);
            if (pprice <= 0) {
                alert("Price must be greater than 0.00");
                return false;
            }

            var pquantity = $("#pquantity").val();
            console.log("Quantity: " + pquantity);
            if (pquantity <= 0) {
                alert("Quantity must be greater than 0");
                return false;
            }

            return true;
        }
    </script>
</section>