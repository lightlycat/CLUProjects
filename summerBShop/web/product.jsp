<%@page language="java" import="DBController.RetriveProduct"%>
<%@ page import="DBObject.Product" %>
<%@ page import="java.util.ArrayList" %>
<%
 RetriveProduct rp = new RetriveProduct();

    ArrayList<Product> prods = rp.getProductWithCat("1");
%>


<html>
    <head>
        <!-- First, add jQuery (and jQuery UI if using custom easing or animation -->
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/addCart.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style 2.css"/>
        <title>Welcome,Product pages!</title>

    </head>
    <body>
    <div><jsp:include page="header.jsp"/></div>


    <div id="Main">
        <section>
            <article>

                <table>
                    <caption>Welcome!</caption>
                    <colgroup><col><col><col>
                    <colgroup><col><col><col>
                    <colgroup><col><col><col>
                        <thead>
                        <tr id="result"> <th colspan="4" > Total items in your cart:&nbsp;<div style="float: right;" id="cart">0</div>
                        </thead>
                        <tbody>
                            <tr> <td rowspan="6" style="VERTICAL-ALIGN: text-top; width: 60px; height: 200px;">
                                <div style="height: 35px; font: 18px Calibri bold italic; text-decoration:underline;">Category</a></div>
                                <div style="height: 30px; font: 18px Calibri bold;"><a href="../product.jsp">Writing</a></div>
                                <div style="height: 30px; font: 18px Calibri bold;"><a href="../product.jsp">NoteBooks</a></div>
                                <td id="product"><input type="image" class="leImage" value="<%=prods.get(0).getProductid()%>" title="Click to put in your cart!" src="${pageContext.request.contextPath}/img/product/<%=prods.get(0).getImg()%>"/>
                                <td id="product"><input type="image" class="leImage" value="<%=prods.get(1).getProductid()%>" title="Click to put in your cart!" src="${pageContext.request.contextPath}/img/product/<%=prods.get(1).getImg()%>"/>
                                <td id="product"><input type="image" class="leImage" value="<%=prods.get(2).getProductid()%>" title="Click to put in your cart!" src="${pageContext.request.contextPath}/img/product/<%=prods.get(2).getImg()%>"/>

                            <tr> <td><div><%=prods.get(0).getProductName()%></div><div>$<%=prods.get(0).getPrice()%>
                                 <td><div><%=prods.get(1).getProductName()%></div><div>$<%=prods.get(1).getPrice()%></div>
                                 <td><div><%=prods.get(2).getProductName()%></div><div>$<%=prods.get(2).getPrice()%></div>
                            <tr> <td id="product"><input type="image" class="leImage" value="<%=prods.get(3).getProductid()%>" title="Click to put in your cart!" src="${pageContext.request.contextPath}/img/product/<%=prods.get(3).getImg()%>"/>
                                 <td id="product"><input type="image" class="leImage" value="<%=prods.get(4).getProductid()%>" title="Click to put in your cart!" src="${pageContext.request.contextPath}/img/product/<%=prods.get(4).getImg()%>"/>
                                 <td id="product"><input type="image" class="leImage" value="<%=prods.get(5).getProductid()%>" title="Click to put in your cart!" src="${pageContext.request.contextPath}/img/product/<%=prods.get(5).getImg()%>"/>
                            <tr> <td><div><%=prods.get(3).getProductName()%></div><div>$<%=prods.get(3).getPrice()%></div>
                                 <td><div><%=prods.get(4).getProductName()%></div><div>$<%=prods.get(4).getPrice()%></div>
                                 <td><div><%=prods.get(5).getProductName()%></div><div>$<%=prods.get(5).getPrice()%></div>
                            <tr> <td id="product"><input type="image" class="leImage" value="<%=prods.get(6).getProductid()%>" title="Click to put in your cart!" src="${pageContext.request.contextPath}/img/product/<%=prods.get(6).getImg()%>"/>
                                 <td id="product"><input type="image" class="leImage" value="<%=prods.get(7).getProductid()%>" title="Click to put in your cart!" src="${pageContext.request.contextPath}/img/product/<%=prods.get(7).getImg()%>"/>
                                 <td id="product"><input type="image" class="leImage" value="<%=prods.get(8).getProductid()%>" title="Click to put in your cart!" src="${pageContext.request.contextPath}/img/product/<%=prods.get(8).getImg()%>"/>
                            <tr> <td><div><%=prods.get(6).getProductName()%></div><div>$<%=prods.get(6).getPrice()%></div>
                                 <td><div><%=prods.get(7).getProductName()%></div><div>$<%=prods.get(7).getPrice()%></div>
                                 <td><div><%=prods.get(8).getProductName()%></div><div>$<%=prods.get(8).getPrice()%></div>
                        </tbody>
                    <tfoot>
                    <tr> <td colspan="4"> Next
                    </tfoot>
                </table>
            </article>
        </section>
    </div>
    <div id="Footer"><jsp:include page="footer.jsp"/></div>


    </body>

</html>
