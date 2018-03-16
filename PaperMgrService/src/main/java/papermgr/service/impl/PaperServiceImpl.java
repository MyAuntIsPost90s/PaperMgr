package papermgr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import lingshi.valid.StringValid;
import papermgr.base.dao.PaperMapper;
import papermgr.base.model.Paper;
import papermgr.base.model.Paperquestion;
import papermgr.common.RandomNum;
import papermgr.service.PaperQuestionService;
import papermgr.service.PaperService;
import papermgr.uimodel.EUIPageList;

@Service
public class PaperServiceImpl implements PaperService {

	@Resource
	private PaperMapper paperMapper;
	@Resource
	private PaperQuestionService paperQuestionService;

	@Override
	public Paper single(String paperId) {
		return paperMapper.getSingle(paperId);
	}

	@Override
	public EUIPageList<Paper> list(Paper paper, int page, int rows) {
		PageList<Paper> pageList = paperMapper.getListWithPage(paper, new PageBounds(page, rows));
		return new EUIPageList<Paper>(pageList.getPaginator().getTotalCount(), pageList);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void add(Paper paper, String questionIds) throws Exception {
		paper.setPaperid(RandomNum.getLGID());
		paper.setPapertime(new Date());
		paperMapper.insert(paper);

		// 添加题目
		String[] ids = questionIds.split(",");
		for (String id : ids) {
			if (StringValid.isNullOrEmpty(id)) {
				continue;
			}
			Paperquestion paperquestion = new Paperquestion();
			paperquestion.setPaperid(paper.getPaperid());
			paperquestion.setQuestionid(id);
			paperQuestionService.add(paperquestion);
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void update(Paper paper, String questionIds) throws Exception {
		paperMapper.update(paper);
		// 添加题目
		List<String> paperIds = new ArrayList<String>();
		paperIds.add(paper.getPaperid());
		paperQuestionService.deleteByPaperId(paperIds); // 删除旧的题目
		String[] ids = questionIds.split(",");
		for (String id : ids) {
			if (StringValid.isNullOrEmpty(id)) {
				continue;
			}
			Paperquestion paperquestion = new Paperquestion();
			paperquestion.setPaperid(paper.getPaperid());
			paperquestion.setQuestionid(id);
			paperQuestionService.add(paperquestion);
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void batchDelete(List<String> ids) {
		paperQuestionService.deleteByPaperId(ids);
		paperMapper.batchDelete(ids);
	}

}
