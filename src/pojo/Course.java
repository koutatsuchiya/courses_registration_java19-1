package pojo;

import dao.SemesterDAO;
import dao.SubjectDAO;

import java.util.Objects;

public class Course {
    private int id;
    private String subjectId;
    private String gvlt;
    private String room;
    private int weekday;
    private int shift;
    private int slot;
    private int semesterId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getGvlt() {
        return gvlt;
    }

    public void setGvlt(String gvlt) {
        this.gvlt = gvlt;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && weekday == course.weekday && shift == course.shift && slot == course.slot && semesterId == course.semesterId && Objects.equals(subjectId, course.subjectId) && Objects.equals(gvlt, course.gvlt) && Objects.equals(room, course.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subjectId, gvlt, room, weekday, shift, slot, semesterId);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", subjectId='" + SubjectDAO.getSubject(subjectId).toString() + '\'' +
                ", gvlt='" + gvlt + '\'' +
                ", room='" + room + '\'' +
                ", weekday=" + weekday +
                ", shift=" + shift +
                ", slot=" + slot +
                ", semesterId=" + SemesterDAO.getSemester(semesterId).toString() +
                '}';
    }
}
