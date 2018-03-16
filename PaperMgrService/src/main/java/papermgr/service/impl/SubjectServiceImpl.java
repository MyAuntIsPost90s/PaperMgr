package papermgr.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import papermgr.base.dao.SubjectMapper;
import papermgr.base.model.Subject;
import papermgr.common.RandomNum;
import papermgr.service.SubjectService;
import papermgr.uimodel.EUIPageList;
import papermgr.uimodel.EUITree;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Resource
	private SubjectMapper subjectMapper;

	@Override
	public Subject single(String subjectId) {
		return subjectMapper.getSingle(subjectId);
	}

	@Override
	public EUIPageList<Subject> list(Subject subject, int page, int rows) {
		PageList<Subject> pageList = subjectMapper.getListWithPage(subject, new PageBounds(page, rows));
		return new EUIPageList<Subject>(pageList.getPaginator().getTotalCount(), pageList);
	}

	@Override
	public List<EUITree> tree(Subject subject) {
		List<Subject> list = subjectMapper.getList(subject);
		EUITree root = new EUITree();
		root.setText("分类");
		root.setId("-1");

		List<EUITree> children = new LinkedList<EUITree>();
		for (Subject item : list) {
			EUITree node = new EUITree();
			node.setId(item.getSubjectid());
			node.setText(item.getSubjectname());
			children.add(node);
		}
		List<EUITree> trees = new LinkedList<EUITree>();
		root.setChildren(children);
		trees.add(root);
		return trees;
	}

	@Override
	public void add(Subject subject) throws Exception {
		subject.setSubjecttime(new Date());
		subject.setSubjectid(RandomNum.getSID());
		subjectMapper.insert(subject);
	}

	@Override
	public void update(Subject subject) {
		subjectMapper.update(subject);
	}

	@Override
	public void batchDelete(List<String> ids) {
		subjectMapper.batchDelete(ids);
	}

}
