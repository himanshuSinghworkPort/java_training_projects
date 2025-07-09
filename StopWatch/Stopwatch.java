import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch implements ActionListener {
    JFrame frame=new JFrame();
    JLabel timeLabel=new JLabel();
    JButton startButton=new JButton("START");
    JButton resetButton=new JButton("RESET");
    int elapsedTime=0;
    int seconds=0;
    int minutes=0;
    int hours=0;
    boolean starded=false;
    String seconds_string=String.format("%02d",seconds);
    String minutes_string=String.format("%02d",minutes);
    String hours_string=String.format("%02d",hours);

    Timer timer=new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime=elapsedTime+1000;
            hours=(elapsedTime/3600000);
            minutes=(elapsedTime/60000) % 60;
            seconds=(elapsedTime/1000) % 60;
            seconds_string=String.format("%02d",seconds);
            minutes_string=String.format("%02d",minutes);
            hours_string=String.format("%02d",hours);
            timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);


        }
    });

    Stopwatch(){


        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        timeLabel.setBounds(100,100,200,100);
        timeLabel.setFont(new Font("Vendana",Font.PLAIN,35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(100,200,100,50);
        startButton.setFocusable(false);
        startButton.setFont(new Font("Ink free",Font.PLAIN,20));
        startButton.addActionListener(this);

        resetButton.setBounds(200,200,100,50);
        resetButton.setFocusable(false);
        resetButton.setFont(new Font("Ink free",Font.PLAIN,20));
        resetButton.addActionListener(this);

        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==startButton){
            if(starded==false){
                starded=true;
                startButton.setText("STOP");
                Start();
            }else{
                starded=false;
                startButton.setText("START");
                Stop();
            }
        }
        if(e.getSource()==resetButton){
            starded=false;
            startButton.setText("START");
            reset();
        }

    }
    void Start(){
        timer.start();

    }
    void Stop(){
        timer.stop();

    }
    void reset(){
        timer.stop();
        elapsedTime=0;
        hours=0;
        minutes=0;
        seconds=0;
        seconds_string=String.format("%02d",seconds);
        minutes_string=String.format("%02d",minutes);
        hours_string=String.format("%02d",hours);
        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);


    }
}
