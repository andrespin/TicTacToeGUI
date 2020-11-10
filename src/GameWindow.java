import javax.swing.*;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.JSplitPane.TOP;
import static javax.swing.SwingConstants.CENTER;

public class GameWindow extends JFrame {

    private ArrayList<JButton> buttons = new ArrayList<>();

    protected JTextArea textArea = new JTextArea();

    private Container container = getContentPane();

    private JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));

    private JPanel grid = new JPanel(new GridLayout(3, 3));

    private JButton startAgain = new JButton();


    private String MySymbol = "0";
    private String AISymbol = "X";




    public JButton getStartAgain() {
        return startAgain;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public JPanel getFlow() {
        return flow;
    }

    public void setFlow(JPanel flow) {
        this.flow = flow;
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<JButton> buttons) {
        this.buttons = buttons;
    }

    public JPanel getGrid() {
        return grid;
    }

    public void setGrid(JPanel grid) {
        this.grid = grid;
    }

    public void openGameWindow()
    {
        setBounds ( 500 , 150 , 600 , 600 );
        Dimension dim = new Dimension(600, 600);
        setMinimumSize(dim);
        setMaximumSize(dim);
        setTitle ("Крестики-нолики");
        setDefaultCloseOperation ( WindowConstants.EXIT_ON_CLOSE );

        Font BigFont = new Font("TimesRoman", Font.BOLD, 90);
        Font SmallFont = new Font("TimesRoman", Font.BOLD, 45);




        JButton jb1 = new JButton();
        buttons.add(jb1);

        JButton jb2 = new JButton();
        buttons.add(jb2);

        JButton jb3 = new JButton();
        buttons.add(jb3);

        JButton jb4 = new JButton();
        buttons.add(jb4);

        JButton jb5 = new JButton();
        buttons.add(jb5);

        JButton jb6 = new JButton();
        buttons.add(jb6);

        JButton jb7 = new JButton();
        buttons.add(jb7);

        JButton jb8 = new JButton();
        buttons.add(jb8);

        JButton jb9 = new JButton();
        buttons.add(jb9);


        for (JButton button: buttons) {
            button.setFont(BigFont);

            button.addActionListener ( new ActionListener() {
                @Override
                public void actionPerformed ( ActionEvent e ) {


                }
            });

            grid.add(button);
        }


        textArea.setFont(BigFont);
        textArea.setText("Text");

        startAgain.setFont(SmallFont);
        startAgain.setText("Начать заново");
        startAgain.setVisible(false);

        flow.add(textArea);
        flow.add(grid);
        flow.add(startAgain);

        // Размещение панели с кнопками внизу справа
        container.add(flow, BorderLayout.CENTER);


        setVisible(true);

    }

}
