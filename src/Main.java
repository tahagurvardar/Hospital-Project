import com.formdev.flatlaf.FlatLightLaf;
import ui.LoginFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        try {

            UIManager.setLookAndFeel(new FlatLightLaf());

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        LoginFrame loginFrame = new LoginFrame();

        loginFrame.setVisible(true);
    }
}
