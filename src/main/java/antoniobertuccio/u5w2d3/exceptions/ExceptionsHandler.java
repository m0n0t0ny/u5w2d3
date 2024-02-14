package antoniobertuccio.u5w2d3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorsPayload handleBadRequest(BadRequestException ex) {
    return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
  }


  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorsPayload handleNotFound(NotFoundException ex) {
    return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
  }

//  @ExceptionHandler(Exception.class)
//  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//  public ErrorsPayload handleGenericErrors(Exception ex) {
//    ex.fillInStackTrace();
//    return new ErrorsPayload("Server error." +
//            "We apologize for the inconvenience, we're working on it!", LocalDateTime.now());
//  }

}
