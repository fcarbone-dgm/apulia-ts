package com.apuliasoft.test.model;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Slot {
    private Integer id;
    private Project project;
    private Employee employee;
    private Double hours;

    @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="GMT")
    private Date date;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public Double getHours() {
        return hours;
    }
    public void setHours(Double hours) {
        this.hours = hours;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHashBy(List<String> by) {
        String key = "";
        Iterator<String> byIterator = by.iterator();
        while(byIterator.hasNext()) {
            String val = byIterator.next();
            switch(val) {
                case "date":
                    key += this.getDate();
                    break;
                case "project":
                    key += this.getProject().getId();
                    break;
                default:
                    key += this.getEmployee().getId();
                    break;
            }
        }
        return key;
      }
}
