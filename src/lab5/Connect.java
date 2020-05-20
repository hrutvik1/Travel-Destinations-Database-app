package net.sqlitetutorial;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;
/**
 *
 * @author sqlitetutorial.net
 */
public class Connect {
           
    /**
     * @param args the command line arguments
     */    
    public static void main(String[] args) {
        
        Scanner in =new Scanner(System.in);                
              
        Connection c = null;
                
                
        try{        
            Class.forName("org.sqlite.JDBC");
            String url="jdbc:sqlite:/Users/hrutvik/sqlite/java/connect/net/sqlitetutorial/Lab5/lab4.db";
            c = DriverManager.getConnection(url);
                        
            System.out.println("Connection to SQLite has been established.");            
            System.out.println("Welcome to the Destinations Database");        
            System.out.println("------------------------------------");
            

            System.out.println("Here is the current database with destinations and their ID's ");
            System.out.println(">   Bora Bora, ID=1");            
            System.out.println(">   Colosseum, ID=2");
            System.out.println(">   Eiffle Tower, ID=3");            
            System.out.println(">   Angkor Wat, ID=4");
            System.out.println(">   Banff National Park, ID=5");            
            System.out.println(">   Santorini, ID=6");
            System.out.println(">   Giza Necropolis, ID=7");            
            System.out.println(">   Skydive Dubai, ID=8");            
            System.out.println(">   Machu Picchu, ID=9");            
            System.out.println(">   Maafushi, ID=10");

            System.out.println("------------------------------------");
            System.out.println("------------------------------------");

                           
            while(true){
            System.out.println("Press 0 to exit");
                    
                System.out.println("Press 1 to see which destination in the safest");        
            System.out.println("Press 2 to see which vacation destination is the cheapest relative to others of its kind");
            System.out.println("Press 3 to see which vacation destination is expensive relative to others of its kind");
            System.out.println("Press 4 to see how long do people usually stay at a destination (occupancy)");                    
            System.out.println("Press 5 to see what is the climate like of a destination?");            
            System.out.println("Press 6 to see how did other tourists rate a vacation destination");            
            System.out.println("Press 7 to see what activities does a destination offer");            
            System.out.println("Press 8 to see what destination people stay at the longest");            
            System.out.println("Press 9 to see what country a destination is in");                                
            System.out.println("Press 10 to see what the population of the city of a destination is");
            System.out.println("------------------------------------");
            System.out.print(">> ");

            int querySelect=in.nextInt();

            if(querySelect==1){                           
                System.out.println("------------------------------------");             
                query1(c);               
                System.out.println("------------------------------------");
            }
             
            if(querySelect==2){                            
                System.out.println("------------------------------------");             
                query2(c);               
                System.out.println("------------------------------------");
            }
          
            if(querySelect==3){                      
                System.out.println("------------------------------------");             
                query3(c);               
                System.out.println("------------------------------------");
            }
                        
            if(querySelect==4){                          
                System.out.println("------------------------------------");
                System.out.println("Enter the ID of the destination you want to see average occupancy of");
                System.out.print(">> ");
                int x=in.nextInt();
                in.nextLine();
                query4(c,x);                                 
                System.out.println("------------------------------------");

            }     
           
            if(querySelect==5){                         
                System.out.println("------------------------------------");
                System.out.println("Enter the ID of the destination you want to see climate of");
                System.out.print(">> ");
                int x=in.nextInt();       
                query5(c,x);               
                System.out.println("------------------------------------");
            }
         
            if(querySelect==6){           
                System.out.println("------------------------------------");
                System.out.println("Enter the ID of the destination you want to see ratings of");
                System.out.print(">> ");
                int x=in.nextInt();
                in.nextLine();
                query6(c,x);                  
                System.out.println("------------------------------------");
            }
         
            if(querySelect==7){                      
                System.out.println("------------------------------------");
                System.out.println("Enter the ID of the destination whose activity you want to see");
                System.out.print(">> ");
                int x=in.nextInt();
                in.nextLine();            
                query7(c,x);                           
                System.out.println("------------------------------------");

            }

            
            
            if(querySelect==8){                      
                System.out.println("------------------------------------");                
                query8(c);                   
                System.out.println("------------------------------------");
            }
         
            if(querySelect==9){                   
                System.out.println("------------------------------------");
                System.out.println("Enter the ID of the destination whose country orgin you want to see");
                System.out.print(">> ");
                int x=in.nextInt();
                in.nextLine();
                query9(c,x);                        
                System.out.println("------------------------------------");

            }                                                                                     
          
            if(querySelect==10){                         
                System.out.println("------------------------------------");
                System.out.println("Enter the ID of the destination whose city population you want to see");
                System.out.print(">> ");
                int x=in.nextInt();
                in.nextLine();            
                query10(c,x);               
                System.out.println("------------------------------------");
            }
            
            if(querySelect==0){                 
                break;
            }
            }
        } catch ( Exception e ) {                                        
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );                         
            System.exit(0);                
        }                                   
    }

    private static void query1(Connection c) {
 try {        
            Statement stmt = c.createStatement();    
            
         String query="SELECT destName FROM destinations WHERE destID=(SELECT countryID FROM country WHERE countrySafetyIndex=(SELECT max(countrySafetyIndex) FROM country));";
         ResultSet rs = stmt.executeQuery(query);                                            
                                                   
            while (rs.next()) {                                                                           
                String destName=rs.getString("destName");                     
                System.out.println("The safest destination in the current database is: "+destName);
            }  
 
        } catch (SQLException e) {                                
            System.out.println(e.getMessage());            
        }             
    
    }

    private static void query2(Connection c) {
        

        try {        
            Statement stmt = c.createStatement();    
            
         String query="SELECT destName FROM destinations WHERE destPricings=(SELECT min(destPricings) FROM destinations);";
                
         ResultSet rs = stmt.executeQuery(query);                                            
                                                   
            while (rs.next()) {                                                                           
                String destName=rs.getString("destName");                     
                System.out.println("The cheapest destination in the current database is: "+destName);
            }  
 
        } catch (SQLException e) {                                
            System.out.println(e.getMessage());            
        }             
    
 
    }

    private static void query3(Connection c) {
try {        
            Statement stmt = c.createStatement();    
            
         String query="SELECT destName FROM destinations WHERE destPricings=(SELECT max(destPricings) FROM destinations);";
                
         
         ResultSet rs = stmt.executeQuery(query);                                            
                                                   
            while (rs.next()) {                                                                           
                String destName=rs.getString("destName");                     
                System.out.println("The most expensive destination in the current database is: "+destName);
            }  
 
        } catch (SQLException e) {                                
            System.out.println(e.getMessage());            
        }             
    
 
    }
  
    public static void query4(Connection c,int x){        
        try {        
            Statement stmt = c.createStatement();                           
         //   String query = "SELECT * FROM country";          
         String query=("SELECT destOccupancy,destName FROM destinations WHERE destID="+x);

         ResultSet rs = stmt.executeQuery(query);                                            
                                                   
            while (rs.next()) {                                                                           
                String destName=rs.getString("destName");                     
                float destOccupancy=rs.getFloat("destOccupancy");                                                                                    
                System.out.println("The destination with ID, "+x+", and name, "+destName+"'s, occupancy is: "+ destOccupancy);                                                                       
            }  
 
        } catch (SQLException e) {                                
            System.out.println(e.getMessage());            
        }             
    }
    
    private static void query5(Connection c,int x) {

            try {        
            Statement stmt = c.createStatement();                           
         //   String query = "SELECT * FROM country";          
         String query=("SELECT cliName FROM climate,destinations WHERE climate.cliId="+x);

         ResultSet rs = stmt.executeQuery(query);                                            
                                                   
            while (rs.next()) {                                                                           
                String cliName=rs.getString("cliName");                     

                System.out.println("The destination with ID, "+x+",'s, climate is: "+ cliName);  
                break;
            }  
 
        } catch (SQLException e) {                                
            System.out.println(e.getMessage());            
        }             
    
    }

    private static void query6(Connection c,int x) {
    
   try {        
            Statement stmt = c.createStatement();                           
         //   String query = "SELECT * FROM country";          
        String query=("SELECT destRatings FROM destinations WHERE destID="+x);

         ResultSet rs = stmt.executeQuery(query);                                            
                                                   
            while (rs.next()) {                                                                           
                int destRatings=rs.getInt("destRatings");                     

                System.out.println("The destination with ID, "+x+",has a rating of: "+ destRatings);  
                break;
            }  
 
        } catch (SQLException e) {                                
            System.out.println(e.getMessage());            
        }                 
    }

    private static void query7(Connection c, int x) {   
        try {        
            Statement stmt = c.createStatement();                           
        String query=("SELECT destActivities FROM destinations WHERE destID="+x);
     
         ResultSet rs = stmt.executeQuery(query);                                            
                                                   
            while (rs.next()) {                                                                           
                String destActivities=rs.getString("destActivities");                     
                System.out.println("The destination with ID, "+x+",'s activity is: "+ destActivities);  
                break;
            }  
 
        } catch (SQLException e) {                                
            System.out.println(e.getMessage());            
        } 
    }

    
    private static void query8(Connection c) {
try {        
            Statement stmt = c.createStatement();    
            
         String query="SELECT destID,destName FROM destinations WHERE destOccupancy=(SELECT max(destOccupancy) FROM destinations)";

         
         ResultSet rs = stmt.executeQuery(query);                                            
                                                   
            while (rs.next()) {                                                                           
                String destName=rs.getString("destName");                              
                
                System.out.println("The destination that people stay the longest in is: "+destName);
                break;
            }  
 
        } catch (SQLException e) {                                
            System.out.println(e.getMessage());            
        }             
    }

    private static void query9(Connection c, int x) {
        try {        
            Statement stmt = c.createStatement();                           
        String query=("SELECT countryName FROM country WHERE countryID="+x);
      

         ResultSet rs = stmt.executeQuery(query);                                            
                                                   
            while (rs.next()) {                                                                           
                String countryName=rs.getString("countryName");                     
                System.out.println("The country that the destination with ID, "+x+", is located is: "+ countryName);  
                break;
            }  
 
        } catch (SQLException e) {                                
            System.out.println(e.getMessage());            
        }
    }

    private static void query10(Connection c, int x) {
          try {        
            Statement stmt = c.createStatement();                           
        String query=("SELECT cityPopulation FROM city WHERE cityID="+x);
      

         ResultSet rs = stmt.executeQuery(query);                                            
                                                   
            while (rs.next()) {                                                                           
                int cityPopulation=rs.getInt("cityPopulation");                     
                System.out.println("The population of the city with the destination ID, "+x+", is: "+ cityPopulation);  
                break;
            }  
 
        } catch (SQLException e) {                                
            System.out.println(e.getMessage());            
        }
    }
}