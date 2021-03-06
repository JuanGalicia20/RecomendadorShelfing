/**
 * 
 */
package dataAccessLayer;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;

import static org.neo4j.driver.Values.parameters;

import java.util.LinkedList;
import java.util.List;
/**
 * @author Administrator
 *
 */
public class EmbeddedNeo4j implements AutoCloseable{

    private final Driver driver;
    

    public EmbeddedNeo4j( String uri, String user, String password )
    {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    @Override
    public void close() throws Exception
    {
        driver.close();
    }
    
    
    public void printGreeting( final String message )
    {
        try ( Session session = driver.session() )
        {
            String greeting = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "CREATE (a:Greeting) " +
                                                     "SET a.message = $message " +
                                                     "RETURN a.message + ', from node ' + id(a)",
                            parameters( "message", message ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
    }
    
    public LinkedList<String> getActors()
    {
    	 try ( Session session = driver.session() )
         {
    		 
    		 
    		 LinkedList<String> actors = session.readTransaction( new TransactionWork<LinkedList<String>>()
             {
                 @Override
                 public LinkedList<String> execute( Transaction tx )
                 {
                     Result result = tx.run( "MATCH (people:Person) RETURN people.name");
                     LinkedList<String> myactors = new LinkedList<String>();
                     List<Record> registros = result.list();
                     for (int i = 0; i < registros.size(); i++) {
                    	 //myactors.add(registros.get(i).toString());
                    	 myactors.add(registros.get(i).get("people.name").asString());
                     }
                     
                     return myactors;
                 }
             } );
             
             return actors;
         }
    }
    public void createUser(String user, String name, String email, String password) {
    try(Session session1 = driver.session())  {
  		 
  		 
  		 LinkedList<String> actors = session1.writeTransaction( new TransactionWork<LinkedList<String>>()
           {
               @Override
               public LinkedList<String> execute( Transaction tx )
               {
                   tx.run("CREATE ("+user+": User {name: \"" + name + "\", username: \"" + user +"\", email: \""+ email + "\", password: \""+password+"\"})");
                   LinkedList<String> myactors = new LinkedList<String>();
                   
                   return myactors;
               }
           } );

       }
    }
    public LinkedList<String> loginUser(String user, String pass){
    	try ( Session session = driver.session() )
        {

   		 LinkedList<String> password = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (n: User) WHERE n.username= \"" + user + "\" RETURN n.password");
                    LinkedList<String> myactors = new LinkedList<String>();
                    //System.out.println(result.list().get(0).get(0).asString());
                   	myactors.add(result.list().get(0).get(0).asString());
                    return myactors;
                }
            } );
            
            return password;
        }    
    }
    
    public LinkedList<String> myBooksUser(String user){
    	try ( Session session = driver.session() )
        {

   		 LinkedList<String> myBooks = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    Result result = tx.run("MATCH (user:User{username: \"" + user + "\"})--(book:Book) RETURN book.name");
                    LinkedList<String> misLibrosU = new LinkedList<String>();
                    //System.out.println(result.list().get(0).get(0).asString());
                   	misLibrosU.add(result.get(i).get("book.name").asString());
                    return misLibrosU;
                }
            } );
            
            return myBooks;
        }    
    }
    

    public LinkedList<String> getMoviesByActor(String actor)
    {
   	 try ( Session session = driver.session() )
        {
   		 
   		 
   		 LinkedList<String> actors = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (tom:Person {name: \"" + actor + "\"})-[:ACTED_IN]->(actorMovies) RETURN actorMovies.title");
                    LinkedList<String> myactors = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 //myactors.add(registros.get(i).toString());
                   	 myactors.add(registros.get(i).get("actorMovies.title").asString());
                    }
                    
                    return myactors;
                }
            } );
            
            return actors;
        }
   }

	

}
