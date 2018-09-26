package com.spring4.linnyk.mvc.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.spring4.linnyk.mvc.annotations.Phone;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Attendee {

    @Size(min = 2, max = 30)
    private String name;

    @NotEmpty
    @Email
    private String emailAddress;

    @NotEmpty
    @Phone
    private String phone;
}
