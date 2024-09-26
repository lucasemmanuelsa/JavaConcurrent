import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExampleExecution {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new RunnableTask());
        executorService.execute(new RunnableTask());
        executorService.execute(new RunnableTask());
        executorService.shutdown();

    }

    public static class RunnableTask implements Runnable{
    
        public void run() {
            try{
                Thread.sleep(3);
            }catch(InterruptedException e){
                
            }
            
            System.out.println("terminou");
        }
    }
}
