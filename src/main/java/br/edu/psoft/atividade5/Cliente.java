package br.edu.psoft.atividade5;

import java.util.Objects;

public final class Cliente {
    private final String nome;

    public Cliente(String nome) {
        this.nome = Objects.requireNonNull(nome, "nome não pode ser nulo").trim();
        if (this.nome.isEmpty()) {
            throw new IllegalArgumentException("nome não pode ser vazio");
        }
    }

    public String getNome() {
        return nome;
    }
}
