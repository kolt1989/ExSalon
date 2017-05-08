import business.WorkWithData;

import static configuration.Config.prop;

/**
 * Created by Kozak on 08.05.2017.
 */
public class Main {
    public static void main(String[] args) {
        WorkWithData workWithData =new WorkWithData();
        workWithData.convertAndWriteToDb(prop.getProperty("salon_1_data"),prop.getProperty("salon_1_convert"));
        workWithData.convertAndWriteToDb(prop.getProperty("salon_2_data"),prop.getProperty("salon_2_convert"));
        workWithData.convertAndWriteToDb(prop.getProperty("salon_3_data"),prop.getProperty("salon_3_convert"));
        System.out.println("Work is finished!");
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            System.out.println("Can't sleep");
        }
    }
}
