package Frame;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

import Component.*;
import Component.Process;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;


public class ProcessesList {

    public static JTable q(ArrayList<Process> processArrayList) {

        Vector v = new Vector();
        for (Process p : processArrayList) {
            Vector Vector1 = new Vector();
            Vector1.add(p.getName());
            Vector1.add(p.getArriveTime());
            Vector1.add(p.getServedTime());
            Vector1.add(p.getRuntime());
            Vector1.add(p.getWaitTime());
            Vector1.add(p.getPriority());
            Vector1.add(p.getRR());
            v.add(Vector1);
        }
//        Vector<Process> v = new Vector<Process>(processArrayList);
        Vector columnName = new Vector();
        columnName.add("PID");
        columnName.add("arriveTime");
        columnName.add("servedTime");
        columnName.add("runtime");
        columnName.add("waitTime");
        columnName.add("priority");
        columnName.add("cyclyingTime");
        JTable jTable = new JTable(v, columnName);
        System.out.println(v);
        return jTable;
    }


    public static void ProcessesListFrame(Cpu cpu) {
        JFrame frame = new JFrame("测试窗口");
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        ArrayList<Process> curr = new ArrayList<>();
        curr.add(cpu.arriveList.get(0));
        JTable jTable1 = q(cpu.arriveList);
        JTable jTable2 = q(cpu.finishList);
        JTable jTable3 = q(curr);
        JScrollPane jsp1 = new JScrollPane(jTable1);
        JScrollPane jsp3 = new JScrollPane(jTable3);
        JScrollPane jsp2 = new JScrollPane(jTable2);
        jsp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp1.setPreferredSize(new Dimension(500, 140));
        jsp2.setPreferredSize(new Dimension(500, 140));
        jsp3.setPreferredSize(new Dimension(500, 140));
        frame.add(new JLabel("就绪队列"));
        frame.add(jsp1);
        frame.add(new JLabel("完成队列"));
        frame.add(jsp2);
        frame.add(new JLabel("当前队列"));
        frame.add(jsp3);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }


}

