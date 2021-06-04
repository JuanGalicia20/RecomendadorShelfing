package main.java;

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
@WebServlet("/myBooksServlet")
public class myBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myBooksServlet() {
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

		JSonArray misLibros = new JSONArray();
	 	
	 	
	 	String user = request.getParameter("user");
	 	try ( EmbeddedNeo4j greeter = new EmbeddedNeo4j( "bolt://localhost:7687", "neo4j", "uvgproy123@" ) )
	        {
	 		 	//System.out.println("Hola workkkkk");
	 		LinkedList<String> myBooksU = greeter.myBooksUser(user);
	 		for ( int i = 0; i< myBooksU.size(); i++){
				 misLibros.add(myBooksU.get(i));

			}
		}catch ( Exception e){
			e.printStackTrace();
		}
	 	 
	 	myResponse.put("libros", misLibros);
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
