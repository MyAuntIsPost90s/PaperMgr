package papermgr.base.model;

import java.util.Date;

public class Subject {
    private String subjectid;

    private String subjectname;

    private Date subjecttime;

    private String userid;

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public Date getSubjecttime() {
        return subjecttime;
    }

    public void setSubjecttime(Date subjecttime) {
        this.subjecttime = subjecttime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}