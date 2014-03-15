package com.acme.po4java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ParserTest
{
    public static void main(String args[]) throws IOException
    {
        File file = new File("src/test/resources/fr_fr.po");
        File result = new File("src/test/resources/results.po");
        
        POParser parser = new POParser(file);
        POFile po = parser.getPOFile();
        System.out.println(po.getEntryLength());
        POEntry[] ea = po.getEntryArray();
        System.out.println(ea.length);
        //for(POEntry e: ea){
        //	System.out.println(e.get(POEntry.StringType.MSGID));
        //}
        //po.printHeader();
        po.printFile();
        // is the 3th entry fuzzy?
        //boolean fuzzy = po.checkFlag("fuzzy", 3);
        // give me the msgid of the 4th entry
        String[] str = po.getStringsFromEntryByType(4, POEntry.StringType.MSGID);
        //for(String s: str){
        //	System.out.println(s);
       // }
        
        po.toFile(new FileWriter(result));
    }
}
