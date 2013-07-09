package com.example.feedrssdisplay;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class ParserRSS extends DefaultHandler{
	 private final static String TAG_ITEM = "item";
	 
	 private static String[] xmltags1={};
	  
	 private final static String[] xmltags = { "title", "link", "pubDate", "description" };
	    private RSS currentitem = null;
	    
	    
	    private ArrayList<RSS> itemarray = null;
	    private int currentindex = -1;
	    private boolean isParsing = false;
	    private StringBuilder builder = new StringBuilder();

	    public ParserRSS(ArrayList<RSS> itemarray) {
	        super();

	        this.itemarray = itemarray;
	    }

	    @Override
	    public void characters(char[] ch, int start, int length) throws SAXException {
	        super.characters(ch, start, length);

	        if(isParsing && -1 != currentindex && null != builder)
	        {
	            builder.append(ch,start,length);
	        }
	    }

	    @Override
	    public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
	        super.startElement(uri, localName, qName, attributes);

	        if(localName.equalsIgnoreCase(TAG_ITEM))
	        {
	            currentitem = new RSS();
//	            xmltags[0]= currentitem.getNameTag1();
//	            xmltags[1]= currentitem.getNameTag2();
//	            xmltags[2]= currentitem.getNameTag3();
//	            xmltags[3]= currentitem.getNameTag4();
//	            
	            currentindex = -1;
	            isParsing = true;

	            itemarray.add(currentitem);
	        }
	        else
	        {
	            currentindex = itemIndexFromString(localName);

	            builder = null;

	            if(-1 != currentindex)
	                builder = new StringBuilder();
	        }
	    }
	    
	    public void tagname(Tags tags){
	    	String[] a = {tags.getNameTag1(), tags.getNameTag2(), tags.getNameTag3(), tags.getNameTag4()};
	    	
	    	xmltags1=a;
	    	
	    	xmltags[1]=tags.getNameTag2();
	    	Log.i("xmltags", a.toString());
	    }

	    @Override
	    public void endElement(String uri, String localName, String qName) throws SAXException {
	        super.endElement(uri, localName, qName);

	        if(localName.equalsIgnoreCase(TAG_ITEM))
	        {
	            isParsing = false;
	        }
	        else if(currentindex != -1)
	        {
	            if(isParsing)
	            {
	                switch(currentindex)
	                {
	                    case 0: 
	                    	currentitem.tag1 = builder.toString();
	                    	break;
	                    case 1: 
	                    	currentitem.tag2 = builder.toString(); 
	                    	break;
	                    case 2: 
	                    	currentitem.tag3= builder.toString();
	                    	break;
	                    case 3: 
	                    	currentitem.tag4= builder.toString();
	                    	break;
	                }
	            }
	        }
	    }

	    private int itemIndexFromString(String tagname){
	        int itemindex = -1;
	        
	        for(int index= 0; index<xmltags.length; ++index)
	        {
	            if(tagname.equalsIgnoreCase(xmltags[index]))
	            {
	                itemindex = index;

	                break;
	            }
	        }

	        return itemindex;
	    }
}
