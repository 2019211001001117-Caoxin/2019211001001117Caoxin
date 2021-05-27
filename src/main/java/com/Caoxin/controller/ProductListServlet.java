package com.Caoxin.controller;

import com.Caoxin.dao.ProductDao;
import com.Caoxin.model.Product;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductListServlet" ,value = "/admin/productList")
public class ProductListServlet extends HttpServlet {
    Connection con=null;

    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("con");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ProductDao productDao=new ProductDao();
            List<Product> productList=productDao.findAll(con);
            request.setAttribute("productList",productList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/views/admin/productList.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}