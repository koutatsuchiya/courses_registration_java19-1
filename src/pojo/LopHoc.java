package pojo;

import java.util.Objects;

public class LopHoc
{
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LopHoc lopHoc = (LopHoc) o;
        return id == lopHoc.id && Objects.equals(name, lopHoc.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "LopHoc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
