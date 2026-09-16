package br.com.fiap.tds.tdspa.javaadv.blogBackend.resources.dtos;


import br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString
public class UserDTO {
    @NotBlank(message="O ID é Obrigatório")
    private @Getter
    @Setter UUID id;

    @NotBlank(message="O Nome é obrigatório")
    @Size(max=60, message="O tamanho máximo do nome é 60 caractereres")
    private @Getter @Setter String name;

    @Size(max = 60, min=8, message = "O email deve ter entre 8 e 60 caracteres.")
    @NotBlank(message="O email é Obrigatório")
    private @Getter @Setter String email;

    @Size(max = 20, min=8, message = "O email deve ter entre 8 e 20 caracteres.")
    @NotBlank(message="O password é obrigatório")
    private @Getter @Setter String password;

    public static UserDTO fromEntity(User user) {

        if( user == null )
            return null;
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static User fromDTO(UserDTO dto){
        if( dto == null )
            return null;
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}

