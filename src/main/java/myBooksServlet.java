<<<<<<< Updated upstream
=======
package main.java;

>>>>>>> Stashed changes

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
 * Servlet implementation class RegisterServlet
 */
<<<<<<< Updated upstream
@WebServlet("/RecommendationServlet")
public class RegisterServlet extends HttpServlet {
=======
@WebServlet("/myBooksServlet")
public class myBooksServlet extends HttpServlet {
>>>>>>> Stashed changes
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
<<<<<<< Updated upstream
    public RecommendationServlet() {
=======
    public myBooksServlet() {
>>>>>>> Stashed changes
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
	 	response.setContentType("application/json");
	 	response.setCharacterEncoding("UTF-8");
	 	
	 	String user = request.getParameter("user");
	 	String name = request.getParameter("user_name");
	 	String email = request.getParameter("user_email");
	 	String pass = request.getParameter("user_pass");
	 	
	 	 try ( EmbeddedNeo4j greeter = new EmbeddedNeo4j( "bolt://localhost:7687", "neo4j", "uvgproy123@" ) )
	        {
	 		 	//System.out.println("Hola workkkkk");
<<<<<<< Updated upstream
			 	greeter.createUser(user, name, email, pass);
			 	//System.out.println("Si workkkkk");
=======
			 	LinkedList<String> myBooksU = greeter.myBooksUser(user);
			 	//System.out.println("Si workkkkk");

                for (int i = 0; i < myBooksU.size(); i++) {
                    out.println( "<p>" + myBooksU.get(i) + "</a>" );
                }
              
          } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
>>>>>>> Stashed changes
	        	
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	
	 	
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