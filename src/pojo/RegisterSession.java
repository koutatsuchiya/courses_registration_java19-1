package pojo;

import java.sql.Date;
import java.util.Objects;

public class RegisterSession {
    private int id;
    private Date dayStart;
    private Date dayEnd;
    Semester semesterId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDayStart() {
        return dayStart;
    }

    public void setDayStart(Date dayStart) {
        this.dayStart = dayStart;
    }

    public Date getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(Date dayEnd) {
        this.dayEnd = dayEnd;
    }

    public Semester getSemesterId(){ return semesterId; }

    public void setSemesterId(Semester semesterId) { this.semesterId = semesterId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterSession that = (RegisterSession) o;
        return id == that.id && Objects.equals(dayStart, that.dayStart) && Objects.equals(dayEnd, that.dayEnd) && Objects.equals(semesterId, that.semesterId);
    }

    @Override
    public int hashCode() { return Objects.hash(id, dayStart, dayEnd, semesterId); }

    @Override
    public String toString() {
        return "RegisterSession{" +
                "id=" + id +
                ", dayStart=" + dayStart +
                ", dayEnd=" + dayEnd +
                ", semesterId=" + semesterId.toString() +
                '}';
    }
}
