package papermgr.service;

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
}
