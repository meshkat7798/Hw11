package question1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main1 {
    // The shared resource between two threads
    private static List<Integer> sharedList = new ArrayList<>();

    // The object used for synchronization
    private static Object lock = new Object();

    // The flag to indicate which thread should write next
    private static boolean isEven = true;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an integer:");
        int number = scanner.nextInt();
        Thread evenThread = new Thread(new EvenRunnable(number));
        Thread oddThread = new Thread(new OddRunnable(number));

        evenThread.start();
        oddThread.start();

// Wait for the threads to finish
        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The shared list is: " + sharedList);
    }

    private static class EvenRunnable implements Runnable {

        private int userNumber;

        public EvenRunnable(int n) {
            this.userNumber = n;
        }

        @Override
        public void run() {
            for (int i = 0; i <= userNumber; i += 2) {
                synchronized (lock) {
                    while (!isEven) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    sharedList.add(i);

// Set the flag to false to indicate it is the turn of the odd thread
                    isEven = false;

// Notify the other thread
                    lock.notify();
                }
            }
        }
    }

    private static class OddRunnable implements Runnable {

        private int userNumber;

        public OddRunnable(int n) {
            this.userNumber = n;
        }

        @Override
        public void run() {
            for (int i = 1; i <= userNumber; i += 2) {
                synchronized (lock) {
                    while (isEven) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    sharedList.add(i);
                    isEven = true;
                    lock.notify();
                }
            }
        }
    }
}