package com.pe.charger;

public class Current {
    Integer cur;

    public Integer getCur() {
        return cur;
    }

    public Current() {
        this.cur = 0;
    }
    public Current(Integer cur) {
        this.cur = cur;
    }

    public void setCur(Integer cur) {
        this.cur = cur;
    }

    @Override
    public String toString() {
        return "Current{" +
                "cur=" + cur +
                '}';
    }

}
