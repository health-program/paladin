/**
 * APRegistrationReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.ap;

@SuppressWarnings({"serial","unused","rawtypes"})public class APRegistrationReq  implements java.io.Serializable {
    private java.lang.String apid;

    private int APPid;

    private java.lang.String hostIP;

    private org.csapi.www.schema.common.v2_0.MessageNotificationType[] messageNotification;

    private org.apache.axis.types.URI APWSURI;

    public APRegistrationReq() {
    }

    public APRegistrationReq(
           java.lang.String apid,
           int APPid,
           java.lang.String hostIP,
           org.csapi.www.schema.common.v2_0.MessageNotificationType[] messageNotification,
           org.apache.axis.types.URI APWSURI) {
           this.apid = apid;
           this.APPid = APPid;
           this.hostIP = hostIP;
           this.messageNotification = messageNotification;
           this.APWSURI = APWSURI;
    }


    /**
     * Gets the apid value for this APRegistrationReq.
     * 
     * @return apid
     */
    public java.lang.String getApid() {
        return apid;
    }


    /**
     * Sets the apid value for this APRegistrationReq.
     * 
     * @param apid
     */
    public void setApid(java.lang.String apid) {
        this.apid = apid;
    }


    /**
     * Gets the APPid value for this APRegistrationReq.
     * 
     * @return APPid
     */
    public int getAPPid() {
        return APPid;
    }


    /**
     * Sets the APPid value for this APRegistrationReq.
     * 
     * @param APPid
     */
    public void setAPPid(int APPid) {
        this.APPid = APPid;
    }


    /**
     * Gets the hostIP value for this APRegistrationReq.
     * 
     * @return hostIP
     */
    public java.lang.String getHostIP() {
        return hostIP;
    }


    /**
     * Sets the hostIP value for this APRegistrationReq.
     * 
     * @param hostIP
     */
    public void setHostIP(java.lang.String hostIP) {
        this.hostIP = hostIP;
    }


    /**
     * Gets the messageNotification value for this APRegistrationReq.
     * 
     * @return messageNotification
     */
    public org.csapi.www.schema.common.v2_0.MessageNotificationType[] getMessageNotification() {
        return messageNotification;
    }


    /**
     * Sets the messageNotification value for this APRegistrationReq.
     * 
     * @param messageNotification
     */
    public void setMessageNotification(org.csapi.www.schema.common.v2_0.MessageNotificationType[] messageNotification) {
        this.messageNotification = messageNotification;
    }

    public org.csapi.www.schema.common.v2_0.MessageNotificationType getMessageNotification(int i) {
        return this.messageNotification[i];
    }

    public void setMessageNotification(int i, org.csapi.www.schema.common.v2_0.MessageNotificationType _value) {
        this.messageNotification[i] = _value;
    }


    /**
     * Gets the APWSURI value for this APRegistrationReq.
     * 
     * @return APWSURI
     */
    public org.apache.axis.types.URI getAPWSURI() {
        return APWSURI;
    }


    /**
     * Sets the APWSURI value for this APRegistrationReq.
     * 
     * @param APWSURI
     */
    public void setAPWSURI(org.apache.axis.types.URI APWSURI) {
        this.APWSURI = APWSURI;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof APRegistrationReq)) return false;
        APRegistrationReq other = (APRegistrationReq) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.apid==null && other.getApid()==null) || 
             (this.apid!=null &&
              this.apid.equals(other.getApid()))) &&
            this.APPid == other.getAPPid() &&
            ((this.hostIP==null && other.getHostIP()==null) || 
             (this.hostIP!=null &&
              this.hostIP.equals(other.getHostIP()))) &&
            ((this.messageNotification==null && other.getMessageNotification()==null) || 
             (this.messageNotification!=null &&
              java.util.Arrays.equals(this.messageNotification, other.getMessageNotification()))) &&
            ((this.APWSURI==null && other.getAPWSURI()==null) || 
             (this.APWSURI!=null &&
              this.APWSURI.equals(other.getAPWSURI())));
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
        if (getApid() != null) {
            _hashCode += getApid().hashCode();
        }
        _hashCode += getAPPid();
        if (getHostIP() != null) {
            _hashCode += getHostIP().hashCode();
        }
        if (getMessageNotification() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMessageNotification());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMessageNotification(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAPWSURI() != null) {
            _hashCode += getAPWSURI().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(APRegistrationReq.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APRegistrationReq"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Apid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("APPid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "APPid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hostIP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "HostIP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageNotification");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MessageNotification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "MessageNotificationType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("APWSURI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "APWSURI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
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
