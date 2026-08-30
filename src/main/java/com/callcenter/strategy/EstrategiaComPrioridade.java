package com.callcenter.strategy;
import com.callcenter.model.Ficha;
import com.callcenter.structure.FilaDeEspera;
import java.util.List;


public class EstrategiaComPrioridade implements EstrategiaDeAtendimento {

    @Override
    public Ficha escolherProximo(FilaDeEspera<Ficha> fila) {
        List<Ficha> todasAsFichas = fila.listarTodos();

        Ficha melhorFicha = null;
        long maiorScore = -1;

        for (Ficha ficha : todasAsFichas) {
            long score = calcularScore(ficha);

            if (score > maiorScore) {
                maiorScore = score;
                melhorFicha = ficha;
            }
        }

        return fila.remover(melhorFicha);
    }

    private long calcularScore(Ficha ficha) {
        long peso = ficha.getPrioridade().getPeso();
        long segundosEsperando = ficha.calcularTempoEspera().getSeconds();
        return peso * 1000L + segundosEsperando;
    }
}