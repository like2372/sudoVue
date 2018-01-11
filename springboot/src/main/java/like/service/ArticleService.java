package like.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class ArticleService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	/**
	 * 分页查询articleList
	 * @param start
	 * @param end
	 * @return
	 */
	public String getArticleList(String start, String end){
		
		JSONObject json=new JSONObject();
		
		JSONArray jsonArray=new JSONArray();
			
		String resultCode="";
		
		String totalNumber="";
		
		try{
			
			String sql="select id,article_title,article_time,article_short_content from article ";
			
			if(start!=null&&start!=""&&end!=null&&end!=""){
				
				sql+=" limit "+start+","+end;
			}
												
			List<Map<String,Object>> resultList=jdbcTemplate.queryForList(sql);
			
			for(int i=0;i<resultList.size();i++){
				
				JSONObject rowJson=new JSONObject();
				
				Map<String,Object> rowMap=resultList.get(i);
				
				String id=rowMap.get("id")==null?"":rowMap.get("id").toString();								
				
				String articleTitle=rowMap.get("article_title")==null?"":rowMap.get("article_title").toString();
				
				String articleTime=rowMap.get("article_time")==null?"":rowMap.get("article_time").toString();
				
				String articleShortContent=rowMap.get("article_short_content")==null?"":rowMap.get("article_short_content").toString();
				
				//String articleContent=rowMap.get("article_content")==null?"":rowMap.get("article_content").toString();
				
				rowJson.put("id", id);			
				rowJson.put("articleTitle", articleTitle);
				rowJson.put("articleTime", articleTime);
				rowJson.put("articleShortContent",articleShortContent);
				//rowJson.put("articleContent", articleContent);
				
				jsonArray.add(rowJson);
			}
			
			String totalSql="select count(*) as total from article";
			
			List<Map<String,Object>> totalResultList=jdbcTemplate.queryForList(totalSql);
			
			totalNumber=totalResultList.get(0).get("total").toString();
			
			resultCode="1";
			
		}catch(Exception e){
			
			resultCode="0";
			
		}
				
		json.put("data", jsonArray);
		
		json.put("resultCode", resultCode);
		
		json.put("totalNumber", totalNumber);
		
		return json.toString();
	}
	
	
	/**
	 * 根据id获取article
	 * @param req
	 * @return
	 */
	public String getArticleDetail(String id) {
		
		JSONObject json=new JSONObject();
		
		JSONArray jsonArray=new JSONArray();
			
		String resultCode="";
		
		try{
			
			String sql="select id,article_title,article_time,article_short_content,article_content from article where 1=1 ";
			
			if(id!=null&&id!=""){
				
				sql+=" and id='"+id+"'";
			}
												
			List<Map<String,Object>> resultList=jdbcTemplate.queryForList(sql);
			
			if(resultList.size()==1){
				
				JSONObject rowJson=new JSONObject();
				
				Map<String,Object> rowMap=resultList.get(0);											
				
				String articleTitle=rowMap.get("article_title")==null?"":rowMap.get("article_title").toString();
				
				String articleTime=rowMap.get("article_time")==null?"":rowMap.get("article_time").toString();
				
				String articleShortContent=rowMap.get("article_short_content")==null?"":rowMap.get("article_short_content").toString();
				
				String articleContent=rowMap.get("article_content")==null?"":rowMap.get("article_content").toString();
				
				rowJson.put("id", id);			
				rowJson.put("articleTitle", articleTitle);
				rowJson.put("articleTime", articleTime);
				rowJson.put("articleShortContent",articleShortContent);
				rowJson.put("articleContent", articleContent);
				
				jsonArray.add(rowJson);
				
				resultCode="1";
				
			}	else{
				
				resultCode="0";
			}		
						
		}catch(Exception e){
			
			resultCode="0";
			
		}
				
		json.put("data", jsonArray);
		
		json.put("resultCode", resultCode);
		
		return json.toString();
	}
	
	public String InsertArticleData(String articleJson){
			
		String resultCode="";
		
		String articleTitle="";
		
		String articleShortContent="";
		
		String articleContent="";
		
		JSONObject resultJson=new JSONObject();
		
		try{
			
			JSONObject json=JSONObject.fromObject(articleJson);
						
			articleTitle=json.getString("acticleTitle");
			
			articleShortContent=json.getString("acticleShortContent");
			
			articleContent=json.getString("acticleContent");
						
			String sql="insert into article (id,article_title,article_time,article_short_content,article_content) values(?,?,?,?,?);";			
				
			UUID uuid=UUID.randomUUID();
			
			Date date=new Date();
			
			SimpleDateFormat sf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			
			String nowDate=sf.format(date);
		
			jdbcTemplate.update(sql, new Object[]{uuid.toString(),articleTitle,nowDate,articleShortContent,articleContent});
			
			resultCode="1";
						
		}catch(Exception e){
			
			resultCode="0";
		}
		
		resultJson.put("resultCode",resultCode);
		
		return resultJson.toString();
		
	}

	public String deleteArticleData(String articleId) {
		
		String resultCode="";
		
		JSONObject resultJson=new JSONObject();
		
		try{
			
			String sql="delete from article where id=?";							
			
			jdbcTemplate.update(sql, new Object[]{articleId});
			
			resultCode="1";
						
		}catch(Exception e){
			
			resultCode="0";
		}
		
		resultJson.put("resultCode",resultCode);
		
		return resultJson.toString();
	}

	public String updateArticleData(String articleId, String articleTitle,
			String articleTime, String articleShortContent,
			String articleContent) {
		
		String resultCode="";
		
		JSONObject resultJson=new JSONObject();
		
		try{
			
			String sql="update article set article_title=? , article_time=? , article_short_content=? , article_content=? where id=?";							
			
			jdbcTemplate.update(sql, new Object[]{articleTitle,articleTime,articleShortContent,articleContent,articleId});
			
			resultCode="1";
						
		}catch(Exception e){
			
			resultCode="0";
		}
		
		resultJson.put("resultCode",resultCode);
		
		return resultJson.toString();
	}


	
		
}
