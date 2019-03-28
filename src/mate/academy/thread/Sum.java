package mate.academy.thread;

import java.util.concurrent.Callable;

public class Sum implements Callable<Integer> {
    int start;
    int end;
    int[] array;
    int s;
    int summa;

    public Sum(int array[], int start, int end) {
        this.start = start;
        this.end = end;
        this.array = array;
    }

    private int sum(int start, int end) {
        try {
            for (int i = start; i <= end; i++) {
                s += array[i];
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR");
        }
        return s;
    }

    @Override
    public Integer call() throws Exception {
        summa = sum(start, end);
        System.out.println("Сумма элементов массива с индекса " + start + " по индекс " + end + " равна " + summa);
        return summa;
    }
}
