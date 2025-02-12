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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Funcionario> funcionarios = new ArrayList<>();

        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.

        funcionarios.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", formatter), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", formatter), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", formatter), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", formatter), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", formatter), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", formatter), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", formatter), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", formatter), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("24/05/2003", formatter), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", formatter), new BigDecimal("2799.93"), "Gerente"));

        imprimirFuncionarios(funcionarios);

        // 3.2 – Remover o funcionário “João” da lista.

        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
        System.out.println("\nLista de funcionários sem o 'João'");

        /* 3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
        • informação de data deve ser exibido no formato dd/mm/aaaa;
        • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.*/

        imprimirFuncionarios(funcionarios);

        // 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.

        System.out.println("\nLista de funcionários após o aumento");
        funcionarios.forEach(funcionario -> funcionario.setSalario(funcionario.getSalario().multiply(BigDecimal.valueOf(1.1))));
        imprimirFuncionarios(funcionarios);

        // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.



        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 – Imprimir os funcionários, agrupados por função.

        System.out.println("\nLista de funcionários por função");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\nFunção: " + funcao);

            imprimirFuncionarios(lista);
        });

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.

        System.out.println("\nLista de funcionários que fazem aniversário no mês 10 e 12");
        imprimirFuncionarios(aniversariantesMes(funcionarios,10));
        imprimirFuncionarios(aniversariantesMes(funcionarios,12));

        //  3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.

        System.out.println("\nLista de funcionários mais velhos");

        maisVelhos(funcionarios).forEach(f ->
                System.out.println("Funcionário mais velho: " + f.getNome() +
                        " - Idade: " + calcularIdade(f.getData_nascimento()) + " anos"));

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética.

        System.out.println("\nLista de funcionários em ordem alfabética");
        imprimirFuncionarios(funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome)).toList());

        // 3.11 – Imprimir o total dos salários dos funcionários

        imprimirTotalSalarios(funcionarios);

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário

        System.out.println("\nLista de funcionários por valor de salários mínimos ganhos");
        imprimirTabelaSalariosMinimos(funcionarios);


    }

    public static void imprimirFuncionarios(List<Funcionario> funcionarios) {

        if (funcionarios.isEmpty()) return;

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-15s %-15s%n", "Nome", "Data Nascimento", "Salário", "Função");
        System.out.println("-----------------------------------------------------------------------------");

        for (Funcionario funcionario : funcionarios) {
            System.out.printf("%-10s %-15s %-15s %-15s%n",
                    funcionario.getNome(),
                    funcionario.getData_nascimento().format(dateFormatter),
                    "R$ " + formatarMoeda(funcionario.getSalario()),
                    funcionario.getFuncao());
        }

        System.out.println("-----------------------------------------------------------------------------");
    }

    public static String formatarMoeda(BigDecimal valor) {
        NumberFormat formato = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        formato.setMinimumFractionDigits(2);
        formato.setMaximumFractionDigits(2);
        return formato.format(valor);
    }

    public static List<Funcionario> aniversariantesMes(List<Funcionario> funcionarios, int mesAniversario){
        return funcionarios.stream()
                .filter(funcionario -> {
                    int mes = funcionario.getData_nascimento().getMonthValue();
                    return mes == mesAniversario;
                })
                .toList();
    }

    public static List<Funcionario> maisVelhos(List<Funcionario> funcionarios) {
        LocalDate dataMaisAntiga = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getData_nascimento))
                .map(Funcionario::getData_nascimento)
                .orElse(null);

        return funcionarios.stream()
                .filter(f -> f.getData_nascimento().equals(dataMaisAntiga))
                .collect(Collectors.toList());
    }

    public static int calcularIdade(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)  // Extrai os salários
                .reduce(BigDecimal.ZERO, BigDecimal::add);  // Soma todos os salários

        System.out.println("Total dos salários: R$ " + totalSalarios);
    }

    public static void imprimirTabelaSalariosMinimos(List<Funcionario> funcionarios) {
        final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

        System.out.println("--------------------------------------");
        System.out.printf("%-15s %-15s%n", "Nome", "Salários Mínimos");
        System.out.println("--------------------------------------");

        for (Funcionario funcionario : funcionarios) {
            BigDecimal qtdSalariosMinimos = funcionario.getSalario()
                    .divide(SALARIO_MINIMO, 10, RoundingMode.FLOOR)
                    .setScale(1, RoundingMode.FLOOR);

            System.out.printf("%-15s %-15s%n", funcionario.getNome(), qtdSalariosMinimos);
        }

        System.out.println("--------------------------------------");
    }
}