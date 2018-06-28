package madbarsoft.com.myappproto;

import java.io.Serializable;

public class QuestionAndAns implements Serializable{
    private int sNo;
    private int categoryId;
    private String qst;
    private String ans;


    public QuestionAndAns(int sNo, int categoryId, String qst, String ans) {
        this.sNo = sNo;
        this.categoryId = categoryId;
        this.qst = qst;
        this.ans = ans;
    }

    public int getsNo() {
        return sNo;
    }

    public void setsNo(int sNo) {
        this.sNo = sNo;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getQst() {
        return qst;
    }

    public void setQst(String qst) {
        this.qst = qst;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
