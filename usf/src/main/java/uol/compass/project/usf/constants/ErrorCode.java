package uol.compass.project.usf.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    
    BAD_REQUEST("Pedido inválido"),
    INVALID_PARAMETER("Paramêtro do pedido inválido"),
    INTERNAL_SERVER_ERROR("Erro interno do servidor"),
<<<<<<< HEAD
//    USF_NOT_FOUND("USF não encontrada"),
//    TEAM_NOT_FOUND("Time não encontrado"),
//    DOCTOR_NOT_FOUND("Médico não encontrado"),
    SOLICITATION_NOT_FOUND("Pedido não encontrado");
=======
    USF_NOT_FOUND("USF não encontrada"),
    TEAM_NOT_FOUND("Equipe não encontrado"),
    DOCTOR_NOT_FOUND("Médico não encontrado"),
    RESOURCE_NOT_FOUND("Recurso não encontrado");
>>>>>>> crud-resource

    private final String message;
    
}
