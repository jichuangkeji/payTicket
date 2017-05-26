package com.weirdotech.payticket.utils;


import java.util.Vector;

/**
 * Utility class useful when dealing with string objects.
 * This class is a collection of static functions, and the usage is:
 *
 * StringUtils.method()
 *
 * it is not allowed to create instances of this class
 */
public class StringUtils {
    
    private static final String HT = "\t";
    private static final String CRLF = "\r\n";

    
    private StringUtils() { }

    /**
     * Split the string into an array of strings using one of the separator
     * in 'sep'.
     *
     * @param s the string to tokenize
     * @param sep a list of separator to use
     *
     * @return the array of tokens (an array of size 1 with the original
     *         string if no separator found)
     */
    public static String[] split(String s, String sep) {
        Vector<Integer> tokenIndex = new Vector<Integer>(10);
        int len = s.length();
        int i;
        
        for (i = 0; i < len; i++) {
            if (sep.indexOf(s.charAt(i)) != -1 ){
                tokenIndex.addElement(Integer.valueOf(i));
            }
        }

        int size = tokenIndex.size();
        String[] elements = new String[size+1];

        if(size == 0) {
            elements[0] = s;
        }
        else {
            int start = 0;
            int end = (tokenIndex.elementAt(0)).intValue();
            elements[0] = s.substring(start, end);

            for (i=1; i<size; i++) {
                start = (tokenIndex.elementAt(i-1)).intValue()+1;
                end = (tokenIndex.elementAt(i)).intValue();
                elements[i] = s.substring(start, end);
            }
            start = (tokenIndex.elementAt(i-1)).intValue()+1;
            elements[i] = (start < s.length()) ? s.substring(start) : "";
        }

        return elements;
    }
    
    /**
     * Split the string into an array of strings using one of the separator 
     * in 'sep'.
     *
     * @param list the string array to join
     * @param sep the separator to use
     *
     * @return the joined string
     */
    public static String join(String[] list, String sep) {
        StringBuffer buffer = new StringBuffer(list[0]);
        int len = list.length;

        for (int i = 1; i < len; i++) {
            buffer.append(sep).append(list[i]);
        }
        return buffer.toString();
    }

    /**
     * Returns the string array
     * @param stringVec the Vecrot of tring to convert
     * @return String []
     */
    public static String[] getStringArray(Vector<String> stringVec){
        
        if(stringVec==null){
            return null;
        }
        
        String[] stringArray=new String[stringVec.size()];
        for(int i=0;i<stringVec.size();i++){
            stringArray[i]=stringVec.elementAt(i);
        }
        return stringArray;
    }
    
    /**
     * Find two consecutive newlines in a string.
     * @param s - The string to search
     * @return int: the position of the empty line
     */
    public static int findEmptyLine(String s){
        int ret = 0; 
        
        while( (ret = s.indexOf("\n", ret)) != -1 ){
            while(s.charAt(ret) == '\r'){
                ret++;
            }
            if(s.charAt(ret) == '\n'){
                ret ++;
                break;
            }
        }
        return ret;
    }
    
    /**
     * Removes unwanted blank characters
     * @param content
     * @return String
     */
    public static String removeBlanks(String content){

        if(content==null){              
            return null;
        }

        StringBuffer buff = new StringBuffer();
        buff.append(content);

        for(int i = buff.length()-1; i >= 0;i--){
            if(' ' == buff.charAt(i)){
                buff.deleteCharAt(i);
            }
        }
        return buff.toString();
    }
    
    /**
     * Removes unwanted backslashes characters
     *
     * @param content The string containing the backslashes to be removed
     * @return the content without backslashes
     */
    public static String removeBackslashes(String content){

        if (content == null) {              
            return null;
        }

        StringBuffer buff = new StringBuffer();
        buff.append(content);

        int len = buff.length();
        for (int i = len - 1; i >= 0; i--) {
            if ('\\' == buff.charAt(i))
                buff.deleteCharAt(i);
        }
        return buff.toString();
    }
    
    /**
     * Builds a list of the recipients email addresses each on a different line,
     * starting just from the second line with an HT ("\t") separator at the
     * head of the line. This is an implementation of the 'folding' concept from
     * the RFC 2822 (par. 2.2.3)
     * 
     * @param recipients
     *            A string containing all recipients comma-separated
     * @return A string containing the email list of the recipients spread over
     *         more lines, ended by CRLF and beginning from the second with the
     *         WSP defined in the RFC 2822
     */
    public static String fold(String recipients) {
        String[] list = StringUtils.split(recipients, ",");

        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < list.length; i++) {
            String address = list[i] + (i != list.length - 1 ? "," : "");
            buffer.append(i == 0 ? address + CRLF : HT + address + CRLF);
        }

        return buffer.toString();
    }
    
    /**
     * This method is missing in CLDC 1.0 String implementation
     */
    public static boolean equalsIgnoreCase(String string1, String string2) {
        if (string1 == null && string2 == null) {
            return true;
        }
        if (string1 == null || string2 == null) {
            return false;
        }
        return (string1.toLowerCase()).equals(string2.toLowerCase());
    }
    
    /**
     * Util method for retrieve a boolean primitive type from a String.
     * Implemented because Boolean class doesn't provide 
     * parseBoolean() method
     */
    public static boolean getBooleanValue(String string) {
        if ((string == null) || string.equals("")) {
            return false;
        } else {
            return StringUtils.equalsIgnoreCase(string, "true");
        }
    }

    /**
     * Removes characters 'c' from the beginning and the end of the string
     */
    public static String trim(String s, char c) {
        int start = 0;
        int end = s.length()-1;

        while(s.charAt(start) == c) {
            if(++start >= end) {
                return "";
            }
        }

        while(s.charAt(end) == c) {
            if(--end <= start) {
                return "";
            }
        }

        return s.substring(start, end+1);
    }
    
    /**
     * Returns true if the given string is null or empty.
     */
    public static boolean isNullOrEmpty(String str) {
        if (str==null) {
            return true;
        }
        if (str.trim().equals("")) {
            return true;
        }
        return str.length() == 0;
    }
    
    /**
     * Returns true if the string does not fit in standard ASCII
     */
    public static boolean isASCII(String str) {
    	if (str == null)
    		return true;
    	for (int i = 0; i < str.length(); ++i) {
    		char c = str.charAt(i);
    		if (c < 0 || c > 0x7f)
    			return false;
    	}
    	return true;
    }

    public static boolean equals(String s1, String s2, boolean ignoreCase){
        if(s1 != null && s2 != null){
            if(ignoreCase){
                return s1.equalsIgnoreCase(s2);
            }
            else{
                return s1.equals(s2);
            }
        }
        else{
            return ((s1 == null && s2 == null)? true : false);
        }
    }


}

