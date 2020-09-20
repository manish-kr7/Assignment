package com.manish;

import okhttp3.OkHttpClient;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class JsonComparator {

    OkHttpClient okHttpClient;
    private static final int MYTHREADS = 50;

    public JsonComparator() {
        okHttpClient = new OkHttpClient();
    }

    public boolean compareTwoFile(String file1, String file2) {
        boolean isAllTrue = true;
        try {

            BufferedReader bufferedReaderFile1 = new BufferedReader(new FileReader(file1));
            BufferedReader bufferedReaderFile2 = new BufferedReader(new FileReader(file2));
            ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);
            Set<Future<Boolean>> futureSet = new HashSet<>();

            while (bufferedReaderFile1.ready() && bufferedReaderFile2.ready()) {
                try {

                    String urlFile1 = bufferedReaderFile1.readLine();
                    String urlFile2 = bufferedReaderFile2.readLine();
                    Callable<Boolean> comparatorCallable = () -> compareTwoUrls(urlFile1, urlFile2);
                    Future<Boolean> future = executor.submit(comparatorCallable);
                    futureSet.add(future);

                } catch (IOException iOException) {
                    System.out.println("Exception Caught");
                }
            }
            executor.shutdown();
            for (Future<Boolean> future : futureSet) {
                isAllTrue = isAllTrue && future.get();
            }

            System.out.println("Comparison Task Completed");

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Exception Caught");
        }
        return isAllTrue;
    }


    public boolean compareTwoUrls(String urlFile1, String urlFile2) {

        ComparatorCallable comparatorCallable = new ComparatorCallable(okHttpClient, urlFile1, urlFile2);

        try {
            return comparatorCallable.compare();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }
}
