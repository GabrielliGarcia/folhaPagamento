
package folhapagamento;

public class Funcionario extends Pessoa {
    
  
    private double salario;
    private double horasTrab;
    private String regime;
    
    public Funcionario(String nomeFuncionario, String cpf, double salario, double horasTrab, String regime){
        super(nomeFuncionario, cpf);
        this.salario = salario;
        this.horasTrab = horasTrab;
        this.regime = regime;
    }
    
    public double getSalario() {
        return salario;
    }

    
    public void setSalario(double salario) {
        this.salario = salario;
    }

    
    public double getHorasTrab() {
        return horasTrab;
    }

    
    public void setHorasTrab(double horasTrab) {
        this.horasTrab = horasTrab;
    }

   
    public String getRegime() {
        return regime;
    }

   
    public void setRegime(String regime) {
        this.regime = regime;
    }
   
    public void verificaRegimeTrabalhista(Funcionario funcionario){
        if(funcionario.getRegime().contains("CLT")){
            realizaCalcCLT(funcionario);
        } else if(funcionario.getRegime().contains("PJ")){
            realizaCalcPJ(funcionario);
        } else if(funcionario.getRegime().contains("COOPERADO")){
            realizaCalcCooperado(funcionario);
        }
    }

    private void realizaCalcCLT(Funcionario funcionario) {
        if(funcionario.getHorasTrab() > 160){
            
            double horasExtras = funcionario.getHorasTrab() - 160;
            double valorHora = funcionario.getSalario()/160;
            double saldo = horasExtras * valorHora;
            double pagamentoTotal = funcionario.getSalario() + saldo;
            
            funcionario.setSalario(pagamentoTotal);           
        }
        
        double inss = calculaInss(funcionario);
        
        double valorLiquido = funcionario.getSalario() - inss;
        funcionario.setSalario(valorLiquido);
        
        double irrf = calculaIrrf(funcionario, inss);
        valorLiquido = funcionario.getSalario() - irrf;
        funcionario.setSalario(valorLiquido);
    }

    private double calculaInss(Funcionario funcionario) {
        double inss = 0.0;
        
        if(funcionario.getRegime().contains("CLT")){
            if(funcionario.getSalario() <= 1412){
                inss = funcionario.getSalario() * 0.075;
            } else if (funcionario.getSalario() >= 1412.01 && funcionario.getSalario() <= 2666.68) {
                inss = funcionario.getSalario() * 0.09;
            } else if (funcionario.getSalario() >= 2666.69 && funcionario.getSalario() <= 4000.03) {
                inss = funcionario.getSalario() * 0.12;
            }else {
                inss = funcionario.getSalario() * 0.14;
                if (inss >= 908.86) {
                    inss = 908.86;
                }
            }
        }
        
        if(funcionario.getRegime().contains("COOPERADO")){
            if(funcionario.getSalario() <= 1412){
                inss = funcionario.getSalario() * 0.075;
            } else if (funcionario.getSalario() >= 1412.01 && funcionario.getSalario() <= 2666.68) {
                inss = funcionario.getSalario() * 0.09;
            } else if (funcionario.getSalario() >= 2666.69 && funcionario.getSalario() <= 4000.03) {
                inss = funcionario.getSalario() * 0.12;
            }else {
                inss = funcionario.getSalario() * 0.14;
                if (inss >= 908.86) {
                    inss = 908.86;
                }
            }
        }
        return inss;
    }

    private double calculaIrrf(Funcionario funcionario, double inss) {
        double irrf = 0.0;
        double deducao = 0.0;
        double desconto = 0.0;
        
            if(funcionario.getSalario() >= 2259.21 && funcionario.getSalario() <= 2826.65){
                deducao = 158.4;
                irrf = funcionario.getSalario() * 0.075;
                desconto = irrf - deducao;
            } else if (funcionario.getSalario() >= 2826.66 && funcionario.getSalario() <= 3751.05) {
                deducao = 370.4;
                irrf = funcionario.getSalario() * 0.15;
                desconto = irrf - deducao;
            } else if (funcionario.getSalario() >= 3751.06 && funcionario.getSalario() <= 4664.68) {
                deducao = 651.73;
                irrf = funcionario.getSalario() * 0.225;
                desconto = irrf - deducao;
            }else if (funcionario.getSalario() >= 4664.69){
                deducao = 884.96;
                irrf = funcionario.getSalario() * 0.275;
                desconto = irrf - deducao;
            }    
        
        return desconto;
    }
   
    
    private void realizaCalcPJ(Funcionario funcionario) {
        double pagamento = funcionario.getSalario() - 76.60; //76,60 é o valor da MEI a ser pago
       
        funcionario.setSalario(pagamento);
       
    }

    private void realizaCalcCooperado(Funcionario funcionario) {
         double inss = calculaInss(funcionario);
         double valorLiquido = funcionario.getSalario() - inss;
         
         funcionario.setSalario(valorLiquido);
    }
    
}
