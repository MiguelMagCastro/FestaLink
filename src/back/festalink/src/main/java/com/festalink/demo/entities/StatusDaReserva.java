package com.festalink.demo.entities;

public enum StatusDaReserva {
    Pendente,
    Confirmada,
    Cancelada;

    @Override
    public String toString() {
    switch (this) {
        case Pendente:
            return "Pendente";
        case Confirmada:
            return "Confirmada";
        case Cancelada:
            return "Cancelada";
        default:
            super.toString();
    }
    return null;
    }

}