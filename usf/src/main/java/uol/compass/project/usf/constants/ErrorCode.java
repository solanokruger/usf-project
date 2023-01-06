package uol.compass.project.usf.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    BAD_REQUEST("Pedido inválido"),
    INVALID_PARAMETER("Paramêtro do pedido inválido"),
    INTERNAL_SERVER_ERROR("Erro interno do servidor"),

    TOKEN_NOT_CREATED("Erro ao lançar Token JWT"),
    TOKEN_NOT_VALID("Token JWT inválido ou expirado!"),
    PASSWORD_NOT_VALID("Senha está incorreta"),

    USF_NOT_FOUND("USF não encontrada"),
    TEAM_NOT_FOUND("Equipe não encontrado"),
    DOCTOR_NOT_FOUND("Médico não encontrado"),
    SOLICITATION_NOT_FOUND("Pedido não encontrado"),
    RESOURCE_NOT_FOUND("Recurso não encontrado"),
    USER_NOT_FOUND("Usuário não encontrado"),
    USER_ALREADY_EXISTS("Usuário já existe"),
    INVENTORY_NOT_FOUND("Inventário não encontrado");

    private final String message;

}
