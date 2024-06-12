import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {

    BigDecimal salario;
    String funcao;

    public Funcionario(String nome, LocalDate data_nascimento, BigDecimal salario, String funcao) {
        super(nome, data_nascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public Funcionario() {
    }

    public Funcionario(BigDecimal salario, String funcao) {
        this.salario = salario;
        this.funcao = funcao;
    }


    public Funcionario(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
