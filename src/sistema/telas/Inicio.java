package sistema.telas;

import javax.swing.JLabel;
import javax.swing.JPanel;
import sistema.Navegador;

public class Inicio extends JPanel{

    JLabel labelTitulo;

    public Inicio (){
        criarComponentes();
        criarEventos();
        Navegador.habilitaMenu();
    }

    private void criarComponentes() {
        setLayout(null);

        // JLabel sendo inicializado com texto e alinhamento de exibição
        labelTitulo = new JLabel("Escolha uma opção no menu superior", JLabel.CENTER);

        // define posicionamento e tamanho do JLabel
        labelTitulo.setBounds(20,100,660,40);

        // adiciona a tela
        add(labelTitulo);

        setVisible(true);
    }
    private void criarEventos(){

    };
}
