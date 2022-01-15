import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.*;

public class ListaZakupow {
    private JPanel mainPanel;
    private JPanel cardPanel;
    private JMenuItem infoMenuItem;
    private JMenuItem helpMenuItem;
    private JMenuItem exitMenuItem;
    private JPanel menuCard;
    private JPanel inputCard;
    private JPanel loadingCard;
    private JPanel outputCard;
    private JPanel infoCard;
    private JPanel helpCard;
    CardLayout cl = new CardLayout();

    public ListaZakupow() {
        cardPanel.setLayout(cl);
        inputCard.setBackground(Color.green);
        menuCard.setBackground(Color.yellow);
        cardPanel.add(menuCard, "1");
        cardPanel.add(inputCard, "2");
        cl.show(cardPanel, "1");
        infoMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(cardPanel, "2");
                FileFilter txtFileFilter = new FileNameExtensionFilter("Text File","txt");
                JFileChooser fc = new JFileChooser();
                fc.setAcceptAllFileFilterUsed(false);
                fc.setFileFilter(txtFileFilter);
                fc.showOpenDialog(mainPanel);
            }
        });
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lista Zakupów");
        frame.setContentPane(new ListaZakupow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(720, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
