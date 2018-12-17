/**
 * APRegistrationRsp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.ap;

@SuppressWarnings({"serial","unused","rawtypes"})public class APRegistrationRsp  implements java.io.Serializable {
    private org.csapi.www.schema.ap.APRegResult regResult;

    private int nextInterval;

    public APRegistrationRsp() {
    }

    public APRegistrationRsp(
           org.csapi.www.schema.ap.APRegResult regResult,
           int nextInterval) {
           this.regResult = regResult;
           this.nextInterval = nextInterval;
    }


    /**
     * Gets the regResult value for this APRegistrationRsp.
     * 
     * @return regResult
     */
    public org.csapi.www.schema.ap.APRegResult getRegResult() {
        return regResult;
    }


    /**
     * Sets the regResult value for this APRegistrationRsp.
     * 
     * @param regResult
     */
    public void setRegResult(org.csapi.www.schema.ap.APRegResult regResult) {
        this.regResult = regResult;
    }


    /**
     * Gets the nextInterval value for this APRegistrationRsp.
     * 
     * @return nextInterval
     */
    public int getNextInterval() {
        return nextInterval;
    }


    /**
     * Sets the nextInterval value for this APRegistrationRsp.
     * 
     * @param nextInterval
     */
    public void setNextInterval(int nextInterval) {
        this.nextInterval = nextInterval;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof APRegistrationRsp)) return false;
        APRegistrationRsp other = (APRegistrationRsp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.regResult==null && other.getRegResult()==null) || 
             (this.regResult!=null &&
              this.regResult.equals(other.getRegResult()))) &&
            this.nextInterval == other.getNextInterval();
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
        if (getRegResult() != null) {
            _hashCode += getRegResult().hashCode();
        }
        _hashCode += getNextInterval();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(APRegistrationRsp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APRegistrationRsp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regResult");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RegResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "APRegResult"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nextInterval");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NextInterval"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
