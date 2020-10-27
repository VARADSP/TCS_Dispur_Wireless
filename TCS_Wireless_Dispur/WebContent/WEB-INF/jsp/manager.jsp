<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Dispur Wireless</title>
  <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet" href="./style.css">

  <!-- Compiled and minified CSS -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- partial -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script  src="./script.js"></script>
<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<style>
body {
  display: flex;
  min-height: 100vh;
  flex-direction: column;
}

main {
  flex: 1 0 auto;
}
</style>
</head>
<body>


  <!--Main Navigation-->
<header>

  <div class="navbar">
    <nav class="blue">
      <div class="nav-wrapper">
        <a href="#!" class="brand-logo center">TCS Dispur Wireless</a>
        <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>
        <ul id="nav-mobile" class="left hide-on-med-and-down">
          <li>Welcome Admin</li>
        </ul>
       
        <ul id="nav-mobile" class="right hide-on-med-and-down">
      
          <li><a href="logout.html" class="red">Logout</a></li>
      
        </ul>
      </div>
    </nav>
  </div>

</header>
<main>
<!-- Modal Structure -->
<div id="modal1" class="modal">
  <div class="modal-content">
    <h4>Customer Plan Details</h4>
    <p>plans</p>
    <div class="GFGclass" id="divGFG"></div>
  </div>
  <div class="modal-footer">
    <a href="#!" class="modal-close waves-effect waves-green btn-flat">Close</a>
  </div>
</div>

<div class="container">
  <h2>List Of Customers</h2>
  <input class="form-control" id="myInput" type="text" placeholder="Search..">
  <br>
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>Customer Id</th>
        <th>Customer Name</th>
        <th>Customer Address</th>
        <th>Customer Email</th>
        <th>Customer Contact Number</th>
        <th>Customer Login Password</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody id="myTable">
    <c:forEach items="${sessionScope.customerdata}" var="customer">
		<tr>
			<td class="customerId">${customer.key.customerid}</td>
			<td class="customerName">${customer.key.customername}</td>
			<td class="customerAddress">${customer.key.customeraddress}</td>
			<td class="customerEmail">${customer.key.customeremail}</td>
			<td class="customerContactNumber">${customer.key.customercontactnumber}</td>
			<td class="customerLoginPassword">${customer.key.customerloginpassword}</td>
			<td>
            	<a class="waves-effect waves-light btn modal-trigger modalBtn" href="#modal1" data-customer-id="${customer.key.customerid}">View</a>
          	</td>
          	<td class="customerplans" style="visibility:hidden;">			 
   			<c:forEach items="${customer.value}" var="plan">
   			<div id="plan${plan.planid}">
   				<input type="hidden" class="planid" value="${plan.planid}">
   				<input type="hidden" class="planname" value="${plan.planname}">
   				<input type="hidden" class="plantype" value="${plan.plantype}">
   				<input type="hidden" class="plantariff" value="${plan.plantariff}">
   				<input type="hidden" class="planvalidity" value="${plan.planvalidity}">
   				<input type="hidden" class="planrentel" value="${plan.planrental}">
   				<input type="hidden" class="planstartdate" value="${plan.planstartdate}">
   			</div>
   			</c:forEach>
		
          	</td>
		</tr>
		
	</c:forEach>   
    </tbody>
  </table>

</div>
</main>
<footer class="page-footer blue">

    <div class="container">
    © 2020 Copyright Text
    </div>

</footer>
<script>
  // Or with jQuery

  $(document).ready(function(){
    $('.sidenav').sidenav();
    $('.modal').modal();
    $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
    
    $(".modalBtn").on('click',function(){
        var customerplans = $(this).parents("tr").find(".customerplans"); 
        var p = ""; 
        $(customerplans).children().each( function(index, element) {
           // children's element
            		   var id = $(element).children().get(0).value;
      			       var a =  $(element).children().get(1).value; 
                       var c = $(element).children().get(2).value; 
                       var d = $(element).children().get(3).value; 
                       var e = $(element).children().get(4).value; 
                       
                       // CREATING DATA TO SHOW ON MODEL 
                         p +=  
                 "<p id='a' name='GFGusername' >Plan Id: " 
                         + id + " </p>"; 
                         
                       p +=  
                 "<p id='a' name='GFGusername' >Plan Name: " 
                         + a + " </p>"; 
                       
                       p += 
                 "<p id='c' name='GFGpp'>Plan Type: "  
                         + c + "</p>"; 
                       p +=  
                 "<p id='d' name='GFGscores' >Plan Price: " 
                         + d + " </p>"; 
                       p +=  
                 "<p id='e' name='GFGcoding' >Plan Validity: " 
                         + e + " </p>"; 
                         
                         p +=  
                             "<br>"; 
                              
                       //CLEARING THE PREFILLED DATA 
                      
            
         });
        $("#divGFG").empty(); 
        //WRITING THE DATA ON MODEL 
        $("#divGFG").append(p); 
        });

    
  });
  </script>

</body>
</html>
