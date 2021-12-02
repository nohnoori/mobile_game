package com.example.test3.DB;

public class Save {
    public static final String TABLE_sNAME="Save";

    public static final String COL_NUM="snum";
    public static final String COL_JSON="sname";
    public static final String COL_PID="sproid";

    private int num;
    private String jsonname;
    private int proid;

    public static final String CREATE_sTABLE=
            "CREATE TABLE "+TABLE_sNAME+"("
            +COL_NUM+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COL_JSON+" TEXT, "
            +COL_PID+" INTEGER)";

    public Save(){}
    public Save(int num, String jsonname,int proid){
        this.num=num;
        this.jsonname=jsonname;
        this.proid=proid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getJsonname() {
        return jsonname;
    }

    public void setJsonname(String jsonname) {
        this.jsonname = jsonname;
    }

    public int getProid() {
        return proid;
    }

    public void setProid(int proid) {
        this.proid = proid;
    }
}