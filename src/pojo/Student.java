package pojo;

import java.util.Objects;

public class Student {
    private int id;
    private String mssv;
    private String name;
    private String gender;
    private LopHoc classId;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) { this.gender = gender; }

    public LopHoc getClassId() { return classId; }

    public void setClassId(LopHoc classId) { this.classId = classId; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(mssv, student.mssv) && Objects.equals(name, student.name) && Objects.equals(gender, student.gender) && Objects.equals(classId, student.classId) && Objects.equals(password, student.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mssv, name, gender, classId, password);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", mssv='" + mssv + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", lop=" + classId.toString() +
                ", password =" + password +
                '}';
    }
}
