package com.callcenter.structure;

import java.util.ArrayList;
import java.util.List;

public class FilaDeEspera<T> {
    private No<T> inicio;
    private No<T> fim;
    private int tamanho;

    public FilaDeEspera(){
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public void enfileirar(T dado) {
        No<T> novoNo = new No<>(dado);

        if(fim == null) {
            inicio = novoNo;
            fim = novoNo;
        }
        else {
            fim.setProximo(novoNo);
            fim = novoNo;
        }

        tamanho++;
    }

    public T chamarProximo() {
        T dado;

        if(inicio == null && fim == null){
            System.out.println("Não há atendimentos a fazer.");
            return null;
        }

        else {

            dado = inicio.getDado();
            inicio = inicio.getProximo();
                if (inicio == null){
                    fim = null;
                }

            tamanho--;
            return dado;
        }

    }

    public boolean estaVazia(){
        return inicio == null;
    }

    public int getTamanho() {
        return tamanho;
    }

    public T remover(T alvo) {
        No<T> anterior = null;
        No<T> atual = inicio;

        while (atual != null && !atual.getDado().equals(alvo)) {
            anterior = atual;
            atual = atual.getProximo();
        }


        if(atual == null) {
            System.out.println("Não há fichas.");
            return null;
        }

        if (anterior == null) {
            inicio = atual.getProximo();

        }

        else {
            anterior.setProximo(atual.getProximo());
        }

        if (atual == fim) {
            fim = anterior;
        }

        tamanho--;
        return  atual.getDado();
    }

    public List<T> listarTodos() {
        List<T> resultado = new ArrayList<>();
        No<T> atual = inicio;

        while (atual != null) {
            resultado.add(atual.getDado());
            atual = atual.getProximo();
        }

        return resultado;
    }

}
