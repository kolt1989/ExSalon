import DAO.TransactionListDao;
import parsing.ConvertFileUnicode;
import parsing.ParsingLine;

import java.io.*;

/**
 * Created by Kozak on 02.03.2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ParsingLine parser = new ParsingLine();
        ConvertFileUnicode fileConverter = new ConvertFileUnicode();

        try {
            fileConverter.convert("D:\\салон\\2016.09.30 20.16.02_37.txt","D:\\салон\\1.txt","windows-1251","UTF8");
            File file = new File("D:\\салон\\1.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                if (line.toString().equals(" ")||line.toString().contains("Кількість")||line.toString().contains("Cума")||line.toString().equals("")){
                    System.out.println(line.toString());
                    line = reader.readLine();
                    continue;
                }
               // System.out.println(line);
                // считываем остальные строки в цикле
               // System.out.println(line.toString());
                parser.parsingLine(line);
                line = reader.readLine();
            }
            TransactionListDao transactionListDao = new TransactionListDao();
            transactionListDao.inSertTranzaction(parser.transactionsList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Finish");
    }
}
