package Frame;

import Component.Algorithm;
import Component.Process;

import static Component.Cpu.cpu;

public class updateFrame {

    public static void drawRoute(String selected, Process p){
        // 开启多线程，更新显示进程，实现实时刷新
        new Thread(new Runnable() {
            public void run() {

                switch (selected){
                    case "FIFO": {
                        try {
                            Algorithm.fifo();
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case "SJF": {
                        Algorithm.sjf();
                    }
                    case "RR": {
                        try {
                            Algorithm.rr(3);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case "PRIORITY": {
                        Algorithm.priority();
                    }
                    case "HRRN": {
                        try {
                            Algorithm.hrrn();
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                displayFrame.display(cpu.getTime(),p);
            }
        }).start();
    }




}
