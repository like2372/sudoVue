package like.controller;

import javax.servlet.http.HttpServletRequest;

import like.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
@RequestMapping("/articleService")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/getArticleList")
	public String getArticleList(HttpServletRequest req){
		
		String start=req.getParameter("start");
		
		String end=req.getParameter("end");
		
		return articleService.getArticleList(start,end);	
	}
	
	/**
	 * 根据id获取article
	 * @param req
	 * @return
	 */
	@RequestMapping("/getArticleDetail")
	public String getArticleDetail(HttpServletRequest req){
		
		String id=req.getParameter("id");
		
		return articleService.getArticleDetail(id);	
	}
	
	@RequestMapping(value="/insertArticleData",method=RequestMethod.POST)
	public String InsertArticleData(HttpServletRequest req,@RequestParam  String articleJson){	
		
		return articleService.InsertArticleData(articleJson);	
	}
	
	@RequestMapping("/updateArticleData")
	public String updateArticleData(HttpServletRequest res){		
		
		String articleId=res.getParameter("articleId");
		
		String articleTitle=res.getParameter("articleTitle");
		
		String articleTime=res.getParameter("articleTime");
		
		String articleShortContent=res.getParameter("articleShortContent");
		
		String articleContent=res.getParameter("articleContent");
		
		return articleService.updateArticleData(articleId,articleTitle,articleTime,articleShortContent,articleContent);	
	}
	
	@RequestMapping("/deleteArticleData")
	public String deleteArticleData(HttpServletRequest res){	
		
		String articleId=res.getParameter("articleId");
				
		return articleService.deleteArticleData(articleId);	
	}
}
