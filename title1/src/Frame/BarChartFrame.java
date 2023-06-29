package Frame;

import java.awt.*;
import java.awt.event.WindowEvent;

import static Component.Cpu.cpu;
import Component.Process;
import Component.CompareItem;

import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

/**
 * 该类用于演示最简单的柱状图生成
 */
public class BarChartFrame extends ApplicationFrame {

  public static CompareItem[] compareList = {new CompareItem(),new CompareItem(),new CompareItem(),
                                              new CompareItem(),new CompareItem()};

  public static void add(int i){
    int averageCyclingTime=0;
    int priorityCyclingTime=0;
    for(int j=0;j<cpu.finishList.size();j++){
      Process p = cpu.finishList.get(j);
      averageCyclingTime += p.getCyclingTime();
      priorityCyclingTime += p.getCyclingTime() / p.getRuntime() ;
    }
    compareList[i].setAverageRR(averageCyclingTime/cpu.finishList.size());
    compareList[i].setPriorityCyclingTime(priorityCyclingTime/cpu.finishList.size());
    compareList[i].setIsRun();
  }

  public static void main(String[] args) {
    BarChartFrame chart = new BarChartFrame("Main Panel Title!");
    chart.pack();
    chart.setMinimumSize(new Dimension(1200, 600));
    chart.setPreferredSize(new Dimension(1200, 600));
    chart.setVisible(true);
  }

  public BarChartFrame(String title) {
    super(title);
    JFreeChart chart = createChart();
    this.setContentPane(new ChartPanel(chart)); //构造函数中自动创建Java的panel面板
  }

  public JFreeChart createChart() {
    JFreeChart chart = ChartFactory.createBarChart3D(
            "性能比较", // 图表标题
            "算法", // 目录轴的显示标签
            "时间", // 数值轴的显示标签
            getDataSet2(), // 数据集
            PlotOrientation.VERTICAL, // 图表方向：水平、垂直
            true,         // 是否显示图例(对于简单的柱状图必须是false)
            false,         // 是否生成工具
            false         // 是否生成URL链接
    );
    /** ---------------------- 中文乱码问题处理 Start ------------------------------- */
    CategoryPlot plot = chart.getCategoryPlot();//获取图表区域对象
    CategoryAxis domainAxis = plot.getDomainAxis();     //水平底部列表
    domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14));     //水平底部标题
    domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); //垂直标题

    ValueAxis rangeAxis = plot.getRangeAxis();//获取柱状
    rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
    chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
    chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));//设置标题字体
    /** ---------------------- 中文乱码问题处理 End ------------------------------- */
    return chart;
  }

  // 数据集构建
  private static CategoryDataset getDataSet2() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    String[] str = {"FIFO","SJF","RR","PRIORITY","HRRN"};
    for(int i=0;i<5;i++){
      if(compareList[i].getIsRun()){
        dataset.addValue(compareList[i].getAverageRR(),str[i],"平均周转时间");
        dataset.addValue(compareList[i].getPriorityCyclingTime(),str[i],"带权周转时间");
      }
    }
    return dataset;
  }

  @Override
  public void windowClosing(final WindowEvent event) {
    if (event.getWindow() == this) {
      dispose();
    }
  }
}