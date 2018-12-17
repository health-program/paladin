package org.csapi.www.service;

public class Cmcc_mas_wbsProxy implements org.csapi.www.service.Cmcc_mas_wbs_PortType {
  private String _endpoint = null;
  private org.csapi.www.service.Cmcc_mas_wbs_PortType cmcc_mas_wbs_PortType = null;
  
  public Cmcc_mas_wbsProxy() {
    _initCmcc_mas_wbsProxy();
  }
  
  public Cmcc_mas_wbsProxy(String endpoint) {
    _endpoint = endpoint;
    _initCmcc_mas_wbsProxy();
  }
  
  private void _initCmcc_mas_wbsProxy() {
    try {
      cmcc_mas_wbs_PortType = (new org.csapi.www.service.Cmcc_mas_wbs_ServiceLocator()).getcmcc_mas_wbs();
      if (cmcc_mas_wbs_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cmcc_mas_wbs_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cmcc_mas_wbs_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cmcc_mas_wbs_PortType != null)
      ((javax.xml.rpc.Stub)cmcc_mas_wbs_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.csapi.www.service.Cmcc_mas_wbs_PortType getCmcc_mas_wbs_PortType() {
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType;
  }
  
  public org.csapi.www.schema.ap.APRegistrationRsp APRegistration(org.csapi.www.schema.ap.APRegistrationReq APRegistrationReq) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.APRegistration(APRegistrationReq);
  }
  
  public org.csapi.www.schema.ap.APStatusRepRsp APStatusRep(org.csapi.www.schema.ap.APStatusRepReq APStatusRepReq) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.APStatusRep(APStatusRepReq);
  }
  
  public org.csapi.www.schema.ap.APLogOutRsp APLogOut(org.csapi.www.schema.ap.APLogOutReq APLogOutReq) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.APLogOut(APLogOutReq);
  }
  
  public org.csapi.www.schema.ap.PauseAPRsp pauseAP(org.csapi.www.schema.ap.PauseAPReq pauseAPReq) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.pauseAP(pauseAPReq);
  }
  
  public org.csapi.www.schema.ap.RecoveryAPRsp recoveryAP(org.csapi.www.schema.ap.RecoveryAPReq recoveryAPReq) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.recoveryAP(recoveryAPReq);
  }
  
  public org.csapi.www.schema.ap.APSvcAuthenticRsp APSvcAuthentic(org.csapi.www.schema.ap.APSvcAuthenticReq APSvcAuthenticReq) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.APSvcAuthentic(APSvcAuthenticReq);
  }
  
  public void APSvcPerfCmd(org.csapi.www.schema.ap.APSvcPerfCmdReq APSvcPerfCmdReq) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    cmcc_mas_wbs_PortType.APSvcPerfCmd(APSvcPerfCmdReq);
  }
  
  public org.csapi.www.schema.ap.AlarmRsp APSvcAlarm(org.csapi.www.schema.ap.AlarmReq alarmReq) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.APSvcAlarm(alarmReq);
  }
  
  public void APSvcPerfReport(org.csapi.www.schema.ap.APSvcPerfReportReq APSvcPerfReportReq) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    cmcc_mas_wbs_PortType.APSvcPerfReport(APSvcPerfReportReq);
  }
  
  public void startNotification(org.csapi.www.schema.notification.StartNotificationRequest startNotificationRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    cmcc_mas_wbs_PortType.startNotification(startNotificationRequest);
  }
  
  public void stopNotification(org.csapi.www.schema.notification.StopNotificationRequest stopNotificationRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    cmcc_mas_wbs_PortType.stopNotification(stopNotificationRequest);
  }
  
  public org.csapi.www.schema.sms.SendSmsResponse sendSms(org.csapi.www.schema.sms.SendSmsRequest sendSmsRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.sendSms(sendSmsRequest);
  }
  
  public void notifySmsDeliveryStatus(org.csapi.www.schema.sms.NotifySmsDeliveryStatusRequest notifySmsDeliveryStatusRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    cmcc_mas_wbs_PortType.notifySmsDeliveryStatus(notifySmsDeliveryStatusRequest);
  }
  
  public org.csapi.www.schema.sms.SMSMessage[] getReceivedSms(org.csapi.www.schema.sms.GetReceivedSmsRequest getReceivedSmsRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.getReceivedSms(getReceivedSmsRequest);
  }
  
  public org.csapi.www.schema.sms.DeliveryInformation[] getSmsDeliveryStatus(org.csapi.www.schema.sms.GetSmsDeliveryStatusRequest getSmsDeliveryStatusRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.getSmsDeliveryStatus(getSmsDeliveryStatusRequest);
  }
  
  public void notifySmsReception(org.csapi.www.schema.sms.NotifySmsReceptionRequest notifySmsReceptionRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    cmcc_mas_wbs_PortType.notifySmsReception(notifySmsReceptionRequest);
  }
  
  public org.csapi.www.schema.mms.SendMessageResponse sendMessage(org.csapi.www.schema.mms.SendMessageRequest sendMessageRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.sendMessage(sendMessageRequest);
  }
  
  public org.csapi.www.schema.mms.DeliveryInformation[] getMessageDeliveryStatus(org.csapi.www.schema.mms.GetMessageDeliveryStatusRequest getMessageDeliveryStatusRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.getMessageDeliveryStatus(getMessageDeliveryStatusRequest);
  }
  
  public org.csapi.www.schema.mms.MessageReference[] getReceivedMessages(org.csapi.www.schema.mms.GetReceivedMessagesRequest getReceivedMessagesRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.getReceivedMessages(getReceivedMessagesRequest);
  }
  
  public org.csapi.www.schema.mms.GetMessageResponse getMessage(org.csapi.www.schema.mms.GetMessageRequest getMessageRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.getMessage(getMessageRequest);
  }
  
  public void notifyMessageReception(org.csapi.www.schema.mms.NotifyMessageReceptionRequest notifyMessageReceptionRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    cmcc_mas_wbs_PortType.notifyMessageReception(notifyMessageReceptionRequest);
  }
  
  public void notifyMessageDeliveryReceipt(org.csapi.www.schema.mms.NotifyMessageDeliveryReceiptRequest notifyMessageDeliveryReceiptRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    cmcc_mas_wbs_PortType.notifyMessageDeliveryReceipt(notifyMessageDeliveryReceiptRequest);
  }
  
  public org.csapi.www.schema.location.GetLocationResponse getLocation(org.csapi.www.schema.location.GetLocationRequest getLocationRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.getLocation(getLocationRequest);
  }
  
  public org.csapi.www.schema.location.LocationData[] getLocationForGroup(org.csapi.www.schema.location.GetLocationForGroupRequest getLocationForGroupRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.getLocationForGroup(getLocationForGroupRequest);
  }
  
  public org.csapi.www.schema.location.StartPeriodicNotificationResponse startPeriodicNotification(org.csapi.www.schema.location.StartPeriodicNotificationRequest startPeriodicNotificationRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.startPeriodicNotification(startPeriodicNotificationRequest);
  }
  
  public void endNotification(org.csapi.www.schema.location.EndNotificationRequest endNotificationRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    cmcc_mas_wbs_PortType.endNotification(endNotificationRequest);
  }
  
  public void locationNotification(org.csapi.www.schema.location.LocationNotificationRequest locationNotificationRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    cmcc_mas_wbs_PortType.locationNotification(locationNotificationRequest);
  }
  
  public void locationError(org.csapi.www.schema.location.LocationErrorRequest locationErrorRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    cmcc_mas_wbs_PortType.locationError(locationErrorRequest);
  }
  
  public void locationEnd(org.csapi.www.schema.location.LocationEndRequest locationEndRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    cmcc_mas_wbs_PortType.locationEnd(locationEndRequest);
  }
  
  public org.csapi.www.schema.wap.SendPushResponse sendPush(org.csapi.www.schema.wap.SendPushRequest sendPushRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.sendPush(sendPushRequest);
  }
  
  public org.csapi.www.schema.wap.DeliveryInformation[] getPushDeliveryStatus(org.csapi.www.schema.wap.GetPushDeliveryStatusRequest getPushDeliveryStatusRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.getPushDeliveryStatus(getPushDeliveryStatusRequest);
  }
  
  public void notifyPushDeliveryReceipt(org.csapi.www.schema.wap.NotifyPushDeliveryReceiptRequest notifyPushDeliveryReceiptRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    cmcc_mas_wbs_PortType.notifyPushDeliveryReceipt(notifyPushDeliveryReceiptRequest);
  }
  
  public org.csapi.www.schema.ussd.MakeUssdResponse makeUssd(org.csapi.www.schema.ussd.MakeUssdRequest makeUssdRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.makeUssd(makeUssdRequest);
  }
  
  public org.csapi.www.schema.ussd.HandleUssdResponse handleUssd(org.csapi.www.schema.ussd.HandleUssdRequest handleUssdRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.handleUssd(handleUssdRequest);
  }
  
  public org.csapi.www.schema.ussd.UssdContinueResponse ussdContinue(org.csapi.www.schema.ussd.UssdContinueRequest ussdContinueRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    return cmcc_mas_wbs_PortType.ussdContinue(ussdContinueRequest);
  }
  
  public void endUssd(org.csapi.www.schema.ussd.EndUssdRequest endUssdRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    cmcc_mas_wbs_PortType.endUssd(endUssdRequest);
  }
  
  public void notifyUssdEnd(org.csapi.www.schema.ussd.NotifyUssdEndRequest notifyUssdEndRequest) throws java.rmi.RemoteException, org.csapi.www.schema.common.v2_0.ServiceException, org.csapi.www.schema.common.v2_0.PolicyException{
    if (cmcc_mas_wbs_PortType == null)
      _initCmcc_mas_wbsProxy();
    cmcc_mas_wbs_PortType.notifyUssdEnd(notifyUssdEndRequest);
  }
  
  
}