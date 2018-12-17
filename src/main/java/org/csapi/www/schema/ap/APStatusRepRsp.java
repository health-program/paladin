/**
 * APStatusRepRsp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.ap;

@SuppressWarnings({"serial","unused","rawtypes"})public class APStatusRepRsp  implements java.io.Serializable {
    private java.lang.String nextCommand;

    private int nextInterval;

    private java.lang.String[] apSvcAuthType;

    private java.lang.String[] apSvcPerfCmdType;

    public APStatusRepRsp() {
    }

    public APStatusRepRsp(
           java.lang.String nextCommand,
           int nextInterval,
           java.lang.String[] apSvcAuthType,
           java.lang.String[] apSvcPerfCmdType) {
           this.nextCommand = nextCommand;
           this.nextInterval = nextInterval;
           this.apSvcAuthType = apSvcAuthType;
           this.apSvcPerfCmdType = apSvcPerfCmdType;
    }


    /**
     * Gets the nextCommand value for this APStatusRepRsp.
     * 
     * @return nextCommand
     */
    public java.lang.String getNextCommand() {
        return nextCommand;
    }


    /**
     * Sets the nextCommand value for this APStatusRepRsp.
     * 
     * @param nextCommand
     */
    public void setNextCommand(java.lang.String nextCommand) {
        this.nextCommand = nextCommand;
    }


    /**
     * Gets the nextInterval value for this APStatusRepRsp.
     * 
     * @return nextInterval
     */
    public int getNextInterval() {
        return nextInterval;
    }


    /**
     * Sets the nextInterval value for this APStatusRepRsp.
     * 
     * @param nextInterval
     */
    public void setNextInterval(int nextInterval) {
        this.nextInterval = nextInterval;
    }


    /**
     * Gets the apSvcAuthType value for this APStatusRepRsp.
     * 
     * @return apSvcAuthType
     */
    public java.lang.String[] getApSvcAuthType() {
        return apSvcAuthType;
    }


    /**
     * Sets the apSvcAuthType value for this APStatusRepRsp.
     * 
     * @param apSvcAuthType
     */
    public void setApSvcAuthType(java.lang.String[] apSvcAuthType) {
        this.apSvcAuthType = apSvcAuthType;
    }

    public java.lang.String getApSvcAuthType(int i) {
        return this.apSvcAuthType[i];
    }

    public void setApSvcAuthType(int i, java.lang.String _value) {
        this.apSvcAuthType[i] = _value;
    }


    /**
     * Gets the apSvcPerfCmdType value for this APStatusRepRsp.
     * 
     * @return apSvcPerfCmdType
     */
    public java.lang.String[] getApSvcPerfCmdType() {
        return apSvcPerfCmdType;
    }


    /**
     * Sets the apSvcPerfCmdType value for this APStatusRepRsp.
     * 
     * @param apSvcPerfCmdType
     */
    public void setApSvcPerfCmdType(java.lang.String[] apSvcPerfCmdType) {
        this.apSvcPerfCmdType = apSvcPerfCmdType;
    }

    public java.lang.String getApSvcPerfCmdType(int i) {
        return this.apSvcPerfCmdType[i];
    }

    public void setApSvcPerfCmdType(int i, java.lang.String _value) {
        this.apSvcPerfCmdType[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof APStatusRepRsp)) return false;
        APStatusRepRsp other = (APStatusRepRsp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nextCommand==null && other.getNextCommand()==null) || 
             (this.nextCommand!=null &&
              this.nextCommand.equals(other.getNextCommand()))) &&
            this.nextInterval == other.getNextInterval() &&
            ((this.apSvcAuthType==null && other.getApSvcAuthType()==null) || 
             (this.apSvcAuthType!=null &&
              java.util.Arrays.equals(this.apSvcAuthType, other.getApSvcAuthType()))) &&
            ((this.apSvcPerfCmdType==null && other.getApSvcPerfCmdType()==null) || 
             (this.apSvcPerfCmdType!=null &&
              java.util.Arrays.equals(this.apSvcPerfCmdType, other.getApSvcPerfCmdType())));
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
        if (getNextCommand() != null) {
            _hashCode += getNextCommand().hashCode();
        }
        _hashCode += getNextInterval();
        if (getApSvcAuthType() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getApSvcAuthType());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getApSvcAuthType(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getApSvcPerfCmdType() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getApSvcPerfCmdType());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getApSvcPerfCmdType(), i);
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
        new org.apache.axis.description.TypeDesc(APStatusRepRsp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APStatusRepRsp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nextCommand");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NextCommand"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nextInterval");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NextInterval"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apSvcAuthType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ApSvcAuthType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apSvcPerfCmdType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ApSvcPerfCmdType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
