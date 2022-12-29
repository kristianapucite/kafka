package com.example.services;

import com.example.mappers.UserMapper;
import com.example.models.UserModel;
import com.example.repositories.UserDAO;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService{

    @Autowired private UserRepository repository;
    @Autowired private UserMapper mapper;

//    private String kafkaTopicName;
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//   // @Autowired private KafkaTemplate<String, String> kafkaTemplate;
//
//    //@Bean
//    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> template, String info) {
//        return args -> {
//            template.send("testTopic", info);
//        };
//    }
//
//    private void sendMessage(String info) {
//
//
//    }


    @Override
    public UserModel getById(Long id) {
        Optional<UserDAO> dao = repository.findById(id);
        return dao.map(userDAO -> mapper.toModel(userDAO)).orElse(null);
    }

    @Override
    public UserModel saveUser(String name, String surname, String email, LocalDate dob) {
        UserModel userToSave = new UserModel(name, surname, email, dob);
        UserDAO dao = mapper.toDAO(userToSave);
        UserDAO saved = repository.save(dao);




        return mapper.toModel(saved);
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<UserDAO> daos = repository.findAll();
        return mapper.toModelList(daos);
    }

    @Override
    public UserModel updateUser(Long id, String name, String surname, String email, LocalDate dob) {
        Optional<UserDAO> daoToUpdate = repository.findById(id);
        if(daoToUpdate.isPresent()) {
            UserModel userToUpdate = mapper.toModel(daoToUpdate.get());
            userToUpdate.setName(name);
            userToUpdate.setSurname(surname);
            userToUpdate.setEmail(email);
            userToUpdate.setDob(dob);

            UserDAO dao = mapper.toDAO(userToUpdate);
            UserDAO saved = repository.save(dao);
            return mapper.toModel(saved);
        }
        else return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<UserDAO> daoToDelete = repository.findById(id);
        if(daoToDelete.isPresent()) {
            repository.delete(daoToDelete.get());
            return true;
        }
        return false;
    }
}
