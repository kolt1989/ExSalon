package DAO;

import DTO.FullTransactionDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 03.03.2017.
 */
public class TransactionListDao {
    public static Connection con;
    String url ="jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String password = "pumpkin1";

    java.util.Date now = new java.util.Date();

    PreparedStatement preparedStatement= null;
    public void inSertTranzaction(ArrayList<FullTransactionDTO> transactionsList)  {


        SqlQueryLibrary query=new SqlQueryLibrary();
        try {

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
                preparedStatement.executeUpdate();
            }
            con.commit();
        } catch (SQLException e) {

            System.out.println(e.getMessage());

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
