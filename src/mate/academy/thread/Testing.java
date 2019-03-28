package mate.academy.thread;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Testing {
    public static void main(String[] args) {
        int[] array = new int[1_000_000];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(10);
            System.out.println("array[" + i + "] = " + array[i]);
        }
        int res;
        ExecutorService ex = Executors.newFixedThreadPool(3);
        Future<Integer> thread1 = ex.submit(new Sum(array, 0, array.length / 5));
        Future<Integer> thread2 = ex.submit(new Sum(array, array.length / 5, array.length / 2));
        Future<Integer> thread3 = ex.submit(new Sum(array, array.length / 2, array.length - 1));

        try {
            res = thread1.get() + thread2.get() + thread3.get();
            System.out.println("Сумма всех элементов массива, расчитанная с помощью потоков, равна " + res);
        } catch (ExecutionException | InterruptedException e) {
            System.out.println(e);
        }
        ex.shutdown();
        System.out.println("Сумма всех элементов массива, расчитанная с помощью статического метода, равна " + summa(array));
    }

    static int summa(int[] array) {
        int summ = 0;
        for (int i = 0; i < array.length; i++) {
            summ += array[i];
        }
        return summ;
    }
}
