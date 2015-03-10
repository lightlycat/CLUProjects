<!DOCTYPE html>
<html>
<head>
    <title>My cart Details</title>
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js" type="text/javascript"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="../js/app.js"></script>


    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style 2.css"/>
</head>
<body>
<div><jsp:include page="header.jsp"/></div>

<form id="myAjaxRequestForm">
    <div id="myAjaxRequestFormDiv">
    <fieldset style="border:2px dotted #949989">
        <legend>My cart details request</legend>

        <p >
            <label for="customerName" style="margin-left:3px; font: normal 14px Calibri !important;">Customer Name:</label>
            <input id="customerName" type="text" name="countryCode" />
            <input id="myButton" type="button" value="Submit" />
        </p>
    </fieldset>
    </div>
</form>

<div id="anotherSection">
    <fieldset style="border:2px dotted #949989">
        <legend>Response from your Request</legend>
        <div id="ajaxResponse"></div>
    </fieldset>
</div>

</body>
</html>