package ds.service;


public interface AuthService {
    boolean login(String email,String password);
    boolean register(String username,String password,String email);



}
