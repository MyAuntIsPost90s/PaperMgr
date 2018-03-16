package papermgr.service;

import java.util.List;

import papermgr.base.model.Subject;
import papermgr.uimodel.EUIPageList;
import papermgr.uimodel.EUITree;

public interface SubjectService {

	Subject single(String subjectId);

	EUIPageList<Subject> list(Subject subject, int page, int rows);
	
	List<EUITree> tree(Subject subject);

	void add(Subject subject) throws Exception;

	void update(Subject subject);

	void batchDelete(List<String> ids);

}
