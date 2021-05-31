

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataAccessLayer.EmbeddedNeo4j;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
	 	response.setContentType("application/json");
	 	response.setCharacterEncoding("UTF-8");
	 	JSONObject myResponse = new JSONObject();
	 	
	 	
	 	String user = request.getParameter("user");
	 	String pass = request.getParameter("user_pass");
	 	
	 	 try ( EmbeddedNeo4j greeter = new EmbeddedNeo4j( "bolt://localhost:7687", "neo4j", "uvgproy123@" ) )
	        {
	 		 	//System.out.println("Hola workkkkk");
	 		LinkedList<String> password = greeter.loginUser(user, pass);
	 		if(password.isEmpty()) {
	 			myResponse.put("resultado", "error");
	 		}
	 		else {
	 			myResponse.put("resultado", password);
	 		}
			 	//System.out.println("Si workkkkk");
	        	
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	 
	 	
	 	out.println(myResponse);
	 	out.flush();  
	 	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
