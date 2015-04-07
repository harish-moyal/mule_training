
package com.sample.mule;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 *         &lt;element name="requestItem" type="{http://www.example.org/schema/DoubleIt}ItemsToProcess"/>
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
    "requestItem"
})
@XmlRootElement(name = "DoubleIt")
public class DoubleIt {

    @XmlElement(required = true)
    protected ItemsToProcess requestItem;

    /**
     * Gets the value of the requestItem property.
     * 
     * @return
     *     possible object is
     *     {@link ItemsToProcess }
     *     
     */
    public ItemsToProcess getRequestItem() {
        return requestItem;
    }

    /**
     * Sets the value of the requestItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemsToProcess }
     *     
     */
    public void setRequestItem(ItemsToProcess value) {
        this.requestItem = value;
    }

}
