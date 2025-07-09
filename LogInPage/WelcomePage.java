import javax.swing.*;
import java.awt.*;

public class WelcomePage {
    JFrame frame=new JFrame();
    JLabel welcomeLabel=new JLabel();
    WelcomePage(String userid){

        welcomeLabel.setBounds(10,30,200,30);
        welcomeLabel.setFont(new Font(null,Font.ITALIC,25));
        welcomeLabel.setText("Hello "+userid);

        frame.add(welcomeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
