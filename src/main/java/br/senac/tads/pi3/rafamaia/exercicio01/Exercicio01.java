/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.rafamaia.exercicio01;

import java.util.Scanner;

/**
 *
 * @author rafael.malexandre
 */
public class Exercicio01 {

    static Scanner leitor = new Scanner(System.in);
    static Pessoa pessoa = new Pessoa();

    static void entradaDeDados(String nome, String telefone, String data, String email) {

        System.out.println("*** Cadastro de Contatos ***");

        System.out.println("Digite o nome:");
        pessoa.setNome(leitor.nextLine());
        
        System.out.println("Digite o e-mail:");
        pessoa.setEmail(leitor.nextLine());
        
        System.out.println("Digite o telefone:");
        pessoa.setTelefone(leitor.nextLine());
        
        System.out.println("Digite o data de nascimento:");
        pessoa.setData(leitor.nextLine());
        
        
        System.out.println("*** Contato salvo ***");

    }

    public static void main(String[] args) {
        String nome = null, telefone = null, data = null, email = null;

        entradaDeDados(nome, telefone, data, email);

    }

    public static void saidaDeDados(Pessoa pessoa) {
        System.out.println("***Dados do contato***");
        System.out.println("Nome:" + pessoa.getNome());
        System.out.println("Email:" + pessoa.getEmail());
        System.out.println("Telefone:" + pessoa.getTelefone());
        System.out.println("Data Nascimento:" + pessoa.getData());
    }

}
