package com.callcenter.structure;

import java.util.ArrayList;
import java.util.List;

public class PilhaDeHistorico<T> {
    private No<T> topo;
    private int tamanho;

    public PilhaDeHistorico(){
        this.topo = null;
        this.tamanho = 0;
    }

    public void empilhar(T dado) {
        No<T> novoNo = new No<>(dado);
        novoNo.setProximo(topo);
        topo = novoNo;
        tamanho++;
    }

    public T desempilhar() {
        T dado;

        if(topo == null) {
            System.out.println("Não há fichas.");
            return null;
        }

        else {
            dado = topo.getDado();
            topo = topo.getProximo();
            tamanho --;
            return dado;
        }
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public int getTamanho() {
        return tamanho;
    }

    public List<T> listarTodos() {
        List<T> resultado = new ArrayList<>();
        No<T> atual = topo;

        while (atual != null) {
            resultado.add(atual.getDado());
            atual = atual.getProximo();
        }

        return resultado;

    }

}
