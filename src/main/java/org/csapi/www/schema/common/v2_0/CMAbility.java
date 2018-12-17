/**
 * CMAbility.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.common.v2_0;

@SuppressWarnings({"serial","unchecked","rawtypes"})public class CMAbility implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected CMAbility(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _SMSAbility = "SMSAbility";
    public static final java.lang.String _MMSAbility = "MMSAbility";
    public static final java.lang.String _WAPAbility = "WAPAbility";
    public static final java.lang.String _USSDAbility = "USSDAbility";
    public static final java.lang.String _LBSAbility = "LBSAbility";
    public static final java.lang.String _GPRSAbility = "GPRSAbility";
    public static final CMAbility SMSAbility = new CMAbility(_SMSAbility);
    public static final CMAbility MMSAbility = new CMAbility(_MMSAbility);
    public static final CMAbility WAPAbility = new CMAbility(_WAPAbility);
    public static final CMAbility USSDAbility = new CMAbility(_USSDAbility);
    public static final CMAbility LBSAbility = new CMAbility(_LBSAbility);
    public static final CMAbility GPRSAbility = new CMAbility(_GPRSAbility);
    public java.lang.String getValue() { return _value_;}
    public static CMAbility fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        CMAbility enumeration = (CMAbility)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static CMAbility fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(CMAbility.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "CMAbility"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
