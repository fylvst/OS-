import Component.Algorithm;
import Component.Process;
import Frame.displayFrame;

public class test {

  static void testHRRN() {
    Process p1 = new Process("1", 3, 1, 5);
    Process p2 = new Process("2", 2, 3, 3);
    Process p3 = new Process("3", 1, 2, 4);
    Process.processes.add(p1);
    Process.processes.add(p2);
    Process.processes.add(p3);
//    Algorithm.hrrn();
  }

  static void testRR() {
    Process p1 = new Process("1", 1, 1, 5);
    Process p2 = new Process("2", 2, 1, 3);
    Process p3 = new Process("3", 3, 2, 4);
    Process.processes.add(p1);
    Process.processes.add(p2);
    Process.processes.add(p3);
//    Algorithm.priority();
  }
//  public static void main(String[] args){
//    testRR();
//    System.out.println("------------");
//    System.out.println(Process.processes.get(0).toString());
//    System.out.println("------------");
//    Algorithm.priority();
//  }

  public static void main(String[] args) {
    new displayFrame();
  }


}
