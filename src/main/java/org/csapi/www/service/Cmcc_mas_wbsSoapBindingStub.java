/**
 * Cmcc_mas_wbsSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.service;

@SuppressWarnings({"unused","rawtypes"})public class Cmcc_mas_wbsSoapBindingStub extends org.apache.axis.client.Stub implements org.csapi.www.service.Cmcc_mas_wbs_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[37];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("APRegistration");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "APRegistrationReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APRegistrationReq"), org.csapi.www.schema.ap.APRegistrationReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APRegistrationRsp"));
        oper.setReturnClass(org.csapi.www.schema.ap.APRegistrationRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "APRegistrationRsp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("APStatusRep");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "APStatusRepReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APStatusRepReq"), org.csapi.www.schema.ap.APStatusRepReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APStatusRepRsp"));
        oper.setReturnClass(org.csapi.www.schema.ap.APStatusRepRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "APStatusRepRsp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("APLogOut");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "APLogOutReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APLogOutReq"), org.csapi.www.schema.ap.APLogOutReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APLogOutRsp"));
        oper.setReturnClass(org.csapi.www.schema.ap.APLogOutRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "APLogOutRsp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("PauseAP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "PauseAPReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">PauseAPReq"), org.csapi.www.schema.ap.PauseAPReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">PauseAPRsp"));
        oper.setReturnClass(org.csapi.www.schema.ap.PauseAPRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "PauseAPRsp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("RecoveryAP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "RecoveryAPReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">RecoveryAPReq"), org.csapi.www.schema.ap.RecoveryAPReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">RecoveryAPRsp"));
        oper.setReturnClass(org.csapi.www.schema.ap.RecoveryAPRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "RecoveryAPRsp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("APSvcAuthentic");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "APSvcAuthenticReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APSvcAuthenticReq"), org.csapi.www.schema.ap.APSvcAuthenticReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APSvcAuthenticRsp"));
        oper.setReturnClass(org.csapi.www.schema.ap.APSvcAuthenticRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "APSvcAuthenticRsp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("APSvcPerfCmd");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "APSvcPerfCmdReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APSvcPerfCmdReq"), org.csapi.www.schema.ap.APSvcPerfCmdReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("APSvcAlarm");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "AlarmReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">AlarmReq"), org.csapi.www.schema.ap.AlarmReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">AlarmRsp"));
        oper.setReturnClass(org.csapi.www.schema.ap.AlarmRsp.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "AlarmRsp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("APSvcPerfReport");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "APSvcPerfReportReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APSvcPerfReportReq"), org.csapi.www.schema.ap.APSvcPerfReportReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("startNotification");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/notification", "startNotificationRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/notification", ">startNotificationRequest"), org.csapi.www.schema.notification.StartNotificationRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("stopNotification");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/notification", "stopNotificationRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/notification", ">stopNotificationRequest"), org.csapi.www.schema.notification.StopNotificationRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("sendSms");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "sendSmsRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", ">sendSmsRequest"), org.csapi.www.schema.sms.SendSmsRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", ">sendSmsResponse"));
        oper.setReturnClass(org.csapi.www.schema.sms.SendSmsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "sendSmsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("notifySmsDeliveryStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "notifySmsDeliveryStatusRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", ">notifySmsDeliveryStatusRequest"), org.csapi.www.schema.sms.NotifySmsDeliveryStatusRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetReceivedSms");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "GetReceivedSmsRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", ">GetReceivedSmsRequest"), org.csapi.www.schema.sms.GetReceivedSmsRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", ">GetReceivedSmsResponse"));
        oper.setReturnClass(org.csapi.www.schema.sms.SMSMessage[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "GetReceivedSmsResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetSmsDeliveryStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "GetSmsDeliveryStatusRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", ">GetSmsDeliveryStatusRequest"), org.csapi.www.schema.sms.GetSmsDeliveryStatusRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", ">GetSmsDeliveryStatusResponse"));
        oper.setReturnClass(org.csapi.www.schema.sms.DeliveryInformation[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "GetSmsDeliveryStatusResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("notifySmsReception");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "notifySmsReceptionRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", ">notifySmsReceptionRequest"), org.csapi.www.schema.sms.NotifySmsReceptionRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("sendMessage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "sendMessageRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">sendMessageRequest"), org.csapi.www.schema.mms.SendMessageRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">sendMessageResponse"));
        oper.setReturnClass(org.csapi.www.schema.mms.SendMessageResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "sendMessageResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMessageDeliveryStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "getMessageDeliveryStatusRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">getMessageDeliveryStatusRequest"), org.csapi.www.schema.mms.GetMessageDeliveryStatusRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">getMessageDeliveryStatusResponse"));
        oper.setReturnClass(org.csapi.www.schema.mms.DeliveryInformation[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "getMessageDeliveryStatusResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getReceivedMessages");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "getReceivedMessagesRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">getReceivedMessagesRequest"), org.csapi.www.schema.mms.GetReceivedMessagesRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">getReceivedMessagesResponse"));
        oper.setReturnClass(org.csapi.www.schema.mms.MessageReference[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "getReceivedMessagesResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMessage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "getMessageRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">getMessageRequest"), org.csapi.www.schema.mms.GetMessageRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">getMessageResponse"));
        oper.setReturnClass(org.csapi.www.schema.mms.GetMessageResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "getMessageResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("notifyMessageReception");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "notifyMessageReceptionRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">notifyMessageReceptionRequest"), org.csapi.www.schema.mms.NotifyMessageReceptionRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("notifyMessageDeliveryReceipt");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "notifyMessageDeliveryReceiptRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">notifyMessageDeliveryReceiptRequest"), org.csapi.www.schema.mms.NotifyMessageDeliveryReceiptRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getLocation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "getLocationRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">getLocationRequest"), org.csapi.www.schema.location.GetLocationRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">getLocationResponse"));
        oper.setReturnClass(org.csapi.www.schema.location.GetLocationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "getLocationResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getLocationForGroup");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "getLocationForGroupRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">getLocationForGroupRequest"), org.csapi.www.schema.location.GetLocationForGroupRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">getLocationForGroupResponse"));
        oper.setReturnClass(org.csapi.www.schema.location.LocationData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "getLocationForGroupResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("startPeriodicNotification");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "startPeriodicNotificationRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">startPeriodicNotificationRequest"), org.csapi.www.schema.location.StartPeriodicNotificationRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">startPeriodicNotificationResponse"));
        oper.setReturnClass(org.csapi.www.schema.location.StartPeriodicNotificationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "startPeriodicNotificationResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("EndNotification");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "EndNotificationRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">EndNotificationRequest"), org.csapi.www.schema.location.EndNotificationRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("LocationNotification");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "LocationNotificationRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">LocationNotificationRequest"), org.csapi.www.schema.location.LocationNotificationRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("LocationError");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "LocationErrorRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">LocationErrorRequest"), org.csapi.www.schema.location.LocationErrorRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("LocationEnd");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "LocationEndRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">LocationEndRequest"), org.csapi.www.schema.location.LocationEndRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("sendPush");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", "sendPushRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", ">sendPushRequest"), org.csapi.www.schema.wap.SendPushRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", ">sendPushResponse"));
        oper.setReturnClass(org.csapi.www.schema.wap.SendPushResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", "sendPushResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getPushDeliveryStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", "getPushDeliveryStatusRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", ">getPushDeliveryStatusRequest"), org.csapi.www.schema.wap.GetPushDeliveryStatusRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", ">getPushDeliveryStatusResponse"));
        oper.setReturnClass(org.csapi.www.schema.wap.DeliveryInformation[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", "getPushDeliveryStatusResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("notifyPushDeliveryReceipt");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", "notifyPushDeliveryReceiptRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", ">notifyPushDeliveryReceiptRequest"), org.csapi.www.schema.wap.NotifyPushDeliveryReceiptRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[31] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("makeUssd");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", "makeUssdRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">makeUssdRequest"), org.csapi.www.schema.ussd.MakeUssdRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">makeUssdResponse"));
        oper.setReturnClass(org.csapi.www.schema.ussd.MakeUssdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", "makeUssdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[32] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("handleUssd");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", "handleUssdRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">handleUssdRequest"), org.csapi.www.schema.ussd.HandleUssdRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">handleUssdResponse"));
        oper.setReturnClass(org.csapi.www.schema.ussd.HandleUssdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", "handleUssdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[33] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ussdContinue");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", "ussdContinueRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">ussdContinueRequest"), org.csapi.www.schema.ussd.UssdContinueRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">ussdContinueResponse"));
        oper.setReturnClass(org.csapi.www.schema.ussd.UssdContinueResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", "ussdContinueResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[34] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("endUssd");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", "endUssdRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">endUssdRequest"), org.csapi.www.schema.ussd.EndUssdRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[35] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("notifyUssdEnd");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", "notifyUssdEndRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">notifyUssdEndRequest"), org.csapi.www.schema.ussd.NotifyUssdEndRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"),
                      "org.csapi.www.schema.common.v2_0.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"),
                      "org.csapi.www.schema.common.v2_0.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException"), 
                      true
                     ));
        _operations[36] = oper;

    }

    public Cmcc_mas_wbsSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public Cmcc_mas_wbsSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    @SuppressWarnings("unchecked")
	public Cmcc_mas_wbsSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">AlarmReq");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.AlarmReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">AlarmRsp");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.AlarmRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APLogOutReq");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.APLogOutReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APLogOutRsp");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.APLogOutRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APRegistrationReq");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.APRegistrationReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APRegistrationRsp");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.APRegistrationRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APStatusRepReq");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.APStatusRepReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APStatusRepRsp");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.APStatusRepRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APSvcAuthenticReq");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.APSvcAuthenticReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APSvcAuthenticRsp");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.APSvcAuthenticRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APSvcPerfCmdReq");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.APSvcPerfCmdReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">APSvcPerfReportReq");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.APSvcPerfReportReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">PauseAPReq");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.PauseAPReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">PauseAPRsp");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.PauseAPRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">RecoveryAPReq");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.RecoveryAPReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", ">RecoveryAPRsp");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.RecoveryAPRsp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "APLogoutResult");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.APLogoutResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "APRegResult");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.APRegResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ap", "APStatusType");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ap.APStatusType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "CMAbility");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.common.v2_0.CMAbility.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "MessageNotificationType");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.common.v2_0.MessageNotificationType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "PolicyException");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.common.v2_0.PolicyException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceError");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.common.v2_0.ServiceError.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "ServiceException");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.common.v2_0.ServiceException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "TimeMetric");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.common.v2_0.TimeMetric.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/common/v2_0", "TimeMetricsValues");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.common.v2_0.TimeMetricsValues.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">EndNotificationRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.location.EndNotificationRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">getLocationForGroupRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.location.GetLocationForGroupRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">getLocationForGroupResponse");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.location.LocationData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "LocationData");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">getLocationRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.location.GetLocationRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">getLocationResponse");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.location.GetLocationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">LocationEndRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.location.LocationEndRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">LocationErrorRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.location.LocationErrorRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">LocationNotificationRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.location.LocationNotificationRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">startPeriodicNotificationRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.location.StartPeriodicNotificationRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", ">startPeriodicNotificationResponse");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.location.StartPeriodicNotificationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "CoordinateReferenceSystem");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.location.CoordinateReferenceSystem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "DelayTolerance");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.location.DelayTolerance.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "LocationData");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.location.LocationData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "LocationInfo");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.location.LocationInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "LocType");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.location.LocType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "Priority");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.location.Priority.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "RetrievalStatus");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.location.RetrievalStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/location", "ServiceType");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.location.ServiceType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">getMessageDeliveryStatusRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.mms.GetMessageDeliveryStatusRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">getMessageDeliveryStatusResponse");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.mms.DeliveryInformation[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "DeliveryInformation");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">getMessageRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.mms.GetMessageRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">getMessageResponse");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.mms.GetMessageResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">getReceivedMessagesRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.mms.GetReceivedMessagesRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">getReceivedMessagesResponse");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.mms.MessageReference[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "MessageReference");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">notifyMessageDeliveryReceiptRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.mms.NotifyMessageDeliveryReceiptRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">notifyMessageReceptionRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.mms.NotifyMessageReceptionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">sendMessageRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.mms.SendMessageRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", ">sendMessageResponse");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.mms.SendMessageResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "DeliveryInformation");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.mms.DeliveryInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "DeliveryStatus");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.mms.DeliveryStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "MessagePriority");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.mms.MessagePriority.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "MessageReference");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.mms.MessageReference.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/mms", "MmsMessage");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.mms.MmsMessage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/notification", ">startNotificationRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.notification.StartNotificationRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/notification", ">stopNotificationRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.notification.StopNotificationRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", ">GetReceivedSmsRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.sms.GetReceivedSmsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", ">GetReceivedSmsResponse");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.sms.SMSMessage[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "SMSMessage");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", ">GetSmsDeliveryStatusRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.sms.GetSmsDeliveryStatusRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", ">GetSmsDeliveryStatusResponse");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.sms.DeliveryInformation[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "DeliveryInformation");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", ">notifySmsDeliveryStatusRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.sms.NotifySmsDeliveryStatusRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", ">notifySmsReceptionRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.sms.NotifySmsReceptionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", ">sendSmsRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.sms.SendSmsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", ">sendSmsResponse");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.sms.SendSmsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "DeliveryInformation");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.sms.DeliveryInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "DeliveryStatus");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.sms.DeliveryStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "MessageFormat");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.sms.MessageFormat.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "SendMethodType");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.sms.SendMethodType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/sms", "SMSMessage");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.sms.SMSMessage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">endUssdRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ussd.EndUssdRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">handleUssdRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ussd.HandleUssdRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">handleUssdResponse");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ussd.HandleUssdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">makeUssdRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ussd.MakeUssdRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">makeUssdResponse");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ussd.MakeUssdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">notifyUssdEndRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ussd.NotifyUssdEndRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">ussdContinueRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ussd.UssdContinueRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">ussdContinueResponse");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ussd.UssdContinueResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", "EndReason");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ussd.EndReason.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", "UssdArray");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.ussd.UssdArray.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", ">getPushDeliveryStatusRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.wap.GetPushDeliveryStatusRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", ">getPushDeliveryStatusResponse");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.wap.DeliveryInformation[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", "DeliveryInformation");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", ">notifyPushDeliveryReceiptRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.wap.NotifyPushDeliveryReceiptRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", ">sendPushRequest");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.wap.SendPushRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", ">sendPushResponse");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.wap.SendPushResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", "DeliveryInformation");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.wap.DeliveryInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/wap", "DeliveryStatus");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.wap.DeliveryStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public org.csapi.www.schema.ap.APRegistrationRsp APRegistration(org.csapi.www.schema.ap.APRegistrationReq APRegistrationReq) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/APRegistration");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "APRegistration"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {APRegistrationReq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.ap.APRegistrationRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.ap.APRegistrationRsp) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.ap.APRegistrationRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.ap.APStatusRepRsp APStatusRep(org.csapi.www.schema.ap.APStatusRepReq APStatusRepReq) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/APStatusRep");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "APStatusRep"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {APStatusRepReq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.ap.APStatusRepRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.ap.APStatusRepRsp) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.ap.APStatusRepRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.ap.APLogOutRsp APLogOut(org.csapi.www.schema.ap.APLogOutReq APLogOutReq) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/APLogOut");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "APLogOut"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {APLogOutReq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.ap.APLogOutRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.ap.APLogOutRsp) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.ap.APLogOutRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.ap.PauseAPRsp pauseAP(org.csapi.www.schema.ap.PauseAPReq pauseAPReq) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/PauseAP");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "PauseAP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pauseAPReq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.ap.PauseAPRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.ap.PauseAPRsp) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.ap.PauseAPRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.ap.RecoveryAPRsp recoveryAP(org.csapi.www.schema.ap.RecoveryAPReq recoveryAPReq) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/RecoveryAP");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "RecoveryAP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {recoveryAPReq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.ap.RecoveryAPRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.ap.RecoveryAPRsp) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.ap.RecoveryAPRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.ap.APSvcAuthenticRsp APSvcAuthentic(org.csapi.www.schema.ap.APSvcAuthenticReq APSvcAuthenticReq) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/APSvcAuthentic");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "APSvcAuthentic"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {APSvcAuthenticReq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.ap.APSvcAuthenticRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.ap.APSvcAuthenticRsp) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.ap.APSvcAuthenticRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void APSvcPerfCmd(org.csapi.www.schema.ap.APSvcPerfCmdReq APSvcPerfCmdReq) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/APSvcPerfCmd");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "APSvcPerfCmd"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {APSvcPerfCmdReq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.ap.AlarmRsp APSvcAlarm(org.csapi.www.schema.ap.AlarmReq alarmReq) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/APSvcAlarm");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "APSvcAlarm"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {alarmReq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.ap.AlarmRsp) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.ap.AlarmRsp) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.ap.AlarmRsp.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void APSvcPerfReport(org.csapi.www.schema.ap.APSvcPerfReportReq APSvcPerfReportReq) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/APSvcPerfReport");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "APSvcPerfReport"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {APSvcPerfReportReq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void startNotification(org.csapi.www.schema.notification.StartNotificationRequest startNotificationRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/startNotification");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "startNotification"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {startNotificationRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void stopNotification(org.csapi.www.schema.notification.StopNotificationRequest stopNotificationRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/stopNotification");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "stopNotification"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {stopNotificationRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.sms.SendSmsResponse sendSms(org.csapi.www.schema.sms.SendSmsRequest sendSmsRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/sendSms");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "sendSms"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sendSmsRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.sms.SendSmsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.sms.SendSmsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.sms.SendSmsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void notifySmsDeliveryStatus(org.csapi.www.schema.sms.NotifySmsDeliveryStatusRequest notifySmsDeliveryStatusRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/notifySmsDeliveryStatus");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "notifySmsDeliveryStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {notifySmsDeliveryStatusRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.sms.SMSMessage[] getReceivedSms(org.csapi.www.schema.sms.GetReceivedSmsRequest getReceivedSmsRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/GetReceivedSms");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetReceivedSms"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getReceivedSmsRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.sms.SMSMessage[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.sms.SMSMessage[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.sms.SMSMessage[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.sms.DeliveryInformation[] getSmsDeliveryStatus(org.csapi.www.schema.sms.GetSmsDeliveryStatusRequest getSmsDeliveryStatusRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/GetSmsDeliveryStatus");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetSmsDeliveryStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getSmsDeliveryStatusRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.sms.DeliveryInformation[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.sms.DeliveryInformation[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.sms.DeliveryInformation[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void notifySmsReception(org.csapi.www.schema.sms.NotifySmsReceptionRequest notifySmsReceptionRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/notifySmsReception");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "notifySmsReception"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {notifySmsReceptionRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.mms.SendMessageResponse sendMessage(org.csapi.www.schema.mms.SendMessageRequest sendMessageRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/sendMessage");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "sendMessage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sendMessageRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.mms.SendMessageResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.mms.SendMessageResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.mms.SendMessageResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.mms.DeliveryInformation[] getMessageDeliveryStatus(org.csapi.www.schema.mms.GetMessageDeliveryStatusRequest getMessageDeliveryStatusRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/getMessageDeliveryStatus");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getMessageDeliveryStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getMessageDeliveryStatusRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.mms.DeliveryInformation[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.mms.DeliveryInformation[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.mms.DeliveryInformation[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.mms.MessageReference[] getReceivedMessages(org.csapi.www.schema.mms.GetReceivedMessagesRequest getReceivedMessagesRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/getReceivedMessages");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getReceivedMessages"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getReceivedMessagesRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.mms.MessageReference[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.mms.MessageReference[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.mms.MessageReference[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.mms.GetMessageResponse getMessage(org.csapi.www.schema.mms.GetMessageRequest getMessageRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/getMessage");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getMessage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getMessageRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.mms.GetMessageResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.mms.GetMessageResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.mms.GetMessageResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void notifyMessageReception(org.csapi.www.schema.mms.NotifyMessageReceptionRequest notifyMessageReceptionRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/notifyMessageReception");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "notifyMessageReception"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {notifyMessageReceptionRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void notifyMessageDeliveryReceipt(org.csapi.www.schema.mms.NotifyMessageDeliveryReceiptRequest notifyMessageDeliveryReceiptRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/notifyMessageDeliveryReceipt");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "notifyMessageDeliveryReceipt"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {notifyMessageDeliveryReceiptRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.location.GetLocationResponse getLocation(org.csapi.www.schema.location.GetLocationRequest getLocationRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/getLocation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getLocation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getLocationRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.location.GetLocationResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.location.GetLocationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.location.GetLocationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.location.LocationData[] getLocationForGroup(org.csapi.www.schema.location.GetLocationForGroupRequest getLocationForGroupRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/getLocationForGroup");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getLocationForGroup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getLocationForGroupRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.location.LocationData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.location.LocationData[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.location.LocationData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.location.StartPeriodicNotificationResponse startPeriodicNotification(org.csapi.www.schema.location.StartPeriodicNotificationRequest startPeriodicNotificationRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/startPeriodicNotification");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "startPeriodicNotification"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {startPeriodicNotificationRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.location.StartPeriodicNotificationResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.location.StartPeriodicNotificationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.location.StartPeriodicNotificationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void endNotification(org.csapi.www.schema.location.EndNotificationRequest endNotificationRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/EndNotification");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "EndNotification"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {endNotificationRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void locationNotification(org.csapi.www.schema.location.LocationNotificationRequest locationNotificationRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/LocationNotification");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "LocationNotification"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {locationNotificationRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void locationError(org.csapi.www.schema.location.LocationErrorRequest locationErrorRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/LocationError");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "LocationError"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {locationErrorRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void locationEnd(org.csapi.www.schema.location.LocationEndRequest locationEndRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/LocationEnd");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "LocationEnd"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {locationEndRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.wap.SendPushResponse sendPush(org.csapi.www.schema.wap.SendPushRequest sendPushRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/sendPush");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "sendPush"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sendPushRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.wap.SendPushResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.wap.SendPushResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.wap.SendPushResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.wap.DeliveryInformation[] getPushDeliveryStatus(org.csapi.www.schema.wap.GetPushDeliveryStatusRequest getPushDeliveryStatusRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/getPushDeliveryStatus");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getPushDeliveryStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getPushDeliveryStatusRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.wap.DeliveryInformation[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.wap.DeliveryInformation[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.wap.DeliveryInformation[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void notifyPushDeliveryReceipt(org.csapi.www.schema.wap.NotifyPushDeliveryReceiptRequest notifyPushDeliveryReceiptRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/notifyPushDeliveryReceipt");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "notifyPushDeliveryReceipt"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {notifyPushDeliveryReceiptRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.ussd.MakeUssdResponse makeUssd(org.csapi.www.schema.ussd.MakeUssdRequest makeUssdRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[32]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/makeUssd");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "makeUssd"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {makeUssdRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.ussd.MakeUssdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.ussd.MakeUssdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.ussd.MakeUssdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.ussd.HandleUssdResponse handleUssd(org.csapi.www.schema.ussd.HandleUssdRequest handleUssdRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[33]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/handleUssd");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "handleUssd"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {handleUssdRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.ussd.HandleUssdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.ussd.HandleUssdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.ussd.HandleUssdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.ussd.UssdContinueResponse ussdContinue(org.csapi.www.schema.ussd.UssdContinueRequest ussdContinueRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[34]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/ussdContinue");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ussdContinue"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {ussdContinueRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.ussd.UssdContinueResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.ussd.UssdContinueResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.ussd.UssdContinueResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void endUssd(org.csapi.www.schema.ussd.EndUssdRequest endUssdRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[35]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/endUssd");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "endUssd"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {endUssdRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void notifyUssdEnd(org.csapi.www.schema.ussd.NotifyUssdEndRequest notifyUssdEndRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[36]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.csapi.org/service/notifyUssdEnd");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "notifyUssdEnd"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {notifyUssdEndRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.ServiceException) {
              throw (org.csapi.www.schema.common.v2_0.ServiceException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.common.v2_0.PolicyException) {
              throw (org.csapi.www.schema.common.v2_0.PolicyException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
