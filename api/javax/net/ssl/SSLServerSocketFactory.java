package javax.net.ssl;

// Droidsafe Imports
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.net.ServerSocketFactory;

import droidsafe.annotations.DSC;
import droidsafe.annotations.DSGeneratedField;
import droidsafe.annotations.DSGenerator;
import droidsafe.annotations.DSModeled;

public abstract class SSLServerSocketFactory extends ServerSocketFactory {
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:17.774 -0400", hash_original_method = "BC3ACB83646E5282AC0839BB4C043770", hash_generated_method = "84A6B101064020819BA93021BE097E95")
    protected  SSLServerSocketFactory() {
        // ---------- Original Method ----------
    }

    
    public static synchronized ServerSocketFactory getDefault() {
        if (defaultServerSocketFactory != null) {
            return defaultServerSocketFactory;
        }
        if (defaultName == null) {
            defaultName = Security.getProperty("ssl.ServerSocketFactory.provider");
            if (defaultName != null) {
                ClassLoader cl = Thread.currentThread().getContextClassLoader();
                if (cl == null) {
                    cl = ClassLoader.getSystemClassLoader();
                }
                try {
                    final Class<?> ssfc = Class.forName(defaultName, true, cl);
                    defaultServerSocketFactory = (ServerSocketFactory) ssfc.newInstance();
                } catch (Exception e) {
                }
            }
        }
        if (defaultServerSocketFactory == null) {
            SSLContext context;
            try {
                context = SSLContext.getDefault();
            } catch (NoSuchAlgorithmException e) {
                context = null;
            }
            if (context != null) {
                defaultServerSocketFactory = context.getServerSocketFactory();
            }
        }
        if (defaultServerSocketFactory == null) {
            defaultServerSocketFactory = new DefaultSSLServerSocketFactory(
                    "No ServerSocketFactory installed");
        }
        return defaultServerSocketFactory;
    }

    
    @DSModeled(DSC.SAFE)
    public abstract String[] getDefaultCipherSuites();

    
    @DSModeled(DSC.SAFE)
    public abstract String[] getSupportedCipherSuites();

    
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:17.776 -0400", hash_original_field = "5DB6A8C6218DA910977120B7A0BE7941", hash_generated_field = "7C978783A0909A95718EDCDB63F65EBA")

    private static ServerSocketFactory defaultServerSocketFactory;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:17.776 -0400", hash_original_field = "3E15478143ED323D8F1D3CE8D42780EA", hash_generated_field = "B5EAD347CF9492DD929521E60E38417F")

    private static String defaultName;
}

