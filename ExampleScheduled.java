import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ExampleScheduled {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        
        ScheduledFuture<Integer> future1 = executorService.schedule(new CallableTask(), 3, TimeUnit.SECONDS);
        ScheduledFuture<Integer> future2 = executorService.schedule(new CallableTask(), 2, TimeUnit.SECONDS);
        ScheduledFuture<Integer> future3 = executorService.schedule(new CallableTask(), 5, TimeUnit.SECONDS);
        
        executorService.shutdown();

        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());
        

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

    public static class CallableTask implements Callable<Integer>{
    
        public Integer call() throws Exception  {
            int tempo = 3;
            Thread.sleep(tempo);
            return tempo;
        }
    }

}


