package com.callcenter.strategy;

import com.callcenter.model.Ficha;
import com.callcenter.structure.FilaDeEspera;

public interface EstrategiaDeAtendimento {
    Ficha escolherProximo(FilaDeEspera<Ficha> fila);

}
