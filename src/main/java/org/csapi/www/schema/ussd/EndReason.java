/**
 * EndReason.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.ussd;

@SuppressWarnings({"serial","unchecked","rawtypes"})public class EndReason implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected EndReason(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _UserEnd = "UserEnd";
    public static final java.lang.String _Busy = "Busy";
    public static final java.lang.String _UserAbsent = "UserAbsent";
    public static final java.lang.String _IllegalEquipment = "IllegalEquipment";
    public static final java.lang.String _SystemError = "SystemError";
    public static final java.lang.String _TimeOut = "TimeOut";
    public static final EndReason UserEnd = new EndReason(_UserEnd);
    public static final EndReason Busy = new EndReason(_Busy);
    public static final EndReason UserAbsent = new EndReason(_UserAbsent);
    public static final EndReason IllegalEquipment = new EndReason(_IllegalEquipment);
    public static final EndReason SystemError = new EndReason(_SystemError);
    public static final EndReason TimeOut = new EndReason(_TimeOut);
    public java.lang.String getValue() { return _value_;}
    public static EndReason fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        EndReason enumeration = (EndReason)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static EndReason fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(EndReason.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", "EndReason"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
