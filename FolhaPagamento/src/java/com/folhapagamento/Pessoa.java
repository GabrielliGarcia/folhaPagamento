
package com.folhapagamento;


public class Pessoa {
    
    private String nomeFuncionario;
    private String cpf;
    
     public Pessoa( String nomeFuncionario, String cpf){
        this.nomeFuncionario = nomeFuncionario;
        this.cpf = cpf;
     }
     
    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

  
    public String getCpf() {
        return cpf;
    }

   
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}

