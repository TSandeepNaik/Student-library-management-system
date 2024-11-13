package com.demo.example.student_library_management.converters;

import com.demo.example.student_library_management.dto.CardRequestDto;
import com.demo.example.student_library_management.model.Card;

public class CardConverter {
    public static Card convertCardRequestDtoToCard(CardRequestDto cardRequestDto){

       Card card = Card.builder().cardstatus(cardRequestDto.getCardstatus()).build();
        return card;
    }
}
