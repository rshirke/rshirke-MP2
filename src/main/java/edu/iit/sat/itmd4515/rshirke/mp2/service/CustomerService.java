/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rshirke.mp2.service;

import edu.iit.sat.itmd4515.rshirke.mp2.model.Customer;
import edu.iit.sat.itmd4515.rshirke.mp2.model.Product;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author Rohan
 */

@Stateless
public class CustomerService {
    
    
    private static final Logger LOG = Logger.getLogger(ProductService.class.getName());

    @Resource(lookup = "jdbc/rshirkeMp2DS")
    private DataSource ds;
    
    /**
     *
     * @return
     */
    public List<Customer> findAll() {
        
        List<Customer> Customers = new ArrayList();
        
        //Customers= null;

         LOG.info("We are into FindAll method ");
          try (Connection c = ds.getConnection()) {

            // JDBC has both Statement and PreparedStatement
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from customer");

            while (rs.next()) {
                LOG.info(rs.getString("last_name"));
   Customers.add(new Customer(rs.getLong("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email")));
                
                
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
       
        return Customers;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Customer findById(Long id) {
        try (Connection c = ds.getConnection()) {

            // JDBC has both Statement and PreparedStatement
            PreparedStatement ps = c.prepareStatement("select * from customer where customer_id = ?");
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getLong("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"));
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        // if nothing was found, return null
        return null;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public int deleteById(Long id) {
        int rs=0;
        
        
        
        LOG.info("INTO delete customer service call");
        try (Connection c = ds.getConnection()) {

            LOG.info("INTO try of deletecustomer");
            // JDBC has both Statement and PreparedStatement
            PreparedStatement ps = c.prepareStatement("Delete from customer where customer_id = ?");
            ps.setLong(1, id);

            rs = ps.executeUpdate();
                    
            if (rs>=1) {
               LOG.info("The Delete Query got successfully executed and the Record got deleted");
            }
            else
            {
                 LOG.info("NO record got deleted ");
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        // if nothing was found, return null
        return rs;
    }
    
    /**
     *
     * @param c
     * @return
     */
    public boolean save(Customer c) {
         
         
        boolean statuscustomer=true;
        
         String insertSql = "insert into customer("
                + "customer_id,"
                + "first_name,"
                + "last_name,"
                + "email,"
                + "store_id,"
                + "address_id,"
                + "create_date)"
                + " values(?,?,?,?,?,?,?)";
        
        
        LOG.info("INTO save customer service call");
          int returnVal = 0;

        try (Connection c1 = ds.getConnection()) {

             PreparedStatement ps = c1.prepareStatement(insertSql);
                ps.setLong(1, c.getId());
                ps.setString(2, c.getFirstName());
                ps.setString(3, c.getLastName());
                ps.setString(4, c.getEmail());
                ps.setInt(5, 10);
                ps.setInt(6, 10);
                ps.setDate(7, Date.valueOf(LocalDate.now()));
                returnVal = ps.executeUpdate();
            
            
                    
            if (returnVal>=1) {
               LOG.info("The Save Query got successfully executed and the Record got Inserted in Table");
            }
            else
            {
                 LOG.info("NO record got Inserted ");
                 statuscustomer =false;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        // if nothing was found, return null
        return statuscustomer;
    }
     
    /**
     *
     * @param c
     * @return
     */
    public boolean update(Customer c) {
         
         
        boolean statuscustomer=true;
        
        String updateSql = "update customer set "
                + "first_name=?, "
                + "last_name=?, "
                + "email=? "
                + "where customer_id=?";
        
        
        LOG.info("INTO update customer service call");
          int returnVal = 0;

        try (Connection c1 = ds.getConnection()) {

             PreparedStatement ps = c1.prepareStatement(updateSql);
               
                ps.setString(1, c.getFirstName());
                ps.setString(2, c.getLastName());
                ps.setString(3, c.getEmail());
                 ps.setLong(4, c.getId());

                returnVal = ps.executeUpdate();
            
            
                    
            if (returnVal>=1) {
               LOG.info("The Update Query got successfully executed and the Record got updated in Table");
            }
            else
            {
                 LOG.info("NO record got updated ");
                 statuscustomer =false;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        // if nothing was found, return null
        return statuscustomer;
    }
    
    
}
