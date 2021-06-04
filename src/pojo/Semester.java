package pojo;

import java.sql.Date;
import java.util.*;

public class Semester {
    private int id;
    private int name;
    private int year;
    private Date dayStart;
    private Date dayEnd;
    private boolean current;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return id == semester.id && name == semester.name && year == semester.year && current == semester.current && Objects.equals(dayStart, semester.dayStart) && Objects.equals(dayEnd, semester.dayEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year, dayStart, dayEnd, current);
    }

    @Override
    public String toString() {
        return "Semester{" +
                "id=" + id +
                ", name=" + name +
                ", year=" + year +
                ", dayStart=" + dayStart +
                ", dayEnd=" + dayEnd +
                ", current=" + current +
                '}';
    }
}
