$(document).ready(function(){
    $.ajax( {
			
			type: "GET",
			url: '/Shelfing/BooksServlet',
			success: function(data) {
				//alert("Result" + data.resultado);
			    var htmlBooksList = "";
				$.each(data.books, function(i,item){
					  htmlBooksList += '<div class="btn-group btn-group-toggle" data-toggle="buttons"><div class="btn-group btn-group-toggle px-1 py-1"><label class="btn btn-warning btn-primary">';
					  htmlBooksList += '<input type="checkbox" value="'+item+'" id="checkbox'+i+'" autocomplete="off"><b <b style="font-size: 24px;">' + item + '</b></input>';
					  htmlBooksList += '</label></div></div>';
				});

				$('#booksList').html(" ");
				$('#booksList').append(htmlBooksList);
			}
		} );	
		
	 $("#continue").click(function(){
            let checkedBoxes = [];
            var name = localStorage.getItem("user");
            localStorage.setItem("user1", name);

            const c1 = document.getElementById('checkbox0');
            const c2 = document.getElementById('checkbox1');
            const c3 = document.getElementById('checkbox2');
            const c4 = document.getElementById('checkbox3');
            const c5 = document.getElementById('checkbox4');
            const c6 = document.getElementById('checkbox5');
            const c7 = document.getElementById('checkbox6');
            const c8 = document.getElementById('checkbox7');
            const c9 = document.getElementById('checkbox8');
            const c10 = document.getElementById('checkbox9');
            const c11 = document.getElementById('checkbox10');
            const c12 = document.getElementById('checkbox11');
            const c13 = document.getElementById('checkbox12');
            const c14 = document.getElementById('checkbox13');
            const c15 = document.getElementById('checkbox14');

          let checkArray = [c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15];
		  
          for(var i=0; i < checkArray.length; i++){
                if(checkArray[i].checked == true){
                    checkedBoxes.push(checkArray[i].value);
                }
                else{
                    checkedBoxes.push("null");
                }
          }

          $.ajax( {
			
			type: "GET",
			url: '/Shelfing/SendSelectedServelt?name='+name+'&book1='+checkedBoxes[0]+'&book2='+checkedBoxes[1]+'&book3='+checkedBoxes[2]+'&book4='+checkedBoxes[3]+'&book5='+checkedBoxes[4]+'&book6='+checkedBoxes[5]+'&book7='+checkedBoxes[6]+'&book8='+checkedBoxes[7]+'&book9='+checkedBoxes[8]+'&book10='+checkedBoxes[9]+'&book11='+checkedBoxes[10]+'&book12='+checkedBoxes[11]+'&book13='+checkedBoxes[12]+'&book14='+checkedBoxes[13]+'&book15='+checkedBoxes[14],
			success: function(data) {
                
               }
		} );
		window.open("recomendations.html","_self");
        });	
});