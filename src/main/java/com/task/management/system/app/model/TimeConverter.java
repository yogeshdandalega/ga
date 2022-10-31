package com.task.management.system.app.model;

public class TimeConverter {


    public static  String timeConvertIntoDay(String time){
        int day=Integer.parseInt(time);
        return String.format("%dd ", day);
    }
    public static String timeConvertIntoDayHourseMinutes(String times){

        double time =Double.parseDouble(times);
        if(time<8){
            int  hourse= (int) time;
            double minutes=((time%hourse)*100);
            if(minutes==0){
                return  String.format("%dh",hourse);
            }
            else if (minutes>31) {
                double minute=  minutes-40;
                return String.format(" %dh %.0fm ", hourse, minute);

            }
            else {
                return String.format("%dh %.0fm ", hourse, minutes);
            }
        } else if (time>=8 &&time<16 ){
            int day=1;
            int hourse= (int) (time-8);
            double hoursed=time-8;
            double minutes=((hoursed%hourse)*100);
            if(minutes==0){
                return  String.format("%dd %dh  ",day,hourse);
            }else if (minutes>31) {
                double minute=  minutes-40;
                return String.format("%dd %dh %.0fm ", day, hourse, minute);

            }
            else {
                return String.format("%dd %dh %.0fm ", day, hourse, minutes);
            }
        } else if (time>=16 &&time<24) {
            int day=2;
            int hourse= (int) (time-16);
            double hoursed=time-16;
            double minutes=((hoursed%hourse)*100);
            if(minutes==0){
                return  String.format("%dd %dh  ",day,hourse);
            }else if (minutes>31) {
                double minute=  minutes-40;
                return String.format("%dd %dh %.0fm ", day, hourse, minute);

            }
            else {
                return String.format("%dd %dh %.0fm ", day, hourse, minutes);
            }
        } else if (time>=24 &&time<32) {
            int day=3;
            int hourse= (int) (time-24);
            double hoursed=time-24;
            double minutes=((hoursed%hourse)*100);
            if(minutes==0){
                return  String.format("%dd %dh  ",day,hourse);
            }else if (minutes>31) {
                double minute=  minutes-40;
                return String.format("%dd %dh %.0fm ", day, hourse, minute);

            }
            else {
                return String.format("%dd %dh %.0fm ", day, hourse, minutes);
            }
        }
        else if (time>=32 &&time<40) {
            int day=4;
            int hourse= (int) (time-32);
            double hoursed=time-32;
            double minutes=((hoursed%hourse)*100);
            if(minutes==0){
                return  String.format("%dd %dh  ",day,hourse);
            } else if (minutes>31) {
                  double minute=  minutes-40;
                return String.format("%dd %dh %.0fm ", day, hourse, minute);

            } else {
                return String.format("%dd %dh %.0fm ", day, hourse, minutes);
            }
        }

        return null;
    }
}
