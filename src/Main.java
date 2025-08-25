import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    private static final Locale PT_BR = new Locale("pt", "BR");
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat NUM_FMT;
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    static {
        NUM_FMT = NumberFormat.getNumberInstance(PT_BR);
        NUM_FMT.setMinimumFractionDigits(2);
        NUM_FMT.setMaximumFractionDigits(2);
        NUM_FMT.setGroupingUsed(true);
    }

    public static void main(String[] args) {
        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela.
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria",   LocalDate.of(2000, 10, 18), bd("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João",    LocalDate.of(1990,  5, 12), bd("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio",    LocalDate.of(1961,  5,  2), bd("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel",  LocalDate.of(1988, 10, 14), bd("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice",   LocalDate.of(1995,  1,  5), bd("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor",  LocalDate.of(1999, 11, 19), bd("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur",  LocalDate.of(1993,  3, 31), bd("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura",   LocalDate.of(1994,  7,  8), bd("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003,  5, 24), bd("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena",  LocalDate.of(1996,  9,  2), bd("2799.93"), "Gerente"));

        System.out.println("\n");

        // 3.2 – Remover o funcionário “João” da lista.
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        // 3.3 - Imprimir todos os funcionários com todas suas informações...
        System.out.println("Funcionários\n");
        imprimirFuncionarios(funcionarios);

        // 3.4 – Aumento de 10%
        funcionarios.forEach(f -> f.setSalario(
                f.getSalario().multiply(new BigDecimal("1.10")).setScale(2, RoundingMode.HALF_UP)
        ));

        System.out.println("\n \nFuncionários após aumento de 10% \n");
        imprimirFuncionarios(funcionarios);

        // 3.5 – Agrupar por função
        Map<String, List<Funcionario>> porFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao, LinkedHashMap::new, Collectors.toList()));

        // 3.6 – Imprimir agrupados por função
        System.out.println("\n \nFuncionários agrupados por função \n");
        porFuncao.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Ordenar funções alfabeticamente
                .forEach(entry -> {
                    String funcao = entry.getKey();
                    List<Funcionario> lista = entry.getValue();
                    System.out.printf("Função: %s (%d funcionário%s)%n",
                            funcao,
                            lista.size(),
                            lista.size() == 1 ? "" : "s");
                    imprimirFuncionarios(lista);
                    System.out.println("-".repeat(60)); // Separador após cada grupo
                    System.out.println();
                });

        // 3.8 – Aniversariantes dos meses 10 e 12
        System.out.println("\n \nFuncionários aniversariantes \n");
        imprimirFuncionarios(funcionarios.stream()
                .filter(f -> {
                    Month m = f.getDataNascimento().getMonth();
                    return m.getValue() == 10 || m.getValue() == 12;
                })
                .collect(Collectors.toList()));

        // 3.9 – Funcionário com maior idade (mais velho)
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento)) // data mais antiga
                .orElse(null);
        if (maisVelho != null) {
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println("\n \nFuncionários mais velho \n");
            System.out.printf("%-20s | Idade: %d anos%n", "Nome: " + maisVelho.getNome(), idade);
        }

        // 3.10 – Lista por ordem alfabética
        System.out.println("\n \nFuncionários por ordem alfabética \n");
        imprimirFuncionarios(funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList()));

        // 3.11 – Total dos salários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\n \nTotal dos salários \n");
        System.out.println(NUM_FMT.format(totalSalarios));

        // 3.12 – Quantos salários mínimos ganha cada funcionário
        System.out.println("\n \nSalários mínimos por funcionário \n");
        System.out.printf("%-20s | %s%n", "Nome", "Salários Mínimos");
        System.out.println("-".repeat(40));
        funcionarios.forEach(f -> {
            BigDecimal qtd = f.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
            System.out.printf("%-20s | %s%n", f.getNome(), NUM_FMT.format(qtd));
        });

        System.out.println("\n \n");
    }

    private static BigDecimal bd(String s) {
        return new BigDecimal(s);
    }

    private static void imprimirFuncionarios(List<Funcionario> lista) {
        // Cabeçalho da tabela
        System.out.printf("%-20s | %-12s | %-12s | %s%n", "Nome", "Nascimento", "Salário", "Função");
        System.out.println("-".repeat(60));
        lista.forEach(Main::imprimirFuncionario);
    }

    private static void imprimirFuncionario(Funcionario f) {
        String data = f.getDataNascimento().format(DATE_FMT);
        String salarioFmt = NUM_FMT.format(f.getSalario());
        System.out.printf("%-20s | %-12s | %-12s | %s%n", f.getNome(), data, salarioFmt, f.getFuncao());
    }
}
