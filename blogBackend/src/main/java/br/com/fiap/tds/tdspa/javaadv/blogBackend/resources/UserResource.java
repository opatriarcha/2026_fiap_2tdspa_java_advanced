package br.com.fiap.tds.tdspa.javaadv.blogBackend.resources;

import br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities.User;
import br.com.fiap.tds.tdspa.javaadv.blogBackend.resources.dtos.UserDTO;
import br.com.fiap.tds.tdspa.javaadv.blogBackend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserResource {


    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> findAll() {
//        List<User> users = this.userService.findAll();
//        List<UserDTO> resultset = new LinkedList<>();
//        for(User user : users ){
//            resultset.add(UserDTO.fromEntity(user));
//        }
//        return ResponseEntity.ok(resultset);

        return ResponseEntity.ok(this.userService.findAll()
                .stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList())
        );
    }

    //http://localhost:8080/api/v1/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findByID( @PathVariable("id") UUID id){
        return this.userService.findById(id)
                .map( user -> ResponseEntity.ok(UserDTO.fromEntity(user)))
                .orElseGet( () -> ResponseEntity.notFound().build());

    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> create( @Valid @RequestBody UserDTO userDto){
        User user  = UserDTO.fromDTO(userDto);
        User savedUser =  this.userService.persist(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(UserDTO.fromEntity(savedUser));
    }



}
