<table class="table table-hover">
	<thead>
				<th>Book Name</th><th>Book ID</th><th>Issued Date</th><th>Amount Paid</th>
	</thead>
	
	<tbody>
				<#list bookList as book>
				<tr>
				<td>${book.bookName}</td><td>${book.bookId}</td><td>${book.issueDate}</td><td>${book.amountPaid}</td>
				</tr>
				</#list>
	</tbody>
</table>