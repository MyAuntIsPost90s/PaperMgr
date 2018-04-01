package papermgr.base.model;

import java.util.Date;

public class Question {
    private String questionid;

    private String questioncontent;

    private String questionselect;

    private String questionanswer;

    private Integer questiontype;

    private String subjectid;

    private Date questiontime;

    private Integer questionratio;

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getQuestioncontent() {
        return questioncontent;
    }

    public void setQuestioncontent(String questioncontent) {
        this.questioncontent = questioncontent;
    }

    public String getQuestionselect() {
        return questionselect;
    }

    public void setQuestionselect(String questionselect) {
        this.questionselect = questionselect;
    }

    public String getQuestionanswer() {
        return questionanswer;
    }

    public void setQuestionanswer(String questionanswer) {
        this.questionanswer = questionanswer;
    }

    public Integer getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(Integer questiontype) {
        this.questiontype = questiontype;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public Date getQuestiontime() {
        return questiontime;
    }

    public void setQuestiontime(Date questiontime) {
        this.questiontime = questiontime;
    }

    public Integer getQuestionratio() {
        return questionratio;
    }

    public void setQuestionratio(Integer questionratio) {
        this.questionratio = questionratio;
    }
}