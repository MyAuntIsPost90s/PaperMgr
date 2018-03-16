package papermgr.base.dao;

import java.util.List;

import lingshi.mybaties.mapperextend.BaseMapper;
import papermgr.base.model.Paperquestion;

public interface PaperquestionMapper extends BaseMapper<Paperquestion> {
	void deleteByPaperId(List<String> ids);
}