/**
 * UssdContinueResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.ussd;

@SuppressWarnings({"serial","unused","rawtypes"})public class UssdContinueResponse  implements java.io.Serializable {
    private org.csapi.www.schema.ussd.UssdArray returnMessage;

    public UssdContinueResponse() {
    }

    public UssdContinueResponse(
           org.csapi.www.schema.ussd.UssdArray returnMessage) {
           this.returnMessage = returnMessage;
    }


    /**
     * Gets the returnMessage value for this UssdContinueResponse.
     * 
     * @return returnMessage
     */
    public org.csapi.www.schema.ussd.UssdArray getReturnMessage() {
        return returnMessage;
    }


    /**
     * Sets the returnMessage value for this UssdContinueResponse.
     * 
     * @param returnMessage
     */
    public void setReturnMessage(org.csapi.www.schema.ussd.UssdArray returnMessage) {
        this.returnMessage = returnMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UssdContinueResponse)) return false;
        UssdContinueResponse other = (UssdContinueResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.returnMessage==null && other.getReturnMessage()==null) || 
             (this.returnMessage!=null &&
              this.returnMessage.equals(other.getReturnMessage())));
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
        if (getReturnMessage() != null) {
            _hashCode += getReturnMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UssdContinueResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">ussdContinueResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "returnMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", "UssdArray"));
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
