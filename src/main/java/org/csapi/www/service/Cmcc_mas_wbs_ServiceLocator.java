/**
 * Cmcc_mas_wbs_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.service;

@SuppressWarnings({"serial","unchecked","rawtypes"})public class Cmcc_mas_wbs_ServiceLocator extends org.apache.axis.client.Service implements org.csapi.www.service.Cmcc_mas_wbs_Service {

    public Cmcc_mas_wbs_ServiceLocator() {
    }


    public Cmcc_mas_wbs_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Cmcc_mas_wbs_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for cmcc_mas_wbs
    private java.lang.String cmcc_mas_wbs_address = "http://223.105.0.175:8804/ry4/services/cmcc_mas_wbs";

    public java.lang.String getcmcc_mas_wbsAddress() {
        return cmcc_mas_wbs_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String cmcc_mas_wbsWSDDServiceName = "cmcc_mas_wbs";

    public java.lang.String getcmcc_mas_wbsWSDDServiceName() {
        return cmcc_mas_wbsWSDDServiceName;
    }

    public void setcmcc_mas_wbsWSDDServiceName(java.lang.String name) {
        cmcc_mas_wbsWSDDServiceName = name;
    }

    public org.csapi.www.service.Cmcc_mas_wbs_PortType getcmcc_mas_wbs() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(cmcc_mas_wbs_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getcmcc_mas_wbs(endpoint);
    }

    public org.csapi.www.service.Cmcc_mas_wbs_PortType getcmcc_mas_wbs(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.csapi.www.service.Cmcc_mas_wbsSoapBindingStub _stub = new org.csapi.www.service.Cmcc_mas_wbsSoapBindingStub(portAddress, this);
            _stub.setPortName(getcmcc_mas_wbsWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setcmcc_mas_wbsEndpointAddress(java.lang.String address) {
        cmcc_mas_wbs_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.csapi.www.service.Cmcc_mas_wbs_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                org.csapi.www.service.Cmcc_mas_wbsSoapBindingStub _stub = new org.csapi.www.service.Cmcc_mas_wbsSoapBindingStub(new java.net.URL(cmcc_mas_wbs_address), this);
                _stub.setPortName(getcmcc_mas_wbsWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("cmcc_mas_wbs".equals(inputPortName)) {
            return getcmcc_mas_wbs();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.csapi.org/service", "cmcc_mas_wbs");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.csapi.org/service", "cmcc_mas_wbs"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("cmcc_mas_wbs".equals(portName)) {
            setcmcc_mas_wbsEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
