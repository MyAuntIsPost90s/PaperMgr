package papermgr.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lingshi.getway.model.RequestHolder;
import papermgr.base.model.Paperquestion;
import papermgr.base.model.Question;
import papermgr.service.PaperQuestionService;

@Controller
@RequestMapping("paperQuestion")
public class PaperQuestionController {

	@Resource
	private PaperQuestionService paperQuestionService;
	
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
	public void list(HttpServletRequest request, HttpServletResponse response, Paperquestion paperquestion) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			List<Question> list = paperQuestionService.list(paperquestion);
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
	public void single(HttpServletRequest request, HttpServletResponse response, String paperquestionId) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Paperquestion paperquestion = paperQuestionService.single(paperquestionId);
			requestHolder.success(paperquestion);
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
	public void add(HttpServletRequest request, HttpServletResponse response, Paperquestion paperquestion) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			paperQuestionService.add(paperquestion);
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
	public void update(HttpServletRequest request, HttpServletResponse response, Paperquestion paperquestion) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			paperQuestionService.update(paperquestion);
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
			paperQuestionService.batchDelete(ids);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}
	
	/**
	 * 删除通过PaperId
	 * 
	 * @param request
	 * @param response
	 * @param ids
	 */
	@ResponseBody
	@RequestMapping("deleteByPaperId")
	public void deleteByPaperId(HttpServletRequest request,HttpServletResponse response,@RequestBody List<String> ids){
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			paperQuestionService.deleteByPaperId(ids);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}
	
}
