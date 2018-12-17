/**
 * NotifySmsDeliveryStatusRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.sms;

@SuppressWarnings({"serial","unused","rawtypes"})public class NotifySmsDeliveryStatusRequest  implements java.io.Serializable {
    private java.lang.String requestIdentifier;

    private org.csapi.www.schema.sms.DeliveryInformation[] deliveryInformation;

    public NotifySmsDeliveryStatusRequest() {
    }

    public NotifySmsDeliveryStatusRequest(
           java.lang.String requestIdentifier,
           org.csapi.www.schema.sms.DeliveryInformation[] deliveryInformation) {
           this.requestIdentifier = requestIdentifier;
           this.deliveryInformation = deliveryInformation;
    }


    /**
     * Gets the requestIdentifier value for this NotifySmsDeliveryStatusRequest.
     * 
     * @return requestIdentifier
     */
    public java.lang.String getRequestIdentifier() {
        return requestIdentifier;
    }


    /**
     * Sets the requestIdentifier value for this NotifySmsDeliveryStatusRequest.
     * 
     * @param requestIdentifier
     */
    public void setRequestIdentifier(java.lang.String requestIdentifier) {
        this.requestIdentifier = requestIdentifier;
    }


    /**
     * Gets the deliveryInformation value for this NotifySmsDeliveryStatusRequest.
     * 
     * @return deliveryInformation
     */
    public org.csapi.www.schema.sms.DeliveryInformation[] getDeliveryInformation() {
        return deliveryInformation;
    }


    /**
     * Sets the deliveryInformation value for this NotifySmsDeliveryStatusRequest.
     * 
     * @param deliveryInformation
     */
    public void setDeliveryInformation(org.csapi.www.schema.sms.DeliveryInformation[] deliveryInformation) {
        this.deliveryInformation = deliveryInformation;
    }

    public org.csapi.www.schema.sms.DeliveryInformation getDeliveryInformation(int i) {
        return this.deliveryInformation[i];
    }

    public void setDeliveryInformation(int i, org.csapi.www.schema.sms.DeliveryInformation _value) {
        this.deliveryInformation[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NotifySmsDeliveryStatusRequest)) return false;
        NotifySmsDeliveryStatusRequest other = (NotifySmsDeliveryStatusRequest) obj;
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
            ((this.deliveryInformation==null && other.getDeliveryInformation()==null) || 
             (this.deliveryInformation!=null &&
              java.util.Arrays.equals(this.deliveryInformation, other.getDeliveryInformation())));
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
        if (getDeliveryInformation() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDeliveryInformation());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDeliveryInformation(), i);
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
        new org.apache.axis.description.TypeDesc(NotifySmsDeliveryStatusRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", ">notifySmsDeliveryStatusRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestIdentifier");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequestIdentifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeliveryInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "DeliveryInformation"));
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
