package br.com.fiap.tds.tdspa.javaadv.blogBackend.resources;

import br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities.User;
import br.com.fiap.tds.tdspa.javaadv.blogBackend.resources.dtos.UserDTO;
import br.com.fiap.tds.tdspa.javaadv.blogBackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

@Tag(name="Users", description = "User management topics")
public class UserResource {


    private final UserService userService;

    @GetMapping("/")
    @Operation(summary = "List all users", description = "List all users in the system")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found"),
            @ApiResponse(responseCode = "404", description = "Not Found",
            content = @Content(schema = @Schema(implementation = UserDTO.class)))
    })
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
    @Operation(summary = "Find a user by ID", description = "Find a user by ID in the system")
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

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable UUID id, @Valid @RequestBody UserDTO userDTO){
        if( !this.userService.existsById(id))
            return ResponseEntity.notFound().build();

        User user = UserDTO.fromDTO(userDTO);
        user.setId(id);
        User updatedUSer = this.userService.persist(user);
        return ResponseEntity.ok(UserDTO.fromEntity(updatedUSer));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> partialUpdate(@PathVariable UUID id, @Valid @RequestBody Map<String, Object> updates){
        if( !this.userService.existsById(id))
            return ResponseEntity.notFound().build();
        Optional<User> user = this.userService.partialUpdate(id, updates);
        return ResponseEntity.ok(user.map(UserDTO::fromEntity).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        if( !this.userService.existsById(id))
            return ResponseEntity.notFound().build();
        this.userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<UserDTO>> findAllPaged(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size,
                                                       @RequestParam(defaultValue = "id") String orderBy,
                                                       @RequestParam(defaultValue = "asc") String direction) {
        Page<User> users = this.userService.findAllPaged(page, size, orderBy, direction);
        return ResponseEntity.ok(users.map(UserDTO::fromEntity));
    }

    @GetMapping("/paged-default")
    public ResponseEntity<Page<UserDTO>> findAllPaged(Pageable pageable) {
       return ResponseEntity.ok(this.userService.findAll(pageable)
               .map(UserDTO::fromEntity));
    }


   // http://loclahost:8080/api/v1/users?role=ADMIN
    @GetMapping("/queryByRole")
    public ResponseEntity<List<UserDTO>> fetchUserByRole( @RequestParam String role){
        return ResponseEntity.ok(this.userService.findByRole(role)
                .stream().map(UserDTO::fromEntity)
                .collect(Collectors.toList()));
    }








}
