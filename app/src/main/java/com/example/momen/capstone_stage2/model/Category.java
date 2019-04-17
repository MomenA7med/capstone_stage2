package com.example.momen.capstone_stage2.model;

/**
 * Created by Momen on 4/16/2019.
 */

public class Category {
    private String categoryName;
    private int maxDegree;

    public Category(){}

    public Category(String categoryName, int maxDegree){
        this.categoryName = categoryName;
        this.maxDegree = maxDegree;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getMaxDegree() {
        return maxDegree;
    }

    public void setMaxDegree(int maxDegree) {
        this.maxDegree = maxDegree;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
