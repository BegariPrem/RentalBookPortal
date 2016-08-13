
<table class="table table-hover">
	<thead>
				<th>Book Name</th><th>Book Author</th><th>Book ID</th><th>Price</th><th>Availability</th>
	</thead>
	
	<tbody>
				<#list bookList as book>
				<tr>
				<td>${book.bookName}</td><td>${book.bookAuthor}</td><td>${book.bookId}</td><td>${book.bookPrice}</td><td>${book.bookAvailability}</td>
				</tr>
				</#list>
	</tbody>
</table>
