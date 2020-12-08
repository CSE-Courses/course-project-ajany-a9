package com.example.beststudy;

import java.util.ArrayList;


class timeBoolean{
    private String time;
    private boolean boo;
    public timeBoolean(String time){
        this.time=time;
        this.boo=true;
    }
    public String getTime(){
        return this.time;
    }
    public boolean getAvailable(){
        return this.boo;
    }
    public void setNotAvailable(){
        this.boo=false;
    }
    public void setAvailable(){
        this.boo=true;
    }
}

public class timeInspector {
    private ArrayList<timeBoolean> timeSet;
    public timeInspector(){
        this.timeSet=iniList();
    }

    public ArrayList<timeBoolean> iniList(){
        ArrayList<timeBoolean> timeSet1= new ArrayList<timeBoolean>();
        for(int i = 7; i<25 ; i++){
            for(int j= 0;j<6;j++){
                Integer hour = new Integer(i);
                Integer minute = new Integer(j);
                String time = hour.toString()+":"+minute+"0";
                timeBoolean timeBoolean= new timeBoolean(time);

                timeSet1.add(timeBoolean);
            }
        }
        return timeSet1;
    }

    public boolean checkTimeBlock(String startTime, String endTime){

        boolean control=true;
        for(timeBoolean s:this.timeSet){
            if(s.getTime().equals(startTime)){
                control=false;
            }
            if(control==false){
              if(s.getAvailable()==false){
                  return false;
              }
            }
            if(s.getTime().equals((endTime))){
                control=true;
            }

        }

        return true;
    }
    public boolean setTimeBlock(String startTime, String endTime){
        if(this.checkTimeBlock(startTime,endTime)==true){
            boolean control = true;
            for (timeBoolean s : this.timeSet) {
                if (s.getTime().equals(startTime)) {
                    control = false;
                }
                if (control == false) {
                    s.setNotAvailable();
                }
                if (s.getTime().equals((endTime))) {
                    control = true;
                }

            }
        }
        else return false;

        return true;
    }



}
