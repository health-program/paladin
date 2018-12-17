/**
 * SendSmsRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.sms;

@SuppressWarnings({"serial","unused","rawtypes"})public class SendSmsRequest  implements java.io.Serializable {
    private java.lang.String applicationID;

    private org.apache.axis.types.URI[] destinationAddresses;

    private java.lang.String extendCode;

    private java.lang.String message;

    private org.csapi.www.schema.sms.MessageFormat messageFormat;

    private org.csapi.www.schema.sms.SendMethodType sendMethod;

    private boolean deliveryResultRequest;

    public SendSmsRequest() {
    }

    public SendSmsRequest(
           java.lang.String applicationID,
           org.apache.axis.types.URI[] destinationAddresses,
           java.lang.String extendCode,
           java.lang.String message,
           org.csapi.www.schema.sms.MessageFormat messageFormat,
           org.csapi.www.schema.sms.SendMethodType sendMethod,
           boolean deliveryResultRequest) {
           this.applicationID = applicationID;
           this.destinationAddresses = destinationAddresses;
           this.extendCode = extendCode;
           this.message = message;
           this.messageFormat = messageFormat;
           this.sendMethod = sendMethod;
           this.deliveryResultRequest = deliveryResultRequest;
    }


    /**
     * Gets the applicationID value for this SendSmsRequest.
     * 
     * @return applicationID
     */
    public java.lang.String getApplicationID() {
        return applicationID;
    }


    /**
     * Sets the applicationID value for this SendSmsRequest.
     * 
     * @param applicationID
     */
    public void setApplicationID(java.lang.String applicationID) {
        this.applicationID = applicationID;
    }


    /**
     * Gets the destinationAddresses value for this SendSmsRequest.
     * 
     * @return destinationAddresses
     */
    public org.apache.axis.types.URI[] getDestinationAddresses() {
        return destinationAddresses;
    }


    /**
     * Sets the destinationAddresses value for this SendSmsRequest.
     * 
     * @param destinationAddresses
     */
    public void setDestinationAddresses(org.apache.axis.types.URI[] destinationAddresses) {
        this.destinationAddresses = destinationAddresses;
    }

    public org.apache.axis.types.URI getDestinationAddresses(int i) {
        return this.destinationAddresses[i];
    }

    public void setDestinationAddresses(int i, org.apache.axis.types.URI _value) {
        this.destinationAddresses[i] = _value;
    }


    /**
     * Gets the extendCode value for this SendSmsRequest.
     * 
     * @return extendCode
     */
    public java.lang.String getExtendCode() {
        return extendCode;
    }


    /**
     * Sets the extendCode value for this SendSmsRequest.
     * 
     * @param extendCode
     */
    public void setExtendCode(java.lang.String extendCode) {
        this.extendCode = extendCode;
    }


    /**
     * Gets the message value for this SendSmsRequest.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this SendSmsRequest.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the messageFormat value for this SendSmsRequest.
     * 
     * @return messageFormat
     */
    public org.csapi.www.schema.sms.MessageFormat getMessageFormat() {
        return messageFormat;
    }


    /**
     * Sets the messageFormat value for this SendSmsRequest.
     * 
     * @param messageFormat
     */
    public void setMessageFormat(org.csapi.www.schema.sms.MessageFormat messageFormat) {
        this.messageFormat = messageFormat;
    }


    /**
     * Gets the sendMethod value for this SendSmsRequest.
     * 
     * @return sendMethod
     */
    public org.csapi.www.schema.sms.SendMethodType getSendMethod() {
        return sendMethod;
    }


    /**
     * Sets the sendMethod value for this SendSmsRequest.
     * 
     * @param sendMethod
     */
    public void setSendMethod(org.csapi.www.schema.sms.SendMethodType sendMethod) {
        this.sendMethod = sendMethod;
    }


    /**
     * Gets the deliveryResultRequest value for this SendSmsRequest.
     * 
     * @return deliveryResultRequest
     */
    public boolean isDeliveryResultRequest() {
        return deliveryResultRequest;
    }


    /**
     * Sets the deliveryResultRequest value for this SendSmsRequest.
     * 
     * @param deliveryResultRequest
     */
    public void setDeliveryResultRequest(boolean deliveryResultRequest) {
        this.deliveryResultRequest = deliveryResultRequest;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SendSmsRequest)) return false;
        SendSmsRequest other = (SendSmsRequest) obj;
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
            ((this.destinationAddresses==null && other.getDestinationAddresses()==null) || 
             (this.destinationAddresses!=null &&
              java.util.Arrays.equals(this.destinationAddresses, other.getDestinationAddresses()))) &&
            ((this.extendCode==null && other.getExtendCode()==null) || 
             (this.extendCode!=null &&
              this.extendCode.equals(other.getExtendCode()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.messageFormat==null && other.getMessageFormat()==null) || 
             (this.messageFormat!=null &&
              this.messageFormat.equals(other.getMessageFormat()))) &&
            ((this.sendMethod==null && other.getSendMethod()==null) || 
             (this.sendMethod!=null &&
              this.sendMethod.equals(other.getSendMethod()))) &&
            this.deliveryResultRequest == other.isDeliveryResultRequest();
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
        if (getDestinationAddresses() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDestinationAddresses());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDestinationAddresses(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getExtendCode() != null) {
            _hashCode += getExtendCode().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getMessageFormat() != null) {
            _hashCode += getMessageFormat().hashCode();
        }
        if (getSendMethod() != null) {
            _hashCode += getSendMethod().hashCode();
        }
        _hashCode += (isDeliveryResultRequest() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SendSmsRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", ">sendSmsRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ApplicationID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinationAddresses");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DestinationAddresses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extendCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ExtendCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MessageFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "MessageFormat"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendMethod");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SendMethod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "SendMethodType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryResultRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeliveryResultRequest"));
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
