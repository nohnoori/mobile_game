package com.example.test3.DB;

public class Thedays {
    public static final String TABLE_tNAME="Thedays";

    public static final String COL_NAME="tname";
    public static final String COL_DAY="tday";

    private String name;
    private String day;

    public static final String CREATE_tTABLE=
            "CREATE TABLE "+ TABLE_tNAME+"( "
            + COL_NAME+" TEXT PRIMARY KEY, "
            + COL_DAY+" TEXT)";

    public Thedays(){}
    public Thedays(String name, String day){
        this.name=name;
        this.day=day;
    }

    public String getName() {
        return name;
    }

    public String getDay() {
        return day;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
