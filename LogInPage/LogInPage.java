import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LogInPage implements ActionListener {
    JFrame frame=new JFrame();
    JButton loginButton=new JButton("LogIn:");
    JButton resetButton=new JButton("Reset:");
    JTextField userIdField=new JTextField();
    JPasswordField userPasswordField=new JPasswordField();
    JLabel userIdlabel=new JLabel("UserId");
    JLabel userPasswordlabel=new JLabel("Password");
    JLabel messageLabel=new JLabel();

    HashMap<String,String> logIninfo=new HashMap<String,String>();

    LogInPage(HashMap<String,String> LogInOriginal){
        logIninfo=LogInOriginal;

        userIdlabel.setBounds(50,100,75,25);
        userPasswordlabel.setBounds(50,150,75,25);

        userIdField.setBounds(125,100,200,25);
        userPasswordField.setBounds(125,150,200,25);

        messageLabel.setBounds(125,250,250,30);
        messageLabel.setFont(new Font(null,Font.ITALIC,25));

        loginButton.setBounds(125,200,100,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        resetButton.setBounds(225,200,100,25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);


        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(userIdlabel);
        frame.add(userPasswordlabel);
        frame.add(userIdField);
        frame.add(userPasswordField);
        frame.add(messageLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==resetButton){
            userIdField.setText("");
            userPasswordField.setText("");
        }
        if(e.getSource()==loginButton){
            String UserId=userIdField.getText();
            String userPassword=String.valueOf(userPasswordField.getPassword());
            if(logIninfo.containsKey(UserId)){
                if (logIninfo.get(UserId).equals(userPassword)){
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login Successfull");
                    frame.dispose();
                    WelcomePage welcomePage=new WelcomePage(UserId);
                }
                else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Wrong Password");
                }
            }
            else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Wrong UserName");
            }
        }

    }
}
