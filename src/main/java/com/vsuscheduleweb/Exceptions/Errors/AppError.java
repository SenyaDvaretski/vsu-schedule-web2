package com.vsuscheduleweb.Exceptions.Errors;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppError {
    private int status;
    private String message;
}
