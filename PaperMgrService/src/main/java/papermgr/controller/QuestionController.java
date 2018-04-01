package papermgr.controller;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lingshi.web.model.RequestHolder;
import papermgr.base.model.Question;
import papermgr.service.QuestionService;
import papermgr.uimodel.EUIPageList;

@Controller
@RequestMapping("question")
public class QuestionController {

	@Resource
	private QuestionService questionService;

	/**
	 * 获取集合
	 * 
	 * @param request
	 * @param response
	 * @param question
	 * @param page
	 * @param rows
	 */
	@ResponseBody
	@RequestMapping("list")
	public void list(HttpServletRequest request, HttpServletResponse response, Question question, int page, int rows) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			EUIPageList<Question> list = questionService.list(question, page, rows);
			requestHolder.success(list);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 获取随机集合
	 * 
	 * @param request
	 * @param response
	 * @param question
	 * @param count
	 */
	@ResponseBody
	@RequestMapping("ramdonList")
	public void ramdonList(HttpServletRequest request, HttpServletResponse response, Question question, int count) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			List<Question> list = questionService.ramdonList(question, count);
			requestHolder.success(list);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 获取单条数据信息
	 * 
	 * @param request
	 * @param response
	 * @param subjectId
	 */
	@ResponseBody
	@RequestMapping("single")
	public void single(HttpServletRequest request, HttpServletResponse response, String questionId) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Question question = questionService.single(questionId);
			requestHolder.success(question);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param response
	 * @param subject
	 */
	@ResponseBody
	@RequestMapping("add")
	public void add(HttpServletRequest request, HttpServletResponse response, Question question) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			questionService.add(question);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param response
	 * @param subject
	 */
	@ResponseBody
	@RequestMapping("update")
	public void update(HttpServletRequest request, HttpServletResponse response, Question question) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			questionService.update(question);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 批量删除
	 * 
	 * @param request
	 * @param response
	 * @param ids
	 */
	@ResponseBody
	@RequestMapping("batchDelete")
	public void batchDelete(HttpServletRequest request, HttpServletResponse response, @RequestBody List<String> ids) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			questionService.batchDelete(ids);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}
	
	/**
	 * 下载问题模板
	 * 
	 * @param request
	 * @param response
	 * @param type
	 */
	@ResponseBody
	@RequestMapping("downloadExcel")
	public void downloadExcel(HttpServletRequest request,HttpServletResponse response,int type){
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			response.reset();
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + java.net.URLEncoder.encode("题目模板"+".xls", "UTF8"));
			questionService.downloadExcel(response.getOutputStream(), type);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}
	
	/**
	 * 导入模板
	 * 
	 * @param request
	 * @param response
	 * @param type
	 */
	@ResponseBody
	@RequestMapping("importExcel")
	public void importExcel(HttpServletRequest request,HttpServletResponse response,int type){
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			InputStream inputStream = requestHolder.getRequestFile().getFile().getInputStream();
			questionService.importExcel(inputStream, type);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}
}
