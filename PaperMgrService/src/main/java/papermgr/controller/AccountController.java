package papermgr.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lingshi.web.model.RequestHolder;
import papermgr.base.model.Userinfo;
import papermgr.service.UserInfoService;

@Controller
@RequestMapping("account")
public class AccountController {

	@Resource
	private UserInfoService userInfoService;

	/**
	 * 登陆
	 * @param request
	 * @param response
	 * @param username
	 * @param password
	 */
	@ResponseBody
	@RequestMapping("login")
	public void login(HttpServletRequest request, HttpServletResponse response, String username, String password) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			StringBuilder stringBuilder = new StringBuilder();
			Userinfo userinfo = userInfoService.login(username, password, stringBuilder);
			if (userinfo == null) {
				requestHolder.fail(stringBuilder.toString());
				return;
			}

			requestHolder.setClientUser(userinfo);
			requestHolder.success(stringBuilder.toString(), userinfo);
		} catch (Exception e) {
			requestHolder.err("登陆失败", e);
		}
	}
}
