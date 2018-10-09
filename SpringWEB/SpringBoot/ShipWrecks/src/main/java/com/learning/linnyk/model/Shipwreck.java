package com.learning.linnyk.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Shipwreck {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String condition;
    private Integer depth;
    private Double latitude;
    private Double longitude;
    private Integer yearDiscovered;

}
