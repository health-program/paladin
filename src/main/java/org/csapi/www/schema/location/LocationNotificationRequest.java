/**
 * LocationNotificationRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.location;

@SuppressWarnings({"serial","unused","rawtypes"})public class LocationNotificationRequest  implements java.io.Serializable {
    private java.lang.String correlator;

    private org.csapi.www.schema.location.LocationData[] data;

    public LocationNotificationRequest() {
    }

    public LocationNotificationRequest(
           java.lang.String correlator,
           org.csapi.www.schema.location.LocationData[] data) {
           this.correlator = correlator;
           this.data = data;
    }


    /**
     * Gets the correlator value for this LocationNotificationRequest.
     * 
     * @return correlator
     */
    public java.lang.String getCorrelator() {
        return correlator;
    }


    /**
     * Sets the correlator value for this LocationNotificationRequest.
     * 
     * @param correlator
     */
    public void setCorrelator(java.lang.String correlator) {
        this.correlator = correlator;
    }


    /**
     * Gets the data value for this LocationNotificationRequest.
     * 
     * @return data
     */
    public org.csapi.www.schema.location.LocationData[] getData() {
        return data;
    }


    /**
     * Sets the data value for this LocationNotificationRequest.
     * 
     * @param data
     */
    public void setData(org.csapi.www.schema.location.LocationData[] data) {
        this.data = data;
    }

    public org.csapi.www.schema.location.LocationData getData(int i) {
        return this.data[i];
    }

    public void setData(int i, org.csapi.www.schema.location.LocationData _value) {
        this.data[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LocationNotificationRequest)) return false;
        LocationNotificationRequest other = (LocationNotificationRequest) obj;
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
            ((this.data==null && other.getData()==null) || 
             (this.data!=null &&
              java.util.Arrays.equals(this.data, other.getData())));
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
        if (getData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getData(), i);
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
        new org.apache.axis.description.TypeDesc(LocationNotificationRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">LocationNotificationRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("correlator");
        elemField.setXmlName(new javax.xml.namespace.QName("", "correlator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("data");
        elemField.setXmlName(new javax.xml.namespace.QName("", "data"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "LocationData"));
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
