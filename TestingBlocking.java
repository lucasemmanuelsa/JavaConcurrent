import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;


public class TestingBlocking {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        
        BlockingQueue<Integer> queue = new LinkedBlockingDeque<>();
        ConcurrentHashMap<Integer, Integer[]> map = new ConcurrentHashMap<>();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        
        Future<Integer> future1 = executorService.submit(new CallableTask());
        Future<Integer> future2 = executorService.submit(new CallableTask());
        Future<Integer> future3 = executorService.submit(new CallableTask());
        


        executorService.shutdown();
        queue.add(future1.get());
        queue.add(future2.get());
        queue.add(future3.get());

        map.put(future1.get(), new Integer[]{1,2,3});
    

        Thread.sleep(3000);
        System.out.println(queue);
        System.out.println(map.toString());
        System.out.println("teste");
        
        

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
