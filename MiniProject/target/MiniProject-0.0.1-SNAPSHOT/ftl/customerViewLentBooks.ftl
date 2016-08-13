<table class="table table-hover">
	<thead>
				<th>Book Name</th><th>Category ID</th><th>Book ID</th><th>Issued Date</th>
	</thead>
	
	<tbody>
				<#list bookList as book>
				<tr>
				<td>${book.bookName}</td><td>${book.categoryId}</td><td>${book.bookId}</td><td>${book.issueDate}</td>
				</tr>
				</#list>
	</tbody>
</table>