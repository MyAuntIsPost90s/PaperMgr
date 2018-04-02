package papermgr.service;

import java.util.List;

import papermgr.base.model.Paperquestion;
import papermgr.base.model.Question;

public interface PaperQuestionService {

	/**
	 * 获取单条文件信息
	 * 
	 * @param paperquestionId
	 * @return
	 */
	Paperquestion single(String paperquestionId);

	List<Question> list(Paperquestion paperquestion);

	void add(Paperquestion paperquestion) throws Exception;

	void update(Paperquestion paperquestion);

	void batchDelete(List<String> ids);

	void deleteByPaperId(List<String> ids);

}
