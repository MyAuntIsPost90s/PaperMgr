package papermgr.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lingshi.web.model.RequestHolder;
import papermgr.base.model.Subject;
import papermgr.base.model.Userinfo;
import papermgr.service.SubjectService;
import papermgr.uimodel.EUIPageList;
import papermgr.uimodel.EUITree;

@Controller
@RequestMapping("subject")
public class SubjectController {

	@Resource
	private SubjectService subjectService;

	/**
	 * 获取集合
	 * 
	 * @param request
	 * @param response
	 * @param subject
	 * @param page
	 * @param rows
	 */
	@ResponseBody
	@RequestMapping("list")
	public void list(HttpServletRequest request, HttpServletResponse response, Subject subject, int page, int rows) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo userinfo = (Userinfo) requestHolder.getClientUser();
			subject.setUserid(userinfo.getUserid());
			EUIPageList<Subject> list = subjectService.list(subject, page, rows);
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
	public void single(HttpServletRequest request, HttpServletResponse response, String subjectId) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Subject subject = subjectService.single(subjectId);
			requestHolder.success(subject);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 获取树状集合
	 * 
	 * @param response
	 * @param request
	 * @param subject
	 */
	@ResponseBody
	@RequestMapping("tree")
	public void tree(HttpServletResponse response, HttpServletRequest request, Subject subject) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo userinfo = (Userinfo) requestHolder.getClientUser();
			subject.setUserid(userinfo.getUserid());
			List<EUITree> list = subjectService.tree(subject);
			requestHolder.success(list);
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
	public void add(HttpServletRequest request, HttpServletResponse response, Subject subject) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo userinfo = (Userinfo) requestHolder.getClientUser();
			subject.setUserid(userinfo.getUserid());
			subjectService.add(subject);
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
	public void update(HttpServletRequest request, HttpServletResponse response, Subject subject) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			subjectService.update(subject);
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
			subjectService.batchDelete(ids);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}
}
