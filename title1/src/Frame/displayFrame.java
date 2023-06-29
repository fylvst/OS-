package Frame;

import Component.Algorithm;
import Component.GlobalVar;

import Component.Cpu;
import Component.Process;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static Component.Cpu.cpu;

public class displayFrame extends JFrame {
  public static JScrollPane jsp;
  public static JTableHeader jth;
  private JComboBox<String> jcb;
  public static JTable table;
  private JLabel cpuTime;
  public static JLabel time;
  private JLabel currProcessName;
  public static JLabel process;
  private JButton start;
  private JButton pause;
  private JButton create;
  private JButton load;
  private JButton check;
  private JButton compare;

  private Process p;
  private int t;

  {
    try {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void display(int t,Process curr){
    String[] title = {"PID","到达时间","运行时间","服务时间","完成时间","优先级","周转时间","当前状态",};
    Object[][] info = new Object[Process.processes.size()][8];
    for(int i=0;i<Process.processes.size();i++){
      info[i][0] = Process.processes.get(i).getName();
      info[i][1] = Process.processes.get(i).getArriveTime();
      info[i][2] = Process.processes.get(i).getRuntime();
      info[i][3] = Process.processes.get(i).getServedTime();
      info[i][4] = Process.processes.get(i).getFinishTime();
      info[i][5] = Process.processes.get(i).getPriority();
      info[i][6] = Process.processes.get(i).getCyclingTime();
      info[i][7] = Process.processes.get(i).getState();
    }
    table = new JTable(info,title);
    jth = table.getTableHeader();
    jsp.getViewport().add(table);
    time.setText(Integer.toString(t));
    process.setText(curr.getName());
  }

  public displayFrame() {
    super("通用处理机调度演示程序");        //JFrame的标题名称
    this.setSize(1080, 768);        //控制窗体大小
    this.setLayout(null);        //自定义布局
    this.setLocation(200, 100);    //点击运行以后，窗体在屏幕的位置
    jsp = new JScrollPane();
    this.start = new JButton("开始");
    this.pause = new JButton("暂停");
    this.create = new JButton("新建");
    this.load = new JButton("读入");
    this.check = new JButton("CPU队列");
    this.compare = new JButton("性能对比");
    this.start.setBounds(405, 527, 180, 66);
    this.pause.setBounds(405, 643, 180, 66);
    this.create.setBounds(630, 527, 180, 66);
    this.load.setBounds(630, 643, 180, 66);
    this.check.setBounds(853, 527, 180, 66);
    this.compare.setBounds(853, 643, 180, 66);
    jsp.setBounds(40, 118, 1000, 397);
    this.jcb = new JComboBox<>();
    this.jcb.addItem("FIFO");
    this.jcb.addItem("RR");
    this.jcb.addItem("SJF");
    this.jcb.addItem("PRIORITY");
    this.jcb.addItem("HRRN");
    this.jcb.setBounds(40,40,266,44);
    this.cpuTime = new JLabel("CPU时间");
    this.currProcessName = new JLabel("当前进程");
    time = new JLabel("1");
    process = new JLabel("0");
    this.cpuTime.setBounds(40,528,188,90);
    time.setBounds(40,618,188,90);
    this.currProcessName.setBounds(228,528,140,90);
    process.setBounds(228,618,140,90);
    this.cpuTime.setOpaque(true);
    process.setOpaque(true);
    this.cpuTime.setHorizontalAlignment(SwingConstants.CENTER);
    time.setHorizontalAlignment(SwingConstants.CENTER);
    this.currProcessName.setHorizontalAlignment(SwingConstants.CENTER);
    process.setHorizontalAlignment(SwingConstants.CENTER);
    t=0;
    p= new Process("0",0,0,0);

    Object[][] info = new Object[Process.processes.size()][8];
    for(int i=0;i<Process.processes.size();i++){
      info[i][0] = Process.processes.get(i).getName();
      info[i][1] = Process.processes.get(i).getArriveTime();
      info[i][2] = Process.processes.get(i).getServedTime();
      info[i][3] = Process.processes.get(i).getRuntime();
      info[i][4] = Process.processes.get(i).getFinishTime();
      info[i][5] = Process.processes.get(i).getPriority();
      info[i][6] = Process.processes.get(i).getRR();
      info[i][7] = Process.processes.get(i).getState();
    }
    String[] title = {"PID","到达时间","服务时间","运行时间","完成时间","优先级","周转时间","当前状态"};
    table = new JTable(info,title);
    jth = table.getTableHeader();
    jsp.getViewport().add(table);

    /********按钮“开始”的响应*******/
    this.start.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Process.resetList();
        cpu=new Cpu();
        String selected = (String) jcb.getSelectedItem();
        updateFrame.drawRoute(selected,p);
        displayFrame.display(cpu.getTime(),p);
      }
    });
    /******按钮 “取消”的响应*****/
    this.pause.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        GlobalVar.flag = !GlobalVar.flag;
      }
    });

    /***按钮 “新建进程” 的响应***/
    this.create.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        newProcessFrame n = new newProcessFrame();
        Process tmp = new Process();
        n.create.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            tmp.setName(n.name.getText());
            tmp.setRuntime(Integer.parseInt(n.runTime.getText()));
            tmp.setArriveTime(Integer.parseInt(n.arriveTime.getText()));
            tmp.setPriority(Integer.parseInt(n.priority.getText()));
            System.out.println(tmp.toString());
            Process.processes.add(tmp);
            display(t,p);
            n.dispose();
          }
        });
      }
    });

    /***** 按钮 ”读取文件“ 的响应 ****/
    this.load.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        File f = new File("D:\\2023.6.19\\OS-\\title1");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setCurrentDirectory(f);

        // 显示对话框
        int ret = chooser.showOpenDialog(jsp);

        if(ret == JFileChooser.APPROVE_OPTION){
          File file = chooser.getSelectedFile();
          try (Scanner sc = new Scanner(new FileReader(file))) {
            while (sc.hasNextLine()) {  //按行读取字符串
              String line = sc.nextLine();
              String[] lines = line.split(" ");
              Process p = new Process(lines[0],Integer.parseInt(lines[1]),
                      Integer.parseInt(lines[2]),Integer.parseInt(lines[3]));
              Process.processes.add(p);
              System.out.println(p.toString());;
            }
          } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
          }
        }
        display(t,p);
      }
    });

    this.load.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new ProcessesList();
      }
    });

    this.check.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ProcessesList.ProcessesListFrame(cpu);
      }
    });

    add(jsp);
    add(this.start);
    add(this.pause);
    add(this.create);
    add(this.load);
    add(this.check);
    add(this.compare);
    add(this.jcb);
    add(this.cpuTime);
    add(time);
    add(this.currProcessName);
    add(process);
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
}
