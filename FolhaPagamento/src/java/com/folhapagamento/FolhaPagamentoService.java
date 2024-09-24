package com.folhapagamento;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;

@WebService
public class FolhaPagamentoService {
    
    @WebMethod
    public String calcularSalarioLiquido(
        @WebParam(name = "nomeFuncionario") String nomeFuncionario,
        @WebParam(name = "cpf") String cpf,
        @WebParam(name = "salario") double salario,
        @WebParam(name = "horasTrab") double horasTrab,
        @WebParam(name = "regime") String regime) {
        
        Funcionario funcionario = new Funcionario(nomeFuncionario, cpf, salario, horasTrab, regime);
        funcionario.verificaRegimeTrabalhista(funcionario);
        return "Salário: R$ " + String.format("%.2f", funcionario.getSalario());
    }
    
}
