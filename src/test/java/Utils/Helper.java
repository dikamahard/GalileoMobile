package Utils;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;

import static Utils.AppiumDriverUtil.driver;

public class Helper {

    public static void  typeSomething(String text) {
        for (char digit : text.toCharArray()) {
            // Map the character to the appropriate AndroidKey and press it
            switch (digit) {
                case '0':
                    driver.pressKey(new KeyEvent(AndroidKey.DIGIT_0));
                    break;
                case '1':
                    driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
                    break;
                case '2':
                    driver.pressKey(new KeyEvent(AndroidKey.DIGIT_2));
                    break;
                case '3':
                    driver.pressKey(new KeyEvent(AndroidKey.DIGIT_3));
                    break;
                case '4':
                    driver.pressKey(new KeyEvent(AndroidKey.DIGIT_4));
                    break;
                case '5':
                    driver.pressKey(new KeyEvent(AndroidKey.DIGIT_5));
                    break;
                case '6':
                    driver.pressKey(new KeyEvent(AndroidKey.DIGIT_6));
                    break;
                case '7':
                    driver.pressKey(new KeyEvent(AndroidKey.DIGIT_7));
                    break;
                case '8':
                    driver.pressKey(new KeyEvent(AndroidKey.DIGIT_8));
                    break;
                case '9':
                    driver.pressKey(new KeyEvent(AndroidKey.DIGIT_9));
                    break;
                case 'A':
                    driver.pressKey(new KeyEvent(AndroidKey.A));
                    break;
                case 'B':
                    driver.pressKey(new KeyEvent(AndroidKey.B));
                    break;
                case 'C':
                    driver.pressKey(new KeyEvent(AndroidKey.C));
                    break;
                case 'D':
                    driver.pressKey(new KeyEvent(AndroidKey.D));
                    break;
                case 'E':
                    driver.pressKey(new KeyEvent(AndroidKey.E));
                    break;
                case 'F':
                    driver.pressKey(new KeyEvent(AndroidKey.F));
                    break;
                case 'G':
                    driver.pressKey(new KeyEvent(AndroidKey.G));
                    break;
                case 'H':
                    driver.pressKey(new KeyEvent(AndroidKey.H));
                    break;
                case 'I':
                    driver.pressKey(new KeyEvent(AndroidKey.I));
                    break;
                case 'J':
                    driver.pressKey(new KeyEvent(AndroidKey.J));
                    break;
                case 'K':
                    driver.pressKey(new KeyEvent(AndroidKey.K));
                    break;
                case 'L':
                    driver.pressKey(new KeyEvent(AndroidKey.L));
                    break;
                case 'M':
                    driver.pressKey(new KeyEvent(AndroidKey.M));
                    break;
                case 'N':
                    driver.pressKey(new KeyEvent(AndroidKey.N));
                    break;
                case 'O':
                    driver.pressKey(new KeyEvent(AndroidKey.O));
                    break;
                case 'P':
                    driver.pressKey(new KeyEvent(AndroidKey.P));
                    break;
                case 'Q':
                    driver.pressKey(new KeyEvent(AndroidKey.Q));
                    break;
                case 'R':
                    driver.pressKey(new KeyEvent(AndroidKey.R));
                    break;
                case 'S':
                    driver.pressKey(new KeyEvent(AndroidKey.S));
                    break;
                case 'T':
                    driver.pressKey(new KeyEvent(AndroidKey.T));
                    break;
                case 'U':
                    driver.pressKey(new KeyEvent(AndroidKey.V));
                    break;
                case 'W':
                    driver.pressKey(new KeyEvent(AndroidKey.W));
                    break;
                case 'X':
                    driver.pressKey(new KeyEvent(AndroidKey.X));
                    break;
                case 'Y':
                    driver.pressKey(new KeyEvent(AndroidKey.Y));
                    break;
                case 'Z':
                    driver.pressKey(new KeyEvent(AndroidKey.Z));
                    break;

                // Special character mappings
                case '!':
                    //driver.pressKey(new KeyEvent(AndroidKey.EXCLAMANATION));
                    break;
                case '@':
                    driver.pressKey(new KeyEvent(AndroidKey.AT));
                    break;
                case '#':
                    driver.pressKey(new KeyEvent(AndroidKey.POUND));
                    break;
                case '$':
                    //driver.pressKey(new KeyEvent(AndroidKey.DOLLAR));
                    break;
                case '%':
                    //driver.pressKey(new KeyEvent(AndroidKey.PERCENT));
                    break;
                case '&':
                    //driver.pressKey(new KeyEvent(AndroidKey.AMPERSAND));
                    break;
                case '*':
                    //driver.pressKey(new KeyEvent(AndroidKey.ASTERISK));
                    break;
                case '-':
                    driver.pressKey(new KeyEvent(AndroidKey.MINUS));
                    break;
                case '_':
                    //driver.pressKey(new KeyEvent(AndroidKey.UNDERSCORE));
                    break;
                case '+':
                    driver.pressKey(new KeyEvent(AndroidKey.PLUS));
                    break;
                case '=':
                    driver.pressKey(new KeyEvent(AndroidKey.EQUALS));
                    break;
                case '~':
                    //driver.pressKey(new KeyEvent(AndroidKey.TILDE));
                    break;
                case '?':
                    //driver.pressKey(new KeyEvent(AndroidKey.QUESTION));
                    break;
                case '{':
                    driver.pressKey(new KeyEvent(AndroidKey.LEFT_BRACKET));
                    break;
                case '}':
                    driver.pressKey(new KeyEvent(AndroidKey.RIGHT_BRACKET));
                    break;
                default:
                    throw new IllegalArgumentException("Character not available");
            }
            System.out.println("key " + digit + " pressed");
        }
    }

    public static void tapByCoordinate(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // Tap by coordinate method //

        // Create a sequence to simulate a touch action
        Sequence tap = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the action
        driver.perform(Arrays.asList(tap));
    }

}