
$(document).ready(function(){
    $("#login").click(function(){
        const user = document.getElementById("user").value;
        const password = document.getElementById("password").value;
        var res = "";
        if(!user || !password ) {
            alert("Debes llenar los datos antes de continuar!");
 
        }
        else{
            //alert("Tu contrase\u00f1a debe coincidir");
            $.ajax( {
                type: "GET",
                url: '/Shelfing/LoginServlet?user=' + user + '&user_pass='+ password,
                success: function(data) {
                    $.each(data.resultado, function(i,item){
                        res += item;
                    });
                }
            }); 
            
            if(res == password){
                alert("Niceeee")
            }
            
        }      
    });
        
});
