package com.devendra.sample.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class XmlFile {
    @Id
    private String fileId;
    private String name;
    private String url;


    public XmlFile(String fileId, String name, String url) {
        this.fileId = fileId;
        this.name = name;
        this.url = url;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
