/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Chamath
 */
public class DBOperations {
    PreparedStatement pst=null;
    Connection con=null;
    ResultSet rs=null; // this is to catch set

    //nikan class ekaka instance ekak haduwa kiyala wadak naha
    
    boolean addEmploy(EmployeeDetails em){
        DBConnection db =new DBConnection();
        try{
            // connection eka hadana eka karanne
           con=DriverManager.getConnection(db.getUrl(),db.getUsername(), db.getPassword());
          
           String query="INSERT INTO empdetails VALUES(?,?,?,?,?,?,?,?)";
           
           // query ekata awashya tika gannai one
           pst=con.prepareStatement(query);
            pst.setInt(1, em.getEmpid());  // add values to the sql query
            pst.setString(2, em.getFirstname());
            pst.setString(3, em.getLastname());
            pst.setInt(4, em.getAge());
            pst.setString(5, em.getCountry());
            pst.setString(6, em.getEmail());
            pst.setString(7, em.getUsername());
            pst.setString(8, em.getPassword());
            
           
            pst.executeQuery(); // methanadi thamai sql ekata update wenne
    
            
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
        finally{
            try{
                if(pst != null){
                    pst.close();
                }
                if(con != null){
                    con.close();
                }
                
            }
            catch(Exception e){
                System.out.println("e");
            }
            
  
        }
       
        
    }
    
    int checkUsername(String username){
        // get connection
        DBConnection db=new DBConnection();
        try{
            //set connection
            con=DriverManager.getConnection(db.getUrl(), db.getUsername(), db.getPassword());
            String query="SELECT username  FROM empdetails";
            pst=con.prepareStatement(query);
            
            // execute query
            rs=pst.executeQuery();
            
            while(rs.next()){
                if(username.equals(rs.getString(1))){
                    //meke karanne column index 1 wena eva mokada return wenne 
                    // eka column ekak nisa
                    
                    return 0; // same username
                }
               
            }
           
            return 1; // not having same username
            
        }catch(Exception e){
            System.out.println(e);
            return 2;
        }
        finally{
            
            try{
                if(pst != null){
                    pst.close();
                }
                if(con != null){
                    con.close();
                }
                
            }
            catch(Exception e){
                System.out.println("e");
            }
            
        }
    }
    
    // check login 
    int checkLogin(String un , String pw){
        // get connection
        DBConnection db=new DBConnection();
        try{
            con=DriverManager.getConnection(db.getUrl(), db.getUsername(), db.getPassword());
            // make query 
            String query="SELECT password FROM empdetails WHERE username=un";//username
            
            pst=con.prepareStatement(query);
            
            rs=pst.executeQuery();
            
            if(rs.getString(1)==pw){
                return 0; // password match
            }
            else{
                return 1; // password not match
            }
            
            //execute query
            
           
        }
        catch(Exception e){
            System.out.println(e);
            return 2; // error
        }
        
        
    }
    
    
}
