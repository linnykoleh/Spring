package com.ps.jms;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Confirmation {

    private int ackNumber;

    private String verificationComment;

}
