$(document).ready(function(){
    $("#login").click(function(){
        const user = document.getElementById("user").value;
        const password = document.getElementById("password").value;
    	
    	
        if(!user || !password ) {
            alert("Debes llenar los datos antes de continuar!");
 
        }
        else{
            //alert("Tu contrase\u00f1a debe coincidir");
            $.ajax( {
                type: "GET",
                url: '/Shelfing/LoginServlet?user='+ user +'&user_pass='+ password,
                success: function(data) {
					var res = "";
                    $.each(data.resultado, function(i,item){
                        res += item;
                    });
                    if(res == password){
                    window.open("chooseBooks.html","_self")
                    localStorage.setItem("user", user);
            		} 
            		else{
            			alert(res);
            		}
                }
            }); 
           
        }      
    });
        
});