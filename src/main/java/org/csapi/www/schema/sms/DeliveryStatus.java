/**
 * DeliveryStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.sms;

@SuppressWarnings({"serial","unchecked","rawtypes"})public class DeliveryStatus implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected DeliveryStatus(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Delivered = "Delivered";
    public static final java.lang.String _DeliveryUncertain = "DeliveryUncertain";
    public static final java.lang.String _DeliveryImpossible = "DeliveryImpossible";
    public static final java.lang.String _MessageWaiting = "MessageWaiting";
    public static final java.lang.String _DeliveryToTerminal = "DeliveryToTerminal";
    public static final java.lang.String _DeliveryNotificationNotSupported = "DeliveryNotificationNotSupported";
    public static final java.lang.String _KeyWordFilterFailed = "KeyWordFilterFailed";
    public static final DeliveryStatus Delivered = new DeliveryStatus(_Delivered);
    public static final DeliveryStatus DeliveryUncertain = new DeliveryStatus(_DeliveryUncertain);
    public static final DeliveryStatus DeliveryImpossible = new DeliveryStatus(_DeliveryImpossible);
    public static final DeliveryStatus MessageWaiting = new DeliveryStatus(_MessageWaiting);
    public static final DeliveryStatus DeliveryToTerminal = new DeliveryStatus(_DeliveryToTerminal);
    public static final DeliveryStatus DeliveryNotificationNotSupported = new DeliveryStatus(_DeliveryNotificationNotSupported);
    public static final DeliveryStatus KeyWordFilterFailed = new DeliveryStatus(_KeyWordFilterFailed);
    public java.lang.String getValue() { return _value_;}
    public static DeliveryStatus fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        DeliveryStatus enumeration = (DeliveryStatus)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static DeliveryStatus fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(DeliveryStatus.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "DeliveryStatus"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
