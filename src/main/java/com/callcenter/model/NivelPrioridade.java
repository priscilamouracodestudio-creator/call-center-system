package com.callcenter.model;

public enum NivelPrioridade {
    NORMAL(1),
    PRIORITARIO(2),
    URGENTE(3);

    private final int peso;

    NivelPrioridade(int peso) {
        this.peso = peso;
    }

    public int getPeso() {
        return peso;
    }
}
