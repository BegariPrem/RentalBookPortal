function onSignIn(googleUser) {
			console.log("in script");
		  var profile = googleUser.getBasicProfile();
		  var id_token = googleUser.getAuthResponse().id_token;
		  console.log(id_token);
		  console.log('ID: ' + profile.getId()); 
		  console.log('Name: ' + profile.getName());
		  console.log('Image URL: ' + profile.getImageUrl());
		  console.log('Email: ' + profile.getEmail());
		 document.getElementById("googleSignInButton").addEventListener('click', function() {
			 document.getElementById('tokenId').value=id_token;
			  document.getElementById('googleForm').submit();

			  console.log("form is submited");
		 }, false);
			 
		  //});
		}	

$(document).ready(function(){
		
	
	
	
		$('.form').find('input, textarea').on('keyup blur focus', function (e) {
		  
		  var $this = $(this),
		      label = $this.prev('label');
		
			  if (e.type === 'keyup') {
					if ($this.val() === '') {
		          label.removeClass('active highlight');
		        } else {
		          label.addClass('active highlight');
		        }
		    } else if (e.type === 'blur') {
		    	if( $this.val() === '' ) {
		    		label.removeClass('active highlight'); 
					} else {
				    label.removeClass('highlight');   
					}   
		    } else if (e.type === 'focus') {
		      
		      if( $this.val() === '' ) {
		    		label.removeClass('highlight'); 
					} 
		      else if( $this.val() !== '' ) {
				    label.addClass('highlight');
					}
		    }
		
		});

		$('.tab a').on('click', function (e) {
		  
		  e.preventDefault();
		  
		  $(this).parent().addClass('active');
		  $(this).parent().siblings().removeClass('active');
		  
		  target = $(this).attr('href');
		
		  $('.tab-content > div').not(target).hide();
		  
		  $(target).fadeIn(600);
		  
		});

		$("#userLoginButton").click(
				function validateDetails(){
					console.log("function called");
					var userEmail =$("#userEmail").val();
					var userPassword = $("#userPassword").val();
					 var pas = /^[a-zA-Z0-9!@#$%^&*]{4,16}$/;
					 var mail=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;	
					if(userEmail == ""||userEmail==null){
						$("#userEmailError").removeAttr("hidden");
						$("#userEmail").focus(function(){
							$("#userEmailError").attr("hidden", "hidden");
						});
						return false;
					}
				
					if(userPassword ==""||userPassword==null){
						$("#userPasswordError").removeAttr("hidden");
						
						$("#userPassword").focus(function(){
							$("#userPasswordError").attr("hidden", "hidden");
						});
						return false;
					}
				
				
					else if(mail.test(userEmail) == false)
					{	//alert(mail.test(email));
						
						$("#userEmailError").removeAttr("hidden").text("Enter the valid Email ID(Like : name@gmail.com)");
						$("#userEmail").focus(function(){
							$("#userEmailError").attr("hidden", "hidden");
						});
						return false;
					}
				
					else if(pas.test(userPassword) == false)
					{
						
						$("#userPasswordError").removeAttr("hidden").text("Enter the valid Password(must have 4 characters)");
						$("#userPassword").focus(function(){
							$("#userPasswordError").attr("hidden", "hidden");
						});
						return false;
					}
			 
		});
	$("#newUserLoginButton").click(
			function UserValidateDetails(){
				console.log("function called");
				var userEmail =$("#newUserEmail").val();
				var userPassword = $("#newUserPassword").val();
				var confirmPassword = $("#newUserConfirmPassword").val();
				 var pas = /^[a-zA-Z0-9!@#$%^&*]{4,16}$/;
				 var mail=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;	
				if(userEmail == ""||userEmail==null){
					$("#newUserEmailError").removeAttr("hidden");
					$("#newUserEmail").focus(function(){
						$("#newUserEmailError").attr("hidden", "hidden");
					});
					return false;
				}
			
				if(userPassword ==""||userPassword==null){
					$("#newUserPasswordError").removeAttr("hidden");
					
					$("#newUserPassword").focus(function(){
						$("#userPasswordError").attr("hidden", "hidden");
					});
					return false;
				}
				 if(confirmPassword == ""){
					$("#newUserConfirmPasswordError").removeAttr("hidden");
					$("#newUserConfirmPassword").focus(function(){
						$("#newUserConfirmPasswordError").attr("hidden", "hidden");
					});
					return false;
				}
			
				else if(mail.test(userEmail) == false)
				{	//alert(mail.test(email));
					
					$("#newUserEmailError").removeAttr("hidden").text("Enter the valid Email ID(Like : name@gmail.com)");
					$("#newUserEmail").focus(function(){
						$("#newUserEmailError").attr("hidden", "hidden");
					});
					return false;
				}
			
				else if(pas.test(userPassword) == false)
				{
					
					$("#newUserPasswordError").removeAttr("hidden").text("Enter the valid Password(must have 4 characters)");
					$("#newUserPassword").focus(function(){
						$("#newUserPasswordError").attr("hidden", "hidden");
					});
					return false;
				}
				else if(userPassword != confirmPassword){
					//alert(password == confirmPassword);
					$("#newUserConfirmPasswordError").removeAttr("hidden").text("confirm password should match password");
					$("#newUserConfirmPassword").focus(function(){
						$("#newUserConfirmPasswordError").attr("hidden", "hidden");
					});
					return false;
				}
			});
	
	

});