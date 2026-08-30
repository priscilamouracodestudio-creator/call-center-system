package com.callcenter;
import com.callcenter.model.Ficha;
import com.callcenter.model.NivelPrioridade;
import com.callcenter.service.CallCenter;
import com.callcenter.strategy.EstrategiaDeAtendimento;
import com.callcenter.strategy.EstrategiaComPrioridade;
import com.callcenter.strategy.EstrategiaFIFO;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha a estratégia de atendimento:");
        System.out.println("1. Ordem de chegada (FIFO)");
        System.out.println("2. Prioridade + tempo de espera");
        int escolhaEstrategia = lerOpcaoValida(scanner);
        scanner.nextLine();

        EstrategiaDeAtendimento estrategia;
        if (escolhaEstrategia == 2) {
            estrategia = new EstrategiaComPrioridade();
        } else {
            estrategia = new EstrategiaFIFO();
        }

        CallCenter callCenter = new CallCenter(estrategia);

        int option = -1;

        while (option != 6){
            System.out.println("1. Nova ficha");
            System.out.println("2. Iniciar atendimento");
            System.out.println("3. Encerrar Atendimento");
            System.out.println("4. Consultar dados de uma ficha");
            System.out.println("5. Desfazer último atendimento");
            System.out.println("6. Sair");

            option = lerOpcaoValida(scanner);
            scanner.nextLine();

            switch (option){
                case 1: {
                    System.out.println("Digite o nome do cliente:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o número da prioridade do cliente: 1. Normal, 2. Prioritário e 3. Urgente");
                    int prioridade = lerOpcaoValida(scanner);

                    NivelPrioridade nivelEscolhido;

                    switch (prioridade) {
                        case 1:
                            nivelEscolhido = NivelPrioridade.NORMAL;
                            break;

                        case 2:
                            nivelEscolhido = NivelPrioridade.PRIORITARIO;
                            break;

                        case 3:
                            nivelEscolhido = NivelPrioridade.URGENTE;
                            break;

                        default:
                            System.out.println("Número digitado inválido. Prioridade normal definida.");
                            nivelEscolhido = NivelPrioridade.NORMAL;
                            break;
                    }

                    Ficha novaFicha = callCenter.criarFicha(nome, nivelEscolhido);
                    System.out.println("Ficha criada com sucesso! ID: " + novaFicha.getId());
                    break;
                }

                case 2: {
                    Ficha fichaEncontrada = callCenter.chamarProximo();

                    if (fichaEncontrada != null) {
                        System.out.println("Atendendo: " + fichaEncontrada.getNomeCliente());

                    } else {
                        System.out.println("Ficha inexistente.");
                    }

                    break;

                }

                case 3: {
                    Ficha fichaEncontrada = callCenter.encerrarAtendimento();

                    if (fichaEncontrada != null) {
                        System.out.println("Atendimento encerrado: " + fichaEncontrada.getNomeCliente());
                    }
                    break;
                }

                case 4: {
                    System.out.println("Digite o id da ficha:");
                    int idBusca = lerOpcaoValida(scanner);
                    scanner.nextLine();

                    Ficha fichaEncontrada = callCenter.buscarPorId(idBusca);

                    if (fichaEncontrada != null) {
                        System.out.println("Ficha encontrada: " + fichaEncontrada.getNomeCliente());
                    }
                    else {
                        System.out.println("Ficha não encontrada.");
                    }

                    break;
                }

                case 5: {
                    Ficha fichaRecuperada = callCenter.desfazerUltimoEncerramento();

                    if (fichaRecuperada != null) {
                        System.out.println("Atendimento restaurado: " + fichaRecuperada.getNomeCliente());
                    }
                    break;
                }

            }

        }

    }
    private static int lerOpcaoValida(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, digite um número válido.");
            scanner.next();
        }
        return scanner.nextInt();
    }

}