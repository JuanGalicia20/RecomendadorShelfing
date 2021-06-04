$(document).ready(function(){
	var name = localStorage.getItem("user1");
    $.ajax( {
			type: "GET",
			url: '/Shelfing/MyBooksServlet?name=' + name,
			success: function(data) {
			    var htmlBooksList = "";
				$.each(data.books, function(i,item){
					  htmlBooksList += '<div class="btn-group btn-group-toggle mx-4"><label class="btn btn-primary" style="border-radius: 10px;">';
					  htmlBooksList += '<input type="checkbox" id="check1" value="Espana" autocomplete="off">' + item + '</input>';
					  htmlBooksList += '</label></div>';
				});

				$('#mybooks').html("");
				$('#mybooks').append(htmlBooksList);
			}
		} );
	$.ajax( {
			type: "GET",
			url: '/Shelfing/RecServlet?name=' + name,
			success: function(data) {
			    var htmlBooksList2 = "";
				$.each(data.books, function(i,item){
					  htmlBooksList2 += '<div class="btn-group btn-group-toggle mx-4"><label class="btn btn-primary" style="border-radius: 10px;">';
					  htmlBooksList2 += '<input type="checkbox" id="check1" value="Espana" autocomplete="off">' + item + '</input>';
					  htmlBooksList2 += '</label></div>';
				});

				$('#myrecs').html("");
				$('#myrecs').append(htmlBooksList2);
			}
		} );

});
