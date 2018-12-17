/**
 * AlarmReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.ap;

@SuppressWarnings({"serial","unused","rawtypes"})public class AlarmReq  implements java.io.Serializable {
    private java.lang.String alarmId;

    private int orgseverity;

    private int orgtype;

    private java.lang.String probablecause;

    private java.lang.String eventTime;

    private java.lang.String ackTime;

    private java.lang.String clearTime;

    private int activestatus;

    private java.lang.String alarmtitle;

    private java.lang.String alarmText;

    public AlarmReq() {
    }

    public AlarmReq(
           java.lang.String alarmId,
           int orgseverity,
           int orgtype,
           java.lang.String probablecause,
           java.lang.String eventTime,
           java.lang.String ackTime,
           java.lang.String clearTime,
           int activestatus,
           java.lang.String alarmtitle,
           java.lang.String alarmText) {
           this.alarmId = alarmId;
           this.orgseverity = orgseverity;
           this.orgtype = orgtype;
           this.probablecause = probablecause;
           this.eventTime = eventTime;
           this.ackTime = ackTime;
           this.clearTime = clearTime;
           this.activestatus = activestatus;
           this.alarmtitle = alarmtitle;
           this.alarmText = alarmText;
    }


    /**
     * Gets the alarmId value for this AlarmReq.
     * 
     * @return alarmId
     */
    public java.lang.String getAlarmId() {
        return alarmId;
    }


    /**
     * Sets the alarmId value for this AlarmReq.
     * 
     * @param alarmId
     */
    public void setAlarmId(java.lang.String alarmId) {
        this.alarmId = alarmId;
    }


    /**
     * Gets the orgseverity value for this AlarmReq.
     * 
     * @return orgseverity
     */
    public int getOrgseverity() {
        return orgseverity;
    }


    /**
     * Sets the orgseverity value for this AlarmReq.
     * 
     * @param orgseverity
     */
    public void setOrgseverity(int orgseverity) {
        this.orgseverity = orgseverity;
    }


    /**
     * Gets the orgtype value for this AlarmReq.
     * 
     * @return orgtype
     */
    public int getOrgtype() {
        return orgtype;
    }


    /**
     * Sets the orgtype value for this AlarmReq.
     * 
     * @param orgtype
     */
    public void setOrgtype(int orgtype) {
        this.orgtype = orgtype;
    }


    /**
     * Gets the probablecause value for this AlarmReq.
     * 
     * @return probablecause
     */
    public java.lang.String getProbablecause() {
        return probablecause;
    }


    /**
     * Sets the probablecause value for this AlarmReq.
     * 
     * @param probablecause
     */
    public void setProbablecause(java.lang.String probablecause) {
        this.probablecause = probablecause;
    }


    /**
     * Gets the eventTime value for this AlarmReq.
     * 
     * @return eventTime
     */
    public java.lang.String getEventTime() {
        return eventTime;
    }


    /**
     * Sets the eventTime value for this AlarmReq.
     * 
     * @param eventTime
     */
    public void setEventTime(java.lang.String eventTime) {
        this.eventTime = eventTime;
    }


    /**
     * Gets the ackTime value for this AlarmReq.
     * 
     * @return ackTime
     */
    public java.lang.String getAckTime() {
        return ackTime;
    }


    /**
     * Sets the ackTime value for this AlarmReq.
     * 
     * @param ackTime
     */
    public void setAckTime(java.lang.String ackTime) {
        this.ackTime = ackTime;
    }


    /**
     * Gets the clearTime value for this AlarmReq.
     * 
     * @return clearTime
     */
    public java.lang.String getClearTime() {
        return clearTime;
    }


    /**
     * Sets the clearTime value for this AlarmReq.
     * 
     * @param clearTime
     */
    public void setClearTime(java.lang.String clearTime) {
        this.clearTime = clearTime;
    }


    /**
     * Gets the activestatus value for this AlarmReq.
     * 
     * @return activestatus
     */
    public int getActivestatus() {
        return activestatus;
    }


    /**
     * Sets the activestatus value for this AlarmReq.
     * 
     * @param activestatus
     */
    public void setActivestatus(int activestatus) {
        this.activestatus = activestatus;
    }


    /**
     * Gets the alarmtitle value for this AlarmReq.
     * 
     * @return alarmtitle
     */
    public java.lang.String getAlarmtitle() {
        return alarmtitle;
    }


    /**
     * Sets the alarmtitle value for this AlarmReq.
     * 
     * @param alarmtitle
     */
    public void setAlarmtitle(java.lang.String alarmtitle) {
        this.alarmtitle = alarmtitle;
    }


    /**
     * Gets the alarmText value for this AlarmReq.
     * 
     * @return alarmText
     */
    public java.lang.String getAlarmText() {
        return alarmText;
    }


    /**
     * Sets the alarmText value for this AlarmReq.
     * 
     * @param alarmText
     */
    public void setAlarmText(java.lang.String alarmText) {
        this.alarmText = alarmText;
    }

    private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlarmReq)) return false;
        AlarmReq other = (AlarmReq) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.alarmId==null && other.getAlarmId()==null) || 
             (this.alarmId!=null &&
              this.alarmId.equals(other.getAlarmId()))) &&
            this.orgseverity == other.getOrgseverity() &&
            this.orgtype == other.getOrgtype() &&
            ((this.probablecause==null && other.getProbablecause()==null) || 
             (this.probablecause!=null &&
              this.probablecause.equals(other.getProbablecause()))) &&
            ((this.eventTime==null && other.getEventTime()==null) || 
             (this.eventTime!=null &&
              this.eventTime.equals(other.getEventTime()))) &&
            ((this.ackTime==null && other.getAckTime()==null) || 
             (this.ackTime!=null &&
              this.ackTime.equals(other.getAckTime()))) &&
            ((this.clearTime==null && other.getClearTime()==null) || 
             (this.clearTime!=null &&
              this.clearTime.equals(other.getClearTime()))) &&
            this.activestatus == other.getActivestatus() &&
            ((this.alarmtitle==null && other.getAlarmtitle()==null) || 
             (this.alarmtitle!=null &&
              this.alarmtitle.equals(other.getAlarmtitle()))) &&
            ((this.alarmText==null && other.getAlarmText()==null) || 
             (this.alarmText!=null &&
              this.alarmText.equals(other.getAlarmText())));
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
        if (getAlarmId() != null) {
            _hashCode += getAlarmId().hashCode();
        }
        _hashCode += getOrgseverity();
        _hashCode += getOrgtype();
        if (getProbablecause() != null) {
            _hashCode += getProbablecause().hashCode();
        }
        if (getEventTime() != null) {
            _hashCode += getEventTime().hashCode();
        }
        if (getAckTime() != null) {
            _hashCode += getAckTime().hashCode();
        }
        if (getClearTime() != null) {
            _hashCode += getClearTime().hashCode();
        }
        _hashCode += getActivestatus();
        if (getAlarmtitle() != null) {
            _hashCode += getAlarmtitle().hashCode();
        }
        if (getAlarmText() != null) {
            _hashCode += getAlarmText().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlarmReq.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">AlarmReq"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alarmId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "alarmId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orgseverity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orgseverity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orgtype");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orgtype"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("probablecause");
        elemField.setXmlName(new javax.xml.namespace.QName("", "probablecause"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eventTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "eventTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ackTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ackTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clearTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clearTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activestatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "activestatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alarmtitle");
        elemField.setXmlName(new javax.xml.namespace.QName("", "alarmtitle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alarmText");
        elemField.setXmlName(new javax.xml.namespace.QName("", "alarmText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
