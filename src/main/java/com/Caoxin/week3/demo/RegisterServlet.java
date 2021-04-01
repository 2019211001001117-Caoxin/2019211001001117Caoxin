package com.Caoxin.week3.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.*;

//automatic -new --> servlet
@WebServlet(
        urlPatterns ={"/register"},
        initParams = {
                @WebInitParam(name="driver",value="com.microsoft.sqlserver.jdbc.SQLServerDriver"),
                @WebInitParam(name="url",value="jdbc:sqlserver://localhost;databaseName=userdb;"),
                @WebInitParam(name="username",value="sa"),
                @WebInitParam(name="password",value="admin123456789"),
        }
)

public class RegisterServlet extends HttpServlet {
    Connection con=null;
    PreparedStatement pstmt =null;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String Id=request.getParameter("id");
//        String username=request.getParameter("username");
//        String gender=request.getParameter("gender");
//        String password=request.getParameter("password");
//        String email=request.getParameter("email");
//        String birthdate=request.getParameter("birthdate");
//           String sql="insert into usertable('id','username','password','email','gender','birthdate') values('1','CaoXin','123456','2762101126@qq.com','male','2021-3-30')";
//        String sql="select *from usertable";
////
//           PreparedStatement pstmt =con.prepareStatement(sql);
//           pstmt.setInt(1, Integer.parseInt(Id));
//           pstmt.setString(2,username);
//           pstmt.setString(3,password);
//           pstmt.setString(4,email);
//           pstmt.setString(5,gender);
//           pstmt.setDate(6, Date.valueOf(birthdate));


        try {
            int rs = pstmt.executeUpdate();
            if(rs==1) System.out.println("OK");
            else System.out.println("ERROR");

            String sql1="select * from usertable";
            ResultSet results=con.createStatement().executeQuery(sql1);
            Writer writer = response.getWriter();
            while (results.next()) {
                PrintWriter writer1 = response.getWriter();
                writer1.print("<table border=\"1\">");
                writer1.print("<tr>");
                writer1.print("<td>id</td>");
                writer1.print("<td>userName</td>");
                writer1.print("<td>email</td>");
                writer1.print("<td>gender</td>");
                writer1.print("<td>password</td>");
                writer1.print("<td>birthday</td>");
                writer1.print("</tr>");
                writer.write("<td>"+results.getInt("Id")+"</td>");
                writer.write("<td>"+results.getString("username")+"</td>");
                writer.write("<td>"+results.getString("email")+"</td>");
                writer.write("<td>"+results.getString("gender")+"</td>");
                writer.write("<td>"+results.getString("password")+"</td>");
                writer.write("<td>"+results.getDate("birthdate")+"</td>");
                writer.write("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

