package br.com.fiap.tds.tdspa.javaadv.blogBackend.services;

import br.com.fiap.tds.tdspa.javaadv.blogBackend.datasource.repositories.UserRepository;
import br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

   @Override
   public List<User> findAll(){
       return userRepository.findAll();
   }

   @Override
   public Optional<User> findById(UUID id){
       return this.userRepository.findById(id);
   }

   @Override
   public User persist(User user){
       return this.userRepository.save(user);
   }

   @Override
   public void delete(User user){
        this.userRepository.delete(user);
   }

   @Override
   public void deleteById(UUID id){
       this.userRepository.deleteById(id);
   }

   @Override
   public Page<User> findAll(Pageable pageable){
       return this.userRepository.findAll(pageable);
   }

   @Override
   public boolean existsById(UUID id){
       return this.userRepository.existsById(id);
   }

    @Override
    public boolean existsById(User user){
        return this.existsById(user.getId());
    }

    @Override
    public Optional<User> partialUpdate(UUID id, Map<String, Object> updates){
       Optional<User> userOptional = this.userRepository.findById(id);

       if( userOptional.isPresent() ){
           User user = userOptional.get();
           updates.forEach((key, value) -> {
               switch(key){
                   case "name":
                       user.setName((String) value);
                       break;
                   case "email":
                       user.setEmail((String) value);
                       break;
                   case "password":
                       user.setPassword((String) value);
                       break;
               }
           });
           return Optional.of(this.userRepository.save(user));
       }
       return Optional.empty();
    }

}
