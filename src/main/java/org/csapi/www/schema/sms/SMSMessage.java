/**
 * SMSMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.sms;

@SuppressWarnings({"serial","unused","rawtypes"})public class SMSMessage  implements java.io.Serializable {
    private java.lang.String message;

    private org.apache.axis.types.URI smsServiceActivationNumber;

    private org.apache.axis.types.URI senderAddress;

    private org.csapi.www.schema.sms.MessageFormat messageFormat;

    public SMSMessage() {
    }

    public SMSMessage(
           java.lang.String message,
           org.apache.axis.types.URI smsServiceActivationNumber,
           org.apache.axis.types.URI senderAddress,
           org.csapi.www.schema.sms.MessageFormat messageFormat) {
           this.message = message;
           this.smsServiceActivationNumber = smsServiceActivationNumber;
           this.senderAddress = senderAddress;
           this.messageFormat = messageFormat;
    }


    /**
     * Gets the message value for this SMSMessage.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this SMSMessage.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the smsServiceActivationNumber value for this SMSMessage.
     * 
     * @return smsServiceActivationNumber
     */
    public org.apache.axis.types.URI getSmsServiceActivationNumber() {
        return smsServiceActivationNumber;
    }


    /**
     * Sets the smsServiceActivationNumber value for this SMSMessage.
     * 
     * @param smsServiceActivationNumber
     */
    public void setSmsServiceActivationNumber(org.apache.axis.types.URI smsServiceActivationNumber) {
        this.smsServiceActivationNumber = smsServiceActivationNumber;
    }


    /**
     * Gets the senderAddress value for this SMSMessage.
     * 
     * @return senderAddress
     */
    public org.apache.axis.types.URI getSenderAddress() {
        return senderAddress;
    }


    /**
     * Sets the senderAddress value for this SMSMessage.
     * 
     * @param senderAddress
     */
    public void setSenderAddress(org.apache.axis.types.URI senderAddress) {
        this.senderAddress = senderAddress;
    }


    /**
     * Gets the messageFormat value for this SMSMessage.
     * 
     * @return messageFormat
     */
    public org.csapi.www.schema.sms.MessageFormat getMessageFormat() {
        return messageFormat;
    }


    /**
     * Sets the messageFormat value for this SMSMessage.
     * 
     * @param messageFormat
     */
    public void setMessageFormat(org.csapi.www.schema.sms.MessageFormat messageFormat) {
        this.messageFormat = messageFormat;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SMSMessage)) return false;
        SMSMessage other = (SMSMessage) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.smsServiceActivationNumber==null && other.getSmsServiceActivationNumber()==null) || 
             (this.smsServiceActivationNumber!=null &&
              this.smsServiceActivationNumber.equals(other.getSmsServiceActivationNumber()))) &&
            ((this.senderAddress==null && other.getSenderAddress()==null) || 
             (this.senderAddress!=null &&
              this.senderAddress.equals(other.getSenderAddress()))) &&
            ((this.messageFormat==null && other.getMessageFormat()==null) || 
             (this.messageFormat!=null &&
              this.messageFormat.equals(other.getMessageFormat())));
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
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getSmsServiceActivationNumber() != null) {
            _hashCode += getSmsServiceActivationNumber().hashCode();
        }
        if (getSenderAddress() != null) {
            _hashCode += getSenderAddress().hashCode();
        }
        if (getMessageFormat() != null) {
            _hashCode += getMessageFormat().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SMSMessage.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "SMSMessage"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("smsServiceActivationNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SmsServiceActivationNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SenderAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MessageFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "MessageFormat"));
        elemField.setNillable(true);
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
