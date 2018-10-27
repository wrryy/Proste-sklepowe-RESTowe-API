package pl.wrydz.sklep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wrydz.sklep.entity.User;
import pl.wrydz.sklep.repository.UserRepo;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public User getUser(long userId){
        return userRepo.getOne(userId);
    }
}
