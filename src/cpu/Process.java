/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpu;

/**
 *
 * @author Mostafa
 */
public class Process {

    private static long PID = 1;
    private int brust, wTime,turnAround,arrival,remaining;
    private boolean finish;
    
    public Process(int brust,int arr) {PID++;this.brust = brust;arrival=arr;}
    public long getId(){return PID;}
    public int getBrust(){return brust;}
    public void setWaitingTime(int wTime){this.wTime=wTime;}
    public int getWaitingTime(){return wTime;}
    public int getArrival(){return arrival;}
    public int getTurnAround() {return turnAround;}
    public void setTurnAround(int turnAround) {this.turnAround = turnAround;}
    public void setFinish(boolean fi){finish=fi;}
    public boolean finish(){return finish;}
    public void setRemaining(int rem){this.remaining=rem;}
    public int getRem(){return remaining;}
}
