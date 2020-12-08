package com.example.beststudy;

public class CourseDetail {

    private String className;
    private String classStart;
    private String classEnd;
    private String classProf;
    private String classLink;
    private String classDay;

    public CourseDetail(String a1, String a2, String a3, String a4, String a5, String a6){
        this.className = a1;
        this.classStart = a2;
        this.classEnd = a3;
        this.classProf = a4;
        this.classLink = a5;
        this.classDay = a6;
    }

    public String getClassName(){
        return className;
    }
    public String getClassStart(){
        return classStart;
    }
    public String getClassEnd(){
        return classEnd;
    }
    public String getClassProf(){
        return classProf;
    }
    public String getClassLink(){
        return classLink;
    }
    public String getClassDay(){
        return classDay;
    }

    public String getFullClass(){
        String full = className + " " + classProf + " " + classDay + " " + classStart + " " +classEnd + " " + classLink;
        return full;
    }



}
