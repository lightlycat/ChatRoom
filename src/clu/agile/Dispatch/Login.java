package clu.agile.Dispatch;

import clu.agile.DBCom.MemberControl;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lightlycat on 2014/9/25.
 */
public class Login extends HttpServlet {

    public Login() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.print("......doPost");
        String customerID = request.getParameter("customerID");
        String pwd = request.getParameter("pwd");
        System.out.println("parameters= " + customerID + ":" + pwd);

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");

        JsonObject myObj = new JsonObject();
        System.out.println("......run Login, check if user exits!");


        MemberControl mc = new MemberControl();

        if(!mc.login(customerID,pwd)){
            myObj.addProperty("success", false);
        }
        else {
            myObj.addProperty("success", true);
        }

        System.out.println("myObj: " + myObj);
        out.println(myObj.toString());
        out.close();

    }

}
