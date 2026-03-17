package com.quantitymeasurement.dto;


import org.antlr.v4.runtime.misc.NotNull;

public class QuantityDTO {

    @NotNull
    private String operation;

    @NotNull
    private Double operand1;

    private Double operand2;

    private Double result;

    // getters & setters
}