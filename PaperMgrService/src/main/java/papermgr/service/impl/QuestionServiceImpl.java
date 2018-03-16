package papermgr.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import papermgr.base.dao.QuestionMapper;
import papermgr.base.model.Question;
import papermgr.common.RandomNum;
import papermgr.service.QuestionService;
import papermgr.uimodel.EUIPageList;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Resource
	private QuestionMapper questionMapper;

	@Override
	public Question single(String questionId) {
		return questionMapper.getSingle(questionId);
	}
	
	@Override
	public List<Question> ramdonList(Question question,int count) {
		long allCount=questionMapper.count(question);
		if(allCount<count){
			count=(int)allCount;
		}
		if(count<1){
			return null;
		}
		long allPage = allCount/count;
		Random random=new Random();
		int page=random.nextInt((int)allPage)+1;	//获取一个随机页码
		return questionMapper.getListWithPage(question, new PageBounds(page,count));
	}

	@Override
	public EUIPageList<Question> list(Question question, int page, int rows) {
		PageList<Question> pageList = questionMapper.getListWithPage(question, new PageBounds(page, rows));
		return new EUIPageList<Question>(pageList.getPaginator().getTotalCount(), pageList);
	}

	@Override
	public void add(Question question) {
		question.setQuestionid(RandomNum.getLGID());
		question.setQuestiontime(new Date());
		questionMapper.insert(question);
	}

	@Override
	public void update(Question question) {
		questionMapper.update(question);
	}

	@Override
	public void batchDelete(List<String> ids) {
		questionMapper.batchDelete(ids);
	}
}
