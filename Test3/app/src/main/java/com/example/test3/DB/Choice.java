package com.example.test3.DB;

public class Choice {
    public static final String TABLE_cNAME="Choice";

    public static final String COL_KEY="ckey";
    public static final String COL_CHOICE="cchoice";

    private String key;
    private String choice;

    public static final String CREATE_cTABLE=
            "CREATE TABLE "+TABLE_cNAME+"( "
            +COL_KEY+" TEXT PRIMARY KEY, "
            +COL_CHOICE+" TEXT)";

    public Choice(){}
    public Choice(String key,String choice){
        this.key=key;
        this.choice=choice;
    }

    public String getKey() {
        return key;
    }

    public String getChoice() {
        return choice;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }
}
