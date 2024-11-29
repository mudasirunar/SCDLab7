class CounterThread extends Thread {
    private volatile boolean running = true; 
    public void stopThread() {
        running = false; 
    }
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                if (!running) {
                    System.out.println("Thread stopped.");
                    break; // Exit the loop when the thread is stopped
                }
                System.out.println("Count: " + i);
                Thread.sleep(1000); // Pause for 1 second
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
    }
}
public class ThreadMethodsDemo {
    public static void main(String[] args) {
        CounterThread counter = new CounterThread();  
        counter.start();
        try {
            Thread.sleep(5000); // Main thread sleeps for 5 seconds
            counter.stopThread(); // Stop the thread gracefully at count 5
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
