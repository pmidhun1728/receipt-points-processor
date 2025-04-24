package com.receipt.controller;


import com.example.dto.ReceiptDTO;
import com.receipt.entity.ReceiptEntity;
import com.receipt.service.ReceiptService;
import com.receipt.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiptController {


    @Autowired
    ReceiptService receiptService;

    @GetMapping("/getreceipt")
    public String getCall() {
        return "This is a Get call";
    }

    @PostMapping("/receipts/process")
    public ReceiptDTO postCall(@RequestBody ReceiptDTO receiptDTO){
        int totalPoints =  receiptService.totalPoints(receiptDTO);
        receiptDTO.setId(receiptService.generateId());
        receiptDTO.setPoints(totalPoints);
        return receiptService.saveReceipt(receiptDTO);
    }
}
