/**
 * APStatusRepReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.ap;

@SuppressWarnings({"serial","unused","rawtypes"})public class APStatusRepReq  implements java.io.Serializable {
    private java.lang.String APid;

    private org.csapi.www.schema.ap.APStatusType APStatus;

    private int APPid;

    public APStatusRepReq() {
    }

    public APStatusRepReq(
           java.lang.String APid,
           org.csapi.www.schema.ap.APStatusType APStatus,
           int APPid) {
           this.APid = APid;
           this.APStatus = APStatus;
           this.APPid = APPid;
    }


    /**
     * Gets the APid value for this APStatusRepReq.
     * 
     * @return APid
     */
    public java.lang.String getAPid() {
        return APid;
    }


    /**
     * Sets the APid value for this APStatusRepReq.
     * 
     * @param APid
     */
    public void setAPid(java.lang.String APid) {
        this.APid = APid;
    }


    /**
     * Gets the APStatus value for this APStatusRepReq.
     * 
     * @return APStatus
     */
    public org.csapi.www.schema.ap.APStatusType getAPStatus() {
        return APStatus;
    }


    /**
     * Sets the APStatus value for this APStatusRepReq.
     * 
     * @param APStatus
     */
    public void setAPStatus(org.csapi.www.schema.ap.APStatusType APStatus) {
        this.APStatus = APStatus;
    }


    /**
     * Gets the APPid value for this APStatusRepReq.
     * 
     * @return APPid
     */
    public int getAPPid() {
        return APPid;
    }


    /**
     * Sets the APPid value for this APStatusRepReq.
     * 
     * @param APPid
     */
    public void setAPPid(int APPid) {
        this.APPid = APPid;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof APStatusRepReq)) return false;
        APStatusRepReq other = (APStatusRepReq) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.APid==null && other.getAPid()==null) || 
             (this.APid!=null &&
              this.APid.equals(other.getAPid()))) &&
            ((this.APStatus==null && other.getAPStatus()==null) || 
             (this.APStatus!=null &&
              this.APStatus.equals(other.getAPStatus()))) &&
            this.APPid == other.getAPPid();
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
        if (getAPid() != null) {
            _hashCode += getAPid().hashCode();
        }
        if (getAPStatus() != null) {
            _hashCode += getAPStatus().hashCode();
        }
        _hashCode += getAPPid();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(APStatusRepReq.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APStatusRepReq"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("APid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "APid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("APStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "APStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "APStatusType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("APPid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "APPid"));
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
