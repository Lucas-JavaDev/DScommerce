package com.DevSuperior.Estudo.Exception;


// RuntimeException = Não exige try-catch
// Exception = Exigie try-catch

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
