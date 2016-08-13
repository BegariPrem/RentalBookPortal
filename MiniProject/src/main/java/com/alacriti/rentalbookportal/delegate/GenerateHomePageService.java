package com.alacriti.rentalbookportal.delegate;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class GenerateHomePageService {
	Logger logger=Logger.getLogger(GenerateHomePageService.class);
	public String generate()
	{
		try{
			Configuration configuration=new Configuration();
			configuration.setClassForTemplateLoading(GenerateHomePageService.class, "/templates");
			Template tmp=configuration.getTemplate("login.ftl");
			StringWriter stringWriter=new StringWriter();
			HashMap<String, String>input=new HashMap<String, String>();
			tmp.process(input, stringWriter);
			return stringWriter.toString();
			
		}
		catch(TemplateException e)
		{
			logger.info("template not found exception "+e.getMessage());
		} catch (IOException e) {
			logger.info("template not found in IO exception mode "+e.getMessage());
		}
		catch (Exception e) {
			logger.info("Exception in Generate HomePageService() "+e.getMessage());
		}
		return "something went wrong";
	}
}
