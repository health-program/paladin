/**
 * NotifyMessageDeliveryReceiptRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.mms;

@SuppressWarnings({"serial","unused","rawtypes"})public class NotifyMessageDeliveryReceiptRequest  implements java.io.Serializable {
    private java.lang.String correlator;

    private org.csapi.www.schema.mms.DeliveryInformation[] deliveryStatus;

    public NotifyMessageDeliveryReceiptRequest() {
    }

    public NotifyMessageDeliveryReceiptRequest(
           java.lang.String correlator,
           org.csapi.www.schema.mms.DeliveryInformation[] deliveryStatus) {
           this.correlator = correlator;
           this.deliveryStatus = deliveryStatus;
    }


    /**
     * Gets the correlator value for this NotifyMessageDeliveryReceiptRequest.
     * 
     * @return correlator
     */
    public java.lang.String getCorrelator() {
        return correlator;
    }


    /**
     * Sets the correlator value for this NotifyMessageDeliveryReceiptRequest.
     * 
     * @param correlator
     */
    public void setCorrelator(java.lang.String correlator) {
        this.correlator = correlator;
    }


    /**
     * Gets the deliveryStatus value for this NotifyMessageDeliveryReceiptRequest.
     * 
     * @return deliveryStatus
     */
    public org.csapi.www.schema.mms.DeliveryInformation[] getDeliveryStatus() {
        return deliveryStatus;
    }


    /**
     * Sets the deliveryStatus value for this NotifyMessageDeliveryReceiptRequest.
     * 
     * @param deliveryStatus
     */
    public void setDeliveryStatus(org.csapi.www.schema.mms.DeliveryInformation[] deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public org.csapi.www.schema.mms.DeliveryInformation getDeliveryStatus(int i) {
        return this.deliveryStatus[i];
    }

    public void setDeliveryStatus(int i, org.csapi.www.schema.mms.DeliveryInformation _value) {
        this.deliveryStatus[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NotifyMessageDeliveryReceiptRequest)) return false;
        NotifyMessageDeliveryReceiptRequest other = (NotifyMessageDeliveryReceiptRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.correlator==null && other.getCorrelator()==null) || 
             (this.correlator!=null &&
              this.correlator.equals(other.getCorrelator()))) &&
            ((this.deliveryStatus==null && other.getDeliveryStatus()==null) || 
             (this.deliveryStatus!=null &&
              java.util.Arrays.equals(this.deliveryStatus, other.getDeliveryStatus())));
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
        if (getCorrelator() != null) {
            _hashCode += getCorrelator().hashCode();
        }
        if (getDeliveryStatus() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDeliveryStatus());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDeliveryStatus(), i);
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
        new org.apache.axis.description.TypeDesc(NotifyMessageDeliveryReceiptRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">notifyMessageDeliveryReceiptRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("correlator");
        elemField.setXmlName(new javax.xml.namespace.QName("", "correlator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deliveryStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "DeliveryInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
