public class Process {
    String name;  //进程名
    int priority;  // 优先级
    double arriveTime; //进程到达时间
    double beginTime; //进程开始执行时间
    double waitTime; //进程等待时间
    double runtime; //需要运行时间
    double finishTime; //进程结束时的时间
    double cyclyingTime;//进程周转时间
    double RR;//当前进程的相应比
    int state; //当前进程状态  就绪0;运行1;完成-1;

    public Process(String name, int priority, double arriveTime, double runtime) {
        this.name = name;
        this.priority = priority;
        this.arriveTime = arriveTime;
        this.runtime = runtime;
        this.state=0;
    }

    @Override
    public String toString() {
        return "Process{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                ", arriveTime=" + arriveTime +
                ", beginTime=" + beginTime +
                ", waitTime=" + waitTime +
                ", runtime=" + runtime +
                ", finishTime=" + finishTime +
                ", cyclyingTime=" + cyclyingTime +
                ", RR=" + RR +
                ", state=" + state +
                '}';
    }
}