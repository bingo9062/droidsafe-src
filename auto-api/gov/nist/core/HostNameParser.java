package gov.nist.core;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;
import java.text.ParseException;

public class HostNameParser extends ParserCore {
    private boolean stripAddressScopeZones = false;
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:19.605 -0400", hash_original_method = "BDF66B8A6A6E733FD4317463FD36B54D", hash_generated_method = "10F3D5E7AE71E8894D0E4F57BCA5E7EF")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public HostNameParser(String hname) {
        dsTaint.addTaint(hname);
        this.lexer = new LexerCore("charLexer", hname);
        stripAddressScopeZones
            = Boolean.getBoolean("gov.nist.core.STRIP_ADDR_SCOPES");
        // ---------- Original Method ----------
        //this.lexer = new LexerCore("charLexer", hname);
        //stripAddressScopeZones
            //= Boolean.getBoolean("gov.nist.core.STRIP_ADDR_SCOPES");
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:19.605 -0400", hash_original_method = "DE9D0F52F3286ED66509E4F5CE87141D", hash_generated_method = "F9A2B30066D1137BFFF94E11B949E99C")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public HostNameParser(LexerCore lexer) {
        dsTaint.addTaint(lexer.dsTaint);
        this.lexer = lexer;
        lexer.selectLexer("charLexer");
        stripAddressScopeZones
            = Boolean.getBoolean("gov.nist.core.STRIP_ADDR_SCOPES");
        // ---------- Original Method ----------
        //this.lexer = lexer;
        //lexer.selectLexer("charLexer");
        //stripAddressScopeZones
            //= Boolean.getBoolean("gov.nist.core.STRIP_ADDR_SCOPES");
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:19.605 -0400", hash_original_method = "4DE693D4586CFA412C55EF35C3691C08", hash_generated_method = "DBA796E5CE34881D94534DF82F8B2BCD")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    protected void consumeDomainLabel() throws ParseException {
        dbg_enter("domainLabel");
        try 
        {
            lexer.consumeValidChars(VALID_DOMAIN_LABEL_CHAR);
        } //End block
        finally 
        {
            dbg_leave("domainLabel");
        } //End block
        // ---------- Original Method ----------
        //if (debug)
            //dbg_enter("domainLabel");
        //try {
            //lexer.consumeValidChars(VALID_DOMAIN_LABEL_CHAR);
        //} finally {
            //if (debug)
                //dbg_leave("domainLabel");
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:19.606 -0400", hash_original_method = "12990E16E23A1648F6B6EE2410C75196", hash_generated_method = "2B10DD3EA2C053A18D748D2FB0750591")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    protected String ipv6Reference() throws ParseException {
        StringBuffer retval;
        retval = new StringBuffer();
        dbg_enter("ipv6Reference");
        try 
        {
            {
                {
                    boolean var255E8660353F5362DB4A60C2DDB0C461_586312411 = (lexer.hasMoreChars());
                    {
                        char la;
                        la = lexer.lookAhead(0);
                        {
                            boolean var994371892917A87989057591FFA23B92_618678841 = (LexerCore.isHexDigit(la) || la == '.' || la == ':'
                            || la == '[');
                            {
                                lexer.consume(1);
                                retval.append(la);
                            } //End block
                            {
                                lexer.consume(1);
                                retval.append(la);
                                String var987524AE42A31E7A1A86AE538108687C_1108341682 = (retval.toString());
                            } //End block
                            {
                                lexer.consume(1);
                                String rest;
                                rest = lexer.getRest();
                                {
                                    boolean varDA96287E1E04F81963DCAB3B1D387F7E_120298311 = (rest == null || rest.length() == 0);
                                } //End collapsed parenthetic
                                int stripLen;
                                stripLen = rest.indexOf(']');
                                lexer.consume(stripLen+1);
                                retval.append("]");
                                String var987524AE42A31E7A1A86AE538108687C_1063714930 = (retval.toString());
                            } //End block
                        } //End collapsed parenthetic
                    } //End block
                } //End collapsed parenthetic
            } //End block
            {
                {
                    boolean var255E8660353F5362DB4A60C2DDB0C461_135132156 = (lexer.hasMoreChars());
                    {
                        char la;
                        la = lexer.lookAhead(0);
                        {
                            boolean var2AC4EE193842A47EBFC6B489F345C5C3_1265123561 = (LexerCore.isHexDigit(la) || la == '.'
                            || la == ':' || la == '[');
                            {
                                lexer.consume(1);
                                retval.append(la);
                            } //End block
                            {
                                lexer.consume(1);
                                retval.append(la);
                                String var987524AE42A31E7A1A86AE538108687C_4942140 = (retval.toString());
                            } //End block
                        } //End collapsed parenthetic
                    } //End block
                } //End collapsed parenthetic
            } //End block
            if (DroidSafeAndroidRuntime.control) throw new ParseException(
                lexer.getBuffer() + ": Illegal Host name ",
                lexer.getPtr());
        } //End block
        finally 
        {
            dbg_leave("ipv6Reference");
        } //End block
        return dsTaint.getTaintString();
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:19.606 -0400", hash_original_method = "59E4521A394425B7E70180091A99EC01", hash_generated_method = "3C6C61F01D75198C66E2FAF3BC22C0AD")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public Host host() throws ParseException {
        dbg_enter("host");
        try 
        {
            String hostname;
            {
                boolean var7F8AD6B3877B097A231B70CAA2872139_2081323748 = (lexer.lookAhead(0) == '[');
                {
                    hostname = ipv6Reference();
                } //End block
                {
                    boolean var6B42F0A72918D59DEEB13AEDBC33A87B_681023497 = (isIPv6Address(lexer.getRest()));
                    {
                        int startPtr;
                        startPtr = lexer.getPtr();
                        lexer.consumeValidChars(
                        new char[] {LexerCore.ALPHADIGIT_VALID_CHARS, ':'});
                        hostname
                    = new StringBuffer("[").append(
                        lexer.getBuffer().substring(startPtr, lexer.getPtr()))
                        .append("]").toString();
                    } //End block
                    {
                        int startPtr;
                        startPtr = lexer.getPtr();
                        consumeDomainLabel();
                        hostname = lexer.getBuffer().substring(startPtr, lexer.getPtr());
                    } //End block
                } //End collapsed parenthetic
            } //End collapsed parenthetic
            {
                boolean var382EE341A83DD8661DDB0D795F508391_758556292 = (hostname.length() == 0);
                if (DroidSafeAndroidRuntime.control) throw new ParseException(
                    lexer.getBuffer() + ": Missing host name",
                    lexer.getPtr());
                Host var80E932A3D9874E45EFEBB09BD3ED0808_1308997217 = (new Host(hostname));
            } //End collapsed parenthetic
        } //End block
        finally 
        {
            dbg_leave("host");
        } //End block
        return (Host)dsTaint.getTaint();
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:19.607 -0400", hash_original_method = "9EB0AD05F46BCDC3EFC8C7DBA5311E53", hash_generated_method = "7294A0F95F6BE149F366231A04796FC2")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private boolean isIPv6Address(String uriHeader) {
        dsTaint.addTaint(uriHeader);
        int hostEnd;
        hostEnd = uriHeader.indexOf(Lexer.QUESTION);
        int semiColonIndex;
        semiColonIndex = uriHeader.indexOf(Lexer.SEMICOLON);
        hostEnd = semiColonIndex;
        hostEnd = uriHeader.length();
        String host;
        host = uriHeader.substring(0, hostEnd);
        int firstColonIndex;
        firstColonIndex = host.indexOf(Lexer.COLON);
        int secondColonIndex;
        secondColonIndex = host.indexOf(Lexer.COLON, firstColonIndex + 1);
        return dsTaint.getTaintBoolean();
        // ---------- Original Method ----------
        //int hostEnd = uriHeader.indexOf(Lexer.QUESTION);
        //int semiColonIndex = uriHeader.indexOf(Lexer.SEMICOLON);
        //if ( hostEnd == -1
            //|| (semiColonIndex!= -1 && hostEnd > semiColonIndex) )
            //hostEnd = semiColonIndex;
        //if ( hostEnd == -1 )
            //hostEnd = uriHeader.length();
        //String host = uriHeader.substring(0, hostEnd);
        //int firstColonIndex = host.indexOf(Lexer.COLON);
        //if(firstColonIndex == -1)
            //return false;
        //int secondColonIndex = host.indexOf(Lexer.COLON, firstColonIndex + 1);
        //if(secondColonIndex == -1)
            //return false;
        //return true;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:19.607 -0400", hash_original_method = "E96E49B49C2326325B1DB743C3D726B3", hash_generated_method = "A77CD1E613ED922C0E28B9733AF06AE0")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public HostPort hostPort( boolean allowWS ) throws ParseException {
        dsTaint.addTaint(allowWS);
        dbg_enter("hostPort");
        try 
        {
            Host host;
            host = this.host();
            HostPort hp;
            hp = new HostPort();
            hp.setHost(host);
            lexer.SPorHT();
            {
                boolean var9783FB04504A12AC5E4CB1806E828D43_221010740 = (lexer.hasMoreChars());
                {
                    char la;
                    la = lexer.lookAhead(0);
                    //Begin case ':' 
                    lexer.consume(1);
                    //End case ':' 
                    //Begin case ':' 
                    lexer.SPorHT();
                    //End case ':' 
                    //Begin case ':' 
                    try 
                    {
                        String port;
                        port = lexer.number();
                        hp.setPort(Integer.parseInt(port));
                    } //End block
                    catch (NumberFormatException nfe)
                    {
                        if (DroidSafeAndroidRuntime.control) throw new ParseException(
                            lexer.getBuffer() + " :Error parsing port ",
                            lexer.getPtr());
                    } //End block
                    //End case ':' 
                    //Begin case default 
                    {
                        if (DroidSafeAndroidRuntime.control) throw new ParseException( lexer.getBuffer() +
                                " Illegal character in hostname:" + lexer.lookAhead(0),
                                lexer.getPtr() );
                    } //End block
                    //End case default 
                } //End block
            } //End collapsed parenthetic
        } //End block
        finally 
        {
            dbg_leave("hostPort");
        } //End block
        return (HostPort)dsTaint.getTaint();
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
        public static void main(String args[]) throws ParseException {
        String hostNames[] =
            {
                "foo.bar.com:1234",
                "proxima.chaplin.bt.co.uk",
                "129.6.55.181:2345",
                ":1234",
                "foo.bar.com:         1234",
                "foo.bar.com     :      1234   ",
                "MIK_S:1234"
            };
        for (int i = 0; i < hostNames.length; i++) {
            try {
                HostNameParser hnp = new HostNameParser(hostNames[i]);
                HostPort hp = hnp.hostPort(true);
                System.out.println("["+hp.encode()+"]");
            } catch (ParseException ex) {
                System.out.println("exception text = " + ex.getMessage());
            }
        }
    }

    
    private static LexerCore Lexer;
    private static final char[] VALID_DOMAIN_LABEL_CHAR =
        new char[] {LexerCore.ALPHADIGIT_VALID_CHARS, '-', '.'};
}

