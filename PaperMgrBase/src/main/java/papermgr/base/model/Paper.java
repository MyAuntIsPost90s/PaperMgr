package papermgr.base.model;

import java.util.Date;

public class Paper {
    private String paperid;

    private String papertitle;

    private String papergroupid;

    private String papernote;

    private Date papertime;

    private String subjectid;

    public String getPaperid() {
        return paperid;
    }

    public void setPaperid(String paperid) {
        this.paperid = paperid;
    }

    public String getPapertitle() {
        return papertitle;
    }

    public void setPapertitle(String papertitle) {
        this.papertitle = papertitle;
    }

    public String getPapergroupid() {
        return papergroupid;
    }

    public void setPapergroupid(String papergroupid) {
        this.papergroupid = papergroupid;
    }

    public String getPapernote() {
        return papernote;
    }

    public void setPapernote(String papernote) {
        this.papernote = papernote;
    }

    public Date getPapertime() {
        return papertime;
    }

    public void setPapertime(Date papertime) {
        this.papertime = papertime;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }
}