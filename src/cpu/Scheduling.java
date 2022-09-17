package cpu;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Scheduling {

    static void RoundRubin(Process[] p, int quantum) {
        Process[] tmp = new Process[p.length];
        System.arraycopy(p, 0, tmp, 0, p.length);
        List<Process> lst = Arrays.asList(tmp);
        for (Process p1 : tmp) {
            p1.setRemaining(p1.getBrust());
        }
        Collections.sort(lst, (Process p1, Process p2) -> {
            return p1.getArrival() - p2.getArrival();
        });
        int ta, wt, remaining; //TurnAround ,Waiting time and remaining time
        int t = lst.get(0).getArrival(); //intial time
        Process min;
        Queue<Process> ready = new LinkedList<>();
        readyQueue(ready, tmp, t);
//        printQ(ready);
        while (true) {
            boolean fi = true;
            if (!ready.isEmpty()) {
                fi = false;
                Iterator it = ready.iterator();
                while (it.hasNext()) {
                    min = ready.remove();
                    if (min.getRem() > quantum) {
                        t += quantum;
                        remaining = min.getRem() - quantum;
                        min.setRemaining(remaining);
                        readyQueue(ready, tmp, t);
//                        printQ(ready);
                        ready.add(min);
                    } else {
                        t += min.getRem();
                        ta = t - min.getArrival();
                        wt = ta - min.getBrust();
                        min.setRemaining(0);
                        min.setTurnAround(ta);
                        min.setWaitingTime(wt);
                        min.setFinish(true);
                        readyQueue(ready, tmp, t);
//                        printQ(ready);
                    }
                }
            }
            if (fi) {
                break;
            }
        }
    }

    static void printQ(Queue<Process> q) { //just for testing
        Iterator it = q.iterator();
        System.out.print("Queue => ");
        while (it.hasNext()) {
            Process tmp = (Process) it.next();
            System.out.print(tmp.getRem() + " ");
        }
        System.out.println("");
    }

    static void readyQueue(Queue<Process> q, Process[] lst, int t) {
        for (Process p : lst) {
            if (p.getArrival() <= t && !p.finish() && p.getBrust() == p.getRem() && !q.contains(p)) {
                q.add(p);
            }
        }
    }

    static void SJF(Process[] p) {
        List<Process> lst = Arrays.asList(p);
        Collections.sort(lst, (Process p1, Process p2) -> {
            return (p1.getArrival() - p2.getArrival()) + (p1.getBrust() - p2.getBrust());
        });
        int t = lst.get(0).getArrival();
        int wt, ta, index;
        Process min;
        while (true) {
            boolean fi = true;
            for (Process p1 : p) {
                if (!p1.finish()) {
                    fi = false;
                    min = Next(p, t);
                    if (min != null) {
                        t += min.getBrust();
                        ta = t - min.getArrival();
                        min.setTurnAround(ta);
                        wt = ta - min.getBrust();
//                        System.out.printf("%d %d %n", wt, min.getBrust());
                        min.setWaitingTime(wt);
                        min.setFinish(true);
                    }
                }
            }
            if (fi) {
                break;
            }
        }
        Arrays.sort(p, (Process p1, Process p2) -> {
            return p1.getArrival() - p2.getArrival();
        });
    }

    private static Process Next(Process[] p, int t) {
        Process proc = null;
        int count = 0;
        int j = 0;
        for (Process p1 : p) {
            if (!p1.finish() && p1.getArrival() <= t) {
                count++;
            }
        }
        if (count > 0) {
            Process[] tmp = new Process[count];
            for (Process p1 : p) {
                if (!p1.finish() && p1.getArrival() <= t) {
                    tmp[j] = p1;
                    j++;
                }
            }
            List<Process> lst = Arrays.asList(tmp);
            Collections.sort(lst, (Process p1, Process p2) -> {
                if (p2.getArrival() <= t && p1.getArrival() <= t) {
                    return p1.getBrust() - p2.getBrust();
                }
                return 0;
            });
            proc = lst.get(0);
        }
        return proc;
    }

    public static void FCFS(Process[] processes) {
        List<Process> lst = Arrays.asList(processes);
        Collections.sort(lst, (Process p1, Process p2) -> {
            return p1.getArrival() - p2.getArrival();
        });
        int wt, ta;
        int t = lst.get(0).getArrival();
        for (Process p : processes) {
            t += p.getBrust();
            ta = t - p.getArrival();
            p.setTurnAround(ta);
            wt = ta - p.getBrust();
            p.setWaitingTime(wt);
            p.setFinish(true);
        }
    }
}
