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

import java.util.ArrayList;
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
    
    public LinkedList<String> getRandomBooks(){
    	 try ( Session session = driver.session() )
         {

    		 LinkedList<String> books = session.readTransaction( new TransactionWork<LinkedList<String>>()
             {
                 @Override
                 public LinkedList<String> execute( Transaction tx )
                 {
                     Result result = tx.run( "match(b1:Book{name:\"Harry Potter and the Order of the Phoenix\"}),\r\n"
                     		+ "(b2:Book{name:\"Moby Dick (Great Illustrated Classics)\"}),\r\n"
                     		+ "(b3:Book{name:\"If I Stay\"}),\r\n"
                     		+ "(b4:Book{name:\"The Lord of the Rings\"}),\r\n"
                     		+ "(b5:Book{name:\"Gullivers Travels\"}),\r\n"
                     		+ "(b6:Book{name:\"V for Vendetta\"}),\r\n"
                     		+ "(b7:Book{name:\"The Exorcist\"}),\r\n"
                     		+ "(b8:Book{name:\"The Maze Runner\"}),\r\n"
                     		+ "(b9:Book{name:\"The Hunger Games\"}),\r\n"
                     		+ "(b10:Book{name:\"The Divine Comedy\"}),\r\n"
                     		+ "(b11:Book{name:\"I, Robot\"}),\r\n"
                     		+ "(b12:Book{name:\"Don Quixote\"}),\r\n"
                     		+ "(b13:Book{name:\"One Hundred Years of Solitude\"}),\r\n"
                     		+ "(b14:Book{name:\"The Great Gatsby\"}),\r\n"
                     		+ "(b15:Book{name:\"Ulysses\"})\r\n"
                     		+ "return b1.name,b2.name,b3.name,b4.name,b5.name,b6.name,b7.name,b8.name,b9.name,b10.name,b11.name,b12.name,b13.name,b14.name,b15.name");
                     
                     LinkedList<String> ratedBooks = new LinkedList<String>();
                     List<Record> resultBooks = result.list();
                     for(int j=0; j < resultBooks.size(); j++) {
                    	 for(int i=0; i < resultBooks.get(j).size(); i++) {
                        	 //System.out.println(resultBooks.get(j).get(i).asString());
                        	 ratedBooks.add(resultBooks.get(j).get(i).asString());

                         }
                     }
                     //for(int i =0; i < ratedBooks.size(); i++) {
                    //	 System.out.println(ratedBooks.size());
                    //	 System.out.println(ratedBooks.get(i));
                    // }
                     
                     return ratedBooks;
                 }
             } );
             
             return books;
         }
    }  
    public void createRelations(String name, ArrayList<String> books)
    {
    	try(Session session1 = driver.session())  {

     		 LinkedList<String> relations = session1.writeTransaction( new TransactionWork<LinkedList<String>>()
              {
                  @Override
                  public LinkedList<String> execute( Transaction tx )
                  {
                	  for(int i=0; i<books.size(); i++) {
                		  tx.run("MATCH (a: User {username: \"" + name + "\"}), (d: Book {name: \""+ books.get(i) + "\"}) CREATE (a)-[:read]->(d)");
                	  }
                      LinkedList<String> myactors = new LinkedList<String>();
                      return myactors;
                  }
              } );
    	}
    }
    
    public LinkedList<String> getMyBooks(String user){
   	 try ( Session session = driver.session() )
        {

   		 LinkedList<String> books = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    Result result = tx.run("MATCH (u:User {username: \"" + user + "\"})-[r:read]-(b:Book) return b.name limit 5");
                    
                    LinkedList<String> ratedBooks = new LinkedList<String>();
                    List<Record> resultBooks = result.list();
                    
                    
                    for(int i=0; i<resultBooks.size(); i++) {
                    	ratedBooks.add(resultBooks.get(i).get("b.name").asString());
                    }
                    //for(int i =0; i < ratedBooks.size(); i++) {
                   //	 System.out.println(ratedBooks.size());
                   //	 System.out.println(ratedBooks.get(i));
                   // }
                    
                    return ratedBooks;
                }
            } );
            
            return books;
        }
   }  
    
    public LinkedList<String> getMyRecs(String user){
      	 try ( Session session = driver.session() )
           {

      		 LinkedList<String> books = session.readTransaction( new TransactionWork<LinkedList<String>>()
               {
                   @Override
                   public LinkedList<String> execute( Transaction tx )
                   {
                       Result result = tx.run("match (u:User{username: \""+user+"\"})\r\n"
                       		+ "with u\r\n"
                       		+ "match (u)-[:read]-(b:Book)-[:is_a|published_last_edition|written_in|wrote]-(bt)\r\n"
                       		+ "with u,collect(distinct bt.name) as s1,count(distinct bt) as intersection\r\n"
                       		+ "MATCH (u)-[:read]-(:Book)-[:is_a|published_last_edition|written_in|wrote]-(t)-[:is_a|published_last_edition|written_in|wrote]-(other:Book)-[:is_a|published_last_edition|written_in|wrote]-(ot) \r\n"
                       		+ "where not exists ((u)-[:read]-(other))\r\n"
                       		+ "with u,s1,collect(DISTINCT ot.name) as s2,intersection,other\r\n"
                       		+ "with u,intersection,s1,s2,s1+[x in s2 where not x in s1] as union,other\r\n"
                       		+ "RETURN u.name,other.name,s1,s2,((1.0*intersection)/SIZE(union)) AS jaccard ORDER BY jaccard DESC LIMIT 5");
                       
                       LinkedList<String> ratedBooks = new LinkedList<String>();
                       List<Record> resultBooks = result.list();
                       
                       System.out.println("Hola2");
                       
                       for(int i=0; i<resultBooks.size(); i++) {
                       	System.out.println(resultBooks.get(i).get("other.name").asString());
                       }
                       //for(int i =0; i < ratedBooks.size(); i++) {
                      //	 System.out.println(ratedBooks.size());
                      //	 System.out.println(ratedBooks.get(i));
                      // }
                       
                       return ratedBooks;
                   }
               } );
               
               return books;
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
