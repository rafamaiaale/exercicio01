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

    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa();

        entradaDeDados(pessoa);

        listarPessoas();

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

        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/agendabd;SecurityMechanism=3",
                "app",
                "app");
        return conn;
    }

}
