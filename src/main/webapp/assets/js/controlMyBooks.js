$(document).ready(function(){
    $.ajax( {

            type: "GET",
            url: '/Shelfing/myBooksServlet',
            success: function(data) {
                //alert("Result" + data.resultado);
                var htmlMyBooksList = "<ul>";
                $.each(data.misLibros, function(i,item){
                      htmlMyBooksList += '<li>'+item+'</li>'; 
                });

                htmlMyBooksList += '</ul>';
                $('#div-myBooks').html("");
                $('#div-myBooks').html("").append(htmlMyBooksList);
            }
        } );
});