/**
 * MakeUssdRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.ussd;

@SuppressWarnings({"serial","unused","rawtypes"})public class MakeUssdRequest  implements java.io.Serializable {
    private java.lang.String applicationID;

    private org.apache.axis.types.URI destinationAddress;

    private org.csapi.www.schema.ussd.UssdArray ussdMessage;

    public MakeUssdRequest() {
    }

    public MakeUssdRequest(
           java.lang.String applicationID,
           org.apache.axis.types.URI destinationAddress,
           org.csapi.www.schema.ussd.UssdArray ussdMessage) {
           this.applicationID = applicationID;
           this.destinationAddress = destinationAddress;
           this.ussdMessage = ussdMessage;
    }


    /**
     * Gets the applicationID value for this MakeUssdRequest.
     * 
     * @return applicationID
     */
    public java.lang.String getApplicationID() {
        return applicationID;
    }


    /**
     * Sets the applicationID value for this MakeUssdRequest.
     * 
     * @param applicationID
     */
    public void setApplicationID(java.lang.String applicationID) {
        this.applicationID = applicationID;
    }


    /**
     * Gets the destinationAddress value for this MakeUssdRequest.
     * 
     * @return destinationAddress
     */
    public org.apache.axis.types.URI getDestinationAddress() {
        return destinationAddress;
    }


    /**
     * Sets the destinationAddress value for this MakeUssdRequest.
     * 
     * @param destinationAddress
     */
    public void setDestinationAddress(org.apache.axis.types.URI destinationAddress) {
        this.destinationAddress = destinationAddress;
    }


    /**
     * Gets the ussdMessage value for this MakeUssdRequest.
     * 
     * @return ussdMessage
     */
    public org.csapi.www.schema.ussd.UssdArray getUssdMessage() {
        return ussdMessage;
    }


    /**
     * Sets the ussdMessage value for this MakeUssdRequest.
     * 
     * @param ussdMessage
     */
    public void setUssdMessage(org.csapi.www.schema.ussd.UssdArray ussdMessage) {
        this.ussdMessage = ussdMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MakeUssdRequest)) return false;
        MakeUssdRequest other = (MakeUssdRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.applicationID==null && other.getApplicationID()==null) || 
             (this.applicationID!=null &&
              this.applicationID.equals(other.getApplicationID()))) &&
            ((this.destinationAddress==null && other.getDestinationAddress()==null) || 
             (this.destinationAddress!=null &&
              this.destinationAddress.equals(other.getDestinationAddress()))) &&
            ((this.ussdMessage==null && other.getUssdMessage()==null) || 
             (this.ussdMessage!=null &&
              this.ussdMessage.equals(other.getUssdMessage())));
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
        if (getApplicationID() != null) {
            _hashCode += getApplicationID().hashCode();
        }
        if (getDestinationAddress() != null) {
            _hashCode += getDestinationAddress().hashCode();
        }
        if (getUssdMessage() != null) {
            _hashCode += getUssdMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MakeUssdRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">makeUssdRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ApplicationID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinationAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destinationAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ussdMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ussdMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", "UssdArray"));
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
