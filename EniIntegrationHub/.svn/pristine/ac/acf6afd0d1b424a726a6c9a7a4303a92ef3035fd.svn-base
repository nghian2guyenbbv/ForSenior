/**
 *CONFIDENTIAL AND PROPRIETARY SOURCE CODE. 
 * 
 * Use and distribution of this code is subject to applicable 
 * licenses and the permission of the code owner. This notice 
 * does not indicate the actual or intended publication of 
 * this source code.
 * 
 * Portions developed for Event Network by BTM Global Consulting
 * LLC and are the property of Event Network.
 * 
 * ===== BTM Modification ===========================================
 * Bug#         ddMMyy          Description
 * BZ47094      201021          [Intergration] Xstore Integrated eCommerce Orders                                                                   
 *===================================================================
 */

package eni.hub.handlers;

import java.io.ByteArrayOutputStream;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.log4j.Logger;

import eni.hub.services.impl.EniOrderHubServiceImpl;

public class EniOrderHubServiceHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger LOG = Logger.getLogger(EniOrderHubServiceImpl.class.getName());

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
    	LOG.info(">> +[handleMessage] START");
        SOAPMessage soapMsg = context.getMessage();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        String soapMsgInOut = "";
        try {
            soapMsg.writeTo(out);
            soapMsgInOut = out.toString();
            if (isRequest) { // Outbound message from HUB return to Xstore
                if (LOG.isDebugEnabled()) {
                    LOG.debug("HUB Outbound: " + soapMsgInOut);
                }

            } else { // Inbound message from Xstore send to HUB
                if (LOG.isDebugEnabled()) {
                    LOG.debug("HUB Inbound: " + soapMsgInOut);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        LOG.info("<< +[handleMessage] END");
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }
}
