/**
 * LocationData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.location;

@SuppressWarnings({"serial","unused","rawtypes"})public class LocationData  implements java.io.Serializable {
    private org.apache.axis.types.URI address;

    private org.csapi.www.schema.location.RetrievalStatus reportStatus;

    private org.csapi.www.schema.location.LocationInfo currentLocation;

    private org.csapi.www.schema.common.v2_0.ServiceError errorInformation;

    public LocationData() {
    }

    public LocationData(
           org.apache.axis.types.URI address,
           org.csapi.www.schema.location.RetrievalStatus reportStatus,
           org.csapi.www.schema.location.LocationInfo currentLocation,
           org.csapi.www.schema.common.v2_0.ServiceError errorInformation) {
           this.address = address;
           this.reportStatus = reportStatus;
           this.currentLocation = currentLocation;
           this.errorInformation = errorInformation;
    }


    /**
     * Gets the address value for this LocationData.
     * 
     * @return address
     */
    public org.apache.axis.types.URI getAddress() {
        return address;
    }


    /**
     * Sets the address value for this LocationData.
     * 
     * @param address
     */
    public void setAddress(org.apache.axis.types.URI address) {
        this.address = address;
    }


    /**
     * Gets the reportStatus value for this LocationData.
     * 
     * @return reportStatus
     */
    public org.csapi.www.schema.location.RetrievalStatus getReportStatus() {
        return reportStatus;
    }


    /**
     * Sets the reportStatus value for this LocationData.
     * 
     * @param reportStatus
     */
    public void setReportStatus(org.csapi.www.schema.location.RetrievalStatus reportStatus) {
        this.reportStatus = reportStatus;
    }


    /**
     * Gets the currentLocation value for this LocationData.
     * 
     * @return currentLocation
     */
    public org.csapi.www.schema.location.LocationInfo getCurrentLocation() {
        return currentLocation;
    }


    /**
     * Sets the currentLocation value for this LocationData.
     * 
     * @param currentLocation
     */
    public void setCurrentLocation(org.csapi.www.schema.location.LocationInfo currentLocation) {
        this.currentLocation = currentLocation;
    }


    /**
     * Gets the errorInformation value for this LocationData.
     * 
     * @return errorInformation
     */
    public org.csapi.www.schema.common.v2_0.ServiceError getErrorInformation() {
        return errorInformation;
    }


    /**
     * Sets the errorInformation value for this LocationData.
     * 
     * @param errorInformation
     */
    public void setErrorInformation(org.csapi.www.schema.common.v2_0.ServiceError errorInformation) {
        this.errorInformation = errorInformation;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LocationData)) return false;
        LocationData other = (LocationData) obj;
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
            ((this.reportStatus==null && other.getReportStatus()==null) || 
             (this.reportStatus!=null &&
              this.reportStatus.equals(other.getReportStatus()))) &&
            ((this.currentLocation==null && other.getCurrentLocation()==null) || 
             (this.currentLocation!=null &&
              this.currentLocation.equals(other.getCurrentLocation()))) &&
            ((this.errorInformation==null && other.getErrorInformation()==null) || 
             (this.errorInformation!=null &&
              this.errorInformation.equals(other.getErrorInformation())));
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
        if (getReportStatus() != null) {
            _hashCode += getReportStatus().hashCode();
        }
        if (getCurrentLocation() != null) {
            _hashCode += getCurrentLocation().hashCode();
        }
        if (getErrorInformation() != null) {
            _hashCode += getErrorInformation().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LocationData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "LocationData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReportStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "RetrievalStatus"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentLocation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CurrentLocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "LocationInfo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ErrorInformation"));
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
