package parsing;

import DTO.FullTransactionDTO;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kozak on 03.03.2017.
 */
public class ParsingLine {
    public static ArrayList<FullTransactionDTO> transactionsList = new ArrayList();
    public void parsingLine(String str) {
        ArrayList<Integer> cost = new ArrayList<Integer>();
        ArrayList<String> service = new ArrayList<String>();
        Integer reference;
        //рандомное число для ключа
        Random rnd = new Random();
        reference=rnd.nextInt();
        //формируем первый массив для записей в базу, всегда будет 3 елемента т.е. пишем одним стеком 3 столбца
        String[] mass = str.split(" -");
        //Формируем потранзакционные записи
        Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        Pattern pat1 = Pattern.compile("[-]?[а-яА-Я]+(.[а-яА-Я]+)?");
        Matcher matcher = pat.matcher(mass[1]);
        Matcher matcher1 = pat1.matcher(mass[1]);
        while (matcher.find()) {
            cost.add(Integer.valueOf(matcher.group()));
        }
        while (matcher1.find()) {
            service.add(String.valueOf(matcher1.group()));
        }
        String [] arrayService = new String[service.size()];
        Integer [] arrayCost = new Integer[cost.size()];

        for(int i =0;i<service.size();i++){
            arrayService[i]=service.get(i);
        }
        for(int i =0;i<cost.size();i++){
            arrayCost[i]=cost.get(i);
        }
        for(int i =0;i<arrayService.length-1;i++){
            FullTransactionDTO tranzactDTO = new FullTransactionDTO();
            tranzactDTO.ref=reference;
            tranzactDTO.cost=arrayCost[i];
            tranzactDTO.date=mass[0];
            tranzactDTO.service=arrayService[i];
            tranzactDTO.phone=mass[2];
            transactionsList.add(tranzactDTO);
        }
    }
}
