$(document).ready(function(){
					$.ajax(
					{
						type:"GET",
						url:"/MiniProject/rest/books/allCategories",
						contentType:"text/html",
						success:function(data){
							$('#categoryDropDown').html(data);
						}
					});
					
					$('#lentCustomerId').click(function(){
						var check=true;
						var customerId=$('#customerId').val();
						if(customerId==""||customerId==null)
						{
							$("#customerIdError").removeAttr("hidden");
							$("#customerId").focus(function(){
								$("#customerIdError").attr("hidden", "hidden");
							});
							check=false;
						}
						if(Math.sign(customerId)==-1)
						{
							
									$("#customerIdError").removeAttr("hidden").text("please enter correct customerId ");
									$("#customerId").focus(function(){
										$("#customerIdError").attr("hidden", "hidden");
									});
									check=false;
								
							
						}
						if(check==true)
						{
							console.log("true");
							var unique_id=0;
							$.ajax(
									{
										type:"GET",
										url:"/MiniProject/rest/user/customer/"+parseInt(customerId),
										dataType:"json",
										success:function(data){
											console.log("/MiniProject/rest/user/customer/"+customerId);
											var count=data.count;
											unique_id=data.unique_id;
											if(count>4){
												var buttons=Math.ceil(count/4.0);
												console.log(count+" "+unique_id+" "+buttons);
												$('#searchCustomerButtons').html("");
												var i;
												for(i=0;i<buttons;i++)
													{
			
														$("#searchCustomerButtons").append("<li style='display:inline' value="+i+"><a>"+(i+1)+"</a></li>");
													}
												  $("#searchCustomerButtons li:first").addClass("active");
											
										
										
										$.ajax({
											type:"GET",
											url:"/MiniProject/rest/user/customerPagination?unique_id="+unique_id+"&offset=0&end=4",
											success:function(data)
											{
												console.log("iam called");
												console.log(data);
												$('#adminCustomerLentResult').html(data);
											}
										
										});
								 
									$('#searchCustomerButtons').on("click","li",function(){
										$('#searchCustomerButtons li').removeClass("active");
										$(this).addClass("active");
										var pageNumber=$(this).val();
										if(pageNumber!=0)
											{
												pageNumber=pageNumber*4;
											}
										$.ajax({
											type:"GET",
											url:"/MiniProject/rest/user/customerPagination?unique_id="+unique_id+"&offset="+pageNumber+"&end=4",
											success:function(data)
											{
												console.log("iam called");
												console.log(data);
												$('#adminCustomerLentResult').html(data);
											}
										
										});
										
										
									});
												
												
											}
											else{$('#searchCustomerButtons').html("");
											$.ajax({
												type:"GET",
												url:"/MiniProject/rest/user/customerPagination?unique_id="+unique_id+"&offset=0&end=8",
												success:function(data)
												{
													console.log("iam called");
													console.log(data);
													$('#adminCustomerLentResult').html(data);
												}
											});
											
											}
										}
									
									});
							console.log("ajax executed");
						}
					});
					
					$('#searchBooksButton').click(function(){
						var bookName=$('#bookName').val();
						var bookAuthor=$('#bookAuthor').val();
						var categoryId=$('#categoryId').val();
						check=true;
						if(Math.sign(categoryId)==-1)
						{
							check=false;
						}
						if(Math.sign(categoryId)==1)
						{
							check=true;
						}
						if(check==true)
						{
							var data={
									"bookName":bookName,
									"bookAuthor":bookAuthor,
									"bookCategory":categoryId
							};
							console.log(data);
							console.log(JSON.stringify(data));
							 var unique_id=0;
							$.ajax(
									{
										type:"POST",
										url:"/MiniProject/rest/books/bookSearch",
										data:JSON.stringify(data),
										contentType:"application/json",
										success:function(data){
											 
											console.log(data);
											 
											var count=data.count;
											unique_id=data.unique_id;
											if(count>8){
												var buttons=Math.ceil(count/8.0);
												console.log(count+" "+unique_id+" "+buttons);
												$('#searchDisplayButtons').html("");
												var i;
												for(i=0;i<buttons;i++)
													{
			
														$("#searchDisplayButtons").append("<li style='display:inline' value="+i+"><a>"+(i+1)+"</a></li>");
													}
												  $("#searchDisplayButtons li:first").addClass("active");
											
										
										
										$.ajax({
											type:"GET",
											url:"/MiniProject/rest/books/searchBookResultPagination?unique_id="+unique_id+"&offset=0&end=8",
											success:function(data)
											{
												console.log("iam called");
												console.log(data);
												$('#searchBookDisplay').html(data);
											}
										
										});
								 
									$('#searchDisplayButtons').on("click","li",function(){
										$('#searchDisplayButtons li').removeClass("active");
										$(this).addClass("active");
										var pageNumber=$(this).val();
										if(pageNumber!=0)
											{
												pageNumber=pageNumber*8;
											}
										$.ajax({
											type:"GET",
											url:"/MiniProject/rest/books/searchBookResultPagination?unique_id="+unique_id+"&offset="+pageNumber+"&end=8",
											success:function(data)
											{
												console.log("iam called");
												console.log(data);
												$('#searchBookDisplay').html(data);
											}
										
										});
										
										
									});
												
												
											}else{$('#searchDisplayButtons').html("");
											$.ajax({
												type:"GET",
												url:"/MiniProject/rest/books/searchBookResultPagination?unique_id="+unique_id+"&offset=0&end=8",
												success:function(data)
												{
													console.log("iam called");
													console.log(data);
													$('#searchBookDisplay').html(data);
												}
											});
											
											}
											
							}
					}); 
				}
					});
					
					$('#logoutButton').click(function(){
						
						var xhttp = new XMLHttpRequest();
						  xhttp.onreadystatechange = function() {
						    if (xhttp.readyState == 4 && xhttp.status == 200) {
						      window.open("/MiniProject/rest/user/logout","_top");
						    }
						  };
						  xhttp.open("GET", "/MiniProject/rest/user/logout", true);
						  xhttp.send();
						
					});
				
});
		