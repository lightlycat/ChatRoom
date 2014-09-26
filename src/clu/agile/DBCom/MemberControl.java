package clu.agile.DBCom;

import java.sql.*;

/**
 * Created by lightlycat on 2014/9/25.
 */
public class MemberControl {

    private Connection conn = null;
    private Statement statement = null;
    private PreparedStatement pst = null;
    private ResultSet rs ;

    public boolean login(String id, String pwd){

        boolean result = false;
        String sql = "select * from customers where account = '" + id +"' and password= '" + pwd + "'";
        DBConn con = new DBConn();
        conn = con.conn();
        try
        {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);

            result = rs.next();
            System.out.println("result =" + result);
            rs.close();
            statement.close();
            statement = null;

        }
        catch(SQLException e)
        {
            System.out.println("DropDB Exception :" + e.toString());
        }
        finally
        {
            con.close();
        }

        return result;

    }
    public void insertCustomer(String id, String pwd){

        DBConn dbcon = new DBConn();
        conn = dbcon.conn();

        String insertdbSQL = "insert into customers (account, password, cur_time )" +
                "values(?,?,?)";

        try
        {
            pst = conn.prepareStatement(insertdbSQL);

            pst.setString(1, id);
            pst.setString(2, pwd);
            pst.setString(3, dbcon.getCurrentDate());

            pst.executeUpdate();
            pst.close();
        }
        catch(SQLException e)
        {
            System.out.println("InsertDB Exception :" + e.toString());
        }
        finally
        {
            dbcon.close();
        }
    }

}
