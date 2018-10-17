
package com.ps.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="userType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OWNER"/>
 *     &lt;enumeration value="SITTER"/>
 *     &lt;enumeration value="BOTH"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "userType", namespace = "http://ws-boot.com/schemas/um")
@XmlEnum
public enum UserType {

    OWNER,
    SITTER,
    BOTH;

    public String value() {
        return name();
    }

    public static UserType fromValue(String v) {
        return valueOf(v);
    }

}
