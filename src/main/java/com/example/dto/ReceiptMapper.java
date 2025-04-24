package com.example.dto;

import com.receipt.entity.ReceiptEntity;

public class ReceiptMapper {

    public static ReceiptEntity toEntity(ReceiptDTO dto) {
        ReceiptEntity entity = new ReceiptEntity();
        entity.setId(dto.getId());
        entity.setPoints(dto.getPoints());
        return entity;
    }

    public static ReceiptDTO toDTO(ReceiptEntity entity, ReceiptDTO existingDTO) {
        // Retain other fields from the existing DTO (if needed), and update points and id
        existingDTO.setId(entity.getId());
        existingDTO.setPoints(entity.getPoints());
        return existingDTO;
    }
}
