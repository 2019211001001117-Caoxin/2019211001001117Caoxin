package com.Caoxin.week6.Demo;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "RedirectServlet", value="/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //redirect -same server- Relation URL
      //1.start without/
        System.out.println("before redirect");

//        response.sendRedirect("index.jsp");//URL-change to index.jsp
        //http://localhost:8080/2019211001001117_Caoxin_war_exploded/redirect
        //http://localhost:8080/2019211001001117_Caoxin_war_exploded/index.jsp
        //see the url -only last part of url changed (redirect -->index.jsp)
        System.out.println("after redirect");
        //2.start with/
//        response.sendRedirect("http://localhost:8080/2019211001001117_Caoxin_war_exploded//index.jsp");//-??-Http stauts 404 - Not Found
        //why ?-look at url
        //http://localhost:8080/2019211001001117_Caoxin_war_exploded/redirect
        //http://localhost:8080/index.jsp
        //url change after 8080 -means tomcat
        //add /2019211001001117_Caoxin_war_exploded/

        //redirect - another server - Absolute URL
        response.sendRedirect("http://www.baidu.com");
        //http://www.baidu.com/redirect
//        http://www.baidu.com
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request ,response);
    }
}
