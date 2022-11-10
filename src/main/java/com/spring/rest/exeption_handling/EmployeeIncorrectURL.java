package com.spring.rest.exeption_handling;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * EmployeeIncorrectURL class which will be creat Object in which will be put
 * Exception message. This Object also will be converting in JSON and send in
 * case wrong URL
 */
@Getter
@Setter
@NoArgsConstructor
public class EmployeeIncorrectURL {

    private String InfoMessage;
}
