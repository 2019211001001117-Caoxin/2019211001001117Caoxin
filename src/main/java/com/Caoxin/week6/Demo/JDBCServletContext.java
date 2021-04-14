
package com.Caoxin.week6.Demo;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@WebListener
public class JDBCServletContext implements ServletContextListener {
    Connection con = null;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext Context =sce.getServletContext();
        String driver =  Context.getInitParameter("driver");
        String url =  Context.getInitParameter("url");
        String username = Context.getInitParameter("username");
        String password =  Context.getInitParameter("password");
        try {
            Class.forName(driver);
            //注册驱动
            con = DriverManager.getConnection(url,username,password);
            System.out.println("i am in contextInitialized()-->"+con);
            Context.setAttribute("con",con);
        }catch (ClassNotFoundException | SQLException e) { //SQLException异常
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("i am in contextDestroyed()");
        sce.getServletContext().removeAttribute("con");
    }
}