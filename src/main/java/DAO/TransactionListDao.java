package DAO;
import DTO.FullTransactionDTO;
import java.sql.*;
import java.util.ArrayList;
import static configuration.Config.prop;
/**
 * Created by user on 03.03.2017.
 */
public class TransactionListDao {
    public static Connection con;
    String url =prop.getProperty("url");
    String user = prop.getProperty("user");
    String password = prop.getProperty("password");

    java.util.Date now = new java.util.Date();

    PreparedStatement preparedStatement= null;
    public void insertTranzaction(ArrayList<FullTransactionDTO> transactionsList,String salonName)  {
        java.util.Date now = new java.util.Date();
        long currentTime = now.getTime();
        Timestamp updateTime = new Timestamp(currentTime);

        SqlQueryLibrary query=new SqlQueryLibrary();
        try {
            Class.forName(prop.getProperty("claspath"));
            con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);
            preparedStatement =con.prepareStatement(query.insertTable);
            for(int i=0; i< transactionsList.size();i++) {
                java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(transactionsList.get(i).date);
                preparedStatement.setTimestamp(1, timestamp);
                preparedStatement.setString(2, transactionsList.get(i).service);
                preparedStatement.setDouble(3, transactionsList.get(i).cost);
                preparedStatement.setDouble(4, transactionsList.get(i).ref);
                preparedStatement.setString(5, transactionsList.get(i).phone);
                preparedStatement.setTimestamp(6, updateTime);
                preparedStatement.setString(7, salonName);
                preparedStatement.executeUpdate();
            }
            con.commit();
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                preparedStatement.close();
            } catch (SQLException se) { /*can't do anything */ }

        }

    }
}
