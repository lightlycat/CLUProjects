$(document).ready(function() {
    //added missing-v quotes
    var account = 0;
    $('.leImage').hover(
        function() {
            $(this).css('opacity' ,'0.8');
        }, function() {
            $(this).css('opacity' ,'1');
        });
    $('.leImage').click(function() {

        account = account + 1;

        var productID =$(this).val();

        //make the AJAX request, dataType is set to json
        //meaning we are expecting JSON data in response from the server
        $.ajax({
            type: "POST",
            url: "InsertCart",
            data:  {productID: productID, customerID: "1"},
            dataType: "json",

            //if received a response from the server
            success: function( data, textStatus, jqXHR) {

                if(data.success){
                    $('#cart').html(account);

                }
                //display error message
                else {
                    alert("The Cart system has problem, try laters!");
                }
            },

            //If there was no response from the server
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


        //alert("The prouduct has put in your cart!");
    });

});