import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        //3.1 Inserção de funcionarios
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 Remoção de João da Lista
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        //3.5 Agrupar funcionários por função em um Map
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario f : funcionarios) {
            String cargo = f.getFuncao();
            if (!funcionariosPorFuncao.containsKey(cargo)) {
                funcionariosPorFuncao.put(cargo, new ArrayList<>());
            }
            funcionariosPorFuncao.get(cargo).add(f);
        }

        // Imprimir todos os funcionários com todas suas imformações
        for (Funcionario f : funcionarios) {
            System.out.println("Nome: " + f.getNome() + ", Data de Nascimento: " + f.getData_nascimento() + ", Salário: " + f.getSalario() + ", Cargo: " + f.getFuncao());
        }

        // 3.3 Formatação de Data e também de valor
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));

        //3.4 Recebem 10% de aumento de salário
        for (Funcionario f : funcionarios){
            String dataFormatada = f.getData_nascimento().format(dateFormatter);
            String salarioFormatado = numberFormat.format(f.getSalario());

            // Calcular aumento de salário de 10%
            BigDecimal aumentoSalario = f.getSalario().multiply(new BigDecimal("0.10"));
            BigDecimal novoSalario = f.getSalario().add(aumentoSalario);


            System.out.println("Nome: " + f.getNome() + ", Data de Nascimento: " + dataFormatada +
                    ", Salário: " + salarioFormatado + ", Cargo: " + f.getFuncao() +
                    ", Novo Salário com aumento de 10%: " + numberFormat.format(novoSalario));

        }

        System.out.println("--------------------- Uso de ações com Map abaixo --------------------");

        // 3.9 Encontrar o funcionário com a maior idade
        Optional<Funcionario> funcionarioMaiorIdade = funcionarios.stream()
                .max(Comparator.comparing(f -> Period.between(f.getData_nascimento(), LocalDate.now()).getYears()));

        //3.9 Exibir o funcionário com a maior idade
        funcionarioMaiorIdade.ifPresent(f -> System.out.println("Funcionário com maior idade: Nome: " + f.getNome() + ", Idade: " +
                Period.between(f.getData_nascimento(), LocalDate.now()).getYears()));

        //3.10 Ordenar a lista de funcionários por nome em ordem alfabética
        List<Funcionario> funcionariosOrdenados = funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .toList();

        //3.10 Exibir a lista de funcionários em ordem alfabética
        System.out.println("Lista de funcionários em ordem alfabética:");
        for (Funcionario f : funcionariosOrdenados) {
            System.out.println("Nome: " + f.getNome());
        }
        //3.8 aniversariantes de outubro e dezembro
        List<Funcionario> aniversariantesOutubroDezembro = funcionarios.stream()
                .filter(f -> f.getData_nascimento().getMonthValue() == 10 || f.getData_nascimento().getMonthValue() == 12)
                .toList();
        System.out.println("Funcionários que fazem aniversário em outubro e dezembro:");
        //3.8 aniversariantes de outubro e dezembro
        for (Funcionario f : aniversariantesOutubroDezembro) {
            System.out.println("Nome: " + f.getNome() + ", Data de Nascimento: " + f.getData_nascimento().format(dateFormatter));
        }
        //3.6 Imprimir os funcionários agrupados por função
        System.out.println("Funcionários agrupados por função:");
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            String cargo = entry.getKey();
            List<Funcionario> funcionariosPorCargo = entry.getValue();

            System.out.println("Função: " + cargo);
            for (Funcionario f : funcionariosPorCargo) {
                System.out.println("\tNome: " + f.getNome() + ", Data de Nascimento: " + f.getData_nascimento().format(dateFormatter) + ", Salário: " + numberFormat.format(f.getSalario()));
            }
        }
        //3.12 Valor do salário mínimo
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        //3.12 Calcular quantos salários mínimos ganha cada funcionário
        Map<Funcionario, BigDecimal> salariosEmSalariosMinimos = new HashMap<>();
        for (Funcionario f : funcionarios) {
            BigDecimal salarioEmSalariosMinimos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            salariosEmSalariosMinimos.put(f, salarioEmSalariosMinimos);
        }

        //3.12 Exibir quantos salários mínimos ganha cada funcionário
        System.out.println("Quantidade de salários mínimos ganha cada funcionário:");
        for (Map.Entry<Funcionario, BigDecimal> entry : salariosEmSalariosMinimos.entrySet()) {
            Funcionario f = entry.getKey();
            BigDecimal salarioEmSalariosMinimos = entry.getValue();
            System.out.println("Nome: " + f.getNome() + ", Salários Mínimos: " + salarioEmSalariosMinimos);
        }
        //3.11 Exibir o total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //3.11 Exibir o total dos salários dos funcionários
        System.out.println("Total dos salários dos funcionários: " + numberFormat.format(totalSalarios));
    }
}