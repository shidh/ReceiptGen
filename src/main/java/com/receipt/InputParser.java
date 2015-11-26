package com.receipt;

import com.receipt.Model.Item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by allen on 26/11/15.
 */
public class InputParser {
    /**
     *
     * @param fileName
     * @return List of items
     */
    public static List<Item> parseInput(String fileName){
        File file = new File(fileName);
        BufferedReader bufferedReader = null;
        String inputLine = "";
        List<Item> items = new ArrayList<Item>();

        try {
            bufferedReader = new BufferedReader(new FileReader(file));

            while ((inputLine = bufferedReader.readLine()) != null) {
                String[] words = inputLine.split(" ");
                Item item = new Item();

                //check is imported or not
                if(inputLine.contains("imported")){
                    item.setImported(true);
                }

                //check quantity
                if(isNumber(words[0])){
                    item.setAmount(Integer.parseInt(words[0]));
                }

                //check price
                if(isNumber(words[words.length - 1])) {
                    item.setPrice(Double.parseDouble(words[words.length - 1]));
                }

                //get item information
                String[] restPart = Arrays.copyOfRange(words, 1, words.length - 2);
                String itemInfo = "";
                for (int index = 0; index < restPart.length; index++) {
                    itemInfo += " " + restPart[index].toLowerCase();
                }

                //set basic sales tax
                String type = getTypeBasedOn(itemInfo);
                if(type.equals(Constants.NOEXEMPT)){
                    item.setExempt(false);
                }else{
                    item.setExempt(true);
                }

                //check is imported
                String itemName = itemInfo.replace("imported", "").trim();
                item.setName(itemName);

                items.add(item);
            }

        }

        catch (IOException error) {
            System.out.println("Invalid File");
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return items;
    }


    /**
     *
     * @param itemInfo
     * @return
     */
    private static String getTypeBasedOn(String itemInfo) {
        HashMap<String, String> itemTypeMapping = new HashMap<String, String>();
        itemTypeMapping.put("book", Constants.BOOK);
        itemTypeMapping.put("books", Constants.BOOK);
        itemTypeMapping.put("chocolate", Constants.FOOD);
        itemTypeMapping.put("chocolates", Constants.FOOD);
        itemTypeMapping.put("pills", Constants.MEDICAL);
        itemTypeMapping.put("music", Constants.NOEXEMPT);
        itemTypeMapping.put("CD", Constants.NOEXEMPT);
        itemTypeMapping.put("perfume", Constants.NOEXEMPT);

        String[] words = itemInfo.split(" ");
        String type = Constants.NOEXEMPT;
        for (int i = 0; i < words.length; i++) {
            if (itemTypeMapping.containsKey(words[i])) {
                type = itemTypeMapping.get(words[i]);
            }
        }
        return type;
    }


    /**
     *
     * @param string
     * @return isNumber or not
     */
    static boolean isNumber(String string){
        String regEx ="^[0-9.]$";
        Pattern pattern = Pattern.compile(regEx);
        boolean isNumber = false;
        for (int i = 0; i < string.length(); i++){
            isNumber = pattern.matcher("" + string.charAt(i)).find();
            if(!isNumber){
                return isNumber;
            }
        }
        return isNumber;
    }
}
