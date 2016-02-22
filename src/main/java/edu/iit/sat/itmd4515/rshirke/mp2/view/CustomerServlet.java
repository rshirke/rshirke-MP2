/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rshirke.mp2.view;

import edu.iit.sat.itmd4515.rshirke.mp2.model.Customer;
import edu.iit.sat.itmd4515.rshirke.mp2.service.CustomerService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 *
 * @author Rohan
 */

 
@WebServlet(name = "CustomerServlet",
        urlPatterns = {
            "/customer","/customers","/delcustomer","/updatecustomer"})
// TODO ,"/customers","/delcustomer","/addcustomer" all this servlets
public class CustomerServlet extends HttpServlet {

    
     @EJB CustomerService svc;
     
      @Resource
    Validator validator;
    
    private static final Logger LOG = Logger.getLogger(ProductServlet.class.getName());
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerServlet at " + request.getContextPath() + "</h1>");
            
            out.println("<ol>");
            for (Customer c : svc.findAll()) {
                out.println("<li>" + c.toString() + "</li>");
            }
            out.println("</ol>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         LOG.info("Inside doGet method.  I am handling dispatch to a page.");

        switch (request.getServletPath()) {
            // GET request to /customers will display a view of all customers
            case "/customers":
                LOG.info("GET request to /customers");
                request.setAttribute("customers", svc.findAll());
                request.getRequestDispatcher("/WEB-INF/pages/customers.jsp").forward(request, response);
                break;
            // GET request to /customer will show a single customer for display 
            // or update, and also provide the ability to create a new customer
            // if no customerId is passed
            case "/customer":
                LOG.info("GET request to /customer");

                // if we are passing a customerId, find it to display
                if (!WebUtils.isEmpty(request.getParameter("customerId"))) {
                    Long customerId = Long.parseLong(WebUtils.trimParam(request.getParameter("customerId")));
                    Customer c = svc.findById(customerId);
                    request.setAttribute("customer", c);
                }

                request.getRequestDispatcher("/WEB-INF/pages/customer.jsp").forward(request, response);
                break;
                
                case "/delcustomer":
                LOG.info("GET request to /delcustomer");

                // if we are passing a customerId, find it to display
                if (!WebUtils.isEmpty(request.getParameter("customerId"))) {
                    Long customerId = Long.parseLong(WebUtils.trimParam(request.getParameter("customerId")));
                    int isDelete = svc.deleteById(customerId);
                    
                }

                request.setAttribute("customers", svc.findAll());
                request.getRequestDispatcher("/WEB-INF/pages/customers.jsp").forward(request, response);
                break;
                
                 case "/updatecustomer":
                LOG.info("GET request to /updatecustomer");

                // if we are passing a customerId, find it to display
                if (!WebUtils.isEmpty(request.getParameter("customerId"))) {
                    Long customerId = Long.parseLong(WebUtils.trimParam(request.getParameter("customerId")));
                    Customer c = svc.findById(customerId);
                    request.setAttribute("customer", c);
                }

                request.getRequestDispatcher("/WEB-INF/pages/updatecustomer.jsp").forward(request, response);
                break;
                
        }
       
       // processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        LOG.info("Inside doPost method.  I am processing a form POST.");

        // we can use a map of strings to place information for the user on the request
        
        
        
        
        Map<String, String> messages = new HashMap<>();
        request.setAttribute("messages", messages);

        // first we need to build the customer from the request
        Long customerId = null;
        if (!WebUtils.isEmpty(request.getParameter("customerId"))) {
            // needs additional checks to ensure it is actually Long and right format
            customerId = Long.parseLong(WebUtils.trimParam(request.getParameter("customerId")));
        }
        String firstName = WebUtils.trimParam(request.getParameter("firstName"));
        String lastName = WebUtils.trimParam(request.getParameter("lastName"));
        String email = WebUtils.trimParam(request.getParameter("email"));
        Customer c = new Customer(customerId, firstName, lastName, email);
        if (!WebUtils.isEmpty(request.getParameter("storeId"))) {
            // needs additional checks to ensure it is actually Long and right format
            c.setStoreId(Integer.parseInt(WebUtils.trimParam(request.getParameter("storeId"))));
        }
        if (!WebUtils.isEmpty(request.getParameter("addressId"))) {
            // needs additional checks to ensure it is actually Long and right format
            c.setAddressId(Integer.parseInt(WebUtils.trimParam(request.getParameter("addressId"))));
        }

        // we have now built a customer from the HttpServletRequest, time to validate
        Set<ConstraintViolation<Customer>> violations = validator.validate(c);

        // if violations is empty, our object passed validation
        if (violations.isEmpty()) {
            LOG.info("Received the following customer from user form:\t" + c.toString());

            
             switch (request.getServletPath()) {
            // GET request to /customers will display a view of all customers
            case "/customer":
                
                LOG.info("Into customer switch case of post request to insert data");
               if (svc.save(c)) {
                messages.put("success", "Successfully saved customer");
                request.setAttribute("customers", svc.findAll());
                // On success, I now want to redirect to my servlet URL pattern to ensure that customers gets set in the requestScope
                request.getRequestDispatcher("/WEB-INF/pages/customers.jsp").forward(request, response);
            }
                break;
                
                 case "/updatecustomer":
                     
                     LOG.info("Into customer switch case of post request to update data");
               if (svc.update(c)) {
                messages.put("success", "Successfully saved customer");
                request.setAttribute("customers", svc.findAll());
                // On success, I now want to redirect to my servlet URL pattern to ensure that customers gets set in the requestScope
                request.getRequestDispatcher("/WEB-INF/pages/customers.jsp").forward(request, response);
            }
                break;
            
             }
            
            // do some processing here - call service or database
            
            
            
            
            
            
            
            
            
            
            
            
            
        } else {
            // if violations is not empty, our object failed validation.  Log!
            LOG.info("There are " + violations.size() + " violations.");

            for (ConstraintViolation<Customer> violation : violations) {
                LOG.info("####### " + violation.getRootBeanClass().getSimpleName()
                        + "." + violation.getPropertyPath() + " failed violation:\t"
                        + violation.getInvalidValue() + " failed with message:\t"
                        + violation.getMessage());
            }

            // if failure, send back to user form with validation messages
            request.setAttribute("violations", violations);
            request.setAttribute("customer", c);
            request.getRequestDispatcher("/WEB-INF/pages/customer.jsp").forward(request, response);
        }
    
        
        
       // processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
