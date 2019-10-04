package co.edu.unal.software_engineering.labs.pojo;

public class RegisterAssociationPOJO {
   private Integer userId;
    private Integer roleId;
    private Integer courseId;
    private Integer periodId;


   public Integer getUserId(){
       return this.userId;
   }
    public Integer getRoleId(){
        return this.roleId;
    }
    public Integer getCourseId(){
        return this.courseId;
    }
    public Integer getPeriodId(){
        return this.periodId;
    }
    public void setUserId(Integer UserId){
        this.userId=UserId;
    }
    public void setRoleId(Integer roleId){
        this.roleId=roleId;
    }
    public void setPeriodId(Integer periodId){
        this.periodId=periodId;

    }
    public void setCourseId(Integer courseId){
        this.courseId=courseId;
    }


 
}