package com.byron.keepaccount.bean;

/**
 * 记录一条数据的bean类
 * @author Byron
 * @date 220228
 */
public class AccountBean {
    private int year, month, day;
    private int id, kind;   //类型：收入：1 支出：0
    private int imageId;    //被选中类型的图片
    private String typeName;    //类型名字
    private String beiZhu, time;    //备注和记账的时间
    private float money;    //记账的金额


    public AccountBean(){

    }
    public AccountBean(int id, String typeName, String beiZhu, String time, float money,
                       int year, int month, int day, int imageId, int kind){
        this.id = id;
        this.typeName = typeName;
        this.beiZhu = beiZhu;
        this.time = time;
        this.money = money;
        this.year = year;
        this.month = month;
        this.day = day;
        this.imageId = imageId;
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "AccountBean{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", id=" + id +
                ", kind=" + kind +
                ", imageId=" + imageId +
                ", typeName='" + typeName + '\'' +
                ", beiZhu='" + beiZhu + '\'' +
                ", time='" + time + '\'' +
                ", money=" + money +
                '}';
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBeiZhu() {
        return beiZhu;
    }

    public void setBeiZhu(String beiZhu) {
        this.beiZhu = beiZhu;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
