package business;

import DAO.TransactionListDao;
import parsing.ConvertFileUnicode;
import parsing.ParsingLine;
import java.io.*;
import static configuration.Config.prop;
/**
 * Created by Kozak on 08.05.2017.
 */
public class WorkWithData {
    //Config config = new Config();

    public void convertAndWriteToDb(String data, String buffer){
        String salonName = null;
        if(data.equals(prop.getProperty("salon_1_data"))){salonName = "parus";}
        if(data.equals(prop.getProperty("salon_2_data"))){salonName = "komunar";}
        if(data.equals(prop.getProperty("salon_3_data"))){salonName = "pobeda";}
        ConvertFileUnicode fileConverter = new ConvertFileUnicode();
        File xmlFile = new File(data);

        File[] files = xmlFile.listFiles();
        System.out.println(data+" includes ---- "+files.length+" ---- files");
        for(int i=0;i<files.length;i++) {

            if (files[i].getName().contains(".txt")) {
                try {
                    ParsingLine parser = new ParsingLine();
                    fileConverter.convert(files[i].getPath(),buffer+i+".txt", "windows-1251", "UTF8");
                    File file = new File(buffer+i+".txt");
                    //создаем объект FileReader для объекта File
                    FileReader fr = new FileReader(file);
                    //создаем BufferedReader с существующего FileReader для построчного считывания
                    BufferedReader reader = new BufferedReader(fr);
                    // считаем сначала первую строку
                    String line = reader.readLine();
                    while (line != null) {
                        if (line.toString().equals(" ") || line.toString().contains("Кількість") || line.toString().contains("Cума") || line.toString().equals("")) {
                            line = reader.readLine();
                            continue;
                        }
                        // считываем остальные строки в цикле
                        parser.parsingLine(line);
                        line = reader.readLine();
                    }

                    reader.close();
                    file.delete();
                    TransactionListDao transactionListDao = new TransactionListDao();
                    transactionListDao.insertTranzaction(parser.transactionsList,salonName);

                } catch (FileNotFoundException e) {
                    continue;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
