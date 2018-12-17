/**
 * MessageNotificationType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.common.v2_0;

@SuppressWarnings({"serial","unused","rawtypes"})public class MessageNotificationType  implements java.io.Serializable {
    private org.csapi.www.schema.common.v2_0.CMAbility CMAbility;

    private org.apache.axis.types.URI[] WSURI;

    public MessageNotificationType() {
    }

    public MessageNotificationType(
           org.csapi.www.schema.common.v2_0.CMAbility CMAbility,
           org.apache.axis.types.URI[] WSURI) {
           this.CMAbility = CMAbility;
           this.WSURI = WSURI;
    }


    /**
     * Gets the CMAbility value for this MessageNotificationType.
     * 
     * @return CMAbility
     */
    public org.csapi.www.schema.common.v2_0.CMAbility getCMAbility() {
        return CMAbility;
    }


    /**
     * Sets the CMAbility value for this MessageNotificationType.
     * 
     * @param CMAbility
     */
    public void setCMAbility(org.csapi.www.schema.common.v2_0.CMAbility CMAbility) {
        this.CMAbility = CMAbility;
    }


    /**
     * Gets the WSURI value for this MessageNotificationType.
     * 
     * @return WSURI
     */
    public org.apache.axis.types.URI[] getWSURI() {
        return WSURI;
    }


    /**
     * Sets the WSURI value for this MessageNotificationType.
     * 
     * @param WSURI
     */
    public void setWSURI(org.apache.axis.types.URI[] WSURI) {
        this.WSURI = WSURI;
    }

    public org.apache.axis.types.URI getWSURI(int i) {
        return this.WSURI[i];
    }

    public void setWSURI(int i, org.apache.axis.types.URI _value) {
        this.WSURI[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MessageNotificationType)) return false;
        MessageNotificationType other = (MessageNotificationType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CMAbility==null && other.getCMAbility()==null) || 
             (this.CMAbility!=null &&
              this.CMAbility.equals(other.getCMAbility()))) &&
            ((this.WSURI==null && other.getWSURI()==null) || 
             (this.WSURI!=null &&
              java.util.Arrays.equals(this.WSURI, other.getWSURI())));
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
        if (getCMAbility() != null) {
            _hashCode += getCMAbility().hashCode();
        }
        if (getWSURI() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getWSURI());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getWSURI(), i);
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
        new org.apache.axis.description.TypeDesc(MessageNotificationType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "MessageNotificationType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CMAbility");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CMAbility"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "CMAbility"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("WSURI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "WSURI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
