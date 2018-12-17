/**
 * NotifyPushDeliveryReceiptRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.wap;

@SuppressWarnings({"serial","unused","rawtypes"})public class NotifyPushDeliveryReceiptRequest  implements java.io.Serializable {
    private java.lang.String requestIdentifier;

    private org.csapi.www.schema.wap.DeliveryInformation[] deliveryStatus;

    public NotifyPushDeliveryReceiptRequest() {
    }

    public NotifyPushDeliveryReceiptRequest(
           java.lang.String requestIdentifier,
           org.csapi.www.schema.wap.DeliveryInformation[] deliveryStatus) {
           this.requestIdentifier = requestIdentifier;
           this.deliveryStatus = deliveryStatus;
    }


    /**
     * Gets the requestIdentifier value for this NotifyPushDeliveryReceiptRequest.
     * 
     * @return requestIdentifier
     */
    public java.lang.String getRequestIdentifier() {
        return requestIdentifier;
    }


    /**
     * Sets the requestIdentifier value for this NotifyPushDeliveryReceiptRequest.
     * 
     * @param requestIdentifier
     */
    public void setRequestIdentifier(java.lang.String requestIdentifier) {
        this.requestIdentifier = requestIdentifier;
    }


    /**
     * Gets the deliveryStatus value for this NotifyPushDeliveryReceiptRequest.
     * 
     * @return deliveryStatus
     */
    public org.csapi.www.schema.wap.DeliveryInformation[] getDeliveryStatus() {
        return deliveryStatus;
    }


    /**
     * Sets the deliveryStatus value for this NotifyPushDeliveryReceiptRequest.
     * 
     * @param deliveryStatus
     */
    public void setDeliveryStatus(org.csapi.www.schema.wap.DeliveryInformation[] deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public org.csapi.www.schema.wap.DeliveryInformation getDeliveryStatus(int i) {
        return this.deliveryStatus[i];
    }

    public void setDeliveryStatus(int i, org.csapi.www.schema.wap.DeliveryInformation _value) {
        this.deliveryStatus[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NotifyPushDeliveryReceiptRequest)) return false;
        NotifyPushDeliveryReceiptRequest other = (NotifyPushDeliveryReceiptRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.requestIdentifier==null && other.getRequestIdentifier()==null) || 
             (this.requestIdentifier!=null &&
              this.requestIdentifier.equals(other.getRequestIdentifier()))) &&
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
        if (getRequestIdentifier() != null) {
            _hashCode += getRequestIdentifier().hashCode();
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
        new org.apache.axis.description.TypeDesc(NotifyPushDeliveryReceiptRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", ">notifyPushDeliveryReceiptRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestIdentifier");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestIdentifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deliveryStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", "DeliveryInformation"));
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
