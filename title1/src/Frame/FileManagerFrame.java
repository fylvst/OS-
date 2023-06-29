package Frame;


import my.MyFrame;

import javax.swing.*;
import java.awt.*;

public class FileManagerFrame {

  JFrame frame;

  public FileManagerFrame(){
    frame = new MyFrame("读取文件");
    frame.setDefaultCloseOperation(3);
    frame.setSize(640, 450);
    centerInScreen(frame);
    frame.setVisible(true);
  }

  public static void centerInScreen(Window win) {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - win.getWidth()) / 2;
    int y = (screenSize.height - win.getHeight()) / 2;
    win.setLocation(x, y);
  }

}
