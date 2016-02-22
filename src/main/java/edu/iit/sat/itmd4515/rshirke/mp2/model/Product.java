/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.iit.sat.itmd4515.rshirke.mp2.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Rohan
 */
public class Product {
    
    
    private Long id;

    
    private String p_name;
    private int p_price;

    private String category;

    private int p_quantity;

    private String p_company;

    /**
     *
     * @param id
     * @param p_name
     * @param p_price
     * @param category
     * @param p_quantity
     * @param p_company
     */
    public Product(Long id, String p_name, int p_price, String category, int p_quantity, String p_company) {
        this.id = id;
        this.p_name = p_name;
        this.p_price = p_price;
        this.category = category;
        this.p_quantity = p_quantity;
        this.p_company = p_company;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getProductName() {
        return p_name;
    }

    /**
     *
     * @param p_name
     */
    public void setProductName(String p_name) {
        this.p_name = p_name;
    }

    /**
     *
     * @return
     */
    public int getP_price() {
        return p_price;
    }

    /**
     *
     * @param p_price
     */
    public void setP_price(int p_price) {
        this.p_price = p_price;
    }

    /**
     *
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
     */
    public int getP_quantity() {
        return p_quantity;
    }

    /**
     *
     * @param p_quantity
     */
    public void setP_quantity(int p_quantity) {
        this.p_quantity = p_quantity;
    }

    /**
     *
     * @return
     */
    public String getP_company() {
        return p_company;
    }

    /**
     *
     * @param p_company
     */
    public void setP_company(String p_company) {
        this.p_company = p_company;
    }
  
    
    
    
    
}
