/**
 * LocationErrorRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.location;

@SuppressWarnings({"serial","unused","rawtypes"})public class LocationErrorRequest  implements java.io.Serializable {
    private java.lang.String correlator;

    private org.apache.axis.types.URI address;

    private org.csapi.www.schema.common.v2_0.ServiceError reason;

    public LocationErrorRequest() {
    }

    public LocationErrorRequest(
           java.lang.String correlator,
           org.apache.axis.types.URI address,
           org.csapi.www.schema.common.v2_0.ServiceError reason) {
           this.correlator = correlator;
           this.address = address;
           this.reason = reason;
    }


    /**
     * Gets the correlator value for this LocationErrorRequest.
     * 
     * @return correlator
     */
    public java.lang.String getCorrelator() {
        return correlator;
    }


    /**
     * Sets the correlator value for this LocationErrorRequest.
     * 
     * @param correlator
     */
    public void setCorrelator(java.lang.String correlator) {
        this.correlator = correlator;
    }


    /**
     * Gets the address value for this LocationErrorRequest.
     * 
     * @return address
     */
    public org.apache.axis.types.URI getAddress() {
        return address;
    }


    /**
     * Sets the address value for this LocationErrorRequest.
     * 
     * @param address
     */
    public void setAddress(org.apache.axis.types.URI address) {
        this.address = address;
    }


    /**
     * Gets the reason value for this LocationErrorRequest.
     * 
     * @return reason
     */
    public org.csapi.www.schema.common.v2_0.ServiceError getReason() {
        return reason;
    }


    /**
     * Sets the reason value for this LocationErrorRequest.
     * 
     * @param reason
     */
    public void setReason(org.csapi.www.schema.common.v2_0.ServiceError reason) {
        this.reason = reason;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LocationErrorRequest)) return false;
        LocationErrorRequest other = (LocationErrorRequest) obj;
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
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            ((this.reason==null && other.getReason()==null) || 
             (this.reason!=null &&
              this.reason.equals(other.getReason())));
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
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        if (getReason() != null) {
            _hashCode += getReason().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LocationErrorRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">LocationErrorRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("correlator");
        elemField.setXmlName(new javax.xml.namespace.QName("", "correlator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reason");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Reason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceError"));
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
