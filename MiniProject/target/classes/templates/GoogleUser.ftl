<html>
	<head>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	 <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	 <script src="https://apis.google.com/js/platform.js" async defer></script>
	 <script src="https://apis.google.com/js/api.js"></script>
	<link rel="stylesheet" href="/MiniProject/css/returnBook.css">
	<script src="/MiniProject/script/normalUser.js">	</script>
	</head>
	
	<body>
		<div class="container-fluid">
		<header id="headerDiv">
		<img id="tree" src="/MiniProject/css/tree.jpg" alt="book tree">
		<span id="headerTitle">Online Book Portal</span>
		<div id="headerButtonHome">
		
		<span id="UserLoginName">Hi ${userName}!</span>
		<input type="button" id="logoutButton"class="logout btn btn-success" value="LogOut">
		</div>
		
	</header>
		
	</header>
	<div class="row" id="topSearchDivRow">
		<div id="searchNavBar" class="col-md-3">
			<form action=# class="col-md-10"  >
		<legend>Search Books</legend>
			  <fieldset class="form-group">
    				<label for="bookName">Book Name</label>
    				<input type="text" class="form-control" id="bookName" name="bookName"  placeholder="Enter Book Name"  >
				<p id="booKNameError" style="color:red;" hidden>Please fill Book Name field!</p>
 			</fieldset>
			<fieldset class="form-group">
    				<label for="bookAuthor">Book Author</label>
    				<input type="text" class="form-control" id="bookAuthor" name="bookAuthor"  placeholder="Enter Author"  >
				<p id="bookAuthorError" style="color:red;" hidden>Please fill Book Author field!</p>
 			</fieldset>
 			<fieldset class="form-group">
    				<label>select a category</label>
    				
    				<span id="categoryDropDown"></span>
					
 			</fieldset>
 			 <button type="button" class="btn btn-success"  id="searchBooksButton"><span class="glyphicon glyphicon-search"></span> Search Books</button>
		</form>
			
			</div>
			
		
		
		<div class="col-md-1"></div>
		<div id="searchBookDisplay" class="col-md-7">
			 

</div>
</div>
 <div class="row"><div class="col-md-3"></div><div class="col-md-1"></div><div id="searchDisplayButtonsDiv"class="col-md-7"><ul class="pagination" id="searchDisplayButtons"></ul></div><div class="col-md-1"></div></div>
		 
	</body>
</html>
