//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, v2.2.4-2
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2013.07.10 at 12:45:26 PM CEST
//
// TOSCA version: TOSCA-v1.0-cs02.xsd
//

package org.opentosca.container.core.tosca.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for tExtension complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="tExtension">
 *   &lt;complexContent>
 *     &lt;extension base="{http://docs.oasis-open.org/tosca/ns/2011/12}tExtensibleElements">
 *       &lt;attribute name="namespace" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="mustUnderstand" type="{http://docs.oasis-open.org/tosca/ns/2011/12}tBoolean" default="yes" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tExtension")
public class TExtension extends TExtensibleElements {

    @XmlAttribute(name = "namespace", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String namespace;
    @XmlAttribute(name = "mustUnderstand")
    protected TBoolean mustUnderstand;


    /**
     * Gets the value of the namespace property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNamespace() {
        return this.namespace;
    }

    /**
     * Sets the value of the namespace property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNamespace(final String value) {
        this.namespace = value;
    }

    /**
     * Gets the value of the mustUnderstand property.
     *
     * @return possible object is {@link TBoolean }
     *
     */
    public TBoolean getMustUnderstand() {
        if (this.mustUnderstand == null) {
            return TBoolean.YES;
        } else {
            return this.mustUnderstand;
        }
    }

    /**
     * Sets the value of the mustUnderstand property.
     *
     * @param value allowed object is {@link TBoolean }
     *
     */
    public void setMustUnderstand(final TBoolean value) {
        this.mustUnderstand = value;
    }

}