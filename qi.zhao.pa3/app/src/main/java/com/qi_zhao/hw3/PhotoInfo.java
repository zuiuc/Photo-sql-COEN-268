package com.qi_zhao.hw3;

/**
 * Created by Qi on 5/17/2016.
 */
public class PhotoInfo {
    protected int id;
    protected String path;
    protected String caption;

    public PhotoInfo(int id, String caption, String path){
        this.id = id;
        this.caption = caption;
        this.path = path;
    }

    public int getId() {return id; }

    public void setId(int id) { this.id = id; }

    public String getCaption() { return caption; }

    public String getPath() { return path; }
}
