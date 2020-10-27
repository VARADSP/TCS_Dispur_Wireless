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
   
</head>
<body>


  <!--Main Navigation-->
<header>

  <div class="navbar-fixed">
    <nav class="blue">
      <div class="nav-wrapper">
        <a href="#!" class="brand-logo center">TCS Dispur Wireless</a>
        
        <ul id="nav-mobile" class="left hide-on-med-and-down">
          <li>Welcome ${sessionScope.loginname} </li>
        </ul>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
          <li><a href="customer.html" class="green">Available Plans</a></li>
          <li><a href="logout.html" class="red">Logout</a></li>
        </ul>
      </div>
    </nav>
  </div>

</header>

  <!-- Modal Structure -->
<div id="modal1" class="modal">
  <div class="modal-content">
   <h4>Available Plans To Change</h4>
      <div class="GFGclass" id="divGFG"></div>
  </div>
  <div class="modal-footer">
    <a href="#!" class="modal-close waves-effect waves-green btn-flat">Close</a>
  </div>
</div>
<!-- partial:index.partial.html -->
<div class="container">
  <!-- Page Content goes here -->
<c:if test="${not empty model.error}">
   <script>
   M.toast({html: '<p style="color:red;">${model.error} :(</p>',classes: 'white rounded'})
   instance.open();
   </script>
</c:if>

  <div class="container">
    <h2>My Subscriptions</h2>
    <input class="form-control" id="myInput" type="text" placeholder="Search..">
    <br>
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>Plan Id</th>
          <th>Plan Name</th>
          <th>Plan Type</th>
          <th>Plan Tariff</th>
          <th>Plan Validity</th>
          <th>Plan Rental</th>
          <th>Plan Start Date</th>         
          <th>Action</th>
        </tr>
      </thead>
      <tbody id="myTable">
       <c:forEach items="${model.list}" var="plan">
		<tr>
			<td class="planId">${plan.planid}</td>
			<td class="planName">${plan.planname}</td>
			<td class="planType">${plan.plantype}</td>
			<td class="planPrice">${plan.plantariff}</td>
			<td class="planValidity">${plan.planvalidity}</td>
			<td><p>&#x20b9;${plan.planrental}</p></td>
			<td class="planStartDate">${plan.planstartdate}</td>			
			<td style='white-space: nowrap'>
				<a class="waves-effect waves-light red btn modal-trigger" href='cancelplan?planid=${plan.planid}&planstartdate=${plan.planstartdate}'>Cancel</a>
            	<a class="waves-effect waves-light btn modal-trigger modalBtn" href="#modal1">Change</a>
          	</td>
          	<td class="remainingplans" style="visibility:hidden;">			 
   			<c:forEach items="${model.availablePlansToChange}" var="plan2">
   			<div id="plan${plan.planid}">
   				<input type="hidden" class="planid" value="${plan2.planid}">
   				<input type="hidden" class="planname" value="${plan2.planname}">
   				<input type="hidden" class="plantype" value="${plan2.plantype}">
   				<input type="hidden" class="plantariff" value="${plan2.plantariff}">
   				<input type="hidden" class="planvalidity" value="${plan2.planvalidity}">
   				<input type="hidden" class="planrentel" value="${plan2.planrental}">
   				<input type="hidden" class="customerid" value="${model.customerid}">
   				<input type="hidden" class="oldplanid" value="${plan.planid}">   				
   			</div>
   			</c:forEach>
		
          	</td>
		</tr>
	</c:forEach>   
      </tbody>
    </table>
  
  </div>
  


</div>
<footer class="page-footer blue" style="position:fixed;bottom:0;left:0;width:100%;">

    <div class="container">
    © 2020 Copyright Text
    </div>

</footer>

<script>
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
    	console.log("click");
        var customerplans = $(this).parents("tr").find(".remainingplans"); 
        var p = 
        	"<table class='table table-bordered'>"+
        	 "<thead>"+
        "<tr>"+
          "<th>Plan Id</th>"+
          "<th>Plan Name</th>"+
          "<th>Plan Type</th>"+
          "<th>Plan Tariff</th>"+
          "<th>Plan Validity</th>"+
          "<th>Plan Rental</th>"
          "<th>Action</th>"+
        "</tr>"+
      "</thead>"+
      "<tbody id='myTable'>";

        $(customerplans).children().each( function(index, element) {
           // children's element
            		  p += "<tr>";
           			   var id = $(element).children().get(0).value;
      			       var a =  $(element).children().get(1).value; 
                       var c = $(element).children().get(2).value; 
                       var d = $(element).children().get(3).value; 
                       var e = $(element).children().get(4).value; 
                       var f = $(element).children().get(5).value; 
                       var customerid = $(element).children().get(6).value; 
                       var oldplanid = $(element).children().get(7).value; 
                       
                       
                       
                       // CREATING DATA TO SHOW ON MODEL 
                         p +=  
                 "<td id='a' name='planId' >Plan Id: " 
                         + id + " </td>"; 
                         
                       p +=  
                 "<td id='a' name='planName' >Plan Name: " 
                         + a + " </td>"; 
                       
                       p += 
                 "<td id='c' name='planType'>Plan Type: "  
                         + c + "</td>"; 
                       p +=  
                 "<td id='d' name='planPrice' >Plan Price: " 
                         + d + " </td>"; 
                       p +=  
                 "<td id='e' name='planValidity' >Plan Validity: " 
                         + e + " </td>"; 
                         
                       p +=  
                  "<td id='f' name='planRental' >Plan Rental: " 
                          + f + " </td>"; 
               
                       p +=  
                   "<td id='g' name='action' >" 
                           + "<a class='waves-effect waves-light btn modal-trigger' href='changeplan?newplanid="+id+"&oldplanid="+oldplanid+"&customerid="+customerid+"'>Change</a>" + " </td>"; 
                                      
                         p +=  
                             "</tr>"; 
                              
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
