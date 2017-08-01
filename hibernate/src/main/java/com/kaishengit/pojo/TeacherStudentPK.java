package com.kaishengit.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class TeacherStudentPK implements Serializable {
    private int teacherId;
    private int studentId;

    @Column(name = "teacher_id")
    @Id
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Column(name = "student_id")
    @Id
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherStudentPK that = (TeacherStudentPK) o;

        if (teacherId != that.teacherId) return false;
        if (studentId != that.studentId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teacherId;
        result = 31 * result + studentId;
        return result;
    }
}
