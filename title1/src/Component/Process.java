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
  private int cyclingTime;//进程周转时间
  private float RR;//当前进程的相应比
  private PCB pcb;

  public Process(){
    this.pcb = new PCB();
  }

  public Process(String name, int priority, int arriveTime, int runtime) {
    this.name = name;
    this.priority = priority;
    this.arriveTime = arriveTime;
    this.runtime = runtime;
    this.pcb = new PCB();
    this.servedTime = 0;
    this.finishTime = 0;
    this.RR = 0;
  }

  public int getID() {
    return pcb.getPid();
  }
  public int getCyclingTime(){
    return this.cyclingTime;
  }
  public void set(Process p){
    this.arriveTime = p.arriveTime;
    this.pcb = p.pcb;
    this.name = p.name;
    this.runtime = p.runtime;
    this.priority = p.priority;
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
            ", cyclyingTime=" + cyclingTime +
            ", RR=" + RR +
            ", state=" + pcb.getState() +
            '}';
  }

  public static void resetList(){
    for(Process p : processes){
      p.servedTime = 0;
      p.finishTime = 0;
      p.pcb.setState(0);
      p.waitTime = 0;
      p.RR=0;
    }
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

  public int getFinishTime() {
    return finishTime;
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

  public void setRuntime(int runtime) {
    this.runtime = runtime;
  }

  public void setArriveTime(int arriveTime) {
    this.arriveTime = arriveTime;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setCyclingTime(int cyclingTime) {
    this.cyclingTime = cyclingTime;
  }

  public int getState(){
    return this.pcb.getState();
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