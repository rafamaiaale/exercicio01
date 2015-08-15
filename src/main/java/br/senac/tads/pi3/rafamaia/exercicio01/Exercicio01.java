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

    static void entradaDeDados(String nome, String telefone, String data, String email) {
        System.out.println("*** Cadastro de Contatos ***");

        System.out.print("Digite o nome do Contato: ");
        nome = leitor.nextLine();

        System.out.print("Digite o telefone do Contato: ");
        telefone = leitor.nextLine();

        System.out.print("Digite a data de nascimento do Contato: ");
        data = leitor.nextLine();

        System.out.print("Digite o Email do Contato: ");
        email = leitor.nextLine();

        System.out.println("*** Contato salvo ***");

    }

    public static void main(String[] args) {
        String nome = null, telefone = null, data = null, email = null;

        entradaDeDados(nome, telefone, data, email);

    }

    public static void saidaDeDados() {
        
    }

}
