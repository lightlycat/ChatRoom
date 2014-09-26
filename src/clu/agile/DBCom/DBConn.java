package clu.agile.DBCom;

/**
 * Created by lightlycat on 2014/9/16.
 */

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DBConn {
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement pst = null;
    private ResultSet rs ;

    public Connection conn(){

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://us-cdbr-azure-west-a.cloudapp.net:3306/as_ff6005dc2e3ac30","bad99c0cfbbdca", "7cbe17fe");

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error!");
        }
        return connection;
    }
    public void selectTable(String sql)
    {

        try
        {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);

            while(rs.next())
            {
                //System.out.print(rs.getObject(1) + " - ");
                System.out.print("account: " + rs.getObject(2) + " - ");
                System.out.println("password: " + rs.getObject(3));
            }
        }
        catch(SQLException e)
        {
            System.out.println("DropDB Exception :" + e.toString());
        }
        finally
        {
            close();
        }
    }
    public void close()
    {
        try
        {
            if(rs!=null)
            {
                rs.close();
                rs = null;
            }
            if(statement!=null)
            {
                statement.close();
                statement = null;
            }
            if(pst!=null)
            {
                pst.close();
                pst = null;
            }
        }
        catch(SQLException e)
        {
            System.out.println("Close Exception :" + e.toString());
        }
    }
    public String getCurrentDate(){

        String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }
    public static void main(String[] args) {
        DBConn db = new DBConn();
        db.selectTable("select * from member");
        db.close();



    }


}
