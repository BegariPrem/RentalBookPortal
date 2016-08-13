package com.alacriti.rentalbookportal.utilities;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.rentalbookportal.vo.BookVO;
import com.alacriti.rentalbookportal.vo.CategoryVO;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FtlProcessClass {
	public static String getAllCategories(List<CategoryVO> categorySearch)
	{
		Logger logger=Logger.getLogger(FtlProcessClass.class);
		HashMap<String,Object> input=new HashMap<String, Object>();
		StringWriter stringWriter=new StringWriter();
		Template tmp=null;
		try 
		{
			if(categorySearch.size()>0)
			{
				Configuration configuration=new Configuration();
				configuration.setClassForTemplateLoading(FtlProcessClass.class,"/templates");
				tmp=configuration.getTemplate("select.ftl");
				input.put("categories", categorySearch);
				tmp.process(input, stringWriter);
				return stringWriter.toString();
			}
			else{
				return "No Books Available with search ";
			}
			
		} catch (TemplateException e) {
			logger.info("template not found exception "+e.getMessage());
		} catch (IOException e) {
			logger.info("template not found exception "+e.getMessage() );
		}
		catch(Exception e)
		{
			logger.info("exception in getAll Books category() bo "+e.getMessage());
		}
		
		return "something went wrong";
	}
	public static String getAllResults(List<BookVO> searchResult)
	{
		Logger logger=Logger.getLogger(FtlProcessClass.class);
		HashMap<String,Object> input=new HashMap<String, Object>();
		StringWriter stringWriter=new StringWriter();
		Template tmp=null;
		try{
			if(searchResult.size()>0)
			{
				
					Configuration configuration=new Configuration();
					configuration.setClassForTemplateLoading(FtlProcessClass.class,"/templates");
					tmp=configuration.getTemplate("searchBookResults.ftl");
					input.put("bookList", searchResult);
					tmp.process(input, stringWriter);
					return stringWriter.toString();
			}
			else
			{
				 return "no more records";
			}
			
			
		} catch (TemplateException e) {
			logger.info("template not found exception "+e.getMessage());
		} catch (IOException e) {
			logger.info("template not found exception "+e.getMessage() );
		}
		catch(Exception e)
		{
			logger.info("exception in getAll Books category() bo "+e.getMessage());
		}
		return "something wrong";
	}
	
}
