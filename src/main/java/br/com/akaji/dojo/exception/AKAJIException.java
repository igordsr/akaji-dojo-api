package br.com.akaji.dojo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AKAJIException extends Exception {
    private static final long serialVersionUID = 1L;
    private String code;
    private String type;
    private String module;
    private String message;
    private List<String> codeList;
    private boolean rollback = true;
    private Exception exception;
    private HttpStatus httpStatus;

    public AKAJIException(String code, String type, String module, String message) {
        this.code = code;
        this.type = type;
        this.module = module;
        this.message = message;
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
