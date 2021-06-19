package com.lab3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HitCountServlet",value = "/HitCountServlet")
public class HitCountServlet extends HttpServlet {
    public int x;
    @Override
    public void init() throws ServletException {
        x=0;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        x++;
        PrintWriter out=response.getWriter();
        out.println("<h1>Total Number of Hits</h1>");
        out.println(x);
    }
}