import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz implements ActionListener {

    String[] questions =    {
                                    "Kto stworzyl Java?",
                                    "Jak nazywa sie nabogatszy czlowiek na swiecie",
                                    "Inna nazwa bakłażana?"
                                };
    String[][] options =    {{"Sun microsystems", "Oracle", "Microsoft", "biedronka"},
                                {"Bill Gates", "Marcin Prokop", "Dorota welman", "Jef Bezos"},
                                {"Oberzyna", "gruszka", "ablko", "sliwka"}
                                 };

    char[] answers =      {'A', 'D', 'A'};
    char guess;
    char answer;
    int index;
    int correct_guesses = 0;
    int result;
    int total_questions = questions.length;
    int seconds =10;

    JFrame frame= new JFrame();
    JTextField textfield = new JTextField();
    JTextArea textarea = new JTextArea();

    JButton buttonA = new JButton();
    JButton buttonB= new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();

    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();

    JLabel time_label = new JLabel();
    JLabel seconds_left = new JLabel();

    JLabel correct_guess = new JLabel();

    Timer timer = new Timer(1000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            seconds_left.setText(String.valueOf(seconds));
            if(seconds<=0) {
                displayAnswer();
            }
        }
    });


    //JButton[] buttons = new JButton[4];


    public Quiz(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(Color.black);
        frame.setLayout(null);
        frame.setResizable(false);

        textfield.setBounds(0,0,800,50);
        textfield.setBackground(Color.white);
        textfield.setForeground(Color.BLUE);
        textfield.setFont(new Font("Ink free", Font.BOLD, 50));
        textfield.setBorder(BorderFactory.createBevelBorder(1));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setText("Welcome to the quiz !");
        textfield.setOpaque(true);
        textfield.setEditable(false);

        textarea.setBounds(0,50,800,50);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setBackground(Color.white);
        textarea.setForeground(Color.BLUE);
        textarea.setFont(new Font("Ink free", Font.BOLD, 20));
        textarea.setBorder(BorderFactory.createBevelBorder(1));
       // textarea.setHorizontalAlignment(JTextArea.CENTER);
        textarea.setText("pytanie !");
        textarea.setOpaque(true);
        textarea.setEditable(false);

        buttonA.setBounds(0,100,100,100);
        buttonA.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonA.setFocusable(false);
        buttonA.setText("A");
        buttonA.addActionListener(this);

        buttonB.setBounds(0,200,100,100);
        buttonB.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonB.setFocusable(false);
        buttonB.setText("B");
        buttonB.addActionListener(this);

        buttonC.setBounds(0,300,100,100);
        buttonC.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonC.setFocusable(false);
        buttonC.setText("C");
        buttonC.addActionListener(this);

        buttonD.setBounds(0,400,100,100);
        buttonD.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonD.setFocusable(false);
        buttonD.setText("D");
        buttonD.addActionListener(this);

        answer_labelA.setBounds(150, 100,500,100);
        answer_labelA.setBackground(new Color(50,50,50));
        answer_labelA.setForeground(new Color(25, 255, 0));
        answer_labelA.setFont(new Font("MV Boli", Font.PLAIN,35));

        answer_labelB.setBounds(150, 200,500,100);
        answer_labelB.setBackground(new Color(50,50,50));
        answer_labelB.setForeground(new Color(25, 255, 0));
        answer_labelB.setFont(new Font("MV Boli", Font.PLAIN,35));

        answer_labelC.setBounds(150, 300,500,100);
        answer_labelC.setBackground(new Color(50,50,50));
        answer_labelC.setForeground(new Color(25, 255, 0));
        answer_labelC.setFont(new Font("MV Boli", Font.PLAIN,35));

        answer_labelD.setBounds(150, 400,500,100);
        answer_labelD.setBackground(new Color(50,50,50));
        answer_labelD.setForeground(new Color(25, 255, 0));
        answer_labelD.setFont(new Font("MV Boli", Font.PLAIN,35));

        seconds_left.setBounds(0, 680, 100,100);
        seconds_left.setBackground(new Color(25,25,25));
        seconds_left.setForeground(new Color(255,0,0));
        seconds_left.setFont(new Font("Ink Free", Font.BOLD, 60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));

        correct_guess.setBounds(350, 300, 100,100);
        correct_guess.setBackground(new Color(25,25,25));
        correct_guess.setForeground(new Color(255,0,0));
        correct_guess.setFont(new Font("Ink Free", Font.BOLD, 60));
        correct_guess.setBorder(BorderFactory.createBevelBorder(1));
        correct_guess.setOpaque(true);
        correct_guess.setHorizontalAlignment(JTextField.CENTER);
        correct_guess.setText(String.valueOf(seconds));
        correct_guess.setVisible(false);

        frame.add(correct_guess);
        frame.add(seconds_left);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);

        frame.add(textfield);
        frame.add(textarea);

        frame.setVisible(true);
        nextQuestion();
    };

    public void nextQuestion(){
        if(index > total_questions){
            results();
        }
        else{
            textarea.setText(questions[index]);
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);
        }
    };

    public void displayAnswer(){
        timer.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(answers[index] != 'A')
            answer_labelA.setForeground(new Color(255,0,0));
        if(answers[index] != 'B')
            answer_labelB.setForeground(new Color(255,0,0));
        if(answers[index] != 'C')
            answer_labelC.setForeground(new Color(255,0,0));
        if(answers[index] != 'D')
            answer_labelD.setForeground(new Color(255,0,0));

        Timer pause = new Timer(2000, new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                answer_labelA.setForeground(new Color(25,255,0));
                answer_labelB.setForeground(new Color(25,255,0));
                answer_labelC.setForeground(new Color(25,255,0));
                answer_labelD.setForeground(new Color(25,255,0));
                answer = ' ';
                seconds=10;
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();
            }
        });
        pause.setRepeats(false);
        pause.start();

    };

    public void results(){
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = (int)(correct_guesses/(double)total_questions)*100;

        textfield.setText("RESULTS");
        textarea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");
        correct_guess.setText(correct_guesses+"/"+total_questions);
        frame.add(correct_guess);

    };

    @Override
    public void actionPerformed(ActionEvent e) {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(e.getSource()==buttonA){
            answer='A';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }
        if(e.getSource()==buttonB){
            answer='B';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }
        if(e.getSource()==buttonC){
            answer='C';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }
        if(e.getSource()==buttonD){
            answer='D';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }
        displayAnswer();
    }

}
