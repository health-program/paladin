/**
 * DeliveryInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.mms;

@SuppressWarnings({"serial","unused","rawtypes"})public class DeliveryInformation  implements java.io.Serializable {
    private org.apache.axis.types.URI address;

    private org.csapi.www.schema.mms.DeliveryStatus deliveryStatus;

    public DeliveryInformation() {
    }

    public DeliveryInformation(
           org.apache.axis.types.URI address,
           org.csapi.www.schema.mms.DeliveryStatus deliveryStatus) {
           this.address = address;
           this.deliveryStatus = deliveryStatus;
    }


    /**
     * Gets the address value for this DeliveryInformation.
     * 
     * @return address
     */
    public org.apache.axis.types.URI getAddress() {
        return address;
    }


    /**
     * Sets the address value for this DeliveryInformation.
     * 
     * @param address
     */
    public void setAddress(org.apache.axis.types.URI address) {
        this.address = address;
    }


    /**
     * Gets the deliveryStatus value for this DeliveryInformation.
     * 
     * @return deliveryStatus
     */
    public org.csapi.www.schema.mms.DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }


    /**
     * Sets the deliveryStatus value for this DeliveryInformation.
     * 
     * @param deliveryStatus
     */
    public void setDeliveryStatus(org.csapi.www.schema.mms.DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeliveryInformation)) return false;
        DeliveryInformation other = (DeliveryInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            ((this.deliveryStatus==null && other.getDeliveryStatus()==null) || 
             (this.deliveryStatus!=null &&
              this.deliveryStatus.equals(other.getDeliveryStatus())));
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
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        if (getDeliveryStatus() != null) {
            _hashCode += getDeliveryStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeliveryInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "DeliveryInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("", "address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deliveryStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "DeliveryStatus"));
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
