package com.demo.example.student_library_management.dto;

import com.demo.example.student_library_management.Enums.Cardstatus;
import lombok.Data;

@Data
public class CardRequestDto {

    //automatically creating things we dont add here like creation timestamp and update timestamp
    private Cardstatus cardstatus;
     private int studentId;
}
