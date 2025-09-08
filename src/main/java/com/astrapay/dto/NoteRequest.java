package com.astrapay.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteRequest {
    @NotBlank(message = "Title must filled")
    @Size(max = 15, message = "Title must not exceed 15 characters")
    private String title;

    @NotBlank(message = "Description must filled")
    @Size(max = 50, message = "Description must not exceed 50 characters")
    private String description;

    @NotBlank(message = "Date must filled")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date harus dalam format yyyy-MM-dd")
    @Schema(description = "Date", example = "2025-07-09", defaultValue = "2025-07-09")
    private String date;
}
