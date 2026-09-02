package com.callcenter.structure;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FilaDeEsperaTest {

    @Test
    void filaComecaVazia() {
        FilaDeEspera<String> fila = new FilaDeEspera<>();

        assertTrue(fila.estaVazia());
        assertEquals(0, fila.getTamanho());
    }

    @Test
    void enfileirarAdicionaUmElemento() {
        FilaDeEspera<String> fila = new FilaDeEspera<>();

        fila.enfileirar("Cliente A");

        assertFalse(fila.estaVazia());
        assertEquals(1, fila.getTamanho());
    }

    @Test
    void chamarProximoRespeitaOrdemDeChegada() {
        FilaDeEspera<String> fila = new FilaDeEspera<>();
        fila.enfileirar("Cliente A");
        fila.enfileirar("Cliente B");

        String primeiro = fila.chamarProximo();

        assertEquals("Cliente A", primeiro);
    }


}
