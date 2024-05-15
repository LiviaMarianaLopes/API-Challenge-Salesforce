package fiap.ddd;

import fiap.ddd.Entities.Login;
import fiap.ddd.Entities.User;
import fiap.ddd.Repositories.UserRepositoryOrcl;

import java.io.IOException;
import java.util.Scanner;

public class MainAcessoCrud {
    public static void main(String[] args) throws IOException {
        menu();
    }
    public static void menu() {
        var opcao = 0;
        //Variável para armazenar os Logins cadastrados
        var users = new UserRepositoryOrcl();
        while (opcao != 4) {
            try {
                System.out.println("1.Cadastrar\n\r2.Listar todos os cadastros\r\n3.Fazer login\n\r4.Sair");
                var scanner = new Scanner(System.in);
                opcao = scanner.nextInt();
                scanner.nextLine();
                if (opcao == 1) {
                    //Cria um user chamando o metodo criar login
                    User login = new User().criarLogin(users.readAll());
                    //adiciona o novo User no banco de dados
                    users.create(login);
                } else if (opcao == 2) {
                    //Mostra no Console os users cadastrados
                    if (!users.readAll().isEmpty()) {
                        System.out.println("Logins cadastrados:\n" + users.readAll());
                    }
                    //Se não houver nenhum cadastro
                    else {
                        System.out.println("Nenhum cadastro foi realizado");
                    }
                } else if (opcao == 3) {
                    var continuar = 0;
                    if (!users.readAll().isEmpty()) {
                        boolean excluirConta = false;
                        while (!excluirConta ) {
                            //Pega as informações de login
                            System.out.println("Digite seu username ou email cadastrado: ");
                            String username = scanner.nextLine();
                            System.out.println("Digite a senha: ");
                            String senha = scanner.nextLine();
                            var login = new Login(username, senha);
                            //Verifica o login usando o método verificarLogin
                            if (users.verificarLogin(login)) {
                                //Salva em uma variável o login que foi logado
                                User userLogado = (users.saberLogin(login));
                                System.out.println("Entities.Login efetuado com sucesso");

                                while (true) {

                                    try {
                                        System.out.println("1. Voltar ao menu principal\n\r" +
                                                "2. Atualizar dados do Login\n\r" +
                                                "3. Deletar conta\n\r");
                                        var escolha = scanner.nextInt();
                                        scanner.nextLine();

                                        if (escolha == 1) {
                                            excluirConta = true;
                                            break;
                                        } else if (escolha == 2) {
                                            userLogado.atualizarLogin(users.readAll());
                                            users.update(userLogado.getId(),userLogado);
                                        } else if (escolha == 3) {
                                            while (true) {
                                                try {

                                                    System.out.println("Tem certeza que deseja excluir sua conta?\r\n" +
                                                            "1. Sim\n\r" +
                                                            "2. Não");
                                                    var resposta = scanner.nextInt();
                                                    scanner.nextLine();
                                                    if (resposta != 2) {
                                                        if (resposta == 1) {
                                                            excluirConta = true;
                                                            users.delete(userLogado.getId());

                                                            break;




                                                        }
                                                        throw new RuntimeException();
                                                    } else {
                                                        break;
                                                    }


                                                } catch (Exception e) {
                                                    System.out.println("Digite um número de 1 à 2");
                                                    scanner.nextLine();
                                                }
                                            }
                                        } else {
                                            throw new RuntimeException();
                                        }break;

                                    } catch (Exception e) {
                                        System.out.println("Digite um número de 1 à 3");
                                        scanner.nextLine();
                                    }

                                }
                            } else {
                                while (true) {
                                    try {
                                        System.out.println("Username ou senha inválido\n\r1. Voltar ao menu principal\n\rQualquer outro número para tentar novamente");
                                        continuar = scanner.nextInt();
                                        scanner.nextLine();
                                        if (continuar == 1) {
                                            excluirConta = true;
                                            break;
                                        } else {
                                            break;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Digite um número");
                                        scanner.nextLine();
                                    }
                                }


                            }
                        }
                    } else {
                        System.out.println("Nenhum cadastro registrado, crie um cadastro  para realizar o login");

                    }

                } else if (opcao == 4) {
                    System.out.println("Encerrando o programa");
                } else {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                System.out.println("Digite um número de 1 à 4");
            }

        }
    }




}
