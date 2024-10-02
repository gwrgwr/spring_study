package br.com.murilo.data.vo.v1;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

public class PersonVO extends RepresentationModel<PersonVO> {
    @Mapping("id")
    private UUID userId;
    private String name;
    private String email;
    private String password;
    public UUID getUserId() {
        return userId;
    }
    public void setId(UUID id) {
        this.userId = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
