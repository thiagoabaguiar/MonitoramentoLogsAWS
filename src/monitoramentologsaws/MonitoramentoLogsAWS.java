package monitoramentologsaws;

import java.util.*;

public class MonitoramentoLogsAWS {

    public static void main(String[] args) {

        System.out.println("Qtos logs?");
        Scanner scanner = new Scanner(System.in);
        int quantidadeLogs = scanner.nextInt();
        scanner.nextLine();

        Map<String, Long> eventosPorServico = new HashMap<>();
        List<String> listaLogs = new ArrayList<>();
        System.out.println("Entre com os logs:");

        for (int i = 0; i < quantidadeLogs; i++) {
            String[] parts = scanner.nextLine().split(",");
            String servico = parts[1];
            listaLogs.add(servico);

            if (eventosPorServico.keySet().contains(servico)) {
                eventosPorServico.put(servico, eventosPorServico.get(servico) + 1);
            } else {
                eventosPorServico.put(servico, 1l);
            }
        }

        long qtdLogMaiorIncidencia = eventosPorServico.values()
                .stream()
                .reduce(0L, (n1, n2) -> {
                    if (n1 > n2) {
                        return n1;
                    }
                    return n2;
                }
                );

        List<String> servicoLogMaiorIncidencia = eventosPorServico.keySet()
                .stream()
                .filter(k -> eventosPorServico.get(k) == qtdLogMaiorIncidencia)
                .toList();

        long qtdLogMenorIncidencia = eventosPorServico.values()
                .stream()
                .reduce((long) listaLogs.size(), (n1, n2) -> {
                    if (n1 < n2) {
                        return n1;
                    }
                    return n2;
                }
                );

        List<String> servicoLogMenorIncidencia = eventosPorServico.keySet()
                .stream()
                .filter(k -> eventosPorServico.get(k) == qtdLogMenorIncidencia)
                .toList();

        System.out.println("Eventos por servico: ");
        for (Map.Entry<String, Long> keyValuePair : eventosPorServico.entrySet()) {
            System.out.println(keyValuePair.getKey() + ":" + keyValuePair.getValue());
        }        
        System.out.println("Maior:" + servicoLogMaiorIncidencia.get(0));
        System.out.println("Menor:" + servicoLogMenorIncidencia.get(0));

    }
}
