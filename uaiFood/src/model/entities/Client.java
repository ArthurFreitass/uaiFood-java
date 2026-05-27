package model.entities;

import model.exceptions.DomainException;

public class Client {

    private String name;
    private String cpf;

    public Client() {
    }

    public Client(String name, String cpf) {
        setName(name);
        setCpf(cpf);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new DomainException("Error: Name cannot be null!");
        }
        if (name.isEmpty()) {
            throw new DomainException("Error: Name cannot be empty!");
        }
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null) {
            throw new DomainException("Error: CPF cannot be null!");
        }

        int lenghtCpf = cpf.split("").length;

        if (lenghtCpf < 11 || lenghtCpf > 12) {
            throw new DomainException("Error: CPF not found!\nCheck if you enter with your CPF no Symbols");
        }
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Nome do cliente: " + name;
    }
}
