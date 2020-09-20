package com.manish;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.Callable;

public class ComparatorCallable {

    private OkHttpClient okHttpClient;
    private String urlFile1;
    private String urlFile2;

    public ComparatorCallable(OkHttpClient okHttpClient, String urlFile1, String urlFile2) {
        this.okHttpClient = okHttpClient;
        this.urlFile1 = urlFile1;
        this.urlFile2 = urlFile2;
    }

    public Boolean compare() throws Exception {
        try {

            String responseUrl1 = getResponseFromUrl(urlFile1);
            String responseUrl2 = getResponseFromUrl(urlFile2);

            Gson gson = new Gson();
            JsonElement jsonElement1 = gson.toJsonTree(responseUrl1);
            JsonElement jsonElement2 = gson.toJsonTree(responseUrl2);

            if (jsonElement1.equals(jsonElement2)) {
                System.out.println(urlFile1 + " equals " + urlFile2);
                return true;
            } else {
                System.out.println(urlFile1 + " not equals " + urlFile2);
            }
        } catch (IllegalArgumentException illegalArgument) {
            System.out.println("Invalid URL Found");
        }
        return false;
    }


    private String getResponseFromUrl(String url) throws IOException, IllegalArgumentException {

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String userResponse = response.body().string();

        return userResponse;
    }
}
