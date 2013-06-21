package java.util;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;

// needed for enhanced for control translations
import java.util.Iterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;

public class SimpleTimeZone extends TimeZone {
    private int rawOffset;
    private int startYear, startMonth, startDay, startDayOfWeek, startTime;
    private int endMonth, endDay, endDayOfWeek, endTime;
    private int startMode, endMode;
    private boolean useDaylight;
    private int dstSavings = 3600000;
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.649 -0400", hash_original_method = "A73AE5A515785364E3BAF8A0CE898B7F", hash_generated_method = "A26CD17E68FCF8A88AEE12B097182347")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public SimpleTimeZone(int offset, final String name) {
        dsTaint.addTaint(name);
        dsTaint.addTaint(offset);
        setID(name);
        // ---------- Original Method ----------
        //setID(name);
        //rawOffset = offset;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.649 -0400", hash_original_method = "8CE6DF24772062229638C2D4C95CF5BA", hash_generated_method = "EA7233D642B9AE276C6BC4850E1770E8")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public SimpleTimeZone(int offset, String name, int startMonth,
            int startDay, int startDayOfWeek, int startTime, int endMonth,
            int endDay, int endDayOfWeek, int endTime) {
        this(offset, name, startMonth, startDay, startDayOfWeek, startTime,
                endMonth, endDay, endDayOfWeek, endTime, 3600000);
        dsTaint.addTaint(startTime);
        dsTaint.addTaint(startDayOfWeek);
        dsTaint.addTaint(endDay);
        dsTaint.addTaint(endDayOfWeek);
        dsTaint.addTaint(startDay);
        dsTaint.addTaint(name);
        dsTaint.addTaint(endTime);
        dsTaint.addTaint(startMonth);
        dsTaint.addTaint(offset);
        dsTaint.addTaint(endMonth);
        // ---------- Original Method ----------
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.650 -0400", hash_original_method = "8623FC95180B9E56345F7D06857248B1", hash_generated_method = "71C907845D55A60FD1CD5165281B2289")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public SimpleTimeZone(int offset, String name, int startMonth,
            int startDay, int startDayOfWeek, int startTime, int endMonth,
            int endDay, int endDayOfWeek, int endTime, int daylightSavings) {
        this(offset, name);
        dsTaint.addTaint(startTime);
        dsTaint.addTaint(startDayOfWeek);
        dsTaint.addTaint(endDay);
        dsTaint.addTaint(endDayOfWeek);
        dsTaint.addTaint(startDay);
        dsTaint.addTaint(name);
        dsTaint.addTaint(endTime);
        dsTaint.addTaint(startMonth);
        dsTaint.addTaint(offset);
        dsTaint.addTaint(daylightSavings);
        dsTaint.addTaint(endMonth);
        {
            if (DroidSafeAndroidRuntime.control) throw new IllegalArgumentException("Invalid daylightSavings: " + daylightSavings);
        } //End block
        setStartRule(startMonth, startDay, startDayOfWeek, startTime);
        setEndRule(endMonth, endDay, endDayOfWeek, endTime);
        // ---------- Original Method ----------
        //if (daylightSavings <= 0) {
            //throw new IllegalArgumentException("Invalid daylightSavings: " + daylightSavings);
        //}
        //dstSavings = daylightSavings;
        //setStartRule(startMonth, startDay, startDayOfWeek, startTime);
        //setEndRule(endMonth, endDay, endDayOfWeek, endTime);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.650 -0400", hash_original_method = "79A83CB077FC893587B86C22BBA871F3", hash_generated_method = "BCC673C32FDD9B23C1EB1019CCEC7896")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public SimpleTimeZone(int offset, String name, int startMonth,
            int startDay, int startDayOfWeek, int startTime, int startTimeMode,
            int endMonth, int endDay, int endDayOfWeek, int endTime,
            int endTimeMode, int daylightSavings) {
        this(offset, name, startMonth, startDay, startDayOfWeek, startTime,
                endMonth, endDay, endDayOfWeek, endTime, daylightSavings);
        dsTaint.addTaint(startTime);
        dsTaint.addTaint(startDayOfWeek);
        dsTaint.addTaint(endDay);
        dsTaint.addTaint(endTimeMode);
        dsTaint.addTaint(startTimeMode);
        dsTaint.addTaint(endDayOfWeek);
        dsTaint.addTaint(startDay);
        dsTaint.addTaint(name);
        dsTaint.addTaint(endTime);
        dsTaint.addTaint(startMonth);
        dsTaint.addTaint(offset);
        dsTaint.addTaint(daylightSavings);
        dsTaint.addTaint(endMonth);
        // ---------- Original Method ----------
        //startMode = startTimeMode;
        //endMode = endTimeMode;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.650 -0400", hash_original_method = "826DFB88845863F51C3C619017A5C62F", hash_generated_method = "34CDE1807421AEE86079E7D91D3FA80F")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public Object clone() {
        SimpleTimeZone zone;
        zone = (SimpleTimeZone) super.clone();
        return (Object)dsTaint.getTaint();
        // ---------- Original Method ----------
        //SimpleTimeZone zone = (SimpleTimeZone) super.clone();
        //return zone;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.651 -0400", hash_original_method = "6B0E934BFCA438C9EF8D8ACC7F42CB32", hash_generated_method = "70EC52029FDE57A5F953A481127C8CDB")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public boolean equals(Object object) {
        dsTaint.addTaint(object.dsTaint);
        SimpleTimeZone tz;
        tz = (SimpleTimeZone) object;
        boolean varAEEAE6FFE43EE54ACEB5105F2E3707CA_1851013339 = (getID().equals(tz.getID())
                && rawOffset == tz.rawOffset
                && useDaylight == tz.useDaylight
                && (!useDaylight || (startYear == tz.startYear
                        && startMonth == tz.startMonth
                        && startDay == tz.startDay && startMode == tz.startMode
                        && startDayOfWeek == tz.startDayOfWeek
                        && startTime == tz.startTime && endMonth == tz.endMonth
                        && endDay == tz.endDay
                        && endDayOfWeek == tz.endDayOfWeek
                        && endTime == tz.endTime && endMode == tz.endMode && dstSavings == tz.dstSavings)));
        return dsTaint.getTaintBoolean();
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.651 -0400", hash_original_method = "80F5C5F4A535ECB3386672D31D5CF9AA", hash_generated_method = "EDC19CD3750BAEBB08411F1FFDDFA3F2")
    @DSModeled(DSC.SAFE)
    @Override
    public int getDSTSavings() {
        return dsTaint.getTaintInt();
        // ---------- Original Method ----------
        //if (!useDaylight) {
            //return 0;
        //}
        //return dstSavings;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.652 -0400", hash_original_method = "8DB3C09D8D79030897991A8AF2C3603A", hash_generated_method = "11DD1622AF70C32FE16FAC3CBB9208BD")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public int getOffset(int era, int year, int month, int day, int dayOfWeek, int time) {
        dsTaint.addTaint(time);
        dsTaint.addTaint(month);
        dsTaint.addTaint(year);
        dsTaint.addTaint(era);
        dsTaint.addTaint(day);
        dsTaint.addTaint(dayOfWeek);
        {
            if (DroidSafeAndroidRuntime.control) throw new IllegalArgumentException("Invalid era: " + era);
        } //End block
        checkRange(month, dayOfWeek, time);
        {
            boolean varDDE26ED6F6F2D378E54C9D0FD807F9B0_1730434947 = (month != Calendar.FEBRUARY || day != 29 || !isLeapYear(year));
            {
                checkDay(month, day);
            } //End block
        } //End collapsed parenthetic
        {
            boolean varAE28A042D21B15EB2BF786A48B929263_1090611813 = (!useDaylightTime() || era != GregorianCalendar.AD || year < startYear);
        } //End collapsed parenthetic
        int ruleDay, daysInMonth, firstDayOfMonth;
        ruleDay = 0;
        firstDayOfMonth = mod7(dayOfWeek - day);
        {
            //Begin case DOM_MODE 
            ruleDay = startDay;
            //End case DOM_MODE 
            //Begin case DOW_IN_MONTH_MODE 
            {
                ruleDay = mod7(startDayOfWeek - firstDayOfMonth) + 1
                                + (startDay - 1) * 7;
            } //End block
            {
                daysInMonth = GregorianCalendar.DaysInMonth[startMonth];
                {
                    boolean var34F59880D95385B2DD6E1245C2952EE3_451992193 = (startMonth == Calendar.FEBRUARY && isLeapYear(
                                year));
                    {
                        daysInMonth += 1;
                    } //End block
                } //End collapsed parenthetic
                ruleDay = daysInMonth
                                + 1
                                + mod7(startDayOfWeek
                                - (firstDayOfMonth + daysInMonth))
                                + startDay * 7;
            } //End block
            //End case DOW_IN_MONTH_MODE 
            //Begin case DOW_GE_DOM_MODE 
            ruleDay = startDay
                            + mod7(startDayOfWeek
                            - (firstDayOfMonth + startDay - 1));
            //End case DOW_GE_DOM_MODE 
            //Begin case DOW_LE_DOM_MODE 
            ruleDay = startDay
                            + mod7(startDayOfWeek
                            - (firstDayOfMonth + startDay - 1));
            //End case DOW_LE_DOM_MODE 
            //Begin case DOW_LE_DOM_MODE 
            {
                ruleDay -= 7;
            } //End block
            //End case DOW_LE_DOM_MODE 
        } //End block
        int ruleTime;
        ruleTime = endTime - dstSavings;
        int nextMonth;
        nextMonth = (month + 1) % 12;
        {
            //Begin case DOM_MODE 
            ruleDay = endDay;
            //End case DOM_MODE 
            //Begin case DOW_IN_MONTH_MODE 
            {
                ruleDay = mod7(endDayOfWeek - firstDayOfMonth) + 1
                                + (endDay - 1) * 7;
            } //End block
            {
                daysInMonth = GregorianCalendar.DaysInMonth[endMonth];
                {
                    boolean varA62BC480A841AF891471E24690DA3AEC_1899142295 = (endMonth == Calendar.FEBRUARY && isLeapYear(year));
                } //End collapsed parenthetic
                ruleDay = daysInMonth
                                + 1
                                + mod7(endDayOfWeek
                                - (firstDayOfMonth + daysInMonth)) + endDay
                                * 7;
            } //End block
            //End case DOW_IN_MONTH_MODE 
            //Begin case DOW_GE_DOM_MODE 
            ruleDay = endDay
                            + mod7(
                            endDayOfWeek - (firstDayOfMonth + endDay - 1));
            //End case DOW_GE_DOM_MODE 
            //Begin case DOW_LE_DOM_MODE 
            ruleDay = endDay
                            + mod7(
                            endDayOfWeek - (firstDayOfMonth + endDay - 1));
            //End case DOW_LE_DOM_MODE 
            //Begin case DOW_LE_DOM_MODE 
            {
                ruleDay -= 7;
            } //End block
            //End case DOW_LE_DOM_MODE 
            int ruleMonth;
            ruleMonth = endMonth;
            {
                int changeDays;
                changeDays = 1 - (ruleTime / 86400000);
                ruleTime = (ruleTime % 86400000) + 86400000;
                ruleDay -= changeDays;
                {
                    {
                        ruleMonth = Calendar.DECEMBER;
                    } //End block
                    ruleDay += GregorianCalendar.DaysInMonth[ruleMonth];
                    {
                        boolean var942B201C9F9DC371A222C49906220991_1438995797 = (ruleMonth == Calendar.FEBRUARY && isLeapYear(year));
                    } //End collapsed parenthetic
                } //End block
            } //End block
        } //End block
        return dsTaint.getTaintInt();
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.653 -0400", hash_original_method = "6DBE603182E450BA9A410285A6D6CE41", hash_generated_method = "33AE4E187559013545046A81E121B721")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public int getOffset(long time) {
        dsTaint.addTaint(time);
        {
            boolean varAC33886EBC7D5E71649B935B25265F07_1311971968 = (!useDaylightTime());
        } //End collapsed parenthetic
        int[] fields;
        fields = Grego.timeToFields(time + rawOffset, null);
        int var68E2FD47563B72F3EFF55569C2664413_1102083858 = (getOffset(GregorianCalendar.AD, fields[0], fields[1], fields[2],
                fields[3], fields[5]));
        return dsTaint.getTaintInt();
        // ---------- Original Method ----------
        //if (!useDaylightTime()) {
            //return rawOffset;
        //}
        //int[] fields = Grego.timeToFields(time + rawOffset, null);
        //return getOffset(GregorianCalendar.AD, fields[0], fields[1], fields[2],
                //fields[3], fields[5]);
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.653 -0400", hash_original_method = "D90129E49D21D0E2F7EA4F6539F9D2CB", hash_generated_method = "F5F3F660F40ADEABA686BD278824CF1B")
    @DSModeled(DSC.SAFE)
    @Override
    public int getRawOffset() {
        return dsTaint.getTaintInt();
        // ---------- Original Method ----------
        //return rawOffset;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.653 -0400", hash_original_method = "543AFBFA58DD3885C5C6D68F749BE5FB", hash_generated_method = "BF8DF8311E11B2C8875C51B248D4743C")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public synchronized int hashCode() {
        int hashCode;
        hashCode = getID().hashCode() + rawOffset;
        {
            hashCode += startYear + startMonth + startDay + startDayOfWeek
                    + startTime + startMode + endMonth + endDay + endDayOfWeek
                    + endTime + endMode + dstSavings;
        } //End block
        return dsTaint.getTaintInt();
        // ---------- Original Method ----------
        //int hashCode = getID().hashCode() + rawOffset;
        //if (useDaylight) {
            //hashCode += startYear + startMonth + startDay + startDayOfWeek
                    //+ startTime + startMode + endMonth + endDay + endDayOfWeek
                    //+ endTime + endMode + dstSavings;
        //}
        //return hashCode;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.654 -0400", hash_original_method = "4BB4BBFFC89848EE3A9E98B493F95D5F", hash_generated_method = "5BCD95E32951375D2AA4C98EF6DBBC7A")
    @DSModeled(DSC.SAFE)
    @Override
    public boolean hasSameRules(TimeZone zone) {
        dsTaint.addTaint(zone.dsTaint);
        SimpleTimeZone tz;
        tz = (SimpleTimeZone) zone;
        return dsTaint.getTaintBoolean();
        // ---------- Original Method ----------
        //if (!(zone instanceof SimpleTimeZone)) {
            //return false;
        //}
        //SimpleTimeZone tz = (SimpleTimeZone) zone;
        //if (useDaylight != tz.useDaylight) {
            //return false;
        //}
        //if (!useDaylight) {
            //return rawOffset == tz.rawOffset;
        //}
        //return rawOffset == tz.rawOffset && dstSavings == tz.dstSavings
                //&& startYear == tz.startYear && startMonth == tz.startMonth
                //&& startDay == tz.startDay && startMode == tz.startMode
                //&& startDayOfWeek == tz.startDayOfWeek
                //&& startTime == tz.startTime && endMonth == tz.endMonth
                //&& endDay == tz.endDay && endDayOfWeek == tz.endDayOfWeek
                //&& endTime == tz.endTime && endMode == tz.endMode;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.654 -0400", hash_original_method = "C51A338C9795AC2507162C0CE1A08702", hash_generated_method = "49D3F62459F54EC622C0051FD95393F9")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public boolean inDaylightTime(Date time) {
        dsTaint.addTaint(time.dsTaint);
        boolean varEE507C2978DFF3408523E16B966BE7F9_135895398 = (useDaylightTime() && getOffset(time.getTime()) != getRawOffset());
        return dsTaint.getTaintBoolean();
        // ---------- Original Method ----------
        //return useDaylightTime() && getOffset(time.getTime()) != getRawOffset();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.654 -0400", hash_original_method = "8AD8F18B8E2CE991A6AB313B3340B306", hash_generated_method = "CB0BE79356F6706AE3684ACF75D4E4B8")
    @DSModeled(DSC.SAFE)
    private boolean isLeapYear(int year) {
        dsTaint.addTaint(year);
        return dsTaint.getTaintBoolean();
        // ---------- Original Method ----------
        //if (year > 1582) {
            //return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
        //}
        //return year % 4 == 0;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.654 -0400", hash_original_method = "EABB822EA3A4CED689A420DE5F07AD18", hash_generated_method = "53C5D863E5B06B54840BD0A846FE5A12")
    @DSModeled(DSC.SAFE)
    private int mod7(int num1) {
        dsTaint.addTaint(num1);
        int rem;
        rem = num1 % 7;
        return dsTaint.getTaintInt();
        // ---------- Original Method ----------
        //int rem = num1 % 7;
        //return (num1 < 0 && rem < 0) ? 7 + rem : rem;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.655 -0400", hash_original_method = "37D938F55D2005A7FF3D5AC31467EF4C", hash_generated_method = "94181794E635E8EF5BCB92E3AADE385C")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setDSTSavings(int milliseconds) {
        dsTaint.addTaint(milliseconds);
        {
            if (DroidSafeAndroidRuntime.control) throw new IllegalArgumentException();
        } //End block
        // ---------- Original Method ----------
        //if (milliseconds > 0) {
            //dstSavings = milliseconds;
        //} else {
            //throw new IllegalArgumentException();
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.655 -0400", hash_original_method = "29EACAF13C91B179EA825409505E4565", hash_generated_method = "EB35436D1F570698083EC1E8F0FA5A80")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void checkRange(int month, int dayOfWeek, int time) {
        dsTaint.addTaint(time);
        dsTaint.addTaint(month);
        dsTaint.addTaint(dayOfWeek);
        {
            if (DroidSafeAndroidRuntime.control) throw new IllegalArgumentException("Invalid month: " + month);
        } //End block
        {
            if (DroidSafeAndroidRuntime.control) throw new IllegalArgumentException("Invalid day of week: " + dayOfWeek);
        } //End block
        {
            if (DroidSafeAndroidRuntime.control) throw new IllegalArgumentException("Invalid time: " + time);
        } //End block
        // ---------- Original Method ----------
        //if (month < Calendar.JANUARY || month > Calendar.DECEMBER) {
            //throw new IllegalArgumentException("Invalid month: " + month);
        //}
        //if (dayOfWeek < Calendar.SUNDAY || dayOfWeek > Calendar.SATURDAY) {
            //throw new IllegalArgumentException("Invalid day of week: " + dayOfWeek);
        //}
        //if (time < 0 || time >= 24 * 3600000) {
            //throw new IllegalArgumentException("Invalid time: " + time);
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.655 -0400", hash_original_method = "F04B5CC4561F29CBDFD2A63D85494102", hash_generated_method = "844B2FF8D6C0B17EC8C5C661764A8D57")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void checkDay(int month, int day) {
        dsTaint.addTaint(month);
        dsTaint.addTaint(day);
        {
            if (DroidSafeAndroidRuntime.control) throw new IllegalArgumentException("Invalid day of month: " + day);
        } //End block
        // ---------- Original Method ----------
        //if (day <= 0 || day > GregorianCalendar.DaysInMonth[month]) {
            //throw new IllegalArgumentException("Invalid day of month: " + day);
        //}
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.655 -0400", hash_original_method = "E98C141304510A5F08CF5DF5CC63E118", hash_generated_method = "F8DEE44AD3F0DB21DB13D763ADEAA1D3")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void setEndMode() {
        {
            endMode = DOM_MODE;
        } //End block
        {
            endDayOfWeek = -endDayOfWeek;
            {
                endDay = -endDay;
                endMode = DOW_LE_DOM_MODE;
            } //End block
            {
                endMode = DOW_GE_DOM_MODE;
            } //End block
        } //End block
        {
            endMode = DOW_IN_MONTH_MODE;
        } //End block
        useDaylight = startDay != 0 && endDay != 0;
        {
            checkRange(endMonth, endMode == DOM_MODE ? 1 : endDayOfWeek,
                    endTime);
            {
                checkDay(endMonth, endDay);
            } //End block
            {
                {
                    if (DroidSafeAndroidRuntime.control) throw new IllegalArgumentException("Day of week in month: " + endDay);
                } //End block
            } //End block
        } //End block
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.656 -0400", hash_original_method = "B4162090EB008A9B00DF0FE1EF92B7AC", hash_generated_method = "EE60183A1E464BB2E5D4D239936F9640")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setEndRule(int month, int dayOfMonth, int time) {
        dsTaint.addTaint(time);
        dsTaint.addTaint(dayOfMonth);
        dsTaint.addTaint(month);
        endDayOfWeek = 0;
        setEndMode();
        // ---------- Original Method ----------
        //endMonth = month;
        //endDay = dayOfMonth;
        //endDayOfWeek = 0;
        //endTime = time;
        //setEndMode();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.656 -0400", hash_original_method = "582DA5383125C3306AD7EBC943F58F20", hash_generated_method = "4DD730E7E2AB369387591E62AC3021FE")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setEndRule(int month, int day, int dayOfWeek, int time) {
        dsTaint.addTaint(time);
        dsTaint.addTaint(month);
        dsTaint.addTaint(day);
        dsTaint.addTaint(dayOfWeek);
        setEndMode();
        // ---------- Original Method ----------
        //endMonth = month;
        //endDay = day;
        //endDayOfWeek = dayOfWeek;
        //endTime = time;
        //setEndMode();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.656 -0400", hash_original_method = "2E94B36A00113021CB8FDF603EDAA945", hash_generated_method = "ADB7E0AA4C4E7861E3377AC0098FE7C7")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setEndRule(int month, int day, int dayOfWeek, int time, boolean after) {
        dsTaint.addTaint(after);
        dsTaint.addTaint(time);
        dsTaint.addTaint(month);
        dsTaint.addTaint(day);
        dsTaint.addTaint(dayOfWeek);
        endDay = after ? day : -day;
        endDayOfWeek = -dayOfWeek;
        setEndMode();
        // ---------- Original Method ----------
        //endMonth = month;
        //endDay = after ? day : -day;
        //endDayOfWeek = -dayOfWeek;
        //endTime = time;
        //setEndMode();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.656 -0400", hash_original_method = "C8A257B93E1C51862FD9A30D6A75C66A", hash_generated_method = "5E7068CA42308B33E07FF2C04A977426")
    @DSModeled(DSC.SAFE)
    @Override
    public void setRawOffset(int offset) {
        dsTaint.addTaint(offset);
        // ---------- Original Method ----------
        //rawOffset = offset;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.657 -0400", hash_original_method = "4370DAC820CAB38F82466934EE4F5C8D", hash_generated_method = "F78AC451ED09031C844DED4FA305FC9D")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void setStartMode() {
        {
            startMode = DOM_MODE;
        } //End block
        {
            startDayOfWeek = -startDayOfWeek;
            {
                startDay = -startDay;
                startMode = DOW_LE_DOM_MODE;
            } //End block
            {
                startMode = DOW_GE_DOM_MODE;
            } //End block
        } //End block
        {
            startMode = DOW_IN_MONTH_MODE;
        } //End block
        useDaylight = startDay != 0 && endDay != 0;
        {
            checkRange(startMonth, startMode == DOM_MODE ? 1 : startDayOfWeek,
                    startTime);
            {
                checkDay(startMonth, startDay);
            } //End block
            {
                {
                    if (DroidSafeAndroidRuntime.control) throw new IllegalArgumentException("Day of week in month: " + startDay);
                } //End block
            } //End block
        } //End block
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.657 -0400", hash_original_method = "1B6B9F6DA0D38FD2E6B0C75070F8A774", hash_generated_method = "03185ABA7BC671B459A23CBAA58DF819")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setStartRule(int month, int dayOfMonth, int time) {
        dsTaint.addTaint(time);
        dsTaint.addTaint(dayOfMonth);
        dsTaint.addTaint(month);
        startDayOfWeek = 0;
        setStartMode();
        // ---------- Original Method ----------
        //startMonth = month;
        //startDay = dayOfMonth;
        //startDayOfWeek = 0;
        //startTime = time;
        //setStartMode();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.657 -0400", hash_original_method = "05B57A1DF0091079AD9D1D4FB9E42767", hash_generated_method = "B2F8D00B508DCC9F8B17C2BBD1098EA9")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setStartRule(int month, int day, int dayOfWeek, int time) {
        dsTaint.addTaint(time);
        dsTaint.addTaint(month);
        dsTaint.addTaint(day);
        dsTaint.addTaint(dayOfWeek);
        setStartMode();
        // ---------- Original Method ----------
        //startMonth = month;
        //startDay = day;
        //startDayOfWeek = dayOfWeek;
        //startTime = time;
        //setStartMode();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.658 -0400", hash_original_method = "D71B6352FE444D82D409CFA5438BDAA8", hash_generated_method = "519E0C7D23928F6DD1C3002F6C331DBD")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    public void setStartRule(int month, int day, int dayOfWeek, int time, boolean after) {
        dsTaint.addTaint(after);
        dsTaint.addTaint(time);
        dsTaint.addTaint(month);
        dsTaint.addTaint(day);
        dsTaint.addTaint(dayOfWeek);
        startDay = after ? day : -day;
        startDayOfWeek = -dayOfWeek;
        setStartMode();
        // ---------- Original Method ----------
        //startMonth = month;
        //startDay = after ? day : -day;
        //startDayOfWeek = -dayOfWeek;
        //startTime = time;
        //setStartMode();
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.658 -0400", hash_original_method = "7CBF25AFDCF863C97C11F8D0CB7F66E7", hash_generated_method = "71446A4C802461E56AE83E2EDD656C4E")
    @DSModeled(DSC.SAFE)
    public void setStartYear(int year) {
        dsTaint.addTaint(year);
        useDaylight = true;
        // ---------- Original Method ----------
        //startYear = year;
        //useDaylight = true;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.659 -0400", hash_original_method = "8DB4BEB563F494A2FC46832022F4609E", hash_generated_method = "EDC5BD7E3BAD7F7B747049A007D251AD")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    @Override
    public String toString() {
        String var325814D57C729356073E55069D93EAFE_69229098 = (getClass().getName()
                + "[id="
                + getID()
                + ",offset="
                + rawOffset
                + ",dstSavings="
                + dstSavings
                + ",useDaylight="
                + useDaylight
                + ",startYear="
                + startYear
                + ",startMode="
                + startMode
                + ",startMonth="
                + startMonth
                + ",startDay="
                + startDay
                + ",startDayOfWeek="
                + (useDaylight && (startMode != DOM_MODE) ? startDayOfWeek + 1
                        : 0) + ",startTime=" + startTime + ",endMode="
                + endMode + ",endMonth=" + endMonth + ",endDay=" + endDay
                + ",endDayOfWeek="
                + (useDaylight && (endMode != DOM_MODE) ? endDayOfWeek + 1 : 0)
                + ",endTime=" + endTime + "]"); //DSFIXME:  CODE0008: Nested ternary operator in expression
        return dsTaint.getTaintString();
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.659 -0400", hash_original_method = "7C1BE50DE9C29B43BADE36CF66AF8A96", hash_generated_method = "5730BE6C804DD6916E3D729FD7EB16E8")
    @DSModeled(DSC.SAFE)
    @Override
    public boolean useDaylightTime() {
        return dsTaint.getTaintBoolean();
        // ---------- Original Method ----------
        //return useDaylight;
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.661 -0400", hash_original_method = "F4691E6CC75F84FE0527C352A6F6F1B9", hash_generated_method = "B3EB25F3C59B61A89981828453F6B1C8")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void writeObject(ObjectOutputStream stream) throws IOException {
        dsTaint.addTaint(stream.dsTaint);
        int sEndDay, sEndDayOfWeek, sStartDay, sStartDayOfWeek;
        sEndDay = endDay;
        sEndDayOfWeek = endDayOfWeek + 1;
        sStartDay = startDay;
        sStartDayOfWeek = startDayOfWeek + 1;
        {
            Calendar cal;
            cal = new GregorianCalendar(this);
            {
                cal.set(Calendar.MONTH, endMonth);
                cal.set(Calendar.DATE, endDay);
                sEndDay = cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
                {
                    sEndDayOfWeek = cal.getFirstDayOfWeek();
                } //End block
            } //End block
            {
                cal.set(Calendar.MONTH, startMonth);
                cal.set(Calendar.DATE, startDay);
                sStartDay = cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
                {
                    sStartDayOfWeek = cal.getFirstDayOfWeek();
                } //End block
            } //End block
        } //End block
        ObjectOutputStream.PutField fields;
        fields = stream.putFields();
        fields.put("dstSavings", dstSavings);
        fields.put("endDay", sEndDay);
        fields.put("endDayOfWeek", sEndDayOfWeek);
        fields.put("endMode", endMode);
        fields.put("endMonth", endMonth);
        fields.put("endTime", endTime);
        fields.put("monthLength", GregorianCalendar.DaysInMonth);
        fields.put("rawOffset", rawOffset);
        fields.put("serialVersionOnStream", 1);
        fields.put("startDay", sStartDay);
        fields.put("startDayOfWeek", sStartDayOfWeek);
        fields.put("startMode", startMode);
        fields.put("startMonth", startMonth);
        fields.put("startTime", startTime);
        fields.put("startYear", startYear);
        fields.put("useDaylight", useDaylight);
        stream.writeFields();
        stream.writeInt(4);
        byte[] values;
        values = new byte[4];
        values[0] = (byte) startDay;
        values[1] = (byte) (startMode == DOM_MODE ? 0 : startDayOfWeek + 1);
        values[2] = (byte) endDay;
        values[3] = (byte) (endMode == DOM_MODE ? 0 : endDayOfWeek + 1);
        stream.write(values);
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.1", generated_on = "2013-06-21 15:40:33.662 -0400", hash_original_method = "19765306B3DA0529AD0AFD53EDC0B4B8", hash_generated_method = "D118619C2FBCAF056AF278A1C6963B03")
    //DSFIXME:  CODE0002: Requires DSC value to be set
    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        dsTaint.addTaint(stream.dsTaint);
        ObjectInputStream.GetField fields;
        fields = stream.readFields();
        rawOffset = fields.get("rawOffset", 0);
        useDaylight = fields.get("useDaylight", false);
        {
            endMonth = fields.get("endMonth", 0);
            endTime = fields.get("endTime", 0);
            startMonth = fields.get("startMonth", 0);
            startTime = fields.get("startTime", 0);
            startYear = fields.get("startYear", 0);
        } //End block
        {
            boolean varB1402D125FA51CA7547D890DB81A6275_681477543 = (fields.get("serialVersionOnStream", 0) == 0);
            {
                {
                    startMode = endMode = DOW_IN_MONTH_MODE;
                    endDay = fields.get("endDay", 0);
                    endDayOfWeek = fields.get("endDayOfWeek", 0) - 1;
                    startDay = fields.get("startDay", 0);
                    startDayOfWeek = fields.get("startDayOfWeek", 0) - 1;
                } //End block
            } //End block
            {
                dstSavings = fields.get("dstSavings", 0);
                {
                    endMode = fields.get("endMode", 0);
                    startMode = fields.get("startMode", 0);
                    int length;
                    length = stream.readInt();
                    byte[] values;
                    values = new byte[length];
                    stream.readFully(values);
                    {
                        startDay = values[0];
                        startDayOfWeek = values[1];
                        endDay = values[2];
                        endDayOfWeek = values[3];
                    } //End block
                } //End block
            } //End block
        } //End collapsed parenthetic
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
    }

    
    private static final long serialVersionUID = -403250971215465050L;
    private static final int DOM_MODE = 1, DOW_IN_MONTH_MODE = 2,
            DOW_GE_DOM_MODE = 3, DOW_LE_DOM_MODE = 4;
    public static final int UTC_TIME = 2;
    public static final int STANDARD_TIME = 1;
    public static final int WALL_TIME = 0;
    private static final ObjectStreamField[] serialPersistentFields = {
        new ObjectStreamField("dstSavings", int.class),
        new ObjectStreamField("endDay", int.class),
        new ObjectStreamField("endDayOfWeek", int.class),
        new ObjectStreamField("endMode", int.class),
        new ObjectStreamField("endMonth", int.class),
        new ObjectStreamField("endTime", int.class),
        new ObjectStreamField("monthLength", byte[].class),
        new ObjectStreamField("rawOffset", int.class),
        new ObjectStreamField("serialVersionOnStream", int.class),
        new ObjectStreamField("startDay", int.class),
        new ObjectStreamField("startDayOfWeek", int.class),
        new ObjectStreamField("startMode", int.class),
        new ObjectStreamField("startMonth", int.class),
        new ObjectStreamField("startTime", int.class),
        new ObjectStreamField("startYear", int.class),
        new ObjectStreamField("useDaylight", boolean.class),
    };
}

