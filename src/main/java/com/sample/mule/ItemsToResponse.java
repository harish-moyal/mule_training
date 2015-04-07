
package com.sample.mule;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ItemsToResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ItemsToResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numberToDouble" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numberToHalf" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemsToResponse", propOrder = {
    "numberToDouble",
    "numberToHalf"
})
public class ItemsToResponse {

    protected int numberToDouble;
    protected int numberToHalf;

    /**
     * Gets the value of the numberToDouble property.
     * 
     */
    public int getNumberToDouble() {
        return numberToDouble;
    }

    /**
     * Sets the value of the numberToDouble property.
     * 
     */
    public void setNumberToDouble(int value) {
        this.numberToDouble = value;
    }

    /**
     * Gets the value of the numberToHalf property.
     * 
     */
    public int getNumberToHalf() {
        return numberToHalf;
    }

    /**
     * Sets the value of the numberToHalf property.
     * 
     */
    public void setNumberToHalf(int value) {
        this.numberToHalf = value;
    }

}
