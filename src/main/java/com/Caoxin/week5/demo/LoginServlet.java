package com.Caoxin.week5.demo;

import com.Caoxin.dao.UserDao;
import com.Caoxin.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet(name = "LoginServlet",value = "/login")

public class LoginServlet extends HttpServlet {
    Connection con = null;

    @Override
    public void init() throws ServletException {

//        ServletContext  context =getServletContext();
//        String driver=context.getInitParameter("driver");//<param-name>driver</param-name>
//        String url=context.getInitParameter("url");//<param-name>url</param-name>
//        String username=context.getInitParameter("username");//<param-name>username</param-name>
//        String password=context.getInitParameter("password");//<param-name>password</param-name>
//
//        try {
//            Class.forName(driver);
//            con = DriverManager.getConnection(url, username, password);
//            System.out.println("init()-->" + con);//ok (java.code)- ok(use config-in web.xml)-- ok--use (@webservlet)
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();

        try {
            String sql = "select * from usertable where username=? and password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            UserDao userDao = new UserDao();
            try {
                User user = userDao.findByUsernamePassword(con, username, password);
                if (user != null) {
                    String rememberMe=request.getParameter("rememberMe");
                    if(rememberMe!=null && rememberMe.equals("1")){
                        Cookie usernameCookies=new Cookie("cUsername",user.getUsername());
                        Cookie passwordCookies=new Cookie("cPassword",user.getPassword());
                        Cookie rememberMeCookies=new Cookie("cRememberMe",rememberMe);

                        usernameCookies.setMaxAge(5);
                        passwordCookies.setMaxAge(5);
                        rememberMeCookies.setMaxAge(5);

                        response.addCookie(usernameCookies);
                        response.addCookie(passwordCookies);
                        response.addCookie(rememberMeCookies);
                    }

                    HttpSession session=request.getSession();
                    System.out.println("session id-->"+session.getId());
                    session.setMaxInactiveInterval(10);
                    session.setAttribute("user", user);
                    request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "Usename or Password Error!!!");
                    request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
//            ResultSet rs=ps.executeQuery();
//            if(rs.next()) {
//                out.println("Login Success!!!");
//                out.println("Welcome "+username);
//                request.setAttribute("id",rs.getInt("id"));
//                request.setAttribute("username",rs.getString("username"));
//                request.setAttribute("password",rs.getString("password"));
//                request.setAttribute("email",rs.getString("email"));
//                request.setAttribute("gender",rs.getString("gender"));
//                request.setAttribute("birthdate",rs.getString("birthdate"));
//                request.getRequestDispatcher("userInfo.jsp").forward(request,response);
//            }
//            else {
////                out.println("Username or Password Error!!!");
//                request.setAttribute("message","Username or Password Error!!!");
//                request.getRequestDispatcher("login.jsp").forward(request,response);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//    }
//    @Override
//    public void destroy() {
//        try {
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}