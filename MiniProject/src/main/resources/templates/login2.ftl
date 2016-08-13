<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign-Up/Login Form</title>
<link
	href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="/MiniProject/css/normalize.css">


<link rel="stylesheet" href="/MiniProject/css/style.css">
</head>

<body>

	<div class="form">

		<ul class="tab-group">
			<li class="tab active"><a href="#signup">First Time User
					login</a></li>
			<li class="tab"><a href="#login">Log In</a></li>
		</ul>

		<div class="tab-content">
			<div id="signup">
				<h1>Register Password</h1>

				<form action="/MiniProject/rest/login" method="post" onsubmit="return UserValidateDetails">
					<div class="field-wrap">
						<label> Email Address<span class="req">*</span>
						</label> <input type="email" id="newUserEmail" name="userEmail" required autocomplete="off" />
						<span id="newUserEmailError" style="color: red;" hidden></span>
					</div>
					<div class="field-wrap">
						<label> Create Password<span class="req">*</span>
						</label> <input type="password" id="newUserPassword" name="userPassword" required
							autocomplete="off" /> <span id="newUserPasswordError"
							style="color: red;" hidden></span>
					</div>

					<div class="field-wrap">
						<label> confirm Password<span class="req">*</span>
						</label> <input type="password" id="newUserConfirmPassword" required
							autocomplete="off" /> <span id="newUserConfirmPasswordError"
							style="color: red;" hidden></span>
					</div>

					<button type="submit" id="newUserLoginButton"
						class="button button-block" />
					Get Started
					</button>

				</form>

			</div>

			<div id="login">
				<h1>Welcome Back!</h1>

				<form action="/MiniProject/rest/login" method="post" onsubmit="return validateDetails">

					<div class="field-wrap">
						<label> Email Address<span class="req">*</span>
						</label> <input type="email" id="userEmail" name="userEmail"   autocomplete="off" />
						<span id="userEmailError" style="color: red;" hidden></span>
					</div>

					<div class="field-wrap">
						<label> Password<span class="req">*</span>
						</label> <input type="password" id="userPassword" name="userPassword"  
							autocomplete="off" /> <span id="userPasswordError"
							style="color: red;" hidden></span>
					</div>

					<button type="submit" id="userLoginButton" class="button button-block" />Log In</button>


				</form>

			</div>

		</div>
		<!-- tab-content -->

	</div>
	<!-- /form -->
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="/MiniProject/script/index.js"></script>
	<script>

		</script>


<div>${loginMessage}</div>
</body>
</html>
