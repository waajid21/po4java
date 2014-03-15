package com.acme.po4java;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Vector;

/**
 * 
 * @author Andre Darian Bonner
 *
 */
public class POEntry extends HashMap<POEntry.StringType,POLine>
{
    
    public enum StringType
    {
        /**translator comments*/
        TRLCMNT("#"),
        /**extracted comments*/
        EXTCMNT("#."),
        /**reference*/
        REFERENCE("#:"),
        /**flag*/
        FLAG("#,"),
        /**previous context*/
        PREVCTXT("#| msgctxt"),
        /**previous untranslated string singular*/
        PREVUNTRSTRSING("#| msgid"),
        /**previous untranslated string plural*/
        PREVUNTRSTRPLUR("#| msgid_plural"),
        /**untranslated string singular*/
        MSGID("msgid"),
        /**translated string*/
        MSGSTR("msgstr"),
        /**context*/
        MSGCTXT("msgctxt"),
        /**header line*/
        HEADER("");
        
        private String prefix;
        
        private StringType(String prefix){
        	this.prefix = prefix;
        }
        
        public String prefix(){
        	return prefix;
        }
        // TODO: support for plural untranslated strings,
        // and translated string cases
    }
    
    public void addLine(StringType type, String string)
    {
    	//If this already contains the key, then add to the POLine[Vector]
        if(containsKey(type)){
            get(type).add(string);
        }
        //Does not contain key, add
        else
        {
            put(type, new POLine(type, string));
        }
    }
    
    public void toFile(Writer out) throws IOException{
        //not doing switch case because i want to controll the order
    	
    	if(containsKey(StringType.TRLCMNT)){
    	get(StringType.TRLCMNT).toFile(out);
    	}
    	if(containsKey(StringType.EXTCMNT)){
        	get(StringType.EXTCMNT).toFile(out);
        }
    	if(containsKey(StringType.REFERENCE)){
        	get(StringType.REFERENCE).toFile(out);
        }
    	if(containsKey(StringType.FLAG)){
        	get(StringType.FLAG).toFile(out);
        }
    	if(containsKey(StringType.PREVCTXT)){
        	get(StringType.PREVCTXT).toFile(out);
        }
    	if(containsKey(StringType.PREVUNTRSTRSING)){
        	get(StringType.PREVUNTRSTRSING).toFile(out);
        }
    	if(containsKey(StringType.PREVUNTRSTRPLUR)){
        	get(StringType.PREVUNTRSTRPLUR).toFile(out);
        }
    	if(containsKey(StringType.MSGID)){
        	get(StringType.MSGID).toFile(out);
        }
    	if(containsKey(StringType.MSGSTR)){
        	get(StringType.MSGSTR).toFile(out);
        }
    	if(containsKey(StringType.MSGCTXT)){
        	get(StringType.MSGCTXT).toFile(out);
        }  
    }
}
