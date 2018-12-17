/**
 * APRegResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.ap;

@SuppressWarnings({"serial","unchecked","rawtypes"})public class APRegResult implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected APRegResult(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _success = "success";
    public static final java.lang.String _illegalAP = "illegalAP";
    public static final java.lang.String _CMAbilityNotSup = "CMAbilityNotSup";
    public static final java.lang.String _repeatedReg = "repeatedReg";
    public static final java.lang.String _svcAddrMismatch = "svcAddrMismatch";
    public static final APRegResult success = new APRegResult(_success);
    public static final APRegResult illegalAP = new APRegResult(_illegalAP);
    public static final APRegResult CMAbilityNotSup = new APRegResult(_CMAbilityNotSup);
    public static final APRegResult repeatedReg = new APRegResult(_repeatedReg);
    public static final APRegResult svcAddrMismatch = new APRegResult(_svcAddrMismatch);
    public java.lang.String getValue() { return _value_;}
    public static APRegResult fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        APRegResult enumeration = (APRegResult)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static APRegResult fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(APRegResult.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "APRegResult"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
