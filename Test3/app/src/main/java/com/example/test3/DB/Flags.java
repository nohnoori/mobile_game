package com.example.test3.DB;

public class Flags {
    public static final String TABLE_fNAME="Flags";

    public static final String COL_NAME="fname";
    public static final String COL_FLAG="fflag";

    private String name;
    private int flag;

    public static final String CREATE_fNAME=
            "CREATE TABLE "+TABLE_fNAME+"( "
            +COL_NAME+" TEXT PRIMARY KEY, "
            +COL_FLAG+" INTEGER)";

    public Flags(){}
    public Flags(String name, int flag){
        this.name=name;
        this.flag=flag;
    }

    public String getName() {
        return name;
    }

    public int getFlag() {
        return flag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
