package com.acme.po4java;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Vector;

/**
 * 
 * @author Andre Darian Bonner
 *
 */
public class POFile
{
    private POEntry[] Entries;
    private POEntry Header;
    private File File;

    POFile(POEntry[] entries, POEntry header, File file)
    {
        this.Entries = entries;
        this.Header = header;
        this.File = file;
    }

    /**
     * Returns with the name of the po file
     * @return name of po file
     */
    public String getFileName()
    {
        return File.getAbsolutePath();
    }

    /**
     * Returns with the POEntry object array
     * @return POEntry array
     */
    public POEntry[] getEntryArray()
    {
        return Entries;
    }

    /**
     * Gets the POEntry object specified by the index
     * @param index, index of the entry
     * @return one POEntry object
     */
    public POEntry getEntry(int index)
    {
        return Entries[index];
    }

    /**
     * Returns how many entries are there in the po file
     * @return count of entries
     */
    public int getEntryLength()
    {
        return Entries.length;
    }

    /**
     * Checks if the specified flag is set in the entry,
     * given by the entry index.
     * @param flag, string representing the flag
     * @param entryIndex, index of the entry to examine
     * @return true, if the flag is set, false otherwise
     */
    public boolean checkFlag(String flag, int entryIndex)
    {
        boolean status = false;
        Vector<String> strings = new Vector<String>();
        strings = Entries[entryIndex].get(POEntry.StringType.FLAG);
        if (strings != null)
        {
            for(int i = 0; i < strings.size(); i++)
            {
                if (strings.get(i).contains(flag))
                {
                    status = true;
                }
            }
        }
        return status;
    }

    /**
     * Returns with all the strings of the given type, from
     * the specified entry.
     * @param entryIndex
     * @param type
     * @return String array of specified type
     */
    public String[] getStringsFromEntryByType(int entryIndex, POEntry.StringType type)
    {
        Vector<String> vector = Entries[entryIndex].get(type);
        String[] str = new String[vector.size()];
        for(int i = 0; i < str.length; i++)
        {
            str[i] = vector.get(i);
        }
        return str;
    }

    /**
     * For debug purposes
     */
    public void printFile()
    {
    	int count = 0;
        for(int i = 0; i < Entries.length; i++)
        {
            Collection<POLine> poLines = Entries[i].values();
            for(POLine temp : poLines){
            		count++;
            		System.out.println(temp);
            
            }
        }
        System.out.println(Entries.length);
        System.out.println(count);
    }

    /**
     * For debug purposes
     */
    public void printHeader()
    {
    	Collection<POLine> poLines = Header.values();
    	 for(POLine temp : poLines){
    		 for(String s : temp){
         		System.out.println(s);
    		 }
         }
    }
    
    public void toPOT(Writer out) throws IOException{
    	Collection<POLine> poHeader = Header.values();
    	for(POLine temp : poHeader){
   		 for(String s : temp){
        		out.write(s +"\n");
   		 }
        }
    	out.append("\n");
    	POEntry[] KeyEntries = Entries.clone();
    	for(int i = 0; i < KeyEntries.length; i++)
        {
    		KeyEntries[i].remove(POEntry.StringType.MSGSTR);
    		KeyEntries[i].addLine(POEntry.StringType.MSGSTR, "");
    		KeyEntries[i].toFile(out);
            out.append("\n");
        }
    	out.flush();
    	out.close();
    }
    
    public void toFile(Writer out) throws IOException{
    	
    	Collection<POLine> poHeader = Header.values();
    	for(POLine temp : poHeader){
   		 for(String s : temp){
        		out.write(s +"\n");
   		 }
    	}
    	out.append("\n");
    	for(int i = 0; i < Entries.length; i++)
        {
            Entries[i].toFile(out);
            out.append("\n");
        }
    	out.flush();
    	out.close();
    }
}
