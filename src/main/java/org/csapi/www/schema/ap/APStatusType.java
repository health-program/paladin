/**
 * APStatusType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.ap;

@SuppressWarnings({"serial","unchecked","rawtypes"})public class APStatusType implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected APStatusType(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Normal = "Normal";
    public static final java.lang.String _OutofActiveTime = "OutofActiveTime";
    public static final java.lang.String _NeedRegistration = "NeedRegistration";
    public static final java.lang.String _OutofService = "OutofService";
    public static final java.lang.String _Paused = "Paused";
    public static final java.lang.String _Closed = "Closed";
    public static final java.lang.String _WaitingforConfirm = "WaitingforConfirm";
    public static final APStatusType Normal = new APStatusType(_Normal);
    public static final APStatusType OutofActiveTime = new APStatusType(_OutofActiveTime);
    public static final APStatusType NeedRegistration = new APStatusType(_NeedRegistration);
    public static final APStatusType OutofService = new APStatusType(_OutofService);
    public static final APStatusType Paused = new APStatusType(_Paused);
    public static final APStatusType Closed = new APStatusType(_Closed);
    public static final APStatusType WaitingforConfirm = new APStatusType(_WaitingforConfirm);
    public java.lang.String getValue() { return _value_;}
    public static APStatusType fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        APStatusType enumeration = (APStatusType)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static APStatusType fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(APStatusType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "APStatusType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
