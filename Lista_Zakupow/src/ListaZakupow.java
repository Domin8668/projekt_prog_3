import javax.swing.*;

public class ListaZakupow {
    private JPanel mainPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lista Zakupów");
        frame.setContentPane(new ListaZakupow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
