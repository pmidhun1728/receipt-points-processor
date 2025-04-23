package com.receipt.service;

import com.example.dto.ItemDescription;
import com.example.dto.ReceiptDTO;
import com.receipt.utils.CommonUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class ReceiptService {

    public int totalPoints(ReceiptDTO receiptDTO){
       return calculatePoints(receiptDTO.getRetailer())+
        itemDescription(receiptDTO.getItems())+
        purchaseDate(receiptDTO.getPurchaseDate())+
        purchaseTotal(receiptDTO.getPurchaseDate())+
        multiplesOfTotal(receiptDTO.getTotal())+
        getTime(receiptDTO.getPurchaseTime()) +
       getTwoItemReceipt(receiptDTO.getItems());
    }

    //One point for every alphanumeric character in the retailer name.
    public static int calculatePoints(String retailerName) {
        int points = 0;

       String specialCharacterCount =  retailerName.replaceAll("[a-zA-Z0-9]", "");
       int totalCount =  retailerName.length() - specialCharacterCount.length();
        return totalCount;
    }

    public static String generateId(){
       String randomId = CommonUtils.randomIdGenerator();
        return randomId;
    }

    //6 points if the day in the purchase date is odd.
    public static int purchaseDate(String purchaseDate) {
        if (purchaseDate != null && purchaseDate.length() >= 2) {
            String lastTwo = purchaseDate.substring(purchaseDate.length() - 2);
            int lastTwoDigits = parseInt(lastTwo);
            if (lastTwoDigits % 2 == 1) {
               return 6;
            }
        }
        return 0;
    }

    //50 points if the total is a round dollar amount with no cents.
    public static int purchaseTotal(String total) {
        try {
            double amount = Double.parseDouble(total);
            return (amount == Math.floor(amount)) ? 50 : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    //25 points if the total is a multiple of 0.25
    public static int multiplesOfTotal(String multipleOfTotal) {

       double multipleTotal =  Double.parseDouble(multipleOfTotal);
        if (multipleOfTotal !=null && multipleTotal%0.25==0){
            return 25;
        }
        return 0;
    }

    //If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
    public static int itemDescription(List<ItemDescription> itemDescription) {
        int itemDescriptionCount=0;
        for(int i=0; i<itemDescription.size(); i++){
        if( itemDescription.get(i).getShortDescription().trim().length()%3==0){

            String price = itemDescription.get(i).getPrice();
            Double getPrice= Double.parseDouble(price);
            itemDescriptionCount+=Math.ceil(getPrice*0.2);
        }
        }
        return itemDescriptionCount;
    }

    //10 points if the time of purchase is after 2:00pm and before 4:00pm.
    public static int getTime(String purchaseTime){
        String firstTwoLetters = purchaseTime.substring(0,2);
        int intFirstTwoLetters = Integer.parseInt(firstTwoLetters);

        if (intFirstTwoLetters > 14 && intFirstTwoLetters < 16) {
            return 10;
        }else {
            return 0;
        }
    }

    public static int getTwoItemReceipt(List<ItemDescription> items) {
        if (items == null) {
            return 0;
        }
        int itemCount =  items.size() / 2 * 5;
        return itemCount;
    }
}


