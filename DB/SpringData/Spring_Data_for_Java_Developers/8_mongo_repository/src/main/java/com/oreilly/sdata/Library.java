package com.oreilly.sdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oreilly.sdata.PointDeserializer;
import lombok.Data;
import org.springframework.data.geo.Point;

@Data
class Library {

    private String libraryId;

    private String name;

    @JsonDeserialize(using = PointDeserializer.class)
    private Point coords;

}
