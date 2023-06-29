package Component;

import Component.*;
import Component.Process;

import java.util.Collections;
import java.util.Comparator;

public class Algorithm {

  public static void fifo() throws InterruptedException {
    Process.resetList();
    Cpu cpu = new Cpu();
    cpu.finishList.clear();
    while(!cpu.waitList.isEmpty()) {
      while(cpu.arriveList.isEmpty()){
        cpu.hold();
      }
      while(!cpu.arriveList.isEmpty()){
        cpu.run(cpu.arriveList.get(0));
        if(cpu.checkComplete(cpu.arriveList.get(0))){
          cpu.finishList.add(cpu.arriveList.get(0));
          cpu.arriveList.remove(cpu.arriveList.get(0));
        }
      }
    }

  }

  public static void sjf(){
    Cpu cpu = new Cpu();
    while(!cpu.waitList.isEmpty()){
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

  public static void rr(int slice) throws InterruptedException {
    Cpu cpu = new Cpu();
    while(!cpu.waitList.isEmpty()){
      while(cpu.arriveList.isEmpty()){
        cpu.hold();
      }
      while(!cpu.arriveList.isEmpty()){
        int flag = 0;
        for(int i=0;i<slice;i++){
          flag = 0;
          if(cpu.checkComplete(cpu.arriveList.get(0))){
            flag = 1;
            cpu.finishList.add(cpu.arriveList.get(0));
            cpu.arriveList.remove(cpu.arriveList.get(0));
            break;
          }
          else{
            cpu.run(cpu.arriveList.get(0));
          }
        }
        if(cpu.arriveList.size()>1 && cpu.checkComplete(cpu.arriveList.get(0))){
          flag = 1;
          cpu.arriveList.remove(cpu.arriveList.get(0));
        }
        if(cpu.arriveList.size()>1 && flag == 0) {
          Process tmp = cpu.arriveList.get(0);
          cpu.arriveList.remove(tmp);
          cpu.arriveList.add(tmp);
        }
      }
    }
  }

  public static void priority(){
    Cpu cpu = new Cpu();
    while(!cpu.waitList.isEmpty()){
      while(cpu.arriveList.isEmpty()){
        cpu.hold();
      }
      Collections.sort(cpu.arriveList, new Comparator<Process>() {
        @Override
        public int compare(Process o1, Process o2) {
          if(o1.getPriority() > o2.getPriority()){
            return 1;
          }
          else{
            return -1;
          }
        }
      });
      while(!cpu.arriveList.isEmpty()){
        cpu.run(cpu.arriveList.get(0),"priority");
      }
    }
  }

  public static void hrrn() throws InterruptedException {
    Cpu cpu = new Cpu();
    while(!cpu.waitList.isEmpty()){
      while(cpu.arriveList.isEmpty()){
        cpu.hold();
      }
      while(!cpu.arriveList.isEmpty()){
        cpu.run(cpu.arriveList.get(0));
        if(cpu.checkComplete(cpu.arriveList.get(0))){
          cpu.finishList.add(cpu.arriveList.get(0));
          cpu.arriveList.remove(cpu.arriveList.get(0));
          cpu.updateRR();
        }
      }
    }
  }
}
