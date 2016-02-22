/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rshirke.mp2.service;

import edu.iit.sat.itmd4515.rshirke.mp2.model.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class ProductService {
    
private static final Logger LOG = Logger.getLogger(ProductService.class.getName());

    @Resource(lookup = "jdbc/rshirkeMp2DS")
    private DataSource ds;
    
    /**
     *
     * @return
     */
    public List<Product> findAll() {
        
        List<Product> products = new ArrayList();

          try (Connection c = ds.getConnection()) {

            // JDBC has both Statement and PreparedStatement
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from customer");

            while (rs.next()) {
                LOG.info(rs.getString("last_name"));
//                products.add(new Product(rs.getLong("customer_id"),
//                        rs.getString("first_name"),
//                        rs.getString("last_name"),
//                        rs.getString("email")));
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
       
        return null;
    }
    
    
    
}
