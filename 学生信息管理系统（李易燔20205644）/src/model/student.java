package model;

public class student {
    private int sid;            //ID，姓名，班级，GPA,出生地
    private String  name;
    private String stuclass;
    private float GPA;
    private String password;

    public student(String name, String stuclass, float GPA, String password) {
        this.name = name;
        this.stuclass = stuclass;
        this.GPA = GPA;
        this.password = password;
    }

    public student() {
    }

    public int getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public String getStuclass() {
        return stuclass;
    }

    public float getGPA() {
        return GPA;
    }

    public String getPassword() {
        return password;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStuclass(String stuclass) {
        this.stuclass = stuclass;
    }

    public void setGPA(float GPA) {
        this.GPA = GPA;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
