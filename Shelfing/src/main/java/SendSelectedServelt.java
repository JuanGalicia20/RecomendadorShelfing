

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccessLayer.EmbeddedNeo4j;

/**
 * Servlet implementation class SendSelectedServelt
 */
@WebServlet("/SendSelectedServelt")
public class SendSelectedServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendSelectedServelt() {
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
	 	
	 	String name = request.getParameter("name");
	 	String book1 = request.getParameter("book1");
	 	String book2 = request.getParameter("book2");
	 	String book3 = request.getParameter("book3");
	 	String book4 = request.getParameter("book4");
	 	String book5 = request.getParameter("book5");
	 	String book6 = request.getParameter("book6");
	 	String book7 = request.getParameter("book7");
	 	String book8 = request.getParameter("book8");
	 	String book9 = request.getParameter("book9");
	 	String book10 = request.getParameter("book10");
	 	String book11 = request.getParameter("book11");
	 	String book12 = request.getParameter("book12");
	 	String book13 = request.getParameter("book13");
	 	String book14 = request.getParameter("book14");
	 	String book15 = request.getParameter("book15");
	 	
	 	String[] books = {book1, book2, book3, book4, book5, book6, book7, book8, book9, book10, book11, book12, book13, book14, book15};
	 	ArrayList<String> selectedBooks = new ArrayList<>();
	 	
	 	
	 	for(int i=0; i < books.length;i++) {
	 		if(books[i].equals("null")){
	 			continue;
	 		}
	 		else{
	 			selectedBooks.add(books[i]);
	 		}
	 	}
	 	
	 	for(int i=0; i<selectedBooks.size();i++) {
	 		System.out.println(selectedBooks.get(i));
	 	}
	 	
	 	 try (EmbeddedNeo4j greeter = new EmbeddedNeo4j( "bolt://localhost:7687", "neo4j", "uvgproy123@" ))
	        {
	 		 	//System.out.println("Hola workkkkk");
			 	greeter.createRelations(name, selectedBooks);
			 	//System.out.println("Si workkkkk");
	        	
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
