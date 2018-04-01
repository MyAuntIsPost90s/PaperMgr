package papermgr.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import papermgr.base.model.Question;
import papermgr.uimodel.EUIPageList;

public interface QuestionService {

	Question single(String questionId);

	EUIPageList<Question> list(Question question, int page, int rows);

	/**
	 * 随机获取题目集合
	 * 
	 * @param question
	 * @param count
	 * @return
	 */
	List<Question> ramdonList(Question question, int count);

	void add(Question question);

	void update(Question question);

	void batchDelete(List<String> ids);

	/**
	 * 导入excel
	 * 
	 * @param fileInputStream
	 * @param type
	 * @throws Exception
	 */
	void importExcel(InputStream inputStream, int type) throws Exception;

	/**
	 * 下载导出模板
	 * 
	 * @param type
	 * @throws Exception
	 */
	void downloadExcel(OutputStream outputStream, int type) throws Exception;
}
