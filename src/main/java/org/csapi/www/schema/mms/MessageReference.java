/**
 * MessageReference.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.mms;

@SuppressWarnings({"serial","unused","rawtypes"})public class MessageReference  implements java.io.Serializable {
    private java.lang.String messageIdentifier;

    private java.lang.String messageServiceActivationNumber;

    private org.apache.axis.types.URI senderAddress;

    private java.lang.String subject;

    private org.csapi.www.schema.mms.MessagePriority priority;

    private byte[] message;

    private java.util.Calendar dateTime;

    public MessageReference() {
    }

    public MessageReference(
           java.lang.String messageIdentifier,
           java.lang.String messageServiceActivationNumber,
           org.apache.axis.types.URI senderAddress,
           java.lang.String subject,
           org.csapi.www.schema.mms.MessagePriority priority,
           byte[] message,
           java.util.Calendar dateTime) {
           this.messageIdentifier = messageIdentifier;
           this.messageServiceActivationNumber = messageServiceActivationNumber;
           this.senderAddress = senderAddress;
           this.subject = subject;
           this.priority = priority;
           this.message = message;
           this.dateTime = dateTime;
    }


    /**
     * Gets the messageIdentifier value for this MessageReference.
     * 
     * @return messageIdentifier
     */
    public java.lang.String getMessageIdentifier() {
        return messageIdentifier;
    }


    /**
     * Sets the messageIdentifier value for this MessageReference.
     * 
     * @param messageIdentifier
     */
    public void setMessageIdentifier(java.lang.String messageIdentifier) {
        this.messageIdentifier = messageIdentifier;
    }


    /**
     * Gets the messageServiceActivationNumber value for this MessageReference.
     * 
     * @return messageServiceActivationNumber
     */
    public java.lang.String getMessageServiceActivationNumber() {
        return messageServiceActivationNumber;
    }


    /**
     * Sets the messageServiceActivationNumber value for this MessageReference.
     * 
     * @param messageServiceActivationNumber
     */
    public void setMessageServiceActivationNumber(java.lang.String messageServiceActivationNumber) {
        this.messageServiceActivationNumber = messageServiceActivationNumber;
    }


    /**
     * Gets the senderAddress value for this MessageReference.
     * 
     * @return senderAddress
     */
    public org.apache.axis.types.URI getSenderAddress() {
        return senderAddress;
    }


    /**
     * Sets the senderAddress value for this MessageReference.
     * 
     * @param senderAddress
     */
    public void setSenderAddress(org.apache.axis.types.URI senderAddress) {
        this.senderAddress = senderAddress;
    }


    /**
     * Gets the subject value for this MessageReference.
     * 
     * @return subject
     */
    public java.lang.String getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this MessageReference.
     * 
     * @param subject
     */
    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }


    /**
     * Gets the priority value for this MessageReference.
     * 
     * @return priority
     */
    public org.csapi.www.schema.mms.MessagePriority getPriority() {
        return priority;
    }


    /**
     * Sets the priority value for this MessageReference.
     * 
     * @param priority
     */
    public void setPriority(org.csapi.www.schema.mms.MessagePriority priority) {
        this.priority = priority;
    }


    /**
     * Gets the message value for this MessageReference.
     * 
     * @return message
     */
    public byte[] getMessage() {
        return message;
    }


    /**
     * Sets the message value for this MessageReference.
     * 
     * @param message
     */
    public void setMessage(byte[] message) {
        this.message = message;
    }


    /**
     * Gets the dateTime value for this MessageReference.
     * 
     * @return dateTime
     */
    public java.util.Calendar getDateTime() {
        return dateTime;
    }


    /**
     * Sets the dateTime value for this MessageReference.
     * 
     * @param dateTime
     */
    public void setDateTime(java.util.Calendar dateTime) {
        this.dateTime = dateTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MessageReference)) return false;
        MessageReference other = (MessageReference) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.messageIdentifier==null && other.getMessageIdentifier()==null) || 
             (this.messageIdentifier!=null &&
              this.messageIdentifier.equals(other.getMessageIdentifier()))) &&
            ((this.messageServiceActivationNumber==null && other.getMessageServiceActivationNumber()==null) || 
             (this.messageServiceActivationNumber!=null &&
              this.messageServiceActivationNumber.equals(other.getMessageServiceActivationNumber()))) &&
            ((this.senderAddress==null && other.getSenderAddress()==null) || 
             (this.senderAddress!=null &&
              this.senderAddress.equals(other.getSenderAddress()))) &&
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              this.subject.equals(other.getSubject()))) &&
            ((this.priority==null && other.getPriority()==null) || 
             (this.priority!=null &&
              this.priority.equals(other.getPriority()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              java.util.Arrays.equals(this.message, other.getMessage()))) &&
            ((this.dateTime==null && other.getDateTime()==null) || 
             (this.dateTime!=null &&
              this.dateTime.equals(other.getDateTime())));
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
        if (getMessageIdentifier() != null) {
            _hashCode += getMessageIdentifier().hashCode();
        }
        if (getMessageServiceActivationNumber() != null) {
            _hashCode += getMessageServiceActivationNumber().hashCode();
        }
        if (getSenderAddress() != null) {
            _hashCode += getSenderAddress().hashCode();
        }
        if (getSubject() != null) {
            _hashCode += getSubject().hashCode();
        }
        if (getPriority() != null) {
            _hashCode += getPriority().hashCode();
        }
        if (getMessage() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMessage());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMessage(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDateTime() != null) {
            _hashCode += getDateTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MessageReference.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "MessageReference"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageIdentifier");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messageIdentifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageServiceActivationNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messageServiceActivationNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senderAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priority");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "MessagePriority"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("", "message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
