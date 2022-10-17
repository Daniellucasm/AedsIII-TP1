import java.io.*;
import java.util.Scanner;

class Conta {
    int idConta;
    String nomePessoa;
    String[] email;
    String nomeUsuario;
    String senha;
    String cpf;
    String cidade;
    int transferenciasRealizadas;
    float saldoConta;
    int qtdEmail = 0;

    Conta() {
    }

    // Metodos Sets
    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public void setEmail(String email, int pos, int qtd) {
        if (pos == 0)
            this.email = new String[qtd];
        this.email[pos] = email;
        qtdEmail = qtd;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCpf(String cpf) {
        if (cpf.length() == 11) {
            this.cpf = cpf;
        } else {
            System.out.println("ERRO");
        }
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setTransferenciasRealizadas(int transferenciasRealizadas) {
        this.transferenciasRealizadas = transferenciasRealizadas;
    }

    public void setSaldoConta(float saldoConta) {
        this.saldoConta = saldoConta;
    }

    // Metodos Gets
    public int getIdConta() {
        return idConta;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public String getEmail(int pos) {
        return email[pos];
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCidade() {
        return cidade;
    }

    public int getTransferenciasRealizadas() {
        return transferenciasRealizadas;
    }

    public float getSaldoConta() {
        return saldoConta;
    }

    // verifica CPF
    public boolean verificaCpf(String cpf) {
        boolean result = false;
        if (cpf.length() == 11) {
            result = cpf.matches("-?\\d+");
        }
        return result;
    }

    // Metodo de criar conta
    public void criarConta(int i) {
        Scanner entrada = new Scanner(System.in);
        // melhorar futuramente
        setIdConta(i);

        // adicionando nome
        System.out.print("Insira seu nome: ");
        String aux = entrada.nextLine();
        setNomePessoa(aux);
        // System.out.println(getNomePessoa());

        // adicionando email
        System.out.println("Quantos emails você deseja registrar? ");
        int qtd = entrada.nextInt();
        entrada.nextLine();
        this.qtdEmail = qtd;
        int x = 0;
        while (x < qtd) {
            System.out.print("Insira o (" + (x + 1) + "º) email: ");
            String email = entrada.nextLine();
            setEmail(email, x, qtd);
            x++;
        }

        // adicionando nomeUsuario
        System.out.print("Insira o seu usuario: ");
        aux = entrada.nextLine();
        setNomeUsuario(aux);
        // System.out.println(getNomeUsuario());

        // adicionando senha
        System.out.print("Insira sua senha: ");
        aux = entrada.nextLine();
        setSenha(aux);
        // System.out.println(getSenha());

        // adicionando cpf
        System.out.print("Insira seu cpf:");
        aux = entrada.nextLine();
        while (!verificaCpf(aux)) {
            System.out.println("ERRO!!!!! INSIRA NOVAMENTE: ");
            aux = entrada.next();
        }
        setCpf(aux);
        // System.out.println(getCpf());

        // adicionando cidade
        System.out.print("Insira sua cidade: ");
        aux = entrada.nextLine();
        setCidade(aux);
        // System.out.println(getCidade());

        // adicionando transferencia realizadas
        setTransferenciasRealizadas(0);

        // adicionando SaldoConta
        System.out.print("Insira seu saldo: ");
        setSaldoConta(entrada.nextFloat());
        // System.out.println(getSaldoConta());
    }

    // Metodo para realizar transferencia
    public void realizarTrans(Conta user) {
        Scanner entrada = new Scanner(System.in);

        // recebendo a quantia que deseja enviar
        System.out.println("Informe o valor");
        float val = entrada.nextFloat();
        if(val <= saldoConta){
            saldoConta = saldoConta - val;
            user.setSaldoConta(user.getSaldoConta()+val);
            System.out.println("Transferencia efetuada!!!");
        } else {
            System.out.println("ERRO");
        }
    }

}

class Registro {
    Registro() {
    }

    // Metodo para salvar todos os dados no arquivo
    public void salvar(BufferedWriter bw, Conta conta) {
        try {
            // salvando todos os dados da conta no arquivo
            bw.write(String.valueOf(conta.getIdConta()));
            bw.write(String.valueOf(conta.getNomePessoa().length()));
            bw.write(conta.getNomePessoa());
            bw.write(String.valueOf(conta.qtdEmail));
            for (int i = 0; i < conta.qtdEmail; i++) {
                bw.write(String.valueOf(conta.getEmail(i).length()));
                bw.write(conta.getEmail(i));
            }
            bw.write(String.valueOf(conta.getNomeUsuario().length()));
            bw.write(conta.getNomeUsuario());
            bw.write(String.valueOf(conta.getSenha().length()));
            bw.write(conta.getSenha());
            bw.write(conta.getCpf());
            bw.write(String.valueOf(conta.getCidade().length()));
            bw.write(conta.getCidade());
            bw.write(String.valueOf(conta.getTransferenciasRealizadas()));
            bw.write(String.valueOf(conta.getSaldoConta()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo para ler os dados de um arquivo
    public void ler(Conta aux) {

        System.out.println("O Usuario que está buscando é: ");
        System.out.println("ID: " + aux.getIdConta() + " Nome: " + aux.getNomePessoa()
                + " CPF: " + aux.getCpf() + " Cidade: " + aux.getCidade());
        System.out.println("SALDO DA CONTA: " + aux.getSaldoConta());
    }

    // Metodo para atualizar os dados do arquivo
    public void atualizar(Conta aux) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("------- Escolha qual alteração deseja realizar -------");
        System.out.println("(1) ALTERAR USUARIO");
        System.out.println("(2) ALTERAR NOME");
        System.out.println("(3) ALTERAR SENHA");
        System.out.println("(4) ALTERAR CIDADE");
        int escolha = entrada.nextInt();
        entrada.nextLine();
        if (escolha == 1) {
            System.out.print("Digite o novo USUARIO: ");
            String user = entrada.nextLine();
            aux.setNomeUsuario(user);
        } else if (escolha == 2) {
            System.out.print("Digite o novo NOME: ");
            String user = entrada.nextLine();
            aux.setNomePessoa(user);
        } else if (escolha == 3) {
            System.out.print("Digite a nova SENHA: ");
            String user = entrada.nextLine();
            aux.setSenha(user);
        } else if (escolha == 4) {
            System.out.print("Digite a nova CIDADE: ");
            String user = entrada.nextLine();
            aux.setCidade(user);
        } else {
            System.out.println("ERRRO!!!!!");
        }
    }

    // Metodo para deletar os dados do arquivo
    public void deletar(Conta aux) {
        aux.setIdConta(-1);
    }

    // Metodo para ordenar os dados do arquivo
    public void ordenar() {

    }

}

public class main {
    // Função para imprimir o Menu Inicial
    public static void menuIni() {
        System.out.println("--------------------- BEM VINDO AO MENU ---------------------");
        System.out.println("(1) Criar um conta bancária");
        System.out.println("(2) Realizar uma transferência");
        System.out.println("(3) Ler um registro");
        System.out.println("(4) Atualizar um registro");
        System.out.println("(5) Deletar um registro");
        System.out.println("(6) Realizar a ordenação do arquivo");
        System.out.println("(0) Sair");
        System.out.print("Escolha uma das opções: ");
    }
    //teste se existe ID
    public static boolean testeId(Conta[] conta, int tam, int id){
        boolean result = false;
        for(int i = 0; i <= tam; i++){
            if(conta[i].getIdConta() == id){
                result = true;
                i = tam;
            } else {
                result = false;
            }
        }
        return result;
    }

    public static void main(String args[]) {
        Scanner entrada = new Scanner(System.in);
        // imprimindo tela menu
        menuIni();
        // criando variaveis auxiliares
        String arquivo = "arq1.txt";
        Conta[] conta = new Conta[100];
        Registro registro = new Registro();
        int i = 0;
        int escolha = entrada.nextInt();

        try {
            // iniciando um arquivo para passar como parametro
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo)));
            while (escolha != 0) {
                switch (escolha) {
                    case 0:
                        // Sair MENU
                        break;
                    case 1:
                        // Criar Conta
                        conta[i] = new Conta();
                        conta[i].criarConta(i);
                        registro.salvar(bw, conta[i]);
                        i++;
                        break;
                    case 2:
                        // Realizar Transferencia
                        System.out.println("Insira o Usuario que irá fazer a transferencia: ");
                        int id = entrada.nextInt();
                        if(testeId(conta, i, id)){
                            System.out.println("Insira o Usuario que irá receber a transferencia: ");
                            int idTrans = entrada.nextInt();
                            Conta user = conta[idTrans];
                            conta[id].realizarTrans(user);
                        } else {
                            System.out.println("ERRO");
                        }
                        break;
                    case 3:
                        // Ler um Registro
                        System.out.print("Insira o ID da Conta: ");
                        id = entrada.nextInt();
                        if(testeId(conta, i, id)){
                            registro.ler(conta[id]);
                        } else {
                            System.out.println("ERRO");
                        } 
                        break;
                    case 4:
                        // Atualizar um Registro
                        System.out.print("Insira o ID da Conta: ");
                        id = entrada.nextInt();
                        if(testeId(conta, i, id)){
                            registro.atualizar(conta[id]);
                        } else {
                            System.out.println("ERRO");
                        }
                        break;
                    case 5:
                        // Deletar um Registro
                        System.out.print("Insira o ID da Conta: ");
                        id = entrada.nextInt();
                        if(testeId(conta, i, id)){
                            registro.deletar(conta[id]);
                        } else {
                            System.out.println("ERRO");
                        }
                        break;
                    case 6:
                        // Ordenar
                        registro.ordenar();
                        break;
                    default:
                        System.out.println("ERRO!!!");
                        System.out.println("Escolha novamente");
                }
                // Menu de escolhas
                menuIni();
                escolha = entrada.nextInt();
            }
            bw.close();
        } catch (Exception erro) {
            System.out.println(erro.getMessage());
        }

    }
}