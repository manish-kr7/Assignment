package com.manish;

import org.junit.Assert;
import org.junit.Test;

public class TestCases {


    @Test
    public void bothFilesAreValid() {
        JsonComparator jsonComparator = new JsonComparator();
        Assert.assertTrue(jsonComparator.compareTwoFile("File1.txt", "file2.txt"));
    }

    @Test
    public void inputsAreNotURL() {
        JsonComparator jsonComparator = new JsonComparator();
        String url1 = "test";
        String url2 = "test1";

        Assert.assertFalse(jsonComparator.compareTwoUrls(url1, url2));
    }

    @Test
    public void oneURlIsNotValid() {
        JsonComparator jsonComparator = new JsonComparator();
        String url1 = "test";
        String url2 = "https://reqres.in/api/users/7";

        Assert.assertFalse(jsonComparator.compareTwoUrls(url1, url2));
    }

    @Test
    public void responseNotEquals() {
        JsonComparator jsonComparator = new JsonComparator();
        String url1 = "https://reqres.in/api/users/5";
        String url2 = "https://reqres.in/api/users/7";

        Assert.assertFalse(jsonComparator.compareTwoUrls(url1, url2));

    }
}
