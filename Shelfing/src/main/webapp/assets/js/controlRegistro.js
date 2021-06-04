$(document).ready(function(){
    $("#register").click(function(){
        const user = document.getElementById("user").value;
        const name = document.getElementById("name").value;
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;
        const pass2 = document.getElementById("password2").value;

        if(!user || !name || !email || !password || !pass2) {
            alert("Debes llenar los datos antes de continuar!");
        	
            
        }
        else{
            if(password == pass2 && password != null){
            
               // alert(password);
               $.ajax( {
				
	              type: "GET",
	              url: '/Shelfing/RegisterServlet?user=' + user + '&user_name=' + name +'&user_email='+ email +'&user_pass='+ password,
	              success: function(data) {
	                alert("Bienvenido");
	               }
	           } );
        	window.open("index.html","_self")
        	}
        	else{
	        	alert("Tu contrase\u00f1a debe coincidir");
	            
        	}
            
        }
        
    });
  });