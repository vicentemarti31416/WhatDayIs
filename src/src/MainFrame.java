package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

public class MainFrame extends JFrame {

    private JPanel panel0;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JComboBox combo1;
    private JComboBox combo2;
    private JComboBox combo3;
    private  JButton button;
    private  JLabel label1;
    private  JLabel label2;

    public MainFrame() {
        setBounds(300, 300, 300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        panel0 = new JPanel();
        label2 = new JLabel("Elige una fecha para ver qué día es");
        panel0.add(label2);

        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        Integer[] DAYS = Stream.iterate(1, x -> x+1).limit(31).toArray(Integer[]::new);
        Month[] MONTHS = Month.values();
        int MIN_YEARS = 1900;
        Integer[] YEARS = Stream.iterate(1900, x -> x+1).limit((LocalDate.now().getYear()+1) - MIN_YEARS).toArray(Integer[]::new);

        combo1 = new JComboBox(DAYS);
        combo2 = new JComboBox(MONTHS);
        combo3 = new JComboBox(YEARS);

        panel1.add(combo1);
        panel1.add(combo2);
        panel1.add(combo3);

        panel2 = new JPanel();
        button = new JButton("Comprobar");
        Inner1 inner1 = new Inner1();
        button.addActionListener(inner1);
        panel2.add(button);

        panel3 = new JPanel();
        label1 = new JLabel();
        panel3.add(label1);

        add(panel0);
        add(panel1);
        add(panel2);
        add(panel3);

        setVisible(true);
    }

    class Inner1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int day = (Integer) combo1.getSelectedItem();
            Month month = (Month) combo2.getSelectedItem();
            int year = (Integer) combo3.getSelectedItem();
            LocalDate localDate = null;
            try {
                localDate = LocalDate.of(year, month, day);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Fecha incorrecta, elija una fecha válida");
            }
            System.out.println(localDate.getDayOfWeek());
            label1.setText(localDate.getDayOfWeek().toString());
        }
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }
}
