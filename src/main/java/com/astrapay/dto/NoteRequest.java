package com.astrapay.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteRequest {
    @NotBlank(message = "Title must filled")
    private String title;

    @NotBlank(message = "Description must filled")
    private String description;

    @NotBlank(message = "Date must filled")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date harus dalam format yyyy-MM-dd")
    @Schema(description = "Date", example = "2025-07-09", defaultValue = "2025-07-09")
    private String date;
}
