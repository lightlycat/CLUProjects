<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link rel="stylesheet" type="text/css" href="../css/style.css"/>
    <link rel="stylesheet" type="text/css" href="../css/style 2.css"/>
    <title>TeamB Final Project</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


    <!-- First, add jQuery (and jQuery UI if using custom easing or animation -->
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>

    <!-- Second, add the Timer and Easing plugins -->
    <script type="text/javascript" src="../js/jquery.timers-1.2.js"></script>
    <script type="text/javascript" src="../js/jquery.easing.1.3.js"></script>

    <!-- Third, add the GalleryView Javascript and CSS files -->
    <script type="text/javascript" src="../js/jquery.galleryview-3.0-dev.js"></script>
    <link type="text/css" rel="stylesheet" href="../css/jquery.galleryview-3.0-dev.css" />

    <!-- Lastly, call the galleryView() function on your unordered list(s) -->
    <script type="text/javascript">
        $(function(){
            $('#myGallery').galleryView();
        });
    </script>
</head>

<body>
    <div><jsp:include page="header.jsp"/></div>
    <div id ="Main">
        <div style="height: 645px;">
              test
        </div>
    </div>

    <div id="Footer"><jsp:include page="footer.jsp"/></div>


</body>
</html>
