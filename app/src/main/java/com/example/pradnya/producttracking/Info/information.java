package com.example.pradnya.producttracking.Info;

public class information {
    private String box_no,category;

    public information() {
    }

    public information(String box_no, String category) {
        this.box_no = box_no;
        this.category = category;
    }

    public String getBox_no() {
        return box_no;
    }

    public void setBox_no(String box_no) {
        this.box_no = box_no;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
