package hu.cai.ait.twoactivitiesdemo;

/**
 * Created by caiglencross on 3/2/17.
 */

public class Preferences {

    private static Preferences instance = null;

    public static Preferences getInstance() {
        if (instance == null) {
            instance = new Preferences();
        }

        return instance;
    }

    private Preferences() {
    }


    private String keyData;

    public String getKeyData() {
        return keyData;
    }

    public void setKeyData(String keyData) {
        this.keyData = keyData;
    }
}
