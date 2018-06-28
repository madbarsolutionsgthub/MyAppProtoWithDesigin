package madbarsoft.com.myappproto;

public class Category {

    private int id;
    private int sNo;
    private String title;
    private String description;
    private int numberOfQuestion;


    public Category(int id, int sNo, String title, String description, int numberOfQuestion) {
        this.id = id;
        this.sNo = sNo;
        this.title = title;
        this.description = description;
        this.numberOfQuestion = numberOfQuestion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getsNo() {
        return sNo;
    }

    public void setsNo(int sNo) {
        this.sNo = sNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(int numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }
}
