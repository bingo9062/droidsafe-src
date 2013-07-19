package gov.nist.javax.sip.stack;

// Droidsafe Imports
import gov.nist.core.InternalErrorHandler;
import gov.nist.core.ServerLogger;
import gov.nist.javax.sip.header.CSeq;
import gov.nist.javax.sip.header.CallID;
import gov.nist.javax.sip.header.From;
import gov.nist.javax.sip.header.RequestLine;
import gov.nist.javax.sip.header.RetryAfter;
import gov.nist.javax.sip.header.StatusLine;
import gov.nist.javax.sip.header.To;
import gov.nist.javax.sip.header.Via;
import gov.nist.javax.sip.header.ViaList;
import gov.nist.javax.sip.message.SIPMessage;
import gov.nist.javax.sip.message.SIPRequest;
import gov.nist.javax.sip.message.SIPResponse;
import gov.nist.javax.sip.parser.Pipeline;
import gov.nist.javax.sip.parser.PipelinedMsgParser;
import gov.nist.javax.sip.parser.SIPMessageListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.ParseException;

import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSocket;
import javax.sip.address.Hop;
import javax.sip.message.Response;

import droidsafe.annotations.DSC;
import droidsafe.annotations.DSGeneratedField;
import droidsafe.annotations.DSGenerator;
import droidsafe.annotations.DSModeled;

public final class TLSMessageChannel extends MessageChannel implements SIPMessageListener, Runnable, RawMessageChannel {
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.829 -0400", hash_original_field = "52CA6755165FFD50983D257A0F1E043B", hash_generated_field = "F3B00F7E70704D963CC4197CD003FB94")

    private Socket mySock;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.829 -0400", hash_original_field = "8C35693B192604DA4AB63AD19D48096F", hash_generated_field = "152EC54D2111EF26096DF65FC2003536")

    private PipelinedMsgParser myParser;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.829 -0400", hash_original_field = "0EDD5FF84F2278A8C37689A050537727", hash_generated_field = "E9FA88EF5BFDD4D9156AADE5BC087A4C")

    private InputStream myClientInputStream;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.829 -0400", hash_original_field = "3C6E0B8A9C15224A8228B9A98CA1531D", hash_generated_field = "2E3018221E30480EB0F957E72002C7D8")

    private String key;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.829 -0400", hash_original_field = "1AC7B394035E343F285A6A4ED1096126", hash_generated_field = "F8E96F7362B3E7CD3DE3A2778DC87A58")

    protected boolean isCached;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.829 -0400", hash_original_field = "39044C41F340C67BFA15E91CC6E98E7D", hash_generated_field = "911E18F9BE05473489F5F21C894DF579")

    protected boolean isRunning;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.829 -0400", hash_original_field = "9842605C72E3BC73DA5398CE4B5EB840", hash_generated_field = "8890F3B68DD2F65EEDEBEEF6284B3EE1")

    private Thread mythread;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.829 -0400", hash_original_field = "AA48C74491DE12FAB31F14F04DE0F567", hash_generated_field = "DEE18A414D72F7D580E73EF519B5B75F")

    private String myAddress;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.829 -0400", hash_original_field = "AB99FC537949F4680FB25A11A38B0042", hash_generated_field = "1FBEE69CBCB5E71F900C5C964DA7ADBD")

    private int myPort;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.829 -0400", hash_original_field = "D7FA5E7DC9BACC5016B81E35D44D1C5F", hash_generated_field = "DB2440387A41D5016778DA700632E003")

    private InetAddress peerAddress;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.829 -0400", hash_original_field = "BBD5741C4F9994864582D25DD194C4DE", hash_generated_field = "FE74A5BA5CFBB5B6DC2B68FEFA76ECFC")

    private int peerPort;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.829 -0400", hash_original_field = "EDB6E8FD971C3E6879E1A00995BCB702", hash_generated_field = "EB665B7537D07145A6FCAD2DB460EADC")

    private String peerProtocol;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.829 -0400", hash_original_field = "8A2A02C2F7B9C43A9E25E5C24EA3F0F6", hash_generated_field = "5A3C22CB6AA628C04F88A4A28D40ED17")

    private TLSMessageProcessor tlsMessageProcessor;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.829 -0400", hash_original_field = "37460D4BF2BA47A13FF9D922C4B14B2E", hash_generated_field = "ABE627693A01BDDC6597F9AB66B3018E")

    private SIPTransactionStack sipStack;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.829 -0400", hash_original_field = "7B8AE7FFC44A9C7B49E7C7EA6A6D3757", hash_generated_field = "12E7910E7EDE55EAF4A3D89EE23A6BC7")

    private HandshakeCompletedListener handshakeCompletedListener;
    
        @DSModeled(DSC.SPEC)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.831 -0400", hash_original_method = "358EB7732CC002B4969C64CA3912C6FF", hash_generated_method = "41AAD92E4FE6A62BC4352A5940D4CEFE")
    protected  TLSMessageChannel(Socket sock, SIPTransactionStack sipStack,
            TLSMessageProcessor msgProcessor) throws IOException {
        if(sipStack.isLoggingEnabled())        
        {
            sipStack.getStackLogger().logDebug("creating new TLSMessageChannel (incoming)");
            sipStack.getStackLogger().logStackTrace();
        } //End block
        mySock = (SSLSocket) sock;
        if(sock instanceof SSLSocket)        
        {
            SSLSocket sslSock = (SSLSocket) sock;
            sslSock.setNeedClientAuth(true);
            this.handshakeCompletedListener = new HandshakeCompletedListenerImpl(this);
            sslSock.addHandshakeCompletedListener(this.handshakeCompletedListener);
            sslSock.startHandshake();
        } //End block
        peerAddress = mySock.getInetAddress();
        myAddress = msgProcessor.getIpAddress().getHostAddress();
        myClientInputStream = mySock.getInputStream();
        mythread = new Thread(this);
        mythread.setDaemon(true);
        mythread.setName("TLSMessageChannelThread");
        this.sipStack = sipStack;
        this.tlsMessageProcessor = msgProcessor;
        this.myPort = this.tlsMessageProcessor.getPort();
        this.peerPort = mySock.getPort();
        super.messageProcessor = msgProcessor;
        mythread.start();
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.834 -0400", hash_original_method = "36AC589476275C6F111789614D727487", hash_generated_method = "2CE7C94DFE28824CA1172998A97A2E0D")
    protected  TLSMessageChannel(InetAddress inetAddr, int port, SIPTransactionStack sipStack,
            TLSMessageProcessor messageProcessor) throws IOException {
        if(sipStack.isLoggingEnabled())        
        {
            sipStack.getStackLogger().logDebug("creating new TLSMessageChannel (outgoing)");
            sipStack.getStackLogger().logStackTrace();
        } //End block
        this.peerAddress = inetAddr;
        this.peerPort = port;
        this.myPort = messageProcessor.getPort();
        this.peerProtocol = "TLS";
        this.sipStack = sipStack;
        this.tlsMessageProcessor = messageProcessor;
        this.myAddress = messageProcessor.getIpAddress().getHostAddress();
        this.key = MessageChannel.getKey(peerAddress, peerPort, "TLS");
        super.messageProcessor = messageProcessor;
        // ---------- Original Method ----------
        //if (sipStack.isLoggingEnabled()) {
            //sipStack.getStackLogger().logDebug("creating new TLSMessageChannel (outgoing)");
            //sipStack.getStackLogger().logStackTrace();
        //}
        //this.peerAddress = inetAddr;
        //this.peerPort = port;
        //this.myPort = messageProcessor.getPort();
        //this.peerProtocol = "TLS";
        //this.sipStack = sipStack;
        //this.tlsMessageProcessor = messageProcessor;
        //this.myAddress = messageProcessor.getIpAddress().getHostAddress();
        //this.key = MessageChannel.getKey(peerAddress, peerPort, "TLS");
        //super.messageProcessor = messageProcessor;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.834 -0400", hash_original_method = "16D0E6CF226A4618D75D1B422CC8FCDF", hash_generated_method = "51273B890350DD70B5BD32226B1158A1")
    public boolean isReliable() {
        boolean varB326B5062B2F0E69046810717534CB09_12512542 = (true);
                boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_986745519 = getTaintBoolean();
        return var84E2C64F38F78BA3EA5C905AB5A2DA27_986745519;
        // ---------- Original Method ----------
        //return true;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.835 -0400", hash_original_method = "9757D034E975DAF9D22BACDE50FF4335", hash_generated_method = "76776E889B7121D34E819849FCBAB07F")
    public void close() {
        try 
        {
            if(mySock != null)            
            mySock.close();
            if(sipStack.isLoggingEnabled())            
            sipStack.getStackLogger().logDebug("Closing message Channel " + this);
        } //End block
        catch (IOException ex)
        {
            if(sipStack.isLoggingEnabled())            
            sipStack.getStackLogger().logDebug("Error closing socket " + ex);
        } //End block
        // ---------- Original Method ----------
        //try {
            //if (mySock != null)
                //mySock.close();
            //if (sipStack.isLoggingEnabled())
                //sipStack.getStackLogger().logDebug("Closing message Channel " + this);
        //} catch (IOException ex) {
            //if (sipStack.isLoggingEnabled())
                //sipStack.getStackLogger().logDebug("Error closing socket " + ex);
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.835 -0400", hash_original_method = "4789C177AC8ECE60B231BA8A866F9D66", hash_generated_method = "5861D2CE3889026C4AAD9D6480234BA9")
    public SIPTransactionStack getSIPStack() {
SIPTransactionStack var0FC1F21ED47F4C0C48881B0DAF112A16_1120219460 =         sipStack;
        var0FC1F21ED47F4C0C48881B0DAF112A16_1120219460.addTaint(taint);
        return var0FC1F21ED47F4C0C48881B0DAF112A16_1120219460;
        // ---------- Original Method ----------
        //return sipStack;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.835 -0400", hash_original_method = "00B4B3FBB10028B216605159EFB81D52", hash_generated_method = "7F7B5381B0C309D83CA01E89B34EA0C5")
    public String getTransport() {
String varB8D2FCEBAA9B11CA37B9DB1974AA0DF8_1245868116 =         "tls";
        varB8D2FCEBAA9B11CA37B9DB1974AA0DF8_1245868116.addTaint(taint);
        return varB8D2FCEBAA9B11CA37B9DB1974AA0DF8_1245868116;
        // ---------- Original Method ----------
        //return "tls";
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.835 -0400", hash_original_method = "59CC467573DB54CDCEF540AE63F43735", hash_generated_method = "AE91765553DC98AB0F257C637405261D")
    public String getPeerAddress() {
        if(peerAddress != null)        
        {
String var4D1B58EF74DF31190A62E0DF268BAE94_1094705900 =             peerAddress.getHostAddress();
            var4D1B58EF74DF31190A62E0DF268BAE94_1094705900.addTaint(taint);
            return var4D1B58EF74DF31190A62E0DF268BAE94_1094705900;
        } //End block
        else
        {
String var5C0621B65E898A3E2061530CDD6F20D5_1809225390 =         getHost();
        var5C0621B65E898A3E2061530CDD6F20D5_1809225390.addTaint(taint);
        return var5C0621B65E898A3E2061530CDD6F20D5_1809225390;
        }
        // ---------- Original Method ----------
        //if (peerAddress != null) {
            //return peerAddress.getHostAddress();
        //} else
            //return getHost();
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.836 -0400", hash_original_method = "1F29EC3BC94C3E0863F530FCD37A61F3", hash_generated_method = "6BC65F8266774400538AC79EEE50A102")
    protected InetAddress getPeerInetAddress() {
InetAddress var73F565C01AAA4A3FCE210191AB87441A_551486029 =         peerAddress;
        var73F565C01AAA4A3FCE210191AB87441A_551486029.addTaint(taint);
        return var73F565C01AAA4A3FCE210191AB87441A_551486029;
        // ---------- Original Method ----------
        //return peerAddress;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.836 -0400", hash_original_method = "DB79607DA3ACB5FA2D24428DF2713F98", hash_generated_method = "88BBBEB8BFE4750BC5E1F9E6DD889ACB")
    public String getPeerProtocol() {
String var6B5233BA3C9F99490638F3C0025EA1D3_465991160 =         this.peerProtocol;
        var6B5233BA3C9F99490638F3C0025EA1D3_465991160.addTaint(taint);
        return var6B5233BA3C9F99490638F3C0025EA1D3_465991160;
        // ---------- Original Method ----------
        //return this.peerProtocol;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.836 -0400", hash_original_method = "769119032395AFB8B9E88BC54405133A", hash_generated_method = "A816F38E2B010419DF44D99118FA6F4D")
    private void sendMessage(byte[] msg, boolean retry) throws IOException {
        addTaint(retry);
        addTaint(msg[0]);
        Socket sock = this.sipStack.ioHandler.sendBytes(
                this.getMessageProcessor().getIpAddress(), this.peerAddress, this.peerPort,
                this.peerProtocol, msg, retry,this);
        if(sock != mySock && sock != null)        
        {
            try 
            {
                if(mySock != null)                
                mySock.close();
            } //End block
            catch (IOException ex)
            {
            } //End block
            mySock = sock;
            this.myClientInputStream = mySock.getInputStream();
            Thread thread = new Thread(this);
            thread.setDaemon(true);
            thread.setName("TLSMessageChannelThread");
            thread.start();
        } //End block
        // ---------- Original Method ----------
        //Socket sock = this.sipStack.ioHandler.sendBytes(
                //this.getMessageProcessor().getIpAddress(), this.peerAddress, this.peerPort,
                //this.peerProtocol, msg, retry,this);
        //if (sock != mySock && sock != null) {
            //try {
                //if (mySock != null)
                    //mySock.close();
            //} catch (IOException ex) {
            //}
            //mySock = sock;
            //this.myClientInputStream = mySock.getInputStream();
            //Thread thread = new Thread(this);
            //thread.setDaemon(true);
            //thread.setName("TLSMessageChannelThread");
            //thread.start();
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.837 -0400", hash_original_method = "99193C77E68A5ABE715A7A9AC8040C34", hash_generated_method = "3AFE7FB96641BAB0037F900E9E93B940")
    public void sendMessage(SIPMessage sipMessage) throws IOException {
        addTaint(sipMessage.getTaint());
        byte[] msg = sipMessage.encodeAsBytes(this.getTransport());
        long time = System.currentTimeMillis();
        this.sendMessage(msg, sipMessage instanceof SIPRequest);
        if(this.sipStack.getStackLogger().isLoggingEnabled(ServerLogger.TRACE_MESSAGES))        
        logMessage(sipMessage, peerAddress, peerPort, time);
        // ---------- Original Method ----------
        //byte[] msg = sipMessage.encodeAsBytes(this.getTransport());
        //long time = System.currentTimeMillis();
        //this.sendMessage(msg, sipMessage instanceof SIPRequest);
        //if (this.sipStack.getStackLogger().isLoggingEnabled(ServerLogger.TRACE_MESSAGES))
            //logMessage(sipMessage, peerAddress, peerPort, time);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.838 -0400", hash_original_method = "C6D6B3579ACFAA2466286D67BFFE3003", hash_generated_method = "C609F7C58CA07E277C1BC50B9684393F")
    public void sendMessage(byte message[], InetAddress receiverAddress, int receiverPort,
            boolean retry) throws IOException {
        addTaint(retry);
        addTaint(receiverPort);
        addTaint(receiverAddress.getTaint());
        addTaint(message[0]);
        if(message == null || receiverAddress == null)        
        {
        IllegalArgumentException varFDDDD686508FEB178E9B557172ECF36A_1289119530 = new IllegalArgumentException("Null argument");
        varFDDDD686508FEB178E9B557172ECF36A_1289119530.addTaint(taint);
        throw varFDDDD686508FEB178E9B557172ECF36A_1289119530;
        }
        Socket sock = this.sipStack.ioHandler.sendBytes(this.messageProcessor.getIpAddress(),
                receiverAddress, receiverPort, "TLS", message, retry, this);
        if(sock != mySock && sock != null)        
        {
            try 
            {
                if(mySock != null)                
                mySock.close();
            } //End block
            catch (IOException ex)
            {
            } //End block
            mySock = sock;
            this.myClientInputStream = mySock.getInputStream();
            Thread mythread = new Thread(this);
            mythread.setDaemon(true);
            mythread.setName("TLSMessageChannelThread");
            mythread.start();
        } //End block
        // ---------- Original Method ----------
        //if (message == null || receiverAddress == null)
            //throw new IllegalArgumentException("Null argument");
        //Socket sock = this.sipStack.ioHandler.sendBytes(this.messageProcessor.getIpAddress(),
                //receiverAddress, receiverPort, "TLS", message, retry, this);
        //if (sock != mySock && sock != null) {
            //try {
                //if (mySock != null)
                    //mySock.close();
            //} catch (IOException ex) {
            //}
            //mySock = sock;
            //this.myClientInputStream = mySock.getInputStream();
            //Thread mythread = new Thread(this);
            //mythread.setDaemon(true);
            //mythread.setName("TLSMessageChannelThread");
            //mythread.start();
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.838 -0400", hash_original_method = "F10EA794C47048E710B5CED57C611CE1", hash_generated_method = "588C0A974D5F9045F3D8141797986EB6")
    public void handleException(ParseException ex, SIPMessage sipMessage, Class hdrClass,
            String header, String message) throws ParseException {
        addTaint(message.getTaint());
        addTaint(header.getTaint());
        addTaint(hdrClass.getTaint());
        addTaint(sipMessage.getTaint());
        addTaint(ex.getTaint());
        if(sipStack.isLoggingEnabled())        
        sipStack.getStackLogger().logException(ex);
        if((hdrClass != null)
                && (hdrClass.equals(From.class) || hdrClass.equals(To.class)
                        || hdrClass.equals(CSeq.class) || hdrClass.equals(Via.class)
                        || hdrClass.equals(CallID.class) || hdrClass.equals(RequestLine.class) || hdrClass
                        .equals(StatusLine.class)))        
        {
            if(sipStack.isLoggingEnabled())            
            sipStack.getStackLogger().logDebug("Encountered bad message \n" + message);
            String msgString = sipMessage.toString();
            if(!msgString.startsWith("SIP/") && !msgString.startsWith("ACK "))            
            {
                String badReqRes = createBadReqRes(msgString, ex);
                if(badReqRes != null)                
                {
                    if(sipStack.isLoggingEnabled())                    
                    {
                        sipStack.getStackLogger().logDebug("Sending automatic 400 Bad Request:");
                        sipStack.getStackLogger().logDebug(badReqRes);
                    } //End block
                    try 
                    {
                        this.sendMessage(badReqRes.getBytes(), this.getPeerInetAddress(), this
                                .getPeerPort(), false);
                    } //End block
                    catch (IOException e)
                    {
                        this.sipStack.getStackLogger().logException(e);
                    } //End block
                } //End block
                else
                {
                    if(sipStack.isLoggingEnabled())                    
                    {
                        sipStack.getStackLogger().logDebug(
                                "Could not formulate automatic 400 Bad Request");
                    } //End block
                } //End block
            } //End block
            ex.addTaint(taint);
            throw ex;
        } //End block
        else
        {
            sipMessage.addUnparsed(header);
        } //End block
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.841 -0400", hash_original_method = "0B661C1775BD5742177C9AA146CAD19A", hash_generated_method = "094BD3330572E05F74FD911B206F97A1")
    public void processMessage(SIPMessage sipMessage) throws Exception {
        addTaint(sipMessage.getTaint());
        try 
        {
            if(sipMessage.getFrom() == null || sipMessage.getTo() == null
                    || sipMessage.getCallId() == null || sipMessage.getCSeq() == null
                    || sipMessage.getViaHeaders() == null)            
            {
                String badmsg = sipMessage.encode();
                if(sipStack.isLoggingEnabled())                
                {
                    sipStack.getStackLogger().logError("bad message " + badmsg);
                    sipStack.getStackLogger().logError(">>> Dropped Bad Msg");
                } //End block
                return;
            } //End block
            ViaList viaList = sipMessage.getViaHeaders();
            if(sipMessage instanceof SIPRequest)            
            {
                Via v = (Via) viaList.getFirst();
                Hop hop = sipStack.addressResolver.resolveAddress(v.getHop());
                this.peerProtocol = v.getTransport();
                try 
                {
                    this.peerAddress = mySock.getInetAddress();
                    if(v.hasParameter(Via.RPORT)
                            || !hop.getHost().equals(this.peerAddress.getHostAddress()))                    
                    {
                        v.setParameter(Via.RECEIVED, this.peerAddress.getHostAddress());
                    } //End block
                    v.setParameter(Via.RPORT, Integer.toString(this.peerPort));
                } //End block
                catch (java.text.ParseException ex)
                {
                    InternalErrorHandler.handleException(ex);
                } //End block
                if(!this.isCached)                
                {
                    ((TLSMessageProcessor) this.messageProcessor).cacheMessageChannel(this);
                    this.isCached = true;
                    String key = IOHandler.makeKey(mySock.getInetAddress(), this.peerPort);
                    sipStack.ioHandler.putSocket(key, mySock);
                } //End block
            } //End block
            long receptionTime = System.currentTimeMillis();
            if(sipMessage instanceof SIPRequest)            
            {
                SIPRequest sipRequest = (SIPRequest) sipMessage;
                if(sipStack.isLoggingEnabled())                
                {
                    sipStack.getStackLogger().logDebug("----Processing Message---");
                } //End block
                if(this.sipStack.getStackLogger().isLoggingEnabled(ServerLogger.TRACE_MESSAGES))                
                {
                    sipStack.serverLogger.logMessage(sipMessage, this.getPeerHostPort().toString(),
                            this.messageProcessor.getIpAddress().getHostAddress() + ":"
                                    + this.messageProcessor.getPort(), false, receptionTime);
                } //End block
                if(sipStack.getMaxMessageSize() > 0
                        && sipRequest.getSize()
                                + (sipRequest.getContentLength() == null ? 0 : sipRequest
                                        .getContentLength().getContentLength()) > sipStack
                                .getMaxMessageSize())                
                {
                    SIPResponse sipResponse = sipRequest
                            .createResponse(SIPResponse.MESSAGE_TOO_LARGE);
                    byte[] resp = sipResponse.encodeAsBytes(this.getTransport());
                    this.sendMessage(resp, false);
                    Exception varDF678B266A68FE82BCEBF8807C609878_964840109 = new Exception("Message size exceeded");
                    varDF678B266A68FE82BCEBF8807C609878_964840109.addTaint(taint);
                    throw varDF678B266A68FE82BCEBF8807C609878_964840109;
                } //End block
                ServerRequestInterface sipServerRequest = sipStack.newSIPServerRequest(
                        sipRequest, this);
                if(sipServerRequest != null)                
                {
                    try 
                    {
                        sipServerRequest.processRequest(sipRequest, this);
                    } //End block
                    finally 
                    {
                        if(sipServerRequest instanceof SIPTransaction)                        
                        {
                            SIPServerTransaction sipServerTx = (SIPServerTransaction) sipServerRequest;
                            if(!sipServerTx.passToListener())                            
                            ((SIPTransaction) sipServerRequest).releaseSem();
                        } //End block
                    } //End block
                } //End block
                else
                {
                    SIPResponse response = sipRequest
                            .createResponse(Response.SERVICE_UNAVAILABLE);
                    RetryAfter retryAfter = new RetryAfter();
                    try 
                    {
                        retryAfter.setRetryAfter((int) (10 * (Math.random())));
                        response.setHeader(retryAfter);
                        this.sendMessage(response);
                    } //End block
                    catch (Exception e)
                    {
                    } //End block
                    if(sipStack.isLoggingEnabled())                    
                    sipStack.getStackLogger()
                            .logWarning("Dropping message -- could not acquire semaphore");
                } //End block
            } //End block
            else
            {
                SIPResponse sipResponse = (SIPResponse) sipMessage;
                try 
                {
                    sipResponse.checkHeaders();
                } //End block
                catch (ParseException ex)
                {
                    if(sipStack.isLoggingEnabled())                    
                    sipStack.getStackLogger()
                                .logError("Dropping Badly formatted response message >>> "
                                        + sipResponse);
                    return;
                } //End block
                if(sipStack.getMaxMessageSize() > 0
                        && sipResponse.getSize()
                                + (sipResponse.getContentLength() == null ? 0 : sipResponse
                                        .getContentLength().getContentLength()) > sipStack
                                .getMaxMessageSize())                
                {
                    if(sipStack.isLoggingEnabled())                    
                    this.sipStack.getStackLogger().logDebug("Message size exceeded");
                    return;
                } //End block
                ServerResponseInterface sipServerResponse = sipStack.newSIPServerResponse(
                        sipResponse, this);
                if(sipServerResponse != null)                
                {
                    try 
                    {
                        if(sipServerResponse instanceof SIPClientTransaction
                                && !((SIPClientTransaction) sipServerResponse)
                                        .checkFromTag(sipResponse))                        
                        {
                            if(sipStack.isLoggingEnabled())                            
                            sipStack.getStackLogger()
                                        .logError("Dropping response message with invalid tag >>> "
                                                + sipResponse);
                            return;
                        } //End block
                        sipServerResponse.processResponse(sipResponse, this);
                    } //End block
                    finally 
                    {
                        if(sipServerResponse instanceof SIPTransaction
                                && !((SIPTransaction) sipServerResponse).passToListener())                        
                        {
                            ((SIPTransaction) sipServerResponse).releaseSem();
                        } //End block
                    } //End block
                } //End block
                else
                {
                    sipStack.getStackLogger().logWarning("Could not get semaphore... dropping response");
                } //End block
            } //End block
        } //End block
        finally 
        {
        } //End block
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.843 -0400", hash_original_method = "F77124F9292782877CB3E3F72C9E9606", hash_generated_method = "4FA7C0EB39A797D56DAD727C818F71E4")
    public void run() {
        Pipeline hispipe = null;
        hispipe = new Pipeline(myClientInputStream, sipStack.readTimeout,
                ((SIPTransactionStack) sipStack).getTimer());
        myParser = new PipelinedMsgParser(this, hispipe, this.sipStack.getMaxMessageSize());
        myParser.processInput();
        int bufferSize = 4096;
        this.tlsMessageProcessor.useCount++;
        this.isRunning = true;
        try 
        {
            while
(true)            
            {
                try 
                {
                    byte[] msg = new byte[bufferSize];
                    int nbytes = myClientInputStream.read(msg, 0, bufferSize);
                    if(nbytes == -1)                    
                    {
                        hispipe.write("\r\n\r\n".getBytes("UTF-8"));
                        try 
                        {
                            if(sipStack.maxConnections != -1)                            
                            {
                                synchronized
(tlsMessageProcessor)                                {
                                    tlsMessageProcessor.nConnections--;
                                    tlsMessageProcessor.notify();
                                } //End block
                            } //End block
                            hispipe.close();
                            mySock.close();
                        } //End block
                        catch (IOException ioex)
                        {
                        } //End block
                        return;
                    } //End block
                    hispipe.write(msg, 0, nbytes);
                } //End block
                catch (IOException ex)
                {
                    try 
                    {
                        hispipe.write("\r\n\r\n".getBytes("UTF-8"));
                    } //End block
                    catch (Exception e)
                    {
                    } //End block
                    try 
                    {
                        if(sipStack.isLoggingEnabled())                        
                        sipStack.getStackLogger().logDebug("IOException  closing sock " + ex);
                        try 
                        {
                            if(sipStack.maxConnections != -1)                            
                            {
                                synchronized
(tlsMessageProcessor)                                {
                                    tlsMessageProcessor.nConnections--;
                                    tlsMessageProcessor.notify();
                                } //End block
                            } //End block
                            mySock.close();
                            hispipe.close();
                        } //End block
                        catch (IOException ioex)
                        {
                        } //End block
                    } //End block
                    catch (Exception ex1)
                    {
                    } //End block
                    return;
                } //End block
                catch (Exception ex)
                {
                    InternalErrorHandler.handleException(ex);
                } //End block
            } //End block
        } //End block
        finally 
        {
            this.isRunning = false;
            this.tlsMessageProcessor.remove(this);
            this.tlsMessageProcessor.useCount--;
            this.myParser.close();
        } //End block
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.844 -0400", hash_original_method = "C5292C7D09796AA7B556BAA154B95B16", hash_generated_method = "19BD5C548BA646BC51923EE3FA878094")
    protected void uncache() {
        if(isCached && !isRunning)        
        {
            this.tlsMessageProcessor.remove(this);
        } //End block
        // ---------- Original Method ----------
        //if (isCached && !isRunning) {    	
    		//this.tlsMessageProcessor.remove(this);
    	//}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.844 -0400", hash_original_method = "DA67C78A7E91B7C25B2E6DD841A2A96C", hash_generated_method = "A4C6BDDDBFD4A8AFC02E13843B503A53")
    public boolean equals(Object other) {
        addTaint(other.getTaint());
        if(!this.getClass().equals(other.getClass()))        
        {
        boolean var68934A3E9455FA72420237EB05902327_86062154 = (false);
                boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_1421643735 = getTaintBoolean();
        return var84E2C64F38F78BA3EA5C905AB5A2DA27_1421643735;
        }
        else
        {
            TLSMessageChannel that = (TLSMessageChannel) other;
            if(this.mySock != that.mySock)            
            {
            boolean var68934A3E9455FA72420237EB05902327_1310277271 = (false);
                        boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_2116339049 = getTaintBoolean();
            return var84E2C64F38F78BA3EA5C905AB5A2DA27_2116339049;
            }
            else
            {
            boolean varB326B5062B2F0E69046810717534CB09_1791749817 = (true);
                        boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_1834526381 = getTaintBoolean();
            return var84E2C64F38F78BA3EA5C905AB5A2DA27_1834526381;
            }
        } //End block
        // ---------- Original Method ----------
        //if (!this.getClass().equals(other.getClass()))
            //return false;
        //else {
            //TLSMessageChannel that = (TLSMessageChannel) other;
            //if (this.mySock != that.mySock)
                //return false;
            //else
                //return true;
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.845 -0400", hash_original_method = "1173AC840F0483E84550BBD280ADF1B8", hash_generated_method = "CC8DAD484DB097CA69A4F65FF76C2035")
    public String getKey() {
        if(this.key != null)        
        {
String var28438ADFC1608AABD649C75D36B519BF_1393943877 =             this.key;
            var28438ADFC1608AABD649C75D36B519BF_1393943877.addTaint(taint);
            return var28438ADFC1608AABD649C75D36B519BF_1393943877;
        } //End block
        else
        {
            this.key = MessageChannel.getKey(this.peerAddress, this.peerPort, "TLS");
String var28438ADFC1608AABD649C75D36B519BF_893750622 =             this.key;
            var28438ADFC1608AABD649C75D36B519BF_893750622.addTaint(taint);
            return var28438ADFC1608AABD649C75D36B519BF_893750622;
        } //End block
        // ---------- Original Method ----------
        //if (this.key != null) {
            //return this.key;
        //} else {
            //this.key = MessageChannel.getKey(this.peerAddress, this.peerPort, "TLS");
            //return this.key;
        //}
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.845 -0400", hash_original_method = "11E5C6A8F9691D7C5E18B4FE0A22A301", hash_generated_method = "D57750185024CBDD91D2F7F7C4AC4B4E")
    public String getViaHost() {
String varE7034C1BD80371A3EE602CF085A2802A_239873482 =         myAddress;
        varE7034C1BD80371A3EE602CF085A2802A_239873482.addTaint(taint);
        return varE7034C1BD80371A3EE602CF085A2802A_239873482;
        // ---------- Original Method ----------
        //return myAddress;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.845 -0400", hash_original_method = "97574FAF366AF2CA74D034931ECBD71B", hash_generated_method = "DF0B5A51D86F953596B7FD789EE61266")
    public int getViaPort() {
        int varAB99FC537949F4680FB25A11A38B0042_404492272 = (myPort);
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1814805341 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1814805341;
        // ---------- Original Method ----------
        //return myPort;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.845 -0400", hash_original_method = "356711D154A2D021F9E9DF4BCD609AF2", hash_generated_method = "2F198F021F1456051BCB33B8AD4477A9")
    public int getPeerPort() {
        int varBBD5741C4F9994864582D25DD194C4DE_40248439 = (peerPort);
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_341881491 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_341881491;
        // ---------- Original Method ----------
        //return peerPort;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.845 -0400", hash_original_method = "B210BD4A713B5B982D229CB138437A53", hash_generated_method = "F605D01B5405F64409253ECA8247DB9B")
    public int getPeerPacketSourcePort() {
        int var863AF973F56BABC8062C15431981E0EC_523809531 = (this.peerPort);
                int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_489613215 = getTaintInt();
        return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_489613215;
        // ---------- Original Method ----------
        //return this.peerPort;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.845 -0400", hash_original_method = "97EAFF51EEE94208DA244D522468157C", hash_generated_method = "B224D2D635E84D48E4909D4F8BF5C234")
    public InetAddress getPeerPacketSourceAddress() {
InetAddress varCB519CCE5105BD67F1450F075D5E971F_1066560003 =         this.peerAddress;
        varCB519CCE5105BD67F1450F075D5E971F_1066560003.addTaint(taint);
        return varCB519CCE5105BD67F1450F075D5E971F_1066560003;
        // ---------- Original Method ----------
        //return this.peerAddress;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.846 -0400", hash_original_method = "57D47F2F9FCDF87C613C3BEA50BCDEDC", hash_generated_method = "976DE4D698E4BBED4D0939344CABBBD0")
    public boolean isSecure() {
        boolean varB326B5062B2F0E69046810717534CB09_2089102917 = (true);
                boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_1761520725 = getTaintBoolean();
        return var84E2C64F38F78BA3EA5C905AB5A2DA27_1761520725;
        // ---------- Original Method ----------
        //return true;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.846 -0400", hash_original_method = "17DB109E74C9C419B95A49BDAE918BA7", hash_generated_method = "58C9BB24EBB7523196DDC479C0D53915")
    public void setHandshakeCompletedListener(
            HandshakeCompletedListener handshakeCompletedListenerImpl) {
        this.handshakeCompletedListener = handshakeCompletedListenerImpl;
        // ---------- Original Method ----------
        //this.handshakeCompletedListener = handshakeCompletedListenerImpl;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:42.846 -0400", hash_original_method = "6CFE0616A7B6D8A833DC048924F270CA", hash_generated_method = "9FD60956F2C532B9BAD060F8BDF74C0D")
    public HandshakeCompletedListenerImpl getHandshakeCompletedListener() {
HandshakeCompletedListenerImpl var9A2E431CC033EB690CBDC6286A497C3F_1515518134 =         (HandshakeCompletedListenerImpl) handshakeCompletedListener;
        var9A2E431CC033EB690CBDC6286A497C3F_1515518134.addTaint(taint);
        return var9A2E431CC033EB690CBDC6286A497C3F_1515518134;
        // ---------- Original Method ----------
        //return (HandshakeCompletedListenerImpl) handshakeCompletedListener;
    }

    
}

