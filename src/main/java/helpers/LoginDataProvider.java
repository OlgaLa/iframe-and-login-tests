package helpers;

import org.testng.annotations.DataProvider;

/**
 * Created by olapanovich on 23.10.16.
 */
public class LoginDataProvider {

    @DataProvider
    public static Object[][] loginDataProvider() {
        return new Object[][]{
                new Object[]{"tomsmith1", "SuperSecretPassword!", "Your username is invalid!"},
                new Object[]{"tomsmith", "SuperSecretPassword!1", "Your password is invalid!"},
                new Object[]{"", "SuperSecretPassword!", "Your username is invalid!"},
                new Object[]{"tomsmith", "", "Your password is invalid!"},
                new Object[]{"", "", "Your username is invalid!"}
        };
    }
}
