package com.example.test3.DB;

public class Places {
    public static final String TABLE_pNAME="Places";

    public static final String COL_NUM="pnum";
    public static final String COL_PLACE="pplace";

    private int num;
    private String place;

    public static final String CREATE_pTABLE=
            "CREATE TABLE "+TABLE_pNAME+"( "
            +COL_NUM+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COL_PLACE+" TEXT)";

    public Places(){}
    public Places(int num, String place){
        this.num=num;
        this.place=place;
    }

    public int getNum() {
        return num;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
