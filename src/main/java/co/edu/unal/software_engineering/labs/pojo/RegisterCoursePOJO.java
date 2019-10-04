package co.edu.unal.software_engineering.labs.pojo;

public class RegisterCoursePOJO {
private String courseName;
private Integer durationHours;



public String getCourseName(){
    return this.courseName;
}
public Integer getDurationHours(){
    return this.durationHours;
    }
public void setCourseName(String courseName){
    this.courseName=courseName;
}
public void setDurationsHours(Integer hours){
    this.durationHours=hours;
}
}