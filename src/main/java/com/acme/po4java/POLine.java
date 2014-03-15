package com.acme.po4java;


import java.io.IOException;
import java.io.Writer;
import java.util.Vector;

/**
 * 
 * @author Andre Darian Bonner
 *
 */
public class POLine extends Vector<String>
{
    private POEntry.StringType type;

    POLine(POEntry.StringType type, String string)
    {
        this.type = type;
        add(string);
    }
     
    public POEntry.StringType getType()
    {
        return type;
    }

    public String toString(){
    	StringBuffer buf = new StringBuffer();
    	buf.append(this.type.prefix() +" ");
    	for(String s : this){
    		buf.append(s);
    	}
    	return buf.toString().trim();
    }
    
    public void toFile(Writer out) throws IOException{
    	boolean msgFlag = false;
    	for(String s : this){
    		if(!this.type.prefix().startsWith("msg")){
    		 out.append(this.type.prefix() + " ");
    		 out.append(s +"\n");
    		}
    		else if(msgFlag){// wrap in string qoutes with /n"
    			out.append("\""+s+"\""+"\n");
    		}
    		else if(!msgFlag && (size() < 2)){ // it starts with msg	
    	    	out.append(this.type.prefix() +" \""+ s +"\""+"\n");
    	    }
    		else{
    			out.append(this.type.prefix() +" \"\""+ "\n");
    			msgFlag=true;
    		}
    	}
    } 
}