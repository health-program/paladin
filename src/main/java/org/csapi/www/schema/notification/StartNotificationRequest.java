/**
 * StartNotificationRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.notification;

@SuppressWarnings({"serial","unused","rawtypes"})public class StartNotificationRequest  implements java.io.Serializable {
    private java.lang.String applicationId;

    private org.csapi.www.schema.common.v2_0.MessageNotificationType[] messageNotification;

    public StartNotificationRequest() {
    }

    public StartNotificationRequest(
           java.lang.String applicationId,
           org.csapi.www.schema.common.v2_0.MessageNotificationType[] messageNotification) {
           this.applicationId = applicationId;
           this.messageNotification = messageNotification;
    }


    /**
     * Gets the applicationId value for this StartNotificationRequest.
     * 
     * @return applicationId
     */
    public java.lang.String getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this StartNotificationRequest.
     * 
     * @param applicationId
     */
    public void setApplicationId(java.lang.String applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * Gets the messageNotification value for this StartNotificationRequest.
     * 
     * @return messageNotification
     */
    public org.csapi.www.schema.common.v2_0.MessageNotificationType[] getMessageNotification() {
        return messageNotification;
    }


    /**
     * Sets the messageNotification value for this StartNotificationRequest.
     * 
     * @param messageNotification
     */
    public void setMessageNotification(org.csapi.www.schema.common.v2_0.MessageNotificationType[] messageNotification) {
        this.messageNotification = messageNotification;
    }

    public org.csapi.www.schema.common.v2_0.MessageNotificationType getMessageNotification(int i) {
        return this.messageNotification[i];
    }

    public void setMessageNotification(int i, org.csapi.www.schema.common.v2_0.MessageNotificationType _value) {
        this.messageNotification[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StartNotificationRequest)) return false;
        StartNotificationRequest other = (StartNotificationRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.applicationId==null && other.getApplicationId()==null) || 
             (this.applicationId!=null &&
              this.applicationId.equals(other.getApplicationId()))) &&
            ((this.messageNotification==null && other.getMessageNotification()==null) || 
             (this.messageNotification!=null &&
              java.util.Arrays.equals(this.messageNotification, other.getMessageNotification())));
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
        if (getApplicationId() != null) {
            _hashCode += getApplicationId().hashCode();
        }
        if (getMessageNotification() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMessageNotification());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMessageNotification(), i);
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
        new org.apache.axis.description.TypeDesc(StartNotificationRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/notification", ">startNotificationRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ApplicationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageNotification");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MessageNotification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "MessageNotificationType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
