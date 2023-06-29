package Frame;

import javax.swing.*;

public class newProcessFrame extends JFrame {
  public JTextField name;
  JTextField runTime;
  JTextField arriveTime;
  JTextField priority;
  JLabel nameLabel;
  JLabel runTimeLabel;
  JLabel arriveTimeLabel;
  JLabel priorityLabel;
  public JButton create;

  {
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public newProcessFrame(){
    super("新建进程");
    this.setBounds(350,250,800,500);
    this.nameLabel = new JLabel("进程名");
    this.runTimeLabel = new JLabel("运行时间");
    this.arriveTimeLabel = new JLabel("到达时间");
    this.priorityLabel = new JLabel("优先级");
    this.setLayout(null);

    this.nameLabel.setBounds(100,20,100,20);
    this.nameLabel.setOpaque(true);
    this.name = new JTextField();
    this.name.setBounds(240,10,400,40);

    this.runTimeLabel.setBounds(100,120,100,20);
    this.runTimeLabel.setOpaque(true);
    this.runTime = new JTextField();
    this.runTime.setBounds(240,110,400,40);

    this.arriveTimeLabel.setBounds(100,220,100,20);
    this.arriveTimeLabel.setOpaque(true);
    this.arriveTime = new JTextField();
    this.arriveTime.setBounds(240,210,400,40);

    this.priorityLabel.setBounds(100,320,100,20);
    this.priorityLabel.setOpaque(true);
    this.priority = new JTextField();
    this.priority.setBounds(240,310,400,40);

    this.create = new JButton("create");
    this.create.setBounds(350,375,100,60);

    this.add(create);
    this.add(name);
    this.add(nameLabel);
    this.add(runTimeLabel);
    this.add(runTime);
    this.add(arriveTimeLabel);
    this.add(arriveTime);
    this.add(priority);
    this.add(priorityLabel);
    this.setVisible(true);
  }
}
