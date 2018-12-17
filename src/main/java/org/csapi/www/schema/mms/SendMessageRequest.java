/**
 * SendMessageRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.mms;

@SuppressWarnings({"serial","unused","rawtypes"})public class SendMessageRequest  implements java.io.Serializable {
    private java.lang.String applicationID;

    private org.apache.axis.types.URI[] addresses;

    private java.lang.String extendCode;

    private java.lang.String subject;

    private org.csapi.www.schema.mms.MessagePriority priority;

    private boolean receiptRequest;

    private byte[] content;

    public SendMessageRequest() {
    }

    public SendMessageRequest(
           java.lang.String applicationID,
           org.apache.axis.types.URI[] addresses,
           java.lang.String extendCode,
           java.lang.String subject,
           org.csapi.www.schema.mms.MessagePriority priority,
           boolean receiptRequest,
           byte[] content) {
           this.applicationID = applicationID;
           this.addresses = addresses;
           this.extendCode = extendCode;
           this.subject = subject;
           this.priority = priority;
           this.receiptRequest = receiptRequest;
           this.content = content;
    }


    /**
     * Gets the applicationID value for this SendMessageRequest.
     * 
     * @return applicationID
     */
    public java.lang.String getApplicationID() {
        return applicationID;
    }


    /**
     * Sets the applicationID value for this SendMessageRequest.
     * 
     * @param applicationID
     */
    public void setApplicationID(java.lang.String applicationID) {
        this.applicationID = applicationID;
    }


    /**
     * Gets the addresses value for this SendMessageRequest.
     * 
     * @return addresses
     */
    public org.apache.axis.types.URI[] getAddresses() {
        return addresses;
    }


    /**
     * Sets the addresses value for this SendMessageRequest.
     * 
     * @param addresses
     */
    public void setAddresses(org.apache.axis.types.URI[] addresses) {
        this.addresses = addresses;
    }

    public org.apache.axis.types.URI getAddresses(int i) {
        return this.addresses[i];
    }

    public void setAddresses(int i, org.apache.axis.types.URI _value) {
        this.addresses[i] = _value;
    }


    /**
     * Gets the extendCode value for this SendMessageRequest.
     * 
     * @return extendCode
     */
    public java.lang.String getExtendCode() {
        return extendCode;
    }


    /**
     * Sets the extendCode value for this SendMessageRequest.
     * 
     * @param extendCode
     */
    public void setExtendCode(java.lang.String extendCode) {
        this.extendCode = extendCode;
    }


    /**
     * Gets the subject value for this SendMessageRequest.
     * 
     * @return subject
     */
    public java.lang.String getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this SendMessageRequest.
     * 
     * @param subject
     */
    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }


    /**
     * Gets the priority value for this SendMessageRequest.
     * 
     * @return priority
     */
    public org.csapi.www.schema.mms.MessagePriority getPriority() {
        return priority;
    }


    /**
     * Sets the priority value for this SendMessageRequest.
     * 
     * @param priority
     */
    public void setPriority(org.csapi.www.schema.mms.MessagePriority priority) {
        this.priority = priority;
    }


    /**
     * Gets the receiptRequest value for this SendMessageRequest.
     * 
     * @return receiptRequest
     */
    public boolean isReceiptRequest() {
        return receiptRequest;
    }


    /**
     * Sets the receiptRequest value for this SendMessageRequest.
     * 
     * @param receiptRequest
     */
    public void setReceiptRequest(boolean receiptRequest) {
        this.receiptRequest = receiptRequest;
    }


    /**
     * Gets the content value for this SendMessageRequest.
     * 
     * @return content
     */
    public byte[] getContent() {
        return content;
    }


    /**
     * Sets the content value for this SendMessageRequest.
     * 
     * @param content
     */
    public void setContent(byte[] content) {
        this.content = content;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SendMessageRequest)) return false;
        SendMessageRequest other = (SendMessageRequest) obj;
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
            ((this.addresses==null && other.getAddresses()==null) || 
             (this.addresses!=null &&
              java.util.Arrays.equals(this.addresses, other.getAddresses()))) &&
            ((this.extendCode==null && other.getExtendCode()==null) || 
             (this.extendCode!=null &&
              this.extendCode.equals(other.getExtendCode()))) &&
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              this.subject.equals(other.getSubject()))) &&
            ((this.priority==null && other.getPriority()==null) || 
             (this.priority!=null &&
              this.priority.equals(other.getPriority()))) &&
            this.receiptRequest == other.isReceiptRequest() &&
            ((this.content==null && other.getContent()==null) || 
             (this.content!=null &&
              java.util.Arrays.equals(this.content, other.getContent())));
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
        if (getAddresses() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAddresses());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAddresses(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getExtendCode() != null) {
            _hashCode += getExtendCode().hashCode();
        }
        if (getSubject() != null) {
            _hashCode += getSubject().hashCode();
        }
        if (getPriority() != null) {
            _hashCode += getPriority().hashCode();
        }
        _hashCode += (isReceiptRequest() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getContent() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContent());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContent(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SendMessageRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">sendMessageRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ApplicationID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addresses");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addresses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extendCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ExtendCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
        elemField.setFieldName("receiptRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("", "receiptRequest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("content");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Content"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
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
