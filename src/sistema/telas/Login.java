package sistema.telas;

import javax.swing.*;
import java.awt.*;


public class Login extends JPanel {
    JLabel labelUsuario;
    JTextField campoUsuario;
    JLabel labelSenha;
    JPasswordField campoSenha;
    JButton botaoEntrar;

    public Login(){
        criarComponentes();
        //criarEventos();
    }
    private void criarComponentes(){
        setLayout(null);//define que não usaremos gerenciador de layout

        //instanciando componentes da tela
        JLabel labelTitulo = new JLabel("Seja Bem VIndo ao Sistema da Company SA!", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 18));
        labelUsuario = new JLabel("Usuário", JLabel.LEFT);//inicia JLabel com texto e alinhamento especifico
        campoUsuario = new JTextField();
        labelSenha = new JLabel("Senha", JLabel.LEFT);
        campoSenha = new JPasswordField();
        botaoEntrar = new JButton("Entrar");

        //definindo posicionamento e tamanho dos componentes na tela
        labelTitulo.setBounds(20, 100, 660, 40);
        labelUsuario.setBounds(250, 220, 200, 20);
        campoUsuario.setBounds(250, 240, 200, 40);
        labelSenha.setBounds(250, 280, 200, 20);
        campoSenha.setBounds(250, 300, 200, 40);
        botaoEntrar.setBounds(250, 350, 200, 40);

        //adicionando os componentes à tela
        add(labelTitulo);
        add(labelUsuario);
        add(campoUsuario);
        add(labelSenha);
        add(campoSenha);
        add(botaoEntrar);

        //tornando a tela visível
        setVisible(true);
    }
}