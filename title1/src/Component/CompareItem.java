package Component;

public class CompareItem {

  private boolean isRun;
  private int averageCyclingTime;
  private double priorityCyclingTime;

  public CompareItem(){
    this.isRun = false;
    this.averageCyclingTime=0;
    this.priorityCyclingTime=0;
  }

  public void setIsRun(){
    this.isRun = true;
  }

  public int setPriorityCyclingTime(int priorityCyclingTime) {
    this.priorityCyclingTime = priorityCyclingTime;
    return priorityCyclingTime;
  }

  public int setAverageRR(int averageRR) {
    this.averageCyclingTime = averageRR;
    return averageRR;
  }

  public boolean getIsRun(){
    return this.isRun;
  }

  public double getPriorityCyclingTime() {
    return priorityCyclingTime;
  }

  public int getAverageRR() {
    return averageCyclingTime;
  }
}
