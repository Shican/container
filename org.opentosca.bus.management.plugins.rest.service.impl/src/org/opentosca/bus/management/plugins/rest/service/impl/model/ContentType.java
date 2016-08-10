//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.29 at 03:39:23 PM MESZ 
//


package org.opentosca.bus.management.plugins.rest.service.impl.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ContentType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ContentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="urlencoded"/>
 *     &lt;enumeration value="xml"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ContentType")
@XmlEnum
public enum ContentType {

    @XmlEnumValue("urlencoded")
    URLENCODED("urlencoded"),
    @XmlEnumValue("xml")
    XML("xml");
    private final String value;

    ContentType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ContentType fromValue(String v) {
        for (ContentType c: ContentType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
