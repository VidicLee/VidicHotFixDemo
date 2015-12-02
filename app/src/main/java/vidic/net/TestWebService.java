package vidic.net;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Administrator on 2015/11/23.
 * name:vidic
 */
public class TestWebService {

    public static String url="http://192.168.226.206:8080/WebServiceProject/services/ToMobileService";

    public static String getUpdataUrl(){
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("http://ws.apache.org/axis2", "getHotUpdataUrl");
        //soapReq.addProperty(p.key, p.value);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, 30000);
        try {
            httpTransport.call("http://ws.apache.org/axis2" + "getHotUpdataUrl", soapEnvelope);
            Object retObj = soapEnvelope.bodyIn;
            if (retObj instanceof SoapFault) {
                SoapFault fault = (SoapFault)retObj;
                Exception ex = new Exception(fault.faultstring);
            } else {
                SoapObject result = (SoapObject)retObj;
                if (result.getPropertyCount() > 0) {
                    Object obj = result.getProperty(0);
                    if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                        SoapPrimitive j = (SoapPrimitive)obj;
                        String resultVariable = j.toString();
                        return resultVariable;
                    } else if (obj != null && obj instanceof String) {
                        String resultVariable = (String)obj;
                        return resultVariable;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{'bSucess':False,'msg':'网络异常'}";
    }

    public static String checkIfHasBug(){
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("http://ws.apache.org/axis2", "checkIfHasBug");
        //soapReq.addProperty(p.key, p.value);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, 30000);
        try {
            httpTransport.call("http://ws.apache.org/axis2" + "checkIfHasBug", soapEnvelope);
            Object retObj = soapEnvelope.bodyIn;
            if (retObj instanceof SoapFault) {
                SoapFault fault = (SoapFault)retObj;
                Exception ex = new Exception(fault.faultstring);
            } else {
                SoapObject result = (SoapObject)retObj;
                if (result.getPropertyCount() > 0) {
                    Object obj = result.getProperty(0);
                    if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                        SoapPrimitive j = (SoapPrimitive)obj;
                        String resultVariable = j.toString();
                        return resultVariable;
                    } else if (obj != null && obj instanceof String) {
                        String resultVariable = (String)obj;
                        return resultVariable;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{'bSucess':False,'msg':'网络异常'}";
    }

}
