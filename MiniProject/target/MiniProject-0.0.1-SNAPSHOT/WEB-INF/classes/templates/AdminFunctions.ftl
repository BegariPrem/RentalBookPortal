<html>
	<head>
	
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="/MiniProject/css/lentBook.css">
	<script src="/MiniProject/script/lentBook.js"></script>
	</head>
	
	<body>
	<div class="container-fluid">
		<header id="headerDiv">
		<img id="tree" src="/MiniProject/css/tree.jpg" alt="book tree">
		<span id="headerTitle">Online Book Portal</span>
		<div id="headerButtonHome">
		
		<span hidden>${customerId}</span>
		<span hidden>${userRole}</span>
		<span id=UserLoginName>Hi ${userName}!</span>
		<input type="button" id="addUserButton" class="addUserClass btn btn-success" value="Add User">
		<input type="button" id="issueBooksButton" class="issueBooksClass btn btn-success" value="Issue Books">
		<input type="button" id="addBooksButton" class="addBooksClass  btn btn-success" value="Add Books">
		<input type="button" id="returnBooksButton" class="returnBooksClass btn btn-success" value="Take Books">
		<input type="button" id="logoutButton"class="logout btn btn-success" value="LogOut">
		
		</div>
		
	</header>
		</header>
		<div class="row">
		<div class="setFixed">
		<div class="col-md-1"></div>
		<div id="ReturnBookDisplay" class="jumbotron col-md-5" >
		
		<form   class="col-md-10"   method="post">
		<legend>Return A Book</legend>
			  <fieldset class="form-group">
    				<label for="customerIdReturn">Customer ID</label>
    				<input type="number" class="form-control" id="customerIdReturn" name="customerId"  placeholder="Enter Customer ID" required>
				<p id="customerIdReturnError" style="color:red;" hidden>Please fill customerId field!</p>
 			</fieldset>
			<fieldset class="form-group">
    				<label for="bookIdReturn">Book ID</label>
    				<input type="number" class="form-control" id="bookIdReturn" name="bookId"  placeholder="Enter Book Id" required>
				<p id="bookIdReturnError" style="color:red;" hidden>Please fill bookId field!</p>
 			</fieldset>
 			 <button type="button" class="btn btn-success"  id="submitReturn">Submit</button>
 			 <p id="returnBookResult">added</p>
		</form>
		
	</div>
	<div class="col-md-1"></div>
		<div id="issueBookDisplay" class="jumbotron col-md-5" >
		
		<form  class="col-md-10">
		<legend>Issue A Book</legend>
			  <fieldset class="form-group">
    				<label for="customerIdIssue">Customer ID</label>
    				<input type="number" class="form-control" id="customerIdIssue" name="customerId" placeholder="Enter Customer ID" required>
				<p id="customerIdIssueError" style="color:red;" hidden>Please fill customerId field!</p>
 			</fieldset>
			<fieldset class="form-group">
    				<label for="bookIdIssue">Book ID</label>
    				<input type="number" class="form-control" id="bookIdIssue"name="bookId"  placeholder="Enter Book Id" required>
				<p id="bookIdIssueError" style="color:red;" hidden>Please fill bookId field!</p>
 			</fieldset>
  			 <button type="button" class="btn btn-success"  id="submitIssue">Submit</button>
 			<p id="issueBookResult">added</p>
		</form>
		
	</div>
	</div>
		</div>
		<div class="row">
		<div class="col-md-1"></div>
	<div class="addBookDisplay col-md-5">
		
		
		<form  class="col-md-10">
				<legend>Add a Book</legend>
			<fieldset class="form-group">
    				<label for="bookName">Book Name</label>
    				<input type="text" class="form-control" id="bookName" name="bookName" placeholder="Enter Book Name" required>
				<p id="bookNameError" style="color:red;" hidden>Please fill Book Name!</p>
 			</fieldset>
  			<fieldset class="form-group">
    				<label for="bookAuthor">Book Author</label>
    				<input type="text" class="form-control" id="bookAuthor" name="bookAuthor" placeholder="Enter Book Author" required>
				<p id="bookAuthorError" style="color:red;" hidden>Please fill book Author field!</p>
 			</fieldset>
 			<fieldset class="form-group">
    				<label for="categoryId">Select A  Category</label>
    				 <div id="categoryDropDown"></div>
 			</fieldset>
			<fieldset class="form-group">
    				<label for="bookQuantity">Book Quantity</label>
    				<input type="number" class="form-control" id="bookQuantity" name="bookQuantity" placeholder="Enter Book Quantity" required>
				<p id="bookQuantityError" style="color:red;" hidden>Please fill quantity field!</p>
  			</fieldset>
			<fieldset class="form-group">
    				<label for="bookPrice">Book Price</label>
    				<input type="number" class="form-control" id="bookPrice" name="bookPrice" placeholder="Enter Book Price" required>
				<p id="bookPriceError" style="color:red;" hidden>Please fill Price field!</p>
  			</fieldset>
			 <button type="button" class="btn btn-primary"  id="submitAddBook">Submit</button>
			 <button type="reset" class="btn btn-primary" style="margin:0px 5px">Reset</button>
			 <p id="addBookResult">added</p>
		</form>
		
	</div>
	<div class="col-md-1"></div>
	<div class="addUserDisplay col-md-5">
		
		<form  class="col-md-10" id="customerRegistrationForm" method="post">
				<legend>Register a Customer</legend>
			  <fieldset class="form-group">
    				<label for="customerFirstName">First Name</label>
    				<input type="text" class="form-control" id="customerFirstName" name="newFirstName" placeholder="Enter First Name"  >
				<p id="firstName" style="color:red;" hidden>Please fill firstName field!</p>
 			</fieldset>
			<fieldset class="form-group">
    				<label for="customerLastName">Last Name</label>
    				<input type="text" class="form-control" id="customerLastName" name="newLastName" placeholder="Enter Last Name"  >
				<p id="lastName" style="color:red;" hidden>Please fill lastName field!</p>
 			</fieldset>
  			<fieldset class="form-group">
    				<label for="customerEmail">Email Address</label>
    				<input type="email" class="form-control" id="customerEmail" name="newEmail" placeholder="Enter Email"  >
				<p id="customerEmailError" style="color:red;" hidden>Please fill email field!</p>
 			</fieldset>
			<fieldset class="form-group">
    				<label for="customerMobileNumber">Mobile Number</label>
    				<input type=number class="form-control" id="customerMobileNumber" name="newPhoneNumber" placeholder="Enter Mobile Number"  >
				<p id="mobileNumber" style="color:red;" hidden>Please fill mobile number field!</p>
 			</fieldset>
			 <button type="button" class="btn btn-primary"  id="submitAddUser">Submit</button>
			 <button type="reset" class="btn btn-primary" style="margin:0px 5px">Reset</button>
			 <p id="addUserResult"> </p>
		</form>
		
	</div>
	</div>
	</div>
		
		<br>
	</body>
</html>
