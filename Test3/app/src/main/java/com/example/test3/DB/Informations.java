package com.example.test3.DB;

public class Informations {
    public static final String TABLE_iNAME="Informations";

    public static final String COL_INFO="iinfo";

    private String info;

    public static final String CREATE_iTABLE=
            "CREATE TABLE "+ TABLE_iNAME+"( "
            +COL_INFO+" TEXT PRIMARY KEY)";

    public Informations(){}
    public Informations(String info){
        this.info=info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
