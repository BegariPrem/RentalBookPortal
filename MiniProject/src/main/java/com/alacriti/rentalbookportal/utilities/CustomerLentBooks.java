package com.alacriti.rentalbookportal.utilities;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.rentalbookportal.vo.BookRentReturn;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class CustomerLentBooks {
	
	public static String processFtl(List<BookRentReturn> customerBookList)
	{
		Logger logger=Logger.getLogger(CustomerLentBooks.class);
		try{
			if(customerBookList.size()>0)
			{
				Configuration configuration=new Configuration();
				HashMap<String,Object> input=new HashMap<String,Object>();
				StringWriter stringWriter=new StringWriter();
				configuration.setClassForTemplateLoading(CustomerLentBooks.class,"/templates");
				
					Template tmp=configuration.getTemplate("customerViewLentBooks.ftl");
					input.put("bookList", customerBookList);
					tmp.process(input, stringWriter);
					return stringWriter.toString();
			}
			else{
				return "<span style='font-size:20px;margin:0 auto;margin-top:40px'><i>customer does not have  to return any books</i></span>";
			}
		}
		catch(TemplateException e)
		{
			logger.info("error in userServiceBo impl view LentBooks() "+e.getMessage());
		} catch (IOException e) {
			logger.info("error in ");
		}
		catch(Exception ex)
		{
			logger.info(" exception in userService bo impl in viewLentBooks() "+ex.getMessage());
		}
		return "something went wrong";
	}
}

