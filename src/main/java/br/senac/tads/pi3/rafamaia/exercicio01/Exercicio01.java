/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.rafamaia.exercicio01;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael.malexandre
 */
public class Exercicio01 {

    static Scanner leitor = new Scanner(System.in);

    static void entradaDeDados(Pessoa pessoa) {

        PreparedStatement stmt = null;
        Connection conn = null;

        Date dataNasc;

        System.out.println("*** Cadastro de Contatos ***");

        System.out.println("Digite o nome: ");
        pessoa.setNome(leitor.nextLine());

        System.out.println("Digite o e-mail: ");
        pessoa.setEmail(leitor.nextLine());

        System.out.println("Digite o telefone: ");
        pessoa.setTelefone(leitor.nextLine());

        System.out.println("Digite o data de nascimento no formato dd/mm/aaaa: ");
        DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dataNasc = formatadorData.parse(leitor.nextLine());
        } catch (ParseException ex) {
            Logger.getLogger(Exercicio01.class.getName()).log(Level.SEVERE, null, ex);
            dataNasc = new Date();
        }
        pessoa.setData(dataNasc.toString());

        String sql = "INSERT INTO TB_PESSOA (NM_PESSOA, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL) VALUES (?, ?, ?, ?)";
        try {
            conn = obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setDate(2, new java.sql.Date(dataNasc.getTime()));
            stmt.setString(3, pessoa.getTelefone());
            stmt.setString(4, pessoa.getEmail());

            stmt.executeUpdate();

            System.out.println("*** Contato salvo ***\n");

        } catch (SQLException ex) {
            Logger.getLogger(Exercicio01.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Exercicio01.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Exercicio01.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Exercicio01.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    static void deletaDados() {
        System.out.print("Digite o ID do dado que será excluido: ");
        int delID = leitor.nextInt();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = " delete from TB_PESSOA where ID_PESSOA = " + delID + ";";

        try {
            conn = obterConexao();
            stmt.executeQuery(sql);

        } catch (SQLException ex) {
            Logger.getLogger(Exercicio01.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Exercicio01.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Exercicio01.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Exercicio01.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    static void alteraDados(Pessoa pessoa) {
        Date dataNasc;

        PreparedStatement stmt = null;
        Connection conn = null;

        System.out.println("*** Alteração de Contatos ***");

        System.out.print("Digite o ID a ser alterado: ");
        int alterID = leitor.nextInt();

        System.out.println("Digite o nome (alterado): ");
        pessoa.setNome(leitor.nextLine());

        System.out.println("Digite o e-mail (alterado): ");
        pessoa.setEmail(leitor.nextLine());

        System.out.println("Digite o telefone (alterado): ");
        pessoa.setTelefone(leitor.nextLine());

        System.out.println("Digite o data de nascimento no formato dd/mm/aaaa: ");
        DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");

        try {
            dataNasc = formatadorData.parse(leitor.nextLine());
        } catch (ParseException ex) {
            Logger.getLogger(Exercicio01.class.getName()).log(Level.SEVERE, null, ex);
            dataNasc = new Date();
        }
        pessoa.setData(dataNasc.toString());

        String sql = "UPDATE TB_PESSOA \n"
                + "SET NM_PESSOA = '" + pessoa.getNome() + "',"
                + "VL_TELEFONE = '" + pessoa.getTelefone() + "',"
                + "VL_EMAIL = '" + pessoa.getEmail() + "' ,"
                + "DT_NASCIMENTO = '" + pessoa.getData() + "'"
                + "WHERE ID_PESSOA = " + alterID;

        try {
            conn = obterConexao();
            stmt = conn.prepareStatement(sql);
            

            stmt.executeUpdate();

            System.out.println("*** Alterações salvas ***\n");

        } catch (SQLException ex) {
            Logger.getLogger(Exercicio01.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Exercicio01.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Exercicio01.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Exercicio01.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa();

        int opcao = 10;

        do {
            System.out.println("Escolha uma Opção:");
            System.out.println("1- Inserir dados");
            System.out.println("2- Listar Pessoas");
            System.out.println("3- Excluir Pessoa");
            System.out.println("4- Alterar Dados");

            opcao = leitor.nextInt();

            if (opcao > 0 && opcao < 5) {
                
                switch (opcao) {
                    case 1:
                        entradaDeDados(pessoa);
                        break;
                    case 2:
                        listarPessoas();
                        break;
                    case 3:
                        deletaDados();
                        break;
                    case 4:
                        alteraDados(pessoa);
                        break;

                }
            }

        } while (opcao != 0);

    }

    public static void listarPessoas() {
        java.sql.Statement stmt = null;
        Connection conn = null;

        String sql = "SELECT ID_PESSOA, NM_PESSOA, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL FROM TB_PESSOA";
        try {
            conn = obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);

            DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");

            while (resultados.next()) {
                Long id = resultados.getLong("ID_PESSOA");
                String nome = resultados.getString("NM_PESSOA");
                Date dataNasc = resultados.getDate("DT_NASCIMENTO");
                String email = resultados.getString("VL_EMAIL");
                String telefone = resultados.getString("VL_TELEFONE");
                System.out.println(String.valueOf(id) + ", " + nome + ", " + formatadorData.format(dataNasc) + ", " + email + ", " + telefone);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Exercicio01.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Exercicio01.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Exercicio01.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Exercicio01.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public static Connection obterConexao() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Class.forName("org.apache.derby.jdbc.ClientDataSource");
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/AgendadB;SecurityMechanism=3",
                "app",
                "app");
        return conn;
    }

}
