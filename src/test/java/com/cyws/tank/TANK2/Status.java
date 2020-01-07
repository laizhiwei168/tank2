package com.cyws.tank.TANK2;
public enum Status {

    SCUUESS("1", "成功"), FAILED("2", "失败");

    private String value;
    private String desc;
    private String va;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private Status(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

	public String getVa() {
		return va;
	}

	public void setVa(String va) {
		this.va = va;
	}
}