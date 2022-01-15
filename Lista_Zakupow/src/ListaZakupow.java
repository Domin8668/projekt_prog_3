import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.filechooser.*;

public class ListaZakupow {
    private JPanel mainPanel;
    private JPanel cardPanel;
    private JMenuItem mainMenuItem;
    private JMenuItem infoMenuItem;
    private JMenuItem helpMenuItem;
    private JMenuItem exitMenuItem;
    private JPanel menuCard;
    private JPanel inputCard;
    private JPanel loadingCard;
    private JPanel outputCard;
    private JPanel infoCard;
    private JPanel helpCard;
    private JLabel clockLabel;
    private JButton chooseFilesButton;
    private JLabel infoLabel;
    private JLabel helpLabel;
    private JTextArea inputTextArea;
    private JButton calculateButton;
    private JScrollPane inputScrollPane;
    private JMenuBar menuBar;
    private JPanel menuPanel;
    private File[] files;
    private ArrayList<String> input;
    private ArrayList<String> result;
    CardLayout cl = new CardLayout();

    public ListaZakupow() {
        cardPanel.setLayout(cl);
        cardPanel.add(menuCard, "1");
        cardPanel.add(inputCard, "2");
        cardPanel.add(loadingCard, "3");
        cardPanel.add(outputCard, "4");
        cardPanel.add(infoCard, "5");
        cardPanel.add(helpCard, "6");
        cl.show(cardPanel, "1");

        startClock();

        menuPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        menuBar.setBorder(null);
        mainMenuItem.addActionListener(e -> cl.show(cardPanel, "1"));
        infoMenuItem.addActionListener(e -> {
            infoLabel.setText("""
            <html>
            Aplikacja "Lista zakupów" napisana w języku Java<br><br>
            Autorzy:<br>&emsp;&emsp;Kamil Giziński<br>&emsp;&emsp;Dominik Sigulski<br>&emsp;&emsp;Bartosz Rolnik<br><br>
            Projekt z przedmiotu Programowanie III<br>
            Wydział Matematyki Stosowanej, Informatyka, sem. III, rok 2021/22<br><br>
            "Lista zakupów" to zadanie z konkursu "Algorytmion" z roku 2012,<br>
            które polega na znalezieniu takich kombinacji produktów,<br>
            aby suma ich wartości odpowiadała budżetowi jaki mamy do rozdysponowania.
            </html>""");
            cl.show(cardPanel, "5");
        });
        helpMenuItem.addActionListener(e -> {
            helpLabel.setText("""
                <html>
                Do wyboru z głównego panelu menu mamy następujące opcje:<br><br>
                &emsp;&emsp;1. Menu główne<br>&emsp;&emsp;2. Informacje<br>&emsp;&emsp;3. Pomoc<br>&emsp;&emsp;4. Wyjście<br><br>
                Opcja 1:<br>
                &emsp;&emsp;W menu głównym możemy podjąc decyzję o tym jakie pliki chcemy analizować<br>
                &emsp;&emsp;klikając przycisk "Wybierz pliki". Następnie poprzez naciśnięcie kolejnego przycisku<br>
                &emsp;&emsp;program analizuje dane i zwraca wynik na ekran oraz do pliku.<br>
                Opcja 2:<br>
                &emsp;&emsp;Wyświetlenie informacji na temat zadania, autorów oraz przedmiotu w zakresie którego<br>
                &emsp;&emsp;realizowany był projekt.<br><br>
                Opcja 3:<br>
                &emsp;&emsp;Wyświetlenie informacji pomocniczych na temat opcji zawartych w aplikacji.<br><br>
                Opcja 4:<br>
                &emsp;&emsp;Zakończenie działania aplikacji.<br><br>
                </html>""");
            cl.show(cardPanel, "6");
        });
        exitMenuItem.addActionListener(e -> System.exit(0));
        calculateButton.addActionListener(e -> {
            cl.show(cardPanel, "4"); // Tymczasowo przechodzimy od razu do output
            Calculate c = new Calculate();
            c.calculate(input);
            result = c.result;
            for(String s : result) {
                System.out.println(s);
            }
        });
        chooseFilesButton.addActionListener(e -> {
            try {
                chooseFiles();
                if(files == null) {
                    JOptionPane.showMessageDialog(mainPanel, "Nie wybrano żadnego pliku.",
                            "Błąd krytyczny", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    input = LoadFromFile.getInput(files);
                    StringBuilder inputToDisplay = new StringBuilder();
                    for(int i = 0; i < files.length; i++) {
                        inputToDisplay.append("Plik ").append(files[i].getName()).append("\n");
                        inputToDisplay.append(input.get(i)).append("\n\n");
                    }
                    inputTextArea.setText(String.valueOf(inputToDisplay));
                    cl.show(cardPanel, "2");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void startClock() {
        ActionListener updateClockAction = e -> clockLabel.setText(((new Date().toString()).substring(11, 19) + "    "));
        Timer t = new Timer(100, updateClockAction);
        t.start();
    }

    public void chooseFiles() {
        JFileChooser fc = new JFileChooser(new File("Test/"));
        FileFilter txtFileFilter = new FileNameExtensionFilter("Pliki tekstowe","txt");
        fc.setMultiSelectionEnabled(true);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileFilter(txtFileFilter);
        int r = fc.showOpenDialog(mainPanel);
        if(r == JFileChooser.APPROVE_OPTION) {
            files = fc.getSelectedFiles();
        }
    }

    public static void main(String[] args) {
        FlatLightLaf.setup(); //setting the look and feel
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Lista Zakupów");
        frame.getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(23,180,252));
        frame.getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.white);
        frame.setContentPane(new ListaZakupow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(720, 530);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createUIComponents() {

    }
}