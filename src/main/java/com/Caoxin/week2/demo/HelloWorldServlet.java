package com.Caoxin.week2.demo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class HelloWorldServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //when client request method is Get - here - inside doGet()
        //we want to send Hello to client
        //we need to write Hello in response
        // get write - java .io
        PrintWriter writer = response.getWriter();
        writer.println("Name :Caoxin\n"+
                "ID:2019211001001117");
        //next we need to tell about this servlet to tomcat - how ? - web.xml

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        //when client request method is Get - here - inside doPost()

    }
}
