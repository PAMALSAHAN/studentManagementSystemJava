/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Chamath
 */
public class DBOperations {

    //nikan class ekaka instance ekak haduwa kiyala wadak naha
    
    void addEmploy(EmployeeDetails em){
        DBConnection db =new DBConnection();
        try{
            // connection eka hadana eka karanne
           Connection con=DriverManager.getConnection(db.getUrl(),db.getUsername(), db.getPassword());
          
           String query="INSERT INTO empdetails VALUES(?,?,?,?,?,?,?,?)";
           
           // query ekata awashya tika gannai one
           PreparedStatement pst=con.prepareStatement(query);
            pst.setInt(1, em.getEmpid());  // add values to the sql query
            pst.setString(2, em.getFirstname());
            pst.setString(3, em.getLastname());
            pst.setInt(4, em.getAge());
            pst.setString(5, em.getCountry());
            pst.setString(6, em.getEmail());
            pst.setString(7, em.getUsername());
            pst.setString(8, em.getPassword());
            
           
            pst.executeUpdate(); // methanadi thamai sql ekata update wenne
    
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    
}
