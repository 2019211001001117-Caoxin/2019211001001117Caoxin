package com.Caoxin.week5.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(
        urlPatterns ={"/login"},
        initParams = {
                @WebInitParam(name="driver",value="com.microsoft.sqlserver.jdbc.SQLServerDriver"),
                @WebInitParam(name="url",value="jdbc:sqlserver://localhost;databaseName=userdb"),
                @WebInitParam(name="username",value="sa"),
                @WebInitParam(name="password",value="admin123456789"),
        },loadOnStartup = 1
)
public class LoginServlet extends HttpServlet {
    Connection con=null;
    @Override
    public void init() throws ServletException {

        ServletConfig  config =getServletConfig();
        String driver=config.getInitParameter("driver");//<param-name>driver</param-name>
        String url=config.getInitParameter("url");//<param-name>url</param-name>
        String username=config.getInitParameter("username");//<param-name>username</param-name>
        String password=config.getInitParameter("password");//<param-name>password</param-name>

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            System.out.println("init()-->" + con);//ok (java.code)- ok(use config-in web.xml)-- ok--use (@webservlet)
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        PrintWriter out=response.getWriter();

        try {
            String sql="select * from usertable where username='CaoXin' and password='123456'";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                out.println("Login Success!!!");
                out.println("Welcome "+username);
                request.setAttribute("username",rs.getString("username"));
                request.getRequestDispatcher("userInfo.jsp").forward(request,response);
            }
            else {
                out.println("Username or Password Error!!!");
                request.setAttribute("message","Username or Password Error!!!");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @Override
    public void destroy() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


