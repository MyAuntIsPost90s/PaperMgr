package papermgr.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import lingshi.web.model.RequestHolder;
import papermgr.base.model.Paper;
import papermgr.common.Constant;
import papermgr.common.RandomNum;
import papermgr.service.PaperService;
import papermgr.uimodel.EUIPageList;

@Controller
@RequestMapping("paper")
public class PaperController {

	@Resource
	private PaperService paperService;

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
	public void list(HttpServletRequest request, HttpServletResponse response, Paper paper, int page, int rows) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			EUIPageList<Paper> list = paperService.list(paper, page, rows);
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
	public void single(HttpServletRequest request, HttpServletResponse response, String paperId) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			Paper paper = paperService.single(paperId);
			requestHolder.success(paper);
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
	public void add(HttpServletRequest request, HttpServletResponse response, Paper paper, String questionIds) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			paperService.add(paper, questionIds);
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
	public void update(HttpServletRequest request, HttpServletResponse response, Paper paper, String questionIds) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			paperService.update(paper, questionIds);
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
			paperService.batchDelete(ids);
			requestHolder.success("操作成功");
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	@ResponseBody
	@RequestMapping("exportWord")
	public void exportWord(HttpServletRequest request, HttpServletResponse response, String html) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		ActiveXComponent app = new ActiveXComponent("Word.Application");
		app.setProperty("Visible", new Variant(false));

		String tempHtmlName = Constant.DOC_TEMP_URL + RandomNum.getRandom() + ".html";
		String tempHtmlPath = requestHolder.getRealPathPath(tempHtmlName);
		String tempWordName = Constant.DOC_TEMP_URL + RandomNum.getRandom() + ".doc";
		String tempWordPath = requestHolder.getRealPathPath(tempWordName);
		try {
			// word内容
			String content = "<html><head><meta http-equiv=\"Content-Type\""
					+ "content=\"text/html; charset=UTF-8\"></head><body>" + html + "</body></html>";
			// 先将html保存到临时区域
			try (FileOutputStream fileOutputStream = new FileOutputStream(tempHtmlPath)) {
				fileOutputStream.write(content.getBytes("utf-8"));
			}

			// 启动word
			Dispatch wordDoc = app.getProperty("Documents").toDispatch();
			Dispatch doc = Dispatch
					.invoke(wordDoc, "Open", Dispatch.Method,
							new Object[] { tempHtmlPath, new Variant(false), new Variant(true) }, new int[1])
					.toDispatch();
			Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] { tempWordPath, new Variant(1) },
					new int[1]);
			Dispatch.call(doc, "Close", new Variant(false));

			request.setCharacterEncoding("utf-8");
			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition",
					"attachment;fileName=" + new String(("试卷.doc").getBytes(), "iso-8859-1"));
			// 写入到输出流
			try (OutputStream outputStream = response.getOutputStream();
					FileInputStream inputStream = new FileInputStream(tempWordPath)) {
				int len = 0;
				byte[] buff = new byte[1024 * 1024];
				while ((len = inputStream.read(buff)) != -1) {
					outputStream.write(buff, 0, len);
				}
			}
		} catch (Exception e) {
			requestHolder.err(e);
		} finally {
			// 删除临时文件
			new File(tempHtmlPath).delete();
			new File(tempWordPath).delete();
			app.invoke("Quit", new Variant[] {}); // 关闭com
			ComThread.Release();
		}
	}

}
