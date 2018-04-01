package papermgr.base.dao;

import java.util.List;

import lingshi.mybaties.mapperextend.BaseMapper;
import papermgr.base.model.Question;

public interface QuestionMapper extends BaseMapper<Question> {
	void batchInsert(List<Question> list);
}