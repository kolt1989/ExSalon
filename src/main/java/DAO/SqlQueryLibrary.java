package DAO;

/**
 * Created by Kozak on 03.03.2017.
 */
public class SqlQueryLibrary {

    String insertTable = "INSERT INTO public.ex_salon_details (datetime,service,cost,ref,phone,updatetime)" +
            "VALUES (?,?,?,?,?,?)";
}
