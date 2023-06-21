package Component;

import java.util.ArrayList;

class PCB{
  private int pid;
  private int state;//进程状态，0为等待，1为就绪，2为正在运行，-1为完成

  public PCB(){
    this.pid = 1;
    this.setState(0);
  }

  public void setPid(int pid) {
    this.pid = pid;
  }

  public void setState(int state){
    this.state = state;
  }

  public int getPid() {
    return pid;
  }

  public int getState() {
    return state;
  }
}

public class Process {

  public static ArrayList<Process> processes = new ArrayList<>();

  private String name;  //进程名
  private int priority;  // 优先级
  private int arriveTime; //进程到达时间
  private int beginTime; //开始执行时间
  private int servedTime; //进程已经执行时间
  private int waitTime; //进程等待时间
  private int runtime; //需要运行时间
  private int finishTime; //进程结束时的时间
  private int cyclyingTime;//进程周转时间
  private float RR;//当前进程的相应比
  private PCB pcb;

  public Process(){

  }

  public Process(String name, int priority, int arriveTime, int runtime) {
    this.name = name;
    this.priority = priority;
    this.arriveTime = arriveTime;
    this.runtime = runtime;
    this.pcb = new PCB();
  }

  @Override
  public String toString() {
    return "Process{" +
            "name='" + name + '\'' +
            " pid " + pcb.getPid() +
            ", priority=" + priority +
            ", arriveTime=" + arriveTime +
            ", servedTime=" + servedTime +
            ", beginTime=" + beginTime +
            ", waitTime=" + waitTime +
            ", runtime=" + runtime +
            ", finishTime=" + finishTime +
            ", cyclyingTime=" + cyclyingTime +
            ", RR=" + RR +
            ", state=" + pcb.getState() +
            '}';
  }

  public int getArriveTime() {
    return arriveTime;
  }

  public int getRuntime() {
    return runtime;
  }

  public PCB getPcb() {
    return pcb;
  }

  public int getServedTime() {
    return servedTime;
  }

  public int getWaitTime() {
    return waitTime;
  }

  public void setWaitTime(int waitTime) {
    this.waitTime = waitTime;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  public int getPriority() {
    return priority;
  }

  public void setServedTime(int servedTime) {
    this.servedTime = servedTime;
  }

  public int getBeginTime() {
    return beginTime;
  }

  public float getRR() {
    return RR;
  }

  public void setRR(float RR) {
    this.RR = RR;
  }

  public void setBeginTime(int beginTime) {
    this.beginTime = beginTime;
  }

  public void setFinishTime(int finishTime) {
    this.finishTime = finishTime;
  }
}