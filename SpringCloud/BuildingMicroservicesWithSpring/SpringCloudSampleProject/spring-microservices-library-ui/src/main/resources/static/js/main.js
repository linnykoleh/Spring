$(document).ready(function() {
	var page = new Page();
	page.init();
});

function Page() {
	this.name = prompt("Please provide your name:");
}

Page.prototype.init = function() {
	this.bind();
};

Page.prototype.bind = function() {
	this.bindNavigation();
};

Page.prototype.bindNavigation = function() {
	var that = this;
	$("#my-reservations").click(function() {
		$.post("http://localhost:5000/reservation/user/" + that.name, function(data) {
				var html = "<h4>Reservations</h4>"; 
				html += "<table>";
				html += "<tr>";
				html += "<th>Reservation Id</th>"
				html += "<th>Book Id</th>"
				html += "<th>Date</th>"
				html += "</tr>"
					
				for (var x = 0; x < data.length; x++) {
					html += "<tr>";
					html += "<td>"+ data[x].reservationId +"</td>";
					html += "<td>"+ data[x].bookId + "</td>";
					html += "<td>"+ data[x].date +"</td>";
					html += "</tr>";
				}
				html += "</table>";
				
				$("#content").html(html);
		})
	});

	$("#catalog").click(function() {
		$.post("http://localhost:5001/catalog", function(data) {
			var html = "<h4>Catalog</h4>"; 
			html += "<table>";
			html += "<tr>";
			html += "<th>Title</th>"
			html += "<th>Author</th>"
			html += "<th>Page Count</th>"
			html += "<th>Genre</th>"
			html += "<th></th>"
			html += "</tr>"
				
			for (var x = 0; x < data.length; x++) {
				html += "<tr>";
				html += "<td>"+ data[x].title +"</td>";
				html += "<td>"+ data[x].authorLastName + ", " + data[x].authorFirstName + "</td>";
				html += "<td>"+ data[x].pages +"</td>";
				html += "<td>"+ data[x].genre +"</td>";
				html += "<td><a href=\"http://localhost:5000/reservation/user/{user}/book/" + data[x].bookId +"\" class=\"reserve-link\">Reserve</a></td>";
				html += "</tr>";
			}
			html += "</table>";
			
			$("#content").html(html);
			
			$("a").click(function(){return false;});
			$(".reserve-link").click(function(e){
				var href = $(this).attr("href");
				href = href.replace("{user}", that.name);
				$.post(href, function(data){
					alert(data);
				});
				return false;
			});
		})
	});
};