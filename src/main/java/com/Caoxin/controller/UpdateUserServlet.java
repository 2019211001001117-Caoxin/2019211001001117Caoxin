package com.Caoxin.controller;

import com.Caoxin.dao.UserDao;
import com.Caoxin.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "UpdateUserServlet", value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {
    Connection con =null;
    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("con");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String gender=request.getParameter("gender");
        Date birthdate=Date.valueOf( request.getParameter("birthdate"));


        User user=new User(id,username,password,email,gender,birthdate);
        UserDao userDao=new UserDao();

        try {
            userDao.updateUser(con,user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        HttpSession session=request.getSession();
        session.setAttribute("user",user);
        session.setMaxInactiveInterval(10);
        request.getRequestDispatcher("accountDetails").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/updateUserViews.jsp").forward(request,response);
    }
}