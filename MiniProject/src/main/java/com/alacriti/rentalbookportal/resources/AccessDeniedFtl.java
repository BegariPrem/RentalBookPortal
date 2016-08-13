package com.alacriti.rentalbookportal.resources;

import java.io.StringWriter;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.alacriti.rentalbookportal.utilities.FtlProcessClass;

import freemarker.template.Configuration;
import freemarker.template.Template;

public  class AccessDeniedFtl {
	public static String AccessDenied(){
		StringWriter stringWriter=new StringWriter();
		try{
			
			HashMap<String,Object> input=new HashMap<String, Object>();
			
			Template tmp=null;
			 
					Configuration configuration=new Configuration();
					configuration.setClassForTemplateLoading(FtlProcessClass.class,"/templates");
					tmp=configuration.getTemplate("AccessDenied.ftl");
				 
					tmp.process(input, stringWriter);
					return stringWriter.toString();
			 
		}
		catch(Exception e)
		{
			
		}
		return stringWriter.toString();
	}
	public static String notAllowed(){
		StringWriter stringWriter=new StringWriter();
		try{
			
			HashMap<String,Object> input=new HashMap<String, Object>();
			
			Template tmp=null;
			 
					Configuration configuration=new Configuration();
					configuration.setClassForTemplateLoading(FtlProcessClass.class,"/templates");
					tmp=configuration.getTemplate("AccessDenied.ftl");
				 
					tmp.process(input, stringWriter);
					return stringWriter.toString();
			 
		}
		catch(Exception e)
		{
			
		}
		return stringWriter.toString();
	}
}
