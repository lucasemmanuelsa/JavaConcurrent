import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExampleCallable {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        
        Future<Integer> future1 = executorService.submit(new CallableTask());
        Future<Integer> future2 = executorService.submit(new CallableTask());
        Future<Integer> future3 = executorService.submit(new CallableTask());
        
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
