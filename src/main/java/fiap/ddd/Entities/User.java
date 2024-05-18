package fiap.ddd.Entities;

import java.util.*;

public class User extends _BaseEntity {
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String empresa;

    public User() {
    }

    public User(int id, String nome, String email, String senha, String telefone, String empresa) {
        super(id);
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.empresa = empresa;

    }

    public User(String nome, String email, String senha, String telefone, String empresa) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.empresa = empresa;

    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    // método para vwlidar informações de um user
    public Map<Boolean, ArrayList<String>> validate() {
        var errors = new ArrayList<String>();
        if (nome == null || nome.isBlank())
            errors.add("O campo nome não pode estar vazio");
        if (empresa == null || empresa.isBlank())
            errors.add("Empresa do usuário não pode estar vazio");
        if (email == null || email.isBlank())
            errors.add("O campo email não pode estar vazio");
        // verifica se o email contem '@' e '.com'
        if (!email.contains("@") || !email.contains(".com"))
            errors.add("Email inválido");
        if (senha == null || senha.isBlank())
            errors.add("Senha não pode estar vazia");
        if (telefone == null || telefone.isBlank())
            errors.add("Telefone não pode estar vazio");
        //verifica se o telefone tem entre 11 á 14 digitos variações possiveis: 11999999999 ou (11)99999-9999
        if (telefone.length() < 11 || telefone.length() > 14)
            errors.add("Telefone inválido");
        return !errors.isEmpty() ?
                Map.of(false, errors) :
                Map.of(true, errors);
    }

    //Método para criar um login
    public static User criarLogin(List<User> users) {

        var scanner = new Scanner(System.in);
        User novoUser = new User();
        System.out.println("Nome: ");
        novoUser.setNome(scanner.nextLine());
        System.out.println("Empresa: ");
        novoUser.setEmpresa(scanner.nextLine());
        System.out.println("Telefone ou celular: ");
        novoUser.setTelefone(scanner.nextLine());

        while (true) {
            System.out.println("Email: ");
            var email = scanner.nextLine();
            try {
                var achou = 0;
                if (!email.contains("@") || !email.contains(".com")) {
                    throw new RuntimeException();
                }

                if (!users.isEmpty()) {
                    for (User l : users) {
                        //Confere se o email já foi cadastrado
                        if (l.getEmail().equals(email)) {
                            System.out.println("Este email já foi cadastrado");
                            achou = 1;
                            break;
                        }
                    }
                }
                if (achou == 0) {
                    novoUser.setEmail(email);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Digite um endereço de email válido");
            }
        }
        System.out.println("Crie uma senha: ");
        novoUser.setSenha(scanner.nextLine());
        //Retorna o Entities.Login cadastrado
        return novoUser;
    }

    //Método que atualiza as informações de um User
    public void atualizarLogin(List<User> users) {
        var scanner = new Scanner(System.in);
        System.out.println("Nome: ");
        setNome(scanner.nextLine());
        System.out.println("Empresa: ");
        setEmpresa(scanner.nextLine());
        System.out.println("Telefone ou celular: ");
        setTelefone(scanner.nextLine());

        while (true) {
            System.out.println("Email: ");
            var emailInserido = scanner.nextLine();
            try {
                var achou = 0;
                if (!emailInserido.contains("@") || !emailInserido.contains(".com")) {
                    throw new RuntimeException();
                }

                if (!users.isEmpty()) {
                    for (User l : users) {
                        //Confere se o email já foi cadastrado e é diferente do antigo email
                        if (l.getEmail().equals(emailInserido) && !emailInserido.equals(this.email)) {
                            System.out.println("Este email já foi cadastrado");
                            achou = 1;
                            break;
                        }
                    }
                }
                if (achou == 0) {
                    setEmail(emailInserido);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Digite um endereço de email válido");
            }
        }
        System.out.println("Crie uma senha: ");
        setSenha(scanner.nextLine());

    }


    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id='" + getId() + "'")
                .add("nome='" + nome + "'")
                .add("email='" + email + "'")
                .add("senha='" + senha + "'")
                .add("telefone='" + telefone + "'")
                .add("empresa='" + empresa + "'")
                .toString();
    }
}
