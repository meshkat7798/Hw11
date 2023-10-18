package question2;

public class Main2 extends Thread {
    // Java program to illustrate Deadlock situation
        static Thread mainThread;
        public void run()
        {
            System.out.println("Child Thread waiting for main thread to complete");
            try {

                // Child thread waiting for main thread to complete
                mainThread.join();
            }
            catch (InterruptedException e) {
                System.out.println("Child thread execution completes");
            }
        }
        public static void main(String[] args)
                throws InterruptedException
        {
            Main2.mainThread = Thread.currentThread();
            Main2 thread = new Main2();

            thread.start();
            System.out.println("Main thread waiting for Child thread to complete");

            // main thread is waiting for the Child thread to complete
            thread.join();

            System.out.println("Main thread execution completes");
        }
    }

