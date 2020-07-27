package com.janjakubowski.technical_task;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Record {

    @Id
    private String primaryKey;
    private String name;
    private String description;
    private String updatedTimestamp;

    public Record(String primaryKey, String name, String description, String updatedTimestamp) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.description = description;
        this.updatedTimestamp = updatedTimestamp;
    }

    public Record() {}

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUpdatedTimestamp(String updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }

    @Override
    public String toString() {
        return "Record{" +
                "primaryKey='" + primaryKey + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", updatedTimestamp=" + updatedTimestamp +
                '}';
    }
}
