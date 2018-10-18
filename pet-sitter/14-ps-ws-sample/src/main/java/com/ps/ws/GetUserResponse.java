
package com.ps.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userType" type="{http://ws-boot.com/schemas/um}userType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "userType"
})
@XmlRootElement(name = "getUserResponse", namespace = "http://ws-boot.com/schemas/um")
public class GetUserResponse {

    @XmlElement(namespace = "http://ws-boot.com/schemas/um", required = true)
    @XmlSchemaType(name = "string")
    protected UserType userType;

    /**
     * Gets the value of the userType property.
     * 
     * @return
     *     possible object is
     *     {@link UserType }
     *     
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Sets the value of the userType property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserType }
     *     
     */
    public void setUserType(UserType value) {
        this.userType = value;
    }

}
