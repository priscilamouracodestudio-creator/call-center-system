package com.callcenter.strategy;


import com.callcenter.model.Ficha;
import com.callcenter.structure.FilaDeEspera;

public class EstrategiaFIFO implements EstrategiaDeAtendimento{
    @Override
    public Ficha escolherProximo(FilaDeEspera<Ficha> fila) {
        return fila.chamarProximo();

    }
}
