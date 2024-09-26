package Atividade;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class Distribuido {

    public static BlockingQueue<CallableTask> tasks = new LinkedBlockingDeque<>();
    public static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
    public static AtomicInteger countForIds = new AtomicInteger(0);
    private ExecutorService maquinaService;
    private ExecutorService clienteService;
    private ScheduledExecutorService displayService;

    public Distribuido(){
        this.maquinaService = Executors.newFixedThreadPool(3);
        this.clienteService = Executors.newFixedThreadPool(3);
        this.displayService = Executors.newSingleThreadScheduledExecutor();
        
    }

    public void run(){

        for(int i = 0; i < 3; i++){
            maquinaService.submit(new NodeTask());
        }
        for(int i = 0; i < 3; i++){
            clienteService.submit(new ClienteTask());
        }

        this.displayService.scheduleAtFixedRate(new DisplayTasks(), 30, 30, java.util.concurrent.TimeUnit.SECONDS);
        clienteService.shutdown();
        maquinaService.shutdown();


        

    }
    public static void main(String[] args) {
        Distribuido d = new Distribuido();
        d.run();
    }

    public static class CallableTask implements Callable<Integer> {
        int id;
        int tempo;
        public CallableTask(int tempo){
            this.tempo = tempo;
            this.id = countForIds.incrementAndGet(); 
        }

        public Integer call() throws Exception {
            Thread.sleep(this.tempo);
            return this.tempo;
        }
    }

    public static class ClienteTask implements Runnable {
        public void run() {
            try{
                Random random = new Random();
                tasks.put(new CallableTask(random.nextInt(10)));
                Thread.sleep(3000);
                tasks.put(new CallableTask(random.nextInt(10)));
                Thread.sleep(3000);
                tasks.put(new CallableTask(random.nextInt(10)));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static class NodeTask implements Runnable {
        public void run() {
            while (true) {
                try{
                    CallableTask task = tasks.take();
                    Integer taskid = task.id;
                    Integer result = task.call();
                    map.put(taskid, result);
                    System.out.println("Task executada: " + taskid + ": Tempo " + result);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static class DisplayTasks implements Runnable {
        public void run(){
            System.out.println(map.toString());
        }
    }

}
