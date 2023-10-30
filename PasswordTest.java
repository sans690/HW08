
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class PasswordTest {
    String curPassword = "password";
    String newPassword = "password123";

    @Test

    // void testIsSomePass() {
    // assertEquals("password123", newPassword);
    // }

    // void testgetPassword(){
    // Password password = new Password(curPassword);
    // String value = password.getPassword();
    // if( curPassword != null){}
    // assertEquals("password", value);};

    // void testsetPassword() {
    // Password password = new Password();
    // Boolean value = password.setPassword(newPassword);
    // if (newPassword != null) {
    // assertEquals(true, value);
    // }

    // }

    void testisDifferentEnough() {
        Password password = new Password(curPassword);
        Boolean value = password.isDifferentEnough(newPassword, curPassword, 3);
       
            assertEquals(true, value);

        }
    }

