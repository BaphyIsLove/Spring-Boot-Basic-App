package com.basicapp.springbootbasicapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangePassword {
    
    
    @NotNull
    private long id;
    
    @NotBlank(message = "Este campo no debe esta vacio")
    private String currentPassword;
    
    @NotBlank(message="Introduce una nueva contraseña")
    private String newPassword;
    
    @NotBlank(message="Las contraseñas no coinciden")
    private String confirmPassword;
    
    public ChangePassword(@NotNull long id) {
        this.id = id;
    }
    
}
