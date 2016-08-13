package com.alacriti.rentalbookportal.delegate;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.rentalbookportal.vo.BookVO;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class PaginationService {
	Logger logger=Logger.getLogger(PaginationService.class);
	Configuration cfg=new Configuration();
	Template tmp=null;
	StringWriter str=new StringWriter();
	List<BookVO> sublist=new ArrayList<BookVO>();
	HashMap<String,Object> input=new HashMap<String,Object>();
	public String getBooksDetails(List<BookVO>  list,int start,int limit)
	{
		logger.info(list);
		cfg.setClassForTemplateLoading(PaginationService.class, "/templates");
		try {
			tmp=cfg.getTemplate("searchBookResults.ftl");
			if((start+limit)<list.size())
			{
				sublist=list.subList(start, limit);
				input.put("bookList", sublist);
				tmp.process(input,str);
				return str.toString();
			}
			else{
				sublist=list.subList(start, list.size());
				input.put("bookList", sublist);
				tmp.process(input,str);
				return str.toString();
			}
		} catch (IOException e) {
			logger.info("io exception in Pagination serive bookList "+e.getMessage());
		} catch (TemplateException e) {
			logger.info("Template exception in Pagination serive bookList "+e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("Exception in Pagination Service "+e.getMessage());
		}
		return "someThing went Wrong";
	}
}
