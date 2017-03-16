import DAO.TransactionListDao;
import parsing.ConvertFileUnicode;
import parsing.ParsingLine;

import java.io.*;

/**
 * Created by Kozak on 13.03.2017.
 */
public class Main1 {
    public static void main(String[] args) throws IOException {

        ConvertFileUnicode fileConverter = new ConvertFileUnicode();
        File xmlFile = new File("D:\\java Projects\\ExSalon\\data\\new\\");

        File[] files = xmlFile.listFiles();
        System.out.println(files.length);
            for(int i=0;i<files.length;i++) {
                if (files[i].getName().contains(".txt")) {
                    try {
                        ParsingLine parser = new ParsingLine();
                        fileConverter.convert("D:\\java Projects\\ExSalon\\data\\new\\"+i+".txt", "D:\\java Projects\\ExSalon\\data\\buffer\\"+i+".txt", "windows-1251", "UTF8");
                        File file = new File("D:\\java Projects\\ExSalon\\data\\buffer\\"+i+".txt");
                        //создаем объект FileReader для объекта File
                        FileReader fr = new FileReader(file);
                        //создаем BufferedReader с существующего FileReader для построчного считывания
                        BufferedReader reader = new BufferedReader(fr);
                        // считаем сначала первую строку
                        String line = reader.readLine();
                        while (line != null) {
                            if (line.toString().equals(" ") || line.toString().contains("Кількість") || line.toString().contains("Cума") || line.toString().equals("")) {
                                System.out.println(line.toString());
                                line = reader.readLine();
                                continue;
                            }
                            // считываем остальные строки в цикле
                            // System.out.println(line.toString());
                            parser.parsingLine(line);
                            line = reader.readLine();
                        }
                        TransactionListDao transactionListDao = new TransactionListDao();
                        transactionListDao.inSertTranzaction(parser.transactionsList);
                    } catch (FileNotFoundException e) {
                        continue;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Finish");
                }
            }

    }
}
