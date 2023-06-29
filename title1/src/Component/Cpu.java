package Component;
import Frame.displayFrame;

import java.util.*;

class sjfComparator implements Comparator<Process>{
  public int compare(Process p1, Process p2) {
    if(p1.getRuntime()-p1.getServedTime() <= p2.getRuntime()-p2.getServedTime()){
      return -1;
    }
    else{
      return 1;
    }
  }
}

class priorityComparator implements Comparator<Process>{
  public int compare(Process p1, Process p2) {
    if(p1.getPriority() < p2.getPriority()){
      return 1;
    }
    else{
      return -1;
    }
  }
}


public class Cpu {
public static Cpu cpu;

  private int time;//cpu计时器
  private int state;//cpu状态，1为被占用，0为空闲
  public ArrayList<Process> arriveList;
  public ArrayList<Process> waitList;
  public ArrayList<Process> finishList;

  public void setTime(int time) {
    this.time = time;
  }

  public void setState(int state) {
    this.state = state;
  }

  public int getState() {
    return state;
  }

  public int getTime() {
    return time;
  }

  public Cpu(){
    this.time = 0;
    this.state = 0;
    this.arriveList = new ArrayList<>();
    this.waitList = new ArrayList<>();
    this.finishList = new ArrayList<>();
    this.waitList = new ArrayList<>(Process.processes);
  }

  public boolean checkComplete(Process p){
    if(p.getServedTime()==p.getRuntime()){
      p.getPcb().setState(-1);
      p.setFinishTime(this.time);
      System.out.println(p.toString());
      return true;
    }
    return false;
  }

  public void run(Process p) throws InterruptedException {//cpu被进程p占用
    if(p.getServedTime()==0){
      p.setBeginTime(this.time);
    }
    this.time++;
    this.state = 1;
    p.setServedTime(p.getServedTime()+1);
    p.getPcb().setState(2);
    checkAndAdd();
    displayFrame.display(this.time,p);
    Thread.sleep(1000);
    while (GlobalVar.flag) {
      Thread.sleep(500);
    }
  }

  public void checkAndAdd() {
    for(int i=0;i< this.waitList.size();i++){
      if(this.waitList.get(i).getArriveTime() == this.time){
        this.waitList.get(i).getPcb().setState(1);
        this.arriveList.add(this.waitList.get(i));
        this.waitList.remove(this.waitList.get(i));
        i--;
      }
    }
  }

  public void run(Process p, String type){
    this.time++;
    this.state = 1;
    for(int i = 1;i < arriveList.size();i++){
      arriveList.get(i).setWaitTime(arriveList.get(i).getWaitTime()+1);
    }
    p.setServedTime(p.getServedTime()+1);
    if(p.getServedTime()==p.getRuntime()){
      p.getPcb().setState(-1);
      p.setFinishTime(this.time);
      System.out.println(p.toString());
      this.finishList.add(p);
      this.arriveList.remove(p);
    }
    else {p.getPcb().setState(2);}
    checkAndAdd();
    if(type.equals("sjf")){
      Collections.sort(arriveList, new sjfComparator());
    }
    else if(type.equals("priority")){
      Collections.sort(arriveList, new priorityComparator());
    }
    else if (type.equals("hrrn")) {
      updateRR();
    } else{
      for(int i=1;i<arriveList.size();i++){
        int tmp = arriveList.get(i).getServedTime();
        arriveList.get(i).setPriority((arriveList.get(i).getWaitTime()+tmp)/tmp);
      }
      Collections.sort(arriveList, new priorityComparator());
    }
  }

  //更新响应比,并按降序排列
  public void updateRR() {
    for (Process p:
         this.arriveList) {
      p.setRR(1+(float)(this.time-p.getArriveTime())/p.getRuntime());
    }
    this.arriveList.sort((p1, p2)->Float.compare(p2.getRR(), p1.getRR()));
  }

  public void hold(){//待机状态，等待下一个到达的进程
    this.time++;
    checkAndAdd();
  }
}
