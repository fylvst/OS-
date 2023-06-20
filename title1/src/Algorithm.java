import Component.Cpu;
import Component.Process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Algorithm {

  public static void fifo(){
    Cpu cpu = new Cpu();
    while(!Process.processes.isEmpty()) {
      while(cpu.arriveList.isEmpty()){
        cpu.hold();
      }
      while(!cpu.arriveList.isEmpty()){
        cpu.run(cpu.arriveList.get(0));
      }
    }
  }

  public static void sjf(){
    Cpu cpu = new Cpu();
    while(!Process.processes.isEmpty()){
      while(cpu.arriveList.isEmpty()){
        cpu.hold();
      }
      Collections.sort(cpu.arriveList, new Comparator<Process>() {
        @Override
        public int compare(Process p1, Process p2) {
          if(p1.getRuntime()-p1.getServedTime() <= p2.getRuntime()-p2.getServedTime()){
            return -1;
          }
          else{
            return 1;
          }
        }
      });
      while(!cpu.arriveList.isEmpty()){
        cpu.run(cpu.arriveList.get(0),"sjf");
      }
    }
  }

  public static void rr(int slice){
    Cpu cpu = new Cpu();
    while(!Process.processes.isEmpty()){
      while(cpu.arriveList.isEmpty()){
        cpu.hold();
      }
      while(!cpu.arriveList.isEmpty()){
        for(int i=0;i<slice;i++){
          if(cpu.checkComplete(cpu.arriveList.get(0))){
            for(int j=i;j<slice;j++){
              cpu.hold();
            }
            cpu.arriveList.remove(cpu.arriveList.get(0));
            break;
          }
          else{
            cpu.run(cpu.arriveList.get(0));
          }
        }
        if(cpu.arriveList.size()>1) {
          Process tmp = cpu.arriveList.get(0);
          cpu.arriveList.remove(tmp);
          cpu.arriveList.add(tmp);
        }
      }
    }
  }

  public static void priority(){
    Cpu cpu = new Cpu();
    while(!Process.processes.isEmpty()){
      while(cpu.arriveList.isEmpty()){
        cpu.hold();
      }
      while(!cpu.arriveList.isEmpty()){
        cpu.run(cpu.arriveList.get(0),"priority");
      }
    }
  }

  public static void hrrn(){
    Cpu cpu = new Cpu();
    while(!Process.processes.isEmpty()){
      while(cpu.arriveList.isEmpty()){
        cpu.hold();
      }
      while(!cpu.arriveList.isEmpty()){
        cpu.run(cpu.arriveList.get(0),"hrrn");
      }
    }
  }
}
