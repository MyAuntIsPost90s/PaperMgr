package papermgr.service;

import java.util.List;

import papermgr.base.model.Paper;
import papermgr.uimodel.EUIPageList;

public interface PaperService {

	Paper single(String paperId);

	EUIPageList<Paper> list(Paper paper, int page, int rows);

	void add(Paper paper, String questionIds) throws Exception;

	void update(Paper paper, String questionIds) throws Exception;

	void batchDelete(List<String> ids);

}
