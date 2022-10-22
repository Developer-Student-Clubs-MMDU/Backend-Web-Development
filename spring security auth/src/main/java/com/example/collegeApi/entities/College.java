package com.example.collegeApi.entities;

public class College {

    private long id;
    private String collegeName;
    private String courseName;
    private String courseFee;
    private String durationCourse;
    private String accommodation;
    private String accommodationFee;

    public College(long id, String collegeName, String courseName, String courseFee, String durationCourse, String accommodation, String accommodationFee) {
        super();
        this.id = id;
        this.collegeName = collegeName;
        this.courseName = courseName;
        this.courseFee = courseFee;
        this.durationCourse = durationCourse;
        this.accommodation = accommodation;
        this.accommodationFee = accommodationFee;
    }
    public College(){
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(String courseFee) {
        this.courseFee = courseFee;
    }

    public String getDurationCourse() {
        return durationCourse;
    }

    public void setDurationCourse(String durationCourse) {
        this.durationCourse = durationCourse;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public String getAccommodationFee() {
        return accommodationFee;
    }

    public void setAccommodationFee(String accommodationFee) {
        this.accommodationFee = accommodationFee;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", collegeName='" + collegeName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseFee='" + courseFee + '\'' +
                ", durationCourse='" + durationCourse + '\'' +
                ", accommodation='" + accommodation + '\'' +
                ", accommodationFee='" + accommodationFee + '\'' +
                '}';
    }
}
