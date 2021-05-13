package com.Caoxin.week3.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;


//automatic -new --> servlet
//@WebServlet(
//        urlPatterns ={"/register"},
//        initParams = {
//                @WebInitParam(name="driver",value="com.microsoft.sqlserver.jdbc.SQLServerDriver"),
//                @WebInitParam(name="url",value="jdbc:sqlserver://localhost;databaseName=userdb;"),
//                @WebInitParam(name="username",value="sa"),
//                @WebInitParam(name="password",value="admin123456789"),
//        }
//)
@WebServlet(name = "RegisterServlet",value = "/register")
public class RegisterServlet extends HttpServlet {
    Connection con =null;
    @Override
    public void init() throws ServletException {
//        ServletContext  context=getServletContext();
//        String driver=context.getInitParameter("driver");//<param-name>driver</param-name>
//        String url=context.getInitParameter("url");//<param-name>url</param-name>
//        String username=context.getInitParameter("username");//<param-name>username</param-name>
//        String password=context.getInitParameter("password");//<param-name>password</param-name>
//        try {
//            Class.forName(driver);
//            con = DriverManager.getConnection(url, username, password);
//            System.out.println("init()-->" + con);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
        con =(Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PreparedStatement ps = null;
        int resultSet;
        int Id = Integer.parseInt(request.getParameter("id"));
//        String Id=request.getParameter("id");
        String Username=request.getParameter("username");
        String Password=request.getParameter("password");
        String Email=request.getParameter("email");
        String Gender=request.getParameter("gender");
        String Birthdate=request.getParameter("birthdate");
        String sql ="insert into usertable(id,username,password,email,gender,birthdate) values(?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,Id);
            ps.setString(2,Username);
            ps.setString(3,Password);
            ps.setString(4,Email);
            ps.setString(5,Gender);
            ps.setString(6,Birthdate);
            resultSet = ps.executeUpdate();





//              sql ="select id,username,password,email,gender,birthdate from usertable";
//              ResultSet rs = st.executeQuery(sql);
//
//            String sql="select * from usertable";
//            ResultSet results=con.createStatement().executeQuery(sql);
//            Writer writer = response.getWriter();
//            while (results.next()) {
//                PrintWriter writer1 = response.getWriter();
//                writer1.print("<table border=\"1\">");
//                writer1.print("<tr>");
//                writer1.print("<td>id</td>");
//                writer1.print("<td>userName</td>");
//                writer1.print("<td>email</td>");
//                writer1.print("<td>gender</td>");
//                writer1.print("<td>password</td>");
//                writer1.print("<td>birthdate</td>");
//                writer1.print("</tr>");
//                writer.write("<td>"+results.getInt("id")+"</td>");
//                writer.write("<td>"+results.getString("username")+"</td>");
//                writer.write("<td>"+results.getString("email")+"</td>");
//                writer.write("<td>"+results.getString("gender")+"</td>");
//                writer.write("<td>"+results.getString("password")+"</td>");
//                writer.write("<td>"+results.getDate("birthdate")+"</td>");
//                writer.write("\n");
//            }
            response.sendRedirect("login");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

