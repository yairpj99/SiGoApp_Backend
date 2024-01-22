package org.casya.backend.SigoAppBackend.Service;

import java.util.List;
import java.util.Optional;

import org.casya.backend.SigoAppBackend.Mapper.UserMapper;
import org.casya.backend.SigoAppBackend.Models.User;
import org.casya.backend.SigoAppBackend.Repository.UserRepository;
import org.casya.backend.SigoAppBackend.dtos.CreateUserDto;
import org.casya.backend.SigoAppBackend.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    public List<UserDto> findAll(){
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public UserDto findById(Long userId) {
        Optional<User> optionalUser = repository.findById(userId);
    
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            UserDto userDto = mapper.toDto(user);
    
            return userDto;
        } else {
            return null;
        }
    }
    

    public UserDto sava(CreateUserDto data){
        User entity = repository.save(mapper.toModel(data));
        return mapper.toDto(entity);
    }

    public boolean deleteById(Long userId){
        try{
            repository.deleteById(userId);
            return true;
        }catch(EmptyResultDataAccessException e){
            return false;
        }catch (Exception e) {
            return false;
        }
    }

    public boolean changePassword(Long userId, String newPassword) {
        try {
            User user = repository.findById(userId).orElse(null);

            if (user != null) {
                // Cambiar la contraseña y guardar el usuario actualizado
                user.setPassword(newPassword);
                repository.save(user);
                return true;
            } else {
                return false; // El usuario no fue encontrado
            }
        } catch (Exception e) {
            return false; // Error al cambiar la contraseña
        }
    }

}
