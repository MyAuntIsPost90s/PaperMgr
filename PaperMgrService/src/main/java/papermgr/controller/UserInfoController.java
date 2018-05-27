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
import papermgr.base.model.Userinfo;
import papermgr.service.UserInfoService;
import papermgr.uimodel.EUIPageList;

@Controller
@RequestMapping("userInfo")
public class UserInfoController {

	@Resource
	private UserInfoService userInfoService;

	/**
	 * 获取当前登陆用户
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("nowUser")
	public void nowUser(HttpServletRequest request, HttpServletResponse response) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo userinfo = (Userinfo) requestHolder.getClientUser();
			requestHolder.success(userinfo);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 获取当前用户列表
	 * 
	 * @param request
	 * @param response
	 * @param userinfo
	 * @param page
	 * @param rows
	 */
	@ResponseBody
	@RequestMapping("list")
	public void list(HttpServletRequest request, HttpServletResponse response, Userinfo userinfo, int page, int rows) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			EUIPageList<Userinfo> list = userInfoService.list(userinfo, page, rows);
			requestHolder.success(list);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 单个信息
	 * 
	 * @param request
	 * @param response
	 * @param userinfoid
	 */
	@ResponseBody
	@RequestMapping("single")
	public void single(HttpServletRequest request, HttpServletResponse response, String userinfoid) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Userinfo userinfo = userInfoService.single(userinfoid);
			requestHolder.success(userinfo);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param response
	 * @param userinfo
	 */
	@ResponseBody
	@RequestMapping("add")
	public void add(HttpServletRequest request, HttpServletResponse response, Userinfo userinfo) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			userInfoService.add(userinfo);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 修改信息
	 * 
	 * @param request
	 * @param response
	 * @param userinfo
	 */
	@ResponseBody
	@RequestMapping("update")
	public void update(HttpServletRequest request, HttpServletResponse response, Userinfo userinfo) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			userInfoService.update(userinfo);

			Userinfo nowUser = (Userinfo) requestHolder.getClientUser();
			if (nowUser.getUserid().equals(userinfo.getUserid())) { // 当修改用户为当前用户时
				nowUser = userInfoService.single(nowUser.getUserid());
				requestHolder.updateClientUser(nowUser);
			}

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
			userInfoService.batchDelete(ids);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

}
