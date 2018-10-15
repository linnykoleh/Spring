package com.ps.jms;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class User {

    private String email;
    private Double rating;
    private boolean active;

}
