/**
 * StartPeriodicNotificationRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.location;

@SuppressWarnings({"serial","unused","rawtypes"})public class StartPeriodicNotificationRequest  implements java.io.Serializable {
    private org.apache.axis.types.URI requester;

    private org.apache.axis.types.URI[] addresses;

    private int requestedAccuracy;

    private org.csapi.www.schema.common.v2_0.TimeMetric frequency;

    private org.csapi.www.schema.common.v2_0.TimeMetric duration;

    private java.lang.String applicationId;

    private org.csapi.www.schema.location.ServiceType serviceType;

    private org.csapi.www.schema.location.CoordinateReferenceSystem crs;

    private org.csapi.www.schema.location.LocType locType;

    private org.csapi.www.schema.location.Priority prio;

    private java.lang.String eventNotification;

    public StartPeriodicNotificationRequest() {
    }

    public StartPeriodicNotificationRequest(
           org.apache.axis.types.URI requester,
           org.apache.axis.types.URI[] addresses,
           int requestedAccuracy,
           org.csapi.www.schema.common.v2_0.TimeMetric frequency,
           org.csapi.www.schema.common.v2_0.TimeMetric duration,
           java.lang.String applicationId,
           org.csapi.www.schema.location.ServiceType serviceType,
           org.csapi.www.schema.location.CoordinateReferenceSystem crs,
           org.csapi.www.schema.location.LocType locType,
           org.csapi.www.schema.location.Priority prio,
           java.lang.String eventNotification) {
           this.requester = requester;
           this.addresses = addresses;
           this.requestedAccuracy = requestedAccuracy;
           this.frequency = frequency;
           this.duration = duration;
           this.applicationId = applicationId;
           this.serviceType = serviceType;
           this.crs = crs;
           this.locType = locType;
           this.prio = prio;
           this.eventNotification = eventNotification;
    }


    /**
     * Gets the requester value for this StartPeriodicNotificationRequest.
     * 
     * @return requester
     */
    public org.apache.axis.types.URI getRequester() {
        return requester;
    }


    /**
     * Sets the requester value for this StartPeriodicNotificationRequest.
     * 
     * @param requester
     */
    public void setRequester(org.apache.axis.types.URI requester) {
        this.requester = requester;
    }


    /**
     * Gets the addresses value for this StartPeriodicNotificationRequest.
     * 
     * @return addresses
     */
    public org.apache.axis.types.URI[] getAddresses() {
        return addresses;
    }


    /**
     * Sets the addresses value for this StartPeriodicNotificationRequest.
     * 
     * @param addresses
     */
    public void setAddresses(org.apache.axis.types.URI[] addresses) {
        this.addresses = addresses;
    }

    public org.apache.axis.types.URI getAddresses(int i) {
        return this.addresses[i];
    }

    public void setAddresses(int i, org.apache.axis.types.URI _value) {
        this.addresses[i] = _value;
    }


    /**
     * Gets the requestedAccuracy value for this StartPeriodicNotificationRequest.
     * 
     * @return requestedAccuracy
     */
    public int getRequestedAccuracy() {
        return requestedAccuracy;
    }


    /**
     * Sets the requestedAccuracy value for this StartPeriodicNotificationRequest.
     * 
     * @param requestedAccuracy
     */
    public void setRequestedAccuracy(int requestedAccuracy) {
        this.requestedAccuracy = requestedAccuracy;
    }


    /**
     * Gets the frequency value for this StartPeriodicNotificationRequest.
     * 
     * @return frequency
     */
    public org.csapi.www.schema.common.v2_0.TimeMetric getFrequency() {
        return frequency;
    }


    /**
     * Sets the frequency value for this StartPeriodicNotificationRequest.
     * 
     * @param frequency
     */
    public void setFrequency(org.csapi.www.schema.common.v2_0.TimeMetric frequency) {
        this.frequency = frequency;
    }


    /**
     * Gets the duration value for this StartPeriodicNotificationRequest.
     * 
     * @return duration
     */
    public org.csapi.www.schema.common.v2_0.TimeMetric getDuration() {
        return duration;
    }


    /**
     * Sets the duration value for this StartPeriodicNotificationRequest.
     * 
     * @param duration
     */
    public void setDuration(org.csapi.www.schema.common.v2_0.TimeMetric duration) {
        this.duration = duration;
    }


    /**
     * Gets the applicationId value for this StartPeriodicNotificationRequest.
     * 
     * @return applicationId
     */
    public java.lang.String getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this StartPeriodicNotificationRequest.
     * 
     * @param applicationId
     */
    public void setApplicationId(java.lang.String applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * Gets the serviceType value for this StartPeriodicNotificationRequest.
     * 
     * @return serviceType
     */
    public org.csapi.www.schema.location.ServiceType getServiceType() {
        return serviceType;
    }


    /**
     * Sets the serviceType value for this StartPeriodicNotificationRequest.
     * 
     * @param serviceType
     */
    public void setServiceType(org.csapi.www.schema.location.ServiceType serviceType) {
        this.serviceType = serviceType;
    }


    /**
     * Gets the crs value for this StartPeriodicNotificationRequest.
     * 
     * @return crs
     */
    public org.csapi.www.schema.location.CoordinateReferenceSystem getCrs() {
        return crs;
    }


    /**
     * Sets the crs value for this StartPeriodicNotificationRequest.
     * 
     * @param crs
     */
    public void setCrs(org.csapi.www.schema.location.CoordinateReferenceSystem crs) {
        this.crs = crs;
    }


    /**
     * Gets the locType value for this StartPeriodicNotificationRequest.
     * 
     * @return locType
     */
    public org.csapi.www.schema.location.LocType getLocType() {
        return locType;
    }


    /**
     * Sets the locType value for this StartPeriodicNotificationRequest.
     * 
     * @param locType
     */
    public void setLocType(org.csapi.www.schema.location.LocType locType) {
        this.locType = locType;
    }


    /**
     * Gets the prio value for this StartPeriodicNotificationRequest.
     * 
     * @return prio
     */
    public org.csapi.www.schema.location.Priority getPrio() {
        return prio;
    }


    /**
     * Sets the prio value for this StartPeriodicNotificationRequest.
     * 
     * @param prio
     */
    public void setPrio(org.csapi.www.schema.location.Priority prio) {
        this.prio = prio;
    }


    /**
     * Gets the eventNotification value for this StartPeriodicNotificationRequest.
     * 
     * @return eventNotification
     */
    public java.lang.String getEventNotification() {
        return eventNotification;
    }


    /**
     * Sets the eventNotification value for this StartPeriodicNotificationRequest.
     * 
     * @param eventNotification
     */
    public void setEventNotification(java.lang.String eventNotification) {
        this.eventNotification = eventNotification;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StartPeriodicNotificationRequest)) return false;
        StartPeriodicNotificationRequest other = (StartPeriodicNotificationRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.requester==null && other.getRequester()==null) || 
             (this.requester!=null &&
              this.requester.equals(other.getRequester()))) &&
            ((this.addresses==null && other.getAddresses()==null) || 
             (this.addresses!=null &&
              java.util.Arrays.equals(this.addresses, other.getAddresses()))) &&
            this.requestedAccuracy == other.getRequestedAccuracy() &&
            ((this.frequency==null && other.getFrequency()==null) || 
             (this.frequency!=null &&
              this.frequency.equals(other.getFrequency()))) &&
            ((this.duration==null && other.getDuration()==null) || 
             (this.duration!=null &&
              this.duration.equals(other.getDuration()))) &&
            ((this.applicationId==null && other.getApplicationId()==null) || 
             (this.applicationId!=null &&
              this.applicationId.equals(other.getApplicationId()))) &&
            ((this.serviceType==null && other.getServiceType()==null) || 
             (this.serviceType!=null &&
              this.serviceType.equals(other.getServiceType()))) &&
            ((this.crs==null && other.getCrs()==null) || 
             (this.crs!=null &&
              this.crs.equals(other.getCrs()))) &&
            ((this.locType==null && other.getLocType()==null) || 
             (this.locType!=null &&
              this.locType.equals(other.getLocType()))) &&
            ((this.prio==null && other.getPrio()==null) || 
             (this.prio!=null &&
              this.prio.equals(other.getPrio()))) &&
            ((this.eventNotification==null && other.getEventNotification()==null) || 
             (this.eventNotification!=null &&
              this.eventNotification.equals(other.getEventNotification())));
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
        if (getRequester() != null) {
            _hashCode += getRequester().hashCode();
        }
        if (getAddresses() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAddresses());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAddresses(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getRequestedAccuracy();
        if (getFrequency() != null) {
            _hashCode += getFrequency().hashCode();
        }
        if (getDuration() != null) {
            _hashCode += getDuration().hashCode();
        }
        if (getApplicationId() != null) {
            _hashCode += getApplicationId().hashCode();
        }
        if (getServiceType() != null) {
            _hashCode += getServiceType().hashCode();
        }
        if (getCrs() != null) {
            _hashCode += getCrs().hashCode();
        }
        if (getLocType() != null) {
            _hashCode += getLocType().hashCode();
        }
        if (getPrio() != null) {
            _hashCode += getPrio().hashCode();
        }
        if (getEventNotification() != null) {
            _hashCode += getEventNotification().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StartPeriodicNotificationRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">startPeriodicNotificationRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requester");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Requester"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addresses");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Addresses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestedAccuracy");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequestedAccuracy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("frequency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Frequency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "TimeMetric"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("duration");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Duration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "TimeMetric"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ApplicationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "serviceType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "ServiceType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("crs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Crs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "CoordinateReferenceSystem"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "locType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "LocType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "Priority"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eventNotification");
        elemField.setXmlName(new javax.xml.namespace.QName("", "eventNotification"));
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
