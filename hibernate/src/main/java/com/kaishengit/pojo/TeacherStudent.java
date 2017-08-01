package com.kaishengit.pojo;

import javax.persistence.*;

@Entity
@Table(name = "teacher_student", schema = "hibernate", catalog = "")
@IdClass(TeacherStudentPK.class)
public class TeacherStudent {
    private int teacherId;
    private int studentId;

    @Id
    @Column(name = "teacher_id")
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Id
    @Column(name = "student_id")
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

        TeacherStudent that = (TeacherStudent) o;

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
