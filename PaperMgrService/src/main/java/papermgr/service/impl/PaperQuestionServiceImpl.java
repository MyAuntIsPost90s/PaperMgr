package papermgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import papermgr.base.dao.PaperquestionMapper;
import papermgr.base.model.Paperquestion;
import papermgr.base.model.Question;
import papermgr.common.RandomNum;
import papermgr.service.PaperQuestionService;
import papermgr.service.QuestionService;

@Service
public class PaperQuestionServiceImpl implements PaperQuestionService {

	@Resource
	private PaperquestionMapper paperquestionMapper;
	@Resource
	private QuestionService questionService;
	
	@Override
	public Paperquestion single(String paperquestionId) {
		return paperquestionMapper.getSingle(paperquestionId);
	}

	@Override
	public List<Question> list(Paperquestion paperquestion) {
		List<Paperquestion> paperquestions = paperquestionMapper.getList(paperquestion);
		List<Question> list=new ArrayList<Question>();
		for (Paperquestion item : paperquestions) {
			Question question =new Question();
			question=questionService.single(item.getQuestionid());
			list.add(question);
		}
		return list;
	}

	@Override
	public void add(Paperquestion paperquestion) throws Exception {
		paperquestion.setPaperquestionid(RandomNum.getLGID());
		paperquestionMapper.insert(paperquestion);
	}

	@Override
	public void update(Paperquestion paperquestion) {
		paperquestionMapper.update(paperquestion);
	}

	@Override
	public void batchDelete(List<String> ids) {
		paperquestionMapper.batchDelete(ids);
	}

	@Override
	public void deleteByPaperId(List<String> ids) {
		paperquestionMapper.deleteByPaperId(ids);
	}

}
