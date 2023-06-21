import Component.*;
import Component.Process;

public class test {

  static void testHRRN(){
    Process p1 = new Process("1",0,1,5);
    Process p2 = new Process("2",0,3,3);
    Process p3 = new Process("3",0,2,4);
    Process.processes.add(p1);
    Process.processes.add(p2);
    Process.processes.add(p3);
    Algorithm.hrrn();
  }

  static void testRR(){
    Process p1 = new Process("1",3,1,5);
    Process p2 = new Process("2",2,1,3);
    Process p3 = new Process("3",1,2,4);
    Process.processes.add(p1);
    Process.processes.add(p2);
    Process.processes.add(p3);
    Algorithm.priority();
  }
  public static void main(String[] args){
    testHRRN();
  }

}
