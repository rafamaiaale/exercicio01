/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.rafamaia.exercicio01;

/**
 *
 * @author rafael.malexandre
 */
public class Pessoa {

    public Pessoa(String nome, String email, String telefone, String data) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.data = data;
    }
    
    public Pessoa() {
        
    }
    
    private String nome;
    private String email;
    private String telefone;
    private String data;

    
      
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
