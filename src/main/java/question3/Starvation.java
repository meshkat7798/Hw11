package question3;

class Starvation extends Thread {
    static int count = 1;
    public void run() {
        System.out.println(count + " Thread execution starts");
        System.out.println("Thread execution completes");
        count++;
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Parent thread execution starts");

        /* Priority of each thread given. */
        /* Thread 1 with priority 7. */
        Starvation thread1 = new Starvation();
        thread1.setPriority(7);
        /* Thread 2 with priority 6. */
        Starvation thread2 = new Starvation();
        thread2.setPriority(6);
        /* Thread 3 with priority 5. */
        Starvation thread3 = new Starvation();
        thread3.setPriority(5);
        /* Thread 4 with priority 4. */
        Starvation thread4 = new Starvation();
        thread4.setPriority(4);
        /* Thread 5 with priority 3. */
        Starvation thread5 = new Starvation();
        thread5.setPriority(3);

        thread1.run();
        thread2.run();
        thread3.run();
        thread4.run();

  /* Here thread 5 have to wait because of the
     other threads */
        thread5.run();

        System.out.println("Parent thread execution completes");
    }
}

