package cpu;

public class Main {

    public static void main(String[] ar) {
        Process p1 = new Process(6, 1);
//        Process p3 = new Process(30, 3);
//        Process p2 = new Process(20, 2);
        Process[] p = {
            p1,
            new Process(8, 4),
            new Process(4, 5),
            new Process(12, 6),
            new Process(6, 2)
        };
        //Scheduling.FCFS(p);
        //Scheduling.SJF(p);
        Scheduling.RoundRubin(p, 4);
        System.out.printf("Job  Arrival  Burst TurnAround Waiting%n");long id;
        for (Process pp : p) {
             id = pp.getId();
            System.out.printf("P%d\t%d\t%d\t%d\t%d%n", id, pp.getArrival(), pp.getBrust(), pp.getTurnAround(), pp.getWaitingTime());
        }
    }
}
