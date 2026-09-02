package com.callcenter.structure;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PilhaDeHistoricoTest {
    @Test
    void pilhaComecaVazia() {
        PilhaDeHistorico<String> pilha = new PilhaDeHistorico<>();

        assertTrue(pilha.estaVazia());
        assertEquals(0, pilha.getTamanho());
    }
    @Test
    void empilharAdicionarUmElemento() {
        PilhaDeHistorico<String> pilha = new PilhaDeHistorico<>();

        pilha.empilhar("Cliente A");

        assertFalse(pilha.estaVazia());
        assertEquals(1, pilha.getTamanho());
    }

    @Test
    void desempilharRespeitaOrdemLIFO() {
        PilhaDeHistorico<String> pilha = new PilhaDeHistorico<>();
        pilha.empilhar("Cliente A");
        pilha.empilhar("Cliente B");

        String ultimo = pilha.desempilhar();
        assertEquals("Cliente B", ultimo);
    }


}

