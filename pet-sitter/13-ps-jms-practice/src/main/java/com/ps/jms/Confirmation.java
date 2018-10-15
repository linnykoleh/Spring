package com.ps.jms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Confirmation implements Serializable {

    private int ackNumber;
    private String verificationComment;

}
