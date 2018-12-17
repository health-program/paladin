/**
 * GetMessageResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.mms;

@SuppressWarnings({"serial","unused","rawtypes"})public class GetMessageResponse  implements java.io.Serializable {
    private org.csapi.www.schema.mms.MmsMessage mmsMessage;

    public GetMessageResponse() {
    }

    public GetMessageResponse(
           org.csapi.www.schema.mms.MmsMessage mmsMessage) {
           this.mmsMessage = mmsMessage;
    }


    /**
     * Gets the mmsMessage value for this GetMessageResponse.
     * 
     * @return mmsMessage
     */
    public org.csapi.www.schema.mms.MmsMessage getMmsMessage() {
        return mmsMessage;
    }


    /**
     * Sets the mmsMessage value for this GetMessageResponse.
     * 
     * @param mmsMessage
     */
    public void setMmsMessage(org.csapi.www.schema.mms.MmsMessage mmsMessage) {
        this.mmsMessage = mmsMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetMessageResponse)) return false;
        GetMessageResponse other = (GetMessageResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.mmsMessage==null && other.getMmsMessage()==null) || 
             (this.mmsMessage!=null &&
              this.mmsMessage.equals(other.getMmsMessage())));
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
        if (getMmsMessage() != null) {
            _hashCode += getMmsMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetMessageResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">getMessageResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mmsMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mmsMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "MmsMessage"));
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
