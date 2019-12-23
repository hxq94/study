package com.tung.study.common.enums;

/**
 * Created by wilbert on 2018/3/30.
 */
public enum TableStateEnum {
	//表状态
    ACTIVE("00A", "生效"),
    NOTACTIVE("00X", "失效"),
    LOCK("00L", "锁定");

    private String cnName;

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;

    private TableStateEnum(String state, String cnName) {
        this.cnName = cnName;
        this.state = state;
    }


}
