////package application;
////
////import java.sql.Connection;
////import java.sql.DriverManager;
////import java.sql.SQLException;
////
////public class database {
////
////    private String url = "jdbc:mysql://localhost:3306/";
////    private String username = "root";
////    private String password = "drish0312";
////    private String db = "edushare";
////
////    public static void main(String[] args) {
////        database db = new database();
////        db.connect_db();
////    }
////
////    public Connection connect_db() {
////        Connection connect = null;
////        try {
////            // Establish database connection
////            connect = DriverManager.getConnection(url + db, username, password);
////            // Print "Connection successful" if connection is successful
////            System.out.println("Connection successful");
////            return connect;
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return connect;
////    }
////}
//
////-------------------------------
//
//package application;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class database {
//    
//    public static Connection connectDb(){
//        
//        try{
//            
//            Class.forName("com.mysql.jdbc.Driver");
//            
//            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/edushare", "root", "drish0312");
//            return connect;
//        }catch(Exception e){e.printStackTrace();}
//        return null;
//    }
//    
//}

package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {
    
    public static Connection connectDb(){
        
        try{
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/edushare", "root", "drish0312");
            return connect;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
}
