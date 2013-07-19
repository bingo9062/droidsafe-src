package org.apache.harmony.xnet.provider.jsse;

// Droidsafe Imports
import java.io.PrintStream;

import libcore.util.EmptyArray;
import droidsafe.annotations.DSC;
import droidsafe.annotations.DSGeneratedField;
import droidsafe.annotations.DSGenerator;
import droidsafe.annotations.DSModeled;

public class Logger {
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:31.666 -0400", hash_original_method = "E12CB5567684A77D5AAB00FDDBE756D7", hash_generated_method = "E12CB5567684A77D5AAB00FDDBE756D7")
    public Logger ()
    {
        //Synthesized constructor
    }


    public static Stream getStream(String name) {
        for (int i=0; i<names.length; i++) {
            if (names[i].equals(name)) {
                return new Stream(name);
            }
        }
        return null;
    }

    
    public static class Stream extends PrintStream {
        @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:31.666 -0400", hash_original_field = "851F5AC9941D720844D143ED9CFCF60A", hash_generated_field = "F717D382565968ED95842821FAEC62F9")

        private String prefix;
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:31.666 -0400", hash_original_method = "5B47239B5174F6EBCA9E4F35C8BFA346", hash_generated_method = "C1A387A4B681305609275C7F863AB8E4")
        public  Stream(String name) {
            super(System.err);
            prefix = name + "["+Thread.currentThread().getName()+"] ";
            // ---------- Original Method ----------
            //prefix = name + "["+Thread.currentThread().getName()+"] ";
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:31.667 -0400", hash_original_method = "D4FE33DE2CA80BB3708E6A0E802F0C32", hash_generated_method = "169A0D7024A07C7ACE474062C763ED75")
        @Override
        public void print(String msg) {
            addTaint(msg.getTaint());
for(int i=0;i<indent;i++)
            {
                super.print("  ");
            } //End block
            super.print(msg);
            // ---------- Original Method ----------
            //for (int i=0; i<indent; i++) {
                //super.print("  ");
            //}
            //super.print(msg);
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:31.667 -0400", hash_original_method = "330ED05F95D8039149C4EE202C44E32C", hash_generated_method = "47144670D508CFD561143F4799BC76AB")
        public void newIndent() {
            indent ++;
            // ---------- Original Method ----------
            //indent ++;
        }

        
                @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:31.667 -0400", hash_original_method = "85AD8C1F1AFBCA83430C30D8B2614DB0", hash_generated_method = "06F7FF18FCADBC918591EB35F5BEEA6F")
        public void endIndent() {
            indent --;
            // ---------- Original Method ----------
            //indent --;
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:31.667 -0400", hash_original_method = "9FCCAC1E0D29D125BE9308EF391306A0", hash_generated_method = "69289A700A38602A4EFDF2DFE85A0F6B")
        @Override
        public void println(String msg) {
            addTaint(msg.getTaint());
            print(prefix);
            super.println(msg);
            // ---------- Original Method ----------
            //print(prefix);
            //super.println(msg);
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:31.668 -0400", hash_original_method = "8FD37FA4B52BC22F9117E57A4C975FCE", hash_generated_method = "ED8D0DE2E5AA0B4C2F4FE94AD6949ED6")
        public void print(byte[] data) {
            addTaint(data[0]);
            printAsHex(16, " ", "", data, 0, data.length);
            // ---------- Original Method ----------
            //printAsHex(16, " ", "", data, 0, data.length);
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:31.668 -0400", hash_original_method = "97F4A1EEA64A5AE021D275219F0FBD08", hash_generated_method = "BB181D24226617F36B2C948E761DB4AF")
        public void print(byte[] data, int offset, int len) {
            addTaint(len);
            addTaint(offset);
            addTaint(data[0]);
            printAsHex(16, " ", "", data, offset, len);
            // ---------- Original Method ----------
            //printAsHex(16, " ", "", data, offset, len);
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:31.669 -0400", hash_original_method = "6BD913AAA4D9B14F7C28D0536E7C7B24", hash_generated_method = "88C77C40CE254F14A3BBEE66CBD686B5")
        public void printAsHex(int perLine, String prefix, String delimiter, byte[] data) {
            addTaint(data[0]);
            addTaint(delimiter.getTaint());
            addTaint(prefix.getTaint());
            addTaint(perLine);
            printAsHex(perLine, prefix, delimiter, data, 0, data.length);
            // ---------- Original Method ----------
            //printAsHex(perLine, prefix, delimiter, data, 0, data.length);
        }

        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:31.669 -0400", hash_original_method = "FDE1B5B6670F726EAB891149ED6137F5", hash_generated_method = "F8B86C6DEA689116C8D721EC1A63959F")
        public void printAsHex(int perLine, String prefix, String delimiter,
                byte[] data, int offset, int len) {
            addTaint(len);
            addTaint(offset);
            addTaint(data[0]);
            addTaint(delimiter.getTaint());
            addTaint(prefix.getTaint());
            addTaint(perLine);
            StringBuilder line = new StringBuilder();
for(int i = 0;i < len;i++)
            {
                line.append(prefix);
                line.append(Byte.toHexString(data[i+offset], false));
                line.append(delimiter);
                if(((i+1)%perLine) == 0)                
                {
                    super.println(line.toString());
                    line = new StringBuilder();
                } //End block
            } //End block
            super.println(line.toString());
            // ---------- Original Method ----------
            //StringBuilder line = new StringBuilder();
            //for (int i = 0; i < len; i++) {
                //line.append(prefix);
                //line.append(Byte.toHexString(data[i+offset], false));
                //line.append(delimiter);
                //if (((i+1)%perLine) == 0) {
                    //super.println(line.toString());
                    //line = new StringBuilder();
                //}
            //}
            //super.println(line.toString());
        }

        
        @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:31.670 -0400", hash_original_field = "423320F92FC463A2B5F25B94F633420D", hash_generated_field = "CC16B6CC34CB003B39D25C6316DD75BD")

        private static int indent = 0;
    }


    
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:31.670 -0400", hash_original_field = "A8998C31A141924D06220074FCDC6925", hash_generated_field = "8B709FD8D963B8653EBAE16DA6B1F60B")

    private static String[] names;
    static {
        try {
            names = System.getProperty("jsse", "").split(",");
        } catch (Exception e) {
            names = EmptyArray.STRING;
        }
    }
    
}

