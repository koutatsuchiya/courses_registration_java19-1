package pojo;

import java.sql.Date;
import java.util.Objects;

public class Registered {
    private int id;
    private int studentId;
    private int courseId;
    private Date dateEnroll;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getDateEnroll() {
        return dateEnroll;
    }

    public void setDateEnroll(Date dateEnroll) {
        this.dateEnroll = dateEnroll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registered that = (Registered) o;
        return id == that.id && studentId == that.studentId && courseId == that.courseId && Objects.equals(dateEnroll, that.dateEnroll);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, courseId, dateEnroll);
    }

    @Override
    public String toString() {
        return "Registered{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", dateEnroll=" + dateEnroll +
                '}';
    }
}
