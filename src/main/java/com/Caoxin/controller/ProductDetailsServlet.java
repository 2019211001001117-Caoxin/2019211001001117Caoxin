package com.Caoxin.controller;

import com.Caoxin.dao.ProductDao;
import com.Caoxin.model.Category;
import com.Caoxin.model.Product;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductDetailsServlet",value = "/productDetails")
public class ProductDetailsServlet extends HttpServlet {
    Connection con=null;
    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("con");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=request.getParameter("id")!=null?Integer.parseInt(request.getParameter("id")):0;
        ProductDao dao=new ProductDao();
        if(id==0) return;
        List<Category> categoryList=Category.findAllCategory(con);
        request.setAttribute("categoryList",categoryList);

        try {
            Product product=dao.findById(id,con);
            request.setAttribute("p",product);
            String path="/WEB-INF/views/productDetails.jsp";
            request.getRequestDispatcher(path).forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}