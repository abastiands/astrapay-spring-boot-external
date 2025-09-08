package com.astrapay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteResponse {
    private Integer id;
    private String title;
    private String description;
    private String date;
}
