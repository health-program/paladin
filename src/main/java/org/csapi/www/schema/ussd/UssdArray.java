/**
 * UssdArray.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.ussd;

@SuppressWarnings({"serial","unused","rawtypes"})public class UssdArray  implements java.io.Serializable {
    private java.lang.String ussdMessage;

    private boolean ussdReturnRequest;

    public UssdArray() {
    }

    public UssdArray(
           java.lang.String ussdMessage,
           boolean ussdReturnRequest) {
           this.ussdMessage = ussdMessage;
           this.ussdReturnRequest = ussdReturnRequest;
    }


    /**
     * Gets the ussdMessage value for this UssdArray.
     * 
     * @return ussdMessage
     */
    public java.lang.String getUssdMessage() {
        return ussdMessage;
    }


    /**
     * Sets the ussdMessage value for this UssdArray.
     * 
     * @param ussdMessage
     */
    public void setUssdMessage(java.lang.String ussdMessage) {
        this.ussdMessage = ussdMessage;
    }


    /**
     * Gets the ussdReturnRequest value for this UssdArray.
     * 
     * @return ussdReturnRequest
     */
    public boolean isUssdReturnRequest() {
        return ussdReturnRequest;
    }


    /**
     * Sets the ussdReturnRequest value for this UssdArray.
     * 
     * @param ussdReturnRequest
     */
    public void setUssdReturnRequest(boolean ussdReturnRequest) {
        this.ussdReturnRequest = ussdReturnRequest;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UssdArray)) return false;
        UssdArray other = (UssdArray) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ussdMessage==null && other.getUssdMessage()==null) || 
             (this.ussdMessage!=null &&
              this.ussdMessage.equals(other.getUssdMessage()))) &&
            this.ussdReturnRequest == other.isUssdReturnRequest();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getUssdMessage() != null) {
            _hashCode += getUssdMessage().hashCode();
        }
        _hashCode += (isUssdReturnRequest() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UssdArray.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", "UssdArray"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ussdMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ussdMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ussdReturnRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ussdReturnRequest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
