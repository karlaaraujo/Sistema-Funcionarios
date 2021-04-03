package sistema;

import javax.swing.JFrame;
import javax.swing.JPanel;
import sistema.telas.Login;

public class Sistema {
    public static JPanel tela;
    public static JFrame frame;

    public static void main(String[] args) {
        criarComponentes();
    }

    private static void criarComponentes(){
        frame = new JFrame("Sistema");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        tela = new Login();
        tela.setVisible(true);
        frame.add(tela);

        frame.setVisible(true);
    }
}
