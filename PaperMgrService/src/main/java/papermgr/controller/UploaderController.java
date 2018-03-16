package papermgr.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lingshi.web.model.RequestFile;
import lingshi.web.model.RequestHolder;
import net.coobird.thumbnailator.Thumbnails;
import papermgr.common.Constant;
import papermgr.common.RandomNum;
import papermgr.uimodel.LayUIEditImg;

@Controller
@RequestMapping("uploader")
public class UploaderController {

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param response
	 * @param basePath
	 */
	@ResponseBody
	@RequestMapping("upload")
	public void upload(HttpServletRequest request, HttpServletResponse response, String basePath) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			RequestFile requestFile = requestHolder.getRequestFile();
			if (requestFile == null || requestFile.isEmpty()) {
				requestHolder.fail("操作失败");
				return;
			}
			String path = basePath + RandomNum.getLGID() + ".png";
			MultipartFile multipartFile = requestFile.getFile();
			Thumbnails.of(multipartFile.getInputStream()).size(500, 500).outputQuality(0.7).outputFormat("png")
					.toFile(new File(requestHolder.getRealPathPath(path)));
			requestHolder.success("操作成功", path);
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}

	/**
	 * 上传图片（主要给富文本编辑器使用）在这里面成功和失败的code相反
	 * 
	 * @param request
	 * @param response
	 * @param basePath
	 */
	@ResponseBody
	@RequestMapping("uploadImg")
	public void uploadImg(HttpServletRequest request, HttpServletResponse response) {
		RequestHolder requestHolder = RequestHolder.get(request, response);
		try {
			RequestFile requestFile = requestHolder.getRequestFile();
			if (requestFile == null || requestFile.isEmpty()) {
				requestHolder.success("操作失败");
				return;
			}
			String path = Constant.EDITIMG_URL + RandomNum.getLGID() + ".png";
			MultipartFile multipartFile = requestFile.getFile();
			Thumbnails.of(multipartFile.getInputStream()).size(500, 500).outputQuality(0.7).outputFormat("png")
					.toFile(new File(requestHolder.getRealPathPath(path)));
			requestHolder.fail(new LayUIEditImg(
					"http://" + request.getServerName() + ":" + request.getServerPort() + Constant.URLHEAD + path));
		} catch (Exception e) {
			requestHolder.err("操作失败", e);
		}
	}
}
