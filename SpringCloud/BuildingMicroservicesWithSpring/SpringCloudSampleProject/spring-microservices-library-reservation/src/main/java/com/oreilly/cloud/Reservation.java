package com.oreilly.cloud;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Reservation {

    private int reservationId;
    private String username;
    private int bookId;
    private Date date;

}
