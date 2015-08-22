/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.rafamaia.exercicio01;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


/**
 *
 * @author rafael.malexandre
 */
public class Exercicio01 {

    static Scanner leitor = new Scanner(System.in);
    
    static void entradaDeDados(Pessoa pessoa) {

        System.out.println("*** Cadastro de Contatos ***");

        System.out.println("Digite o nome: ");
        pessoa.setNome(leitor.nextLine());
        
        System.out.println("Digite o e-mail: ");
        pessoa.setEmail(leitor.nextLine());
        
        System.out.println("Digite o telefone: ");
        pessoa.setTelefone(leitor.nextLine());
        
        System.out.println("Digite o data de nascimento: ");
        pessoa.setData(leitor.nextLine());
        
        
        System.out.println("*** Contato salvo ***\n");

    }

    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa();
        
        entradaDeDados(pessoa);
        
        saidaDeDados(pessoa);

    }

    public static void saidaDeDados(Pessoa pessoa) {
        System.out.println("***Dados do contato***");
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Email: " + pessoa.getEmail());
        System.out.println("Telefone: " + pessoa.getTelefone());
        System.out.println("Data Nascimento: " + pessoa.getData());
    }
    public static void listarPessoas(){
        Statement stmt = null;
        Connection conn = null;
        
        String sql = "Select * from TB_PESSOA";
    
        try{            
            conn = obterConexao();
            stmt = conn.createStatement();
            
            ResultSet resultados = stmt.execute(sql);
        
        }
        
        
    }
    public Connection obterConexao() throws SQLException,ClassNotFoundException{
        Connection conn = null;
        Class.forName("org.apache.derby.jdbc.ClientDataSource");
        
        conn = DriverManager.getConnection("jdbc:derby://localhost:");
        
        return conn;
    }

}
