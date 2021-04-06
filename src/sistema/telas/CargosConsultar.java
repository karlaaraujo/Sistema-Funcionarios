package sistema.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sistema.BancoDeDados;
import sistema.Navegador;
import sistema.entidades.Cargo;

public class CargosConsultar extends JPanel{

    Cargo cargoAtual;
    JLabel labelTitulo, labelCargo;
    JTextField campoCargo;
    JButton botaoPesquisar, botaoEditar, botaoExcluir;
    DefaultListModel<Cargo> listasCargosModelo = new DefaultListModel();
    JList<Cargo> listaCargos;

    public CargosConsultar(){
        criarComponentes();
        criarEventos();
    }

    private void criarComponentes(){
        setLayout(null);

        // instanciando componentes da tela
        labelTitulo = new JLabel("Consulta de Cargos", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));
        labelCargo = new JLabel("Nome do cargo", JLabel.LEFT);
        campoCargo = new JTextField();
        botaoPesquisar = new JButton("Pesquisar Cargo");
        botaoEditar = new JButton("Editar Cargo");
        botaoEditar.setEnabled(false);
        botaoExcluir = new JButton("Excluir Cargo");
        botaoExcluir.setEnabled(false);
        listasCargosModelo = new DefaultListModel();
        listaCargos = new JList();
        listaCargos.setModel(listasCargosModelo);
        listaCargos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // definindo posicionamento e tamanho dos componentes
        labelTitulo.setBounds(20, 20, 660, 40);
        labelCargo.setBounds(150, 120, 400, 20);
        campoCargo.setBounds(150, 140, 400, 40);
        botaoPesquisar.setBounds(560, 140, 130, 40);
        listaCargos.setBounds(150, 200, 400, 240);
        botaoEditar.setBounds(560, 360, 130, 40);
        botaoExcluir.setBounds(560, 400, 130, 40);

        // adicionando componentes à tela
        add(labelTitulo);
        add(labelCargo);
        add(campoCargo);
        add(listaCargos);
        add(botaoPesquisar);
        add(botaoEditar);
        add(botaoExcluir);

        // tornando a tela visível
        setVisible(true);
    }

    private void criarEventos(){
        botaoPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlPesquisarCargos(campoCargo.getText());
            }
        });
        botaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Navegador.cargosEditar(cargoAtual);
            }
        });
        botaoExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlDeletarCargo();
            }
        });
        listaCargos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                cargoAtual = listaCargos.getSelectedValue();
                if (cargoAtual == null){
                    botaoEditar.setEnabled(false);
                    botaoExcluir.setEnabled(false);
                } else {
                    botaoEditar.setEnabled(true);
                    botaoExcluir.setEnabled(true);
                }
            }
        });
    }

    private void sqlPesquisarCargos(String nome){
        // conexão
        Connection conexao;
        // instrucao SQL
        Statement instrucaoSQL;
        // resultados
        ResultSet resultados;

        try {
            // conectando ao banco de dados
            conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);

            // criando a instrucao SQL
            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultados = instrucaoSQL.executeQuery("SELECT * FROM cargos WHERE nome like '%" + nome + "%'");

            listasCargosModelo.clear();

            while (resultados.next()){
                Cargo cargo = new Cargo();
                cargo.setId(resultados.getInt("id"));
                cargo.setNome(resultados.getString("nome"));

                listasCargosModelo.addElement(cargo);
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar os Cargos.");
            Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sqlDeletarCargo(){

        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o cargo "+
                cargoAtual.getNome()+"?","Excluir", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {

            // conexão
            Connection conexao;
            // instrucao SQL
            Statement instrucaoSQL;
            // resultados
            ResultSet resultados;

            try {
                // conectando ao banco de dados
                conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);

                // criando a instrucao SQL
                instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                instrucaoSQL.executeUpdate("DELETE FROM cargos WHERE id="+ cargoAtual.getId()+";");

                JOptionPane.showMessageDialog(null, "Cargo deletado com sucesso!");

            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir o Cargo.");
                Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
