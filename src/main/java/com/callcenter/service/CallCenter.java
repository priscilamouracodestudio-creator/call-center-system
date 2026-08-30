package com.callcenter.service;

import com.callcenter.model.Ficha;
import com.callcenter.model.NivelPrioridade;
import com.callcenter.structure.FilaDeEspera;
import com.callcenter.structure.PilhaDeHistorico;
import com.callcenter.strategy.EstrategiaDeAtendimento;

import java.time.LocalDateTime;

public class CallCenter {
    private final FilaDeEspera<Ficha> filaDeEspera;
    private final PilhaDeHistorico<Ficha> pilhaDeHistorico;
    private final EstrategiaDeAtendimento estrategia;
    private Ficha atendimentoAtual;
    private int proximoId;

    public CallCenter(EstrategiaDeAtendimento estrategia) {
        this.filaDeEspera = new FilaDeEspera<>();
        this.pilhaDeHistorico = new PilhaDeHistorico<>();
        this.estrategia = estrategia;
        this.atendimentoAtual = null;
        this.proximoId = 1;
    }


    public Ficha criarFicha(String nomeCliente, NivelPrioridade prioridade) {
        Ficha novaFicha = new Ficha(proximoId, nomeCliente, prioridade, LocalDateTime.now());
        proximoId++;
        filaDeEspera.enfileirar(novaFicha);
        return novaFicha;
    }

    public Ficha chamarProximo() {
        if (atendimentoAtual != null) {
            System.out.println("Já existe um atendimento em andamento. Encerre-o antes de chamar o próximo.");
            return null;
        }

        Ficha proxima = estrategia.escolherProximo(filaDeEspera);

        if (proxima == null) {
            System.out.println("Não há clientes esperando.");
            return null;
        }

        proxima.iniciarAtendimento();
        atendimentoAtual = proxima;
        return proxima;
    }

    public Ficha buscarPorId(Integer id){
        if (atendimentoAtual != null && atendimentoAtual.getId().equals(id)){
            return atendimentoAtual;
        }

        for (Ficha ficha : pilhaDeHistorico.listarTodos()){
            if (ficha.getId().equals(id)){
                return ficha;
            }
        }

        return null;
    }

    public Ficha encerrarAtendimento() {
        if (atendimentoAtual == null) {
            System.out.println("Não há atendimento em andamento para encerrar.");
            return null;
        }

        atendimentoAtual.encerrarAtendimento();
        Ficha fichaEncerrada = atendimentoAtual;
        pilhaDeHistorico.empilhar(fichaEncerrada);
        atendimentoAtual = null;
        return fichaEncerrada;
    }

    public Ficha desfazerUltimoEncerramento(){
        if(pilhaDeHistorico.estaVazia()){
            System.out.println("Não há encerramentos para desfazer.");
            return null;
        }

        if (atendimentoAtual != null){
            System.out.println("Um atendimento está em andamento. Encerre-o antes de desfazer.");
            return null;
        }

        Ficha fichaRecuperada = pilhaDeHistorico.desempilhar();
        atendimentoAtual = fichaRecuperada;
        return fichaRecuperada;
    }
}