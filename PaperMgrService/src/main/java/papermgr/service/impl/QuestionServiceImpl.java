package papermgr.service.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import lingshi.convert.Convert;
import papermgr.base.dao.QuestionMapper;
import papermgr.base.model.Question;
import papermgr.common.ExcelUtil;
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

	@Override
	public void importExcel(InputStream inputStream, int type) throws Exception {
		try(Workbook wb = new HSSFWorkbook(inputStream)){
			Sheet sht = wb.getSheetAt(0);
			//对Sheet中的每一行进行迭代
			List<Question> list=new ArrayList<Question>();
			for (Row row : sht) {
				if(row.getRowNum()<1){
					continue;
				}
				Question question=new Question();
				question.setQuestionid(RandomNum.getLGID());
				row.getCell(0).setCellType(CellType.STRING);
				row.getCell(1).setCellType(CellType.STRING);
				row.getCell(2).setCellType(CellType.STRING);
				row.getCell(3).setCellType(CellType.STRING);
				question.setQuestioncontent(row.getCell(0).getStringCellValue());
				if(type==1){
					row.getCell(4).setCellType(CellType.STRING);
					question.setQuestionselect(row.getCell(1).getStringCellValue());
					question.setQuestionanswer(row.getCell(2).getStringCellValue());
					question.setQuestionratio(Convert.toInt(row.getCell(3).getStringCellValue()));
					question.setSubjectid(row.getCell(4).getStringCellValue() );
				}else{
					question.setQuestionselect("");
					question.setQuestionanswer(row.getCell(1).getStringCellValue());
					question.setQuestionratio(Convert.toInt(row.getCell(2).getStringCellValue()));
					question.setSubjectid(row.getCell(3).getStringCellValue() );
				}
				question.setQuestiontype(type);
				question.setQuestiontime(new Date());
				list.add(question);
			}
			questionMapper.batchInsert(list);
		}finally {
			inputStream.close();
		}
	}

	@Override
	public void downloadExcel(OutputStream outputStream,int type) throws Exception {
		if(type==1){
			String[] cols={"问题","选项(选项请用逗号隔开)","答案","难度系数","所属编号"};
			ExcelUtil.exportExcel(outputStream, null, "题目模板", cols);
		}else {
			String[] cols={"问题","答案","难度系数","所属编号"};
			ExcelUtil.exportExcel(outputStream, null, "题目模板", cols);
		}
	}
}
