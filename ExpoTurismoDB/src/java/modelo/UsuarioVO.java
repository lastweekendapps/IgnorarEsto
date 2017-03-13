package modelo;

public class UsuarioVO {

    private int id;
    private char[] user;
    private char[] password;

    public UsuarioVO(int id, char[] user, char[] password) {
        this.id = id;
        this.user = user;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char[] getUser() {
        return user;
    }

    public char[] getPassword() {
        return password;
    }

    public void setUser(char[] user) {
        this.user = user;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

}
