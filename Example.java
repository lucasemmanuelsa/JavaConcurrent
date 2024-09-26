public class Example {
    public static void main(String[] args) {
        new Thread (new RunnableTask()).start();
        new Thread (new RunnableTask()).start();
        new Thread (new RunnableTask()).start();
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