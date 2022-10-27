package br.com.brunolutterbach.aluraflix.exception;

import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationErrorDetails {

    private String title;
    private int status;
    private String detail;
    private ZonedDateTime date;



}
