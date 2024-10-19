package zad1;

import java.io.Serializable;

public class SensitiveData implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private transient String pesel;
    private transient String password;

    public SensitiveData(String name, String pesel, String password) {
        this.name = name;
        this.pesel = pesel;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPesel() {
        return pesel;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", pesel='" + pesel + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
