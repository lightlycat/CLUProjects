$(document).ready(function() {

    //Stops the submit request
    $("#myAjaxRequestForm").submit(function(e){
        e.preventDefault();
    });

    //checks for the button click event
    $("#myButton").click(function(e){

        //get the form data and then serialize that
        dataString = $("#myAjaxRequestForm").serialize();

        //get the form data using another method
        var customerName = $("input#customerName").val();
        dataString = "customerName=" + customerName;

        //make the AJAX request, dataType is set to json
        //meaning we are expecting JSON data in response from the server
        $.ajax({
            type: "POST",
            url: "CartInformation",
            data: dataString,
            dataType: "json",

            //if received a response from the server
            success: function( data, textStatus, jqXHR) {

                if(data.success){

                    $("#ajaxResponse").html("");
                    $.each(data.carts, function (key, val) {
                        var img = new Image();
                        var content = val.productName + ":" + val.amount;
                        img.src =  "../img/product/" + val.img;
                        $("#ajaxResponse").append("<div>" + content + "</div>");
                        $("#ajaxResponse").append(img);

                    });

                }
                //display error message
                else {
                    $("#ajaxResponse").html("<div><b>Country code in Invalid!</b></div>");
                }
            },

            //If there was no resonse from the server
            error: function(jqXHR, textStatus, errorThrown){
                console.log("Something really bad happened " + textStatus);
                $("#ajaxResponse").html(jqXHR.responseText);
            },

            //capture the request before it was sent to server
            beforeSend: function(jqXHR, settings){
                //adding some Dummy data to the request
                settings.data += "&dummyData=whatever";
                //disable the button until we get the response
                $('#myButton').attr("disabled", true);
            },

            //this is called after the response or error functions are finsihed
            //so that we can take some action
            complete: function(jqXHR, textStatus){
                //enable the button
                $('#myButton').attr("disabled", false);
            }

        });
    });

});