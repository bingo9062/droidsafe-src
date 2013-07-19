package gov.nist.javax.sip.parser;

// Droidsafe Imports
import gov.nist.core.LexerCore;
import gov.nist.javax.sip.header.extensions.JoinHeader;
import gov.nist.javax.sip.header.extensions.MinSEHeader;
import gov.nist.javax.sip.header.extensions.ReferencesHeader;
import gov.nist.javax.sip.header.extensions.ReferredByHeader;
import gov.nist.javax.sip.header.extensions.ReplacesHeader;
import gov.nist.javax.sip.header.extensions.SessionExpiresHeader;
import gov.nist.javax.sip.header.ims.PAccessNetworkInfoHeader;
import gov.nist.javax.sip.header.ims.PAssertedIdentityHeader;
import gov.nist.javax.sip.header.ims.PAssertedServiceHeader;
import gov.nist.javax.sip.header.ims.PAssociatedURIHeader;
import gov.nist.javax.sip.header.ims.PCalledPartyIDHeader;
import gov.nist.javax.sip.header.ims.PChargingFunctionAddressesHeader;
import gov.nist.javax.sip.header.ims.PChargingVectorHeader;
import gov.nist.javax.sip.header.ims.PMediaAuthorizationHeader;
import gov.nist.javax.sip.header.ims.PPreferredIdentityHeader;
import gov.nist.javax.sip.header.ims.PPreferredServiceHeader;
import gov.nist.javax.sip.header.ims.PProfileKeyHeader;
import gov.nist.javax.sip.header.ims.PServedUserHeader;
import gov.nist.javax.sip.header.ims.PUserDatabaseHeader;
import gov.nist.javax.sip.header.ims.PVisitedNetworkIDHeader;
import gov.nist.javax.sip.header.ims.PathHeader;
import gov.nist.javax.sip.header.ims.PrivacyHeader;
import gov.nist.javax.sip.header.ims.SecurityClientHeader;
import gov.nist.javax.sip.header.ims.SecurityServerHeader;
import gov.nist.javax.sip.header.ims.SecurityVerifyHeader;
import gov.nist.javax.sip.header.ims.ServiceRouteHeader;

import java.util.Hashtable;

import javax.sip.header.AcceptEncodingHeader;
import javax.sip.header.AcceptHeader;
import javax.sip.header.AcceptLanguageHeader;
import javax.sip.header.AlertInfoHeader;
import javax.sip.header.AllowEventsHeader;
import javax.sip.header.AllowHeader;
import javax.sip.header.AuthenticationInfoHeader;
import javax.sip.header.AuthorizationHeader;
import javax.sip.header.CSeqHeader;
import javax.sip.header.CallIdHeader;
import javax.sip.header.CallInfoHeader;
import javax.sip.header.ContactHeader;
import javax.sip.header.ContentDispositionHeader;
import javax.sip.header.ContentEncodingHeader;
import javax.sip.header.ContentLanguageHeader;
import javax.sip.header.ContentLengthHeader;
import javax.sip.header.ContentTypeHeader;
import javax.sip.header.DateHeader;
import javax.sip.header.ErrorInfoHeader;
import javax.sip.header.EventHeader;
import javax.sip.header.ExpiresHeader;
import javax.sip.header.FromHeader;
import javax.sip.header.InReplyToHeader;
import javax.sip.header.MaxForwardsHeader;
import javax.sip.header.MimeVersionHeader;
import javax.sip.header.MinExpiresHeader;
import javax.sip.header.OrganizationHeader;
import javax.sip.header.PriorityHeader;
import javax.sip.header.ProxyAuthenticateHeader;
import javax.sip.header.ProxyAuthorizationHeader;
import javax.sip.header.ProxyRequireHeader;
import javax.sip.header.RAckHeader;
import javax.sip.header.RSeqHeader;
import javax.sip.header.ReasonHeader;
import javax.sip.header.RecordRouteHeader;
import javax.sip.header.ReferToHeader;
import javax.sip.header.ReplyToHeader;
import javax.sip.header.RequireHeader;
import javax.sip.header.RetryAfterHeader;
import javax.sip.header.RouteHeader;
import javax.sip.header.SIPETagHeader;
import javax.sip.header.SIPIfMatchHeader;
import javax.sip.header.ServerHeader;
import javax.sip.header.SubjectHeader;
import javax.sip.header.SubscriptionStateHeader;
import javax.sip.header.SupportedHeader;
import javax.sip.header.TimeStampHeader;
import javax.sip.header.ToHeader;
import javax.sip.header.UnsupportedHeader;
import javax.sip.header.UserAgentHeader;
import javax.sip.header.ViaHeader;
import javax.sip.header.WWWAuthenticateHeader;
import javax.sip.header.WarningHeader;

import droidsafe.annotations.DSC;
import droidsafe.annotations.DSGenerator;
import droidsafe.annotations.DSModeled;

public class Lexer extends LexerCore {
    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:37.759 -0400", hash_original_method = "1890ACF170ADFCBA047D359B33ABA230", hash_generated_method = "7AD0628F236553F5847A73488C6F1B5F")
    public  Lexer(String lexerName, String buffer) {
        super(lexerName, buffer);
        addTaint(buffer.getTaint());
        addTaint(lexerName.getTaint());
        this.selectLexer(lexerName);
        // ---------- Original Method ----------
        //this.selectLexer(lexerName);
    }

    
    public static String getHeaderName(String line) {
        if (line == null)
            return null;
        String headerName = null;
        try {
            int begin = line.indexOf(":");
            headerName = null;
            if (begin >= 1)
                headerName = line.substring(0, begin).trim();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        return headerName;
    }

    
    @DSModeled(DSC.SAFE)
    public static String getHeaderValue(String line) {
        if (line == null)
            return null;
        String headerValue = null;
        try {
            int begin = line.indexOf(":");
            headerValue = line.substring(begin + 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        return headerValue;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:37.763 -0400", hash_original_method = "524EFDA3A3BEC88BDD052CB8EB3761F7", hash_generated_method = "0EB5C0F95D2A88875ACE2347C5727BB0")
    public void selectLexer(String lexerName) {
        addTaint(lexerName.getTaint());
        synchronized
(lexerTables)        {
            currentLexer = (Hashtable) lexerTables.get(lexerName);
            this.currentLexerName = lexerName;
            if(currentLexer == null)            
            {
                addLexer(lexerName);
                if(lexerName.equals("method_keywordLexer"))                
                {
                    addKeyword(TokenNames.REGISTER, TokenTypes.REGISTER);
                    addKeyword(TokenNames.ACK, TokenTypes.ACK);
                    addKeyword(TokenNames.OPTIONS, TokenTypes.OPTIONS);
                    addKeyword(TokenNames.BYE, TokenTypes.BYE);
                    addKeyword(TokenNames.INVITE, TokenTypes.INVITE);
                    addKeyword(TokenNames.SIP.toUpperCase(), TokenTypes.SIP);
                    addKeyword(TokenNames.SIPS.toUpperCase(), TokenTypes.SIPS);
                    addKeyword(TokenNames.SUBSCRIBE, TokenTypes.SUBSCRIBE);
                    addKeyword(TokenNames.NOTIFY, TokenTypes.NOTIFY);
                    addKeyword(TokenNames.MESSAGE, TokenTypes.MESSAGE);
                    addKeyword(TokenNames.PUBLISH, TokenTypes.PUBLISH);
                } //End block
                else
                if(lexerName.equals("command_keywordLexer"))                
                {
                    addKeyword(ErrorInfoHeader.NAME.toUpperCase(),
                            TokenTypes.ERROR_INFO);
                    addKeyword(AllowEventsHeader.NAME.toUpperCase(),
                            TokenTypes.ALLOW_EVENTS);
                    addKeyword(AuthenticationInfoHeader.NAME.toUpperCase(),
                            TokenTypes.AUTHENTICATION_INFO);
                    addKeyword(EventHeader.NAME.toUpperCase(), TokenTypes.EVENT);
                    addKeyword(MinExpiresHeader.NAME.toUpperCase(),
                            TokenTypes.MIN_EXPIRES);
                    addKeyword(RSeqHeader.NAME.toUpperCase(), TokenTypes.RSEQ);
                    addKeyword(RAckHeader.NAME.toUpperCase(), TokenTypes.RACK);
                    addKeyword(ReasonHeader.NAME.toUpperCase(),
                            TokenTypes.REASON);
                    addKeyword(ReplyToHeader.NAME.toUpperCase(),
                            TokenTypes.REPLY_TO);
                    addKeyword(SubscriptionStateHeader.NAME.toUpperCase(),
                            TokenTypes.SUBSCRIPTION_STATE);
                    addKeyword(TimeStampHeader.NAME.toUpperCase(),
                            TokenTypes.TIMESTAMP);
                    addKeyword(InReplyToHeader.NAME.toUpperCase(),
                            TokenTypes.IN_REPLY_TO);
                    addKeyword(MimeVersionHeader.NAME.toUpperCase(),
                            TokenTypes.MIME_VERSION);
                    addKeyword(AlertInfoHeader.NAME.toUpperCase(),
                            TokenTypes.ALERT_INFO);
                    addKeyword(FromHeader.NAME.toUpperCase(), TokenTypes.FROM);
                    addKeyword(ToHeader.NAME.toUpperCase(), TokenTypes.TO);
                    addKeyword(ReferToHeader.NAME.toUpperCase(),
                            TokenTypes.REFER_TO);
                    addKeyword(ViaHeader.NAME.toUpperCase(), TokenTypes.VIA);
                    addKeyword(UserAgentHeader.NAME.toUpperCase(),
                            TokenTypes.USER_AGENT);
                    addKeyword(ServerHeader.NAME.toUpperCase(),
                            TokenTypes.SERVER);
                    addKeyword(AcceptEncodingHeader.NAME.toUpperCase(),
                            TokenTypes.ACCEPT_ENCODING);
                    addKeyword(AcceptHeader.NAME.toUpperCase(),
                            TokenTypes.ACCEPT);
                    addKeyword(AllowHeader.NAME.toUpperCase(), TokenTypes.ALLOW);
                    addKeyword(RouteHeader.NAME.toUpperCase(), TokenTypes.ROUTE);
                    addKeyword(AuthorizationHeader.NAME.toUpperCase(),
                            TokenTypes.AUTHORIZATION);
                    addKeyword(ProxyAuthorizationHeader.NAME.toUpperCase(),
                            TokenTypes.PROXY_AUTHORIZATION);
                    addKeyword(RetryAfterHeader.NAME.toUpperCase(),
                            TokenTypes.RETRY_AFTER);
                    addKeyword(ProxyRequireHeader.NAME.toUpperCase(),
                            TokenTypes.PROXY_REQUIRE);
                    addKeyword(ContentLanguageHeader.NAME.toUpperCase(),
                            TokenTypes.CONTENT_LANGUAGE);
                    addKeyword(UnsupportedHeader.NAME.toUpperCase(),
                            TokenTypes.UNSUPPORTED);
                    addKeyword(SupportedHeader.NAME.toUpperCase(),
                            TokenTypes.SUPPORTED);
                    addKeyword(WarningHeader.NAME.toUpperCase(),
                            TokenTypes.WARNING);
                    addKeyword(MaxForwardsHeader.NAME.toUpperCase(),
                            TokenTypes.MAX_FORWARDS);
                    addKeyword(DateHeader.NAME.toUpperCase(), TokenTypes.DATE);
                    addKeyword(PriorityHeader.NAME.toUpperCase(),
                            TokenTypes.PRIORITY);
                    addKeyword(ProxyAuthenticateHeader.NAME.toUpperCase(),
                            TokenTypes.PROXY_AUTHENTICATE);
                    addKeyword(ContentEncodingHeader.NAME.toUpperCase(),
                            TokenTypes.CONTENT_ENCODING);
                    addKeyword(ContentLengthHeader.NAME.toUpperCase(),
                            TokenTypes.CONTENT_LENGTH);
                    addKeyword(SubjectHeader.NAME.toUpperCase(),
                            TokenTypes.SUBJECT);
                    addKeyword(ContentTypeHeader.NAME.toUpperCase(),
                            TokenTypes.CONTENT_TYPE);
                    addKeyword(ContactHeader.NAME.toUpperCase(),
                            TokenTypes.CONTACT);
                    addKeyword(CallIdHeader.NAME.toUpperCase(),
                            TokenTypes.CALL_ID);
                    addKeyword(RequireHeader.NAME.toUpperCase(),
                            TokenTypes.REQUIRE);
                    addKeyword(ExpiresHeader.NAME.toUpperCase(),
                            TokenTypes.EXPIRES);
                    addKeyword(RecordRouteHeader.NAME.toUpperCase(),
                            TokenTypes.RECORD_ROUTE);
                    addKeyword(OrganizationHeader.NAME.toUpperCase(),
                            TokenTypes.ORGANIZATION);
                    addKeyword(CSeqHeader.NAME.toUpperCase(), TokenTypes.CSEQ);
                    addKeyword(AcceptLanguageHeader.NAME.toUpperCase(),
                            TokenTypes.ACCEPT_LANGUAGE);
                    addKeyword(WWWAuthenticateHeader.NAME.toUpperCase(),
                            TokenTypes.WWW_AUTHENTICATE);
                    addKeyword(CallInfoHeader.NAME.toUpperCase(),
                            TokenTypes.CALL_INFO);
                    addKeyword(ContentDispositionHeader.NAME.toUpperCase(),
                            TokenTypes.CONTENT_DISPOSITION);
                    addKeyword(TokenNames.K.toUpperCase(), TokenTypes.SUPPORTED);
                    addKeyword(TokenNames.C.toUpperCase(),
                            TokenTypes.CONTENT_TYPE);
                    addKeyword(TokenNames.E.toUpperCase(),
                            TokenTypes.CONTENT_ENCODING);
                    addKeyword(TokenNames.F.toUpperCase(), TokenTypes.FROM);
                    addKeyword(TokenNames.I.toUpperCase(), TokenTypes.CALL_ID);
                    addKeyword(TokenNames.M.toUpperCase(), TokenTypes.CONTACT);
                    addKeyword(TokenNames.L.toUpperCase(),
                            TokenTypes.CONTENT_LENGTH);
                    addKeyword(TokenNames.S.toUpperCase(), TokenTypes.SUBJECT);
                    addKeyword(TokenNames.T.toUpperCase(), TokenTypes.TO);
                    addKeyword(TokenNames.U.toUpperCase(),
                            TokenTypes.ALLOW_EVENTS);
                    addKeyword(TokenNames.V.toUpperCase(), TokenTypes.VIA);
                    addKeyword(TokenNames.R.toUpperCase(), TokenTypes.REFER_TO);
                    addKeyword(TokenNames.O.toUpperCase(), TokenTypes.EVENT);
                    addKeyword(TokenNames.X.toUpperCase(), TokenTypes.SESSIONEXPIRES_TO);
                    addKeyword(SIPETagHeader.NAME.toUpperCase(),
                            TokenTypes.SIP_ETAG);
                    addKeyword(SIPIfMatchHeader.NAME.toUpperCase(),
                            TokenTypes.SIP_IF_MATCH);
                    addKeyword(SessionExpiresHeader.NAME.toUpperCase(),
                            TokenTypes.SESSIONEXPIRES_TO);
                    addKeyword(MinSEHeader.NAME.toUpperCase(),
                            TokenTypes.MINSE_TO);
                    addKeyword(ReferredByHeader.NAME.toUpperCase(),
                            TokenTypes.REFERREDBY_TO);
                    addKeyword(ReplacesHeader.NAME.toUpperCase(),
                            TokenTypes.REPLACES_TO);
                    addKeyword(JoinHeader.NAME.toUpperCase(),
                            TokenTypes.JOIN_TO);
                    addKeyword(PathHeader.NAME.toUpperCase(), TokenTypes.PATH);
                    addKeyword(ServiceRouteHeader.NAME.toUpperCase(),
                            TokenTypes.SERVICE_ROUTE);
                    addKeyword(PAssertedIdentityHeader.NAME.toUpperCase(),
                            TokenTypes.P_ASSERTED_IDENTITY);
                    addKeyword(PPreferredIdentityHeader.NAME.toUpperCase(),
                            TokenTypes.P_PREFERRED_IDENTITY);
                    addKeyword(PrivacyHeader.NAME.toUpperCase(),
                            TokenTypes.PRIVACY);
                    addKeyword(PCalledPartyIDHeader.NAME.toUpperCase(),
                            TokenTypes.P_CALLED_PARTY_ID);
                    addKeyword(PAssociatedURIHeader.NAME.toUpperCase(),
                            TokenTypes.P_ASSOCIATED_URI);
                    addKeyword(PVisitedNetworkIDHeader.NAME.toUpperCase(),
                            TokenTypes.P_VISITED_NETWORK_ID);
                    addKeyword(PChargingFunctionAddressesHeader.NAME
                            .toUpperCase(),
                            TokenTypes.P_CHARGING_FUNCTION_ADDRESSES);
                    addKeyword(PChargingVectorHeader.NAME.toUpperCase(),
                            TokenTypes.P_VECTOR_CHARGING);
                    addKeyword(PAccessNetworkInfoHeader.NAME.toUpperCase(),
                            TokenTypes.P_ACCESS_NETWORK_INFO);
                    addKeyword(PMediaAuthorizationHeader.NAME.toUpperCase(),
                            TokenTypes.P_MEDIA_AUTHORIZATION);
                    addKeyword(SecurityServerHeader.NAME.toUpperCase(),
                            TokenTypes.SECURITY_SERVER);
                    addKeyword(SecurityVerifyHeader.NAME.toUpperCase(),
                            TokenTypes.SECURITY_VERIFY);
                    addKeyword(SecurityClientHeader.NAME.toUpperCase(),
                            TokenTypes.SECURITY_CLIENT);
                    addKeyword(PUserDatabaseHeader.NAME.toUpperCase(),
                            TokenTypes.P_USER_DATABASE);
                    addKeyword(PProfileKeyHeader.NAME.toUpperCase(),
                            TokenTypes.P_PROFILE_KEY);
                    addKeyword(PServedUserHeader.NAME.toUpperCase(),
                            TokenTypes.P_SERVED_USER);
                    addKeyword(PPreferredServiceHeader.NAME.toUpperCase(),
                            TokenTypes.P_PREFERRED_SERVICE);
                    addKeyword(PAssertedServiceHeader.NAME.toUpperCase(),
                            TokenTypes.P_ASSERTED_SERVICE);
                    addKeyword(ReferencesHeader.NAME.toUpperCase(),TokenTypes.REFERENCES);
                } //End block
                else
                if(lexerName.equals("status_lineLexer"))                
                {
                    addKeyword(TokenNames.SIP.toUpperCase(), TokenTypes.SIP);
                } //End block
                else
                if(lexerName.equals("request_lineLexer"))                
                {
                    addKeyword(TokenNames.SIP.toUpperCase(), TokenTypes.SIP);
                } //End block
                else
                if(lexerName.equals("sip_urlLexer"))                
                {
                    addKeyword(TokenNames.TEL.toUpperCase(), TokenTypes.TEL);
                    addKeyword(TokenNames.SIP.toUpperCase(), TokenTypes.SIP);
                    addKeyword(TokenNames.SIPS.toUpperCase(), TokenTypes.SIPS);
                } //End block
            } //End block
        } //End block
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
}

