package com.samuel.house.manager.error;

import com.samuel.house.manager.exception.HouseNotFoundException;
import com.samuel.house.manager.message.error.ErrorMessageResponse;
import com.samuel.house.manager.model.dto.ValidationErrorFieldsDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Slf4j
@Service
@ResponseBody
@ControllerAdvice(basePackages = {"com.samuel.house.manager.controller"})
public class ServiceExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    private String invalid_parameter_message = "Invalid parameter";

    public static String getFormattedMessage(String requestUrl, int httpStatus, String errorMessage) {
        return String.format("Request url: %s, HTTP status code: %d, Error message: %s", requestUrl, httpStatus, errorMessage);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageResponse handleInternalErrors(HttpServletRequest request, Exception exception) {
        log.error(getFormattedMessage(request.getRequestURL().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()), exception);
        return ErrorMessageResponse.builder()
                .url(request.getRequestURL().toString())
                .reason(exception.getMessage())
                .build();
    }

    /**
     * Process validation errors and prepare response with ist of field errors
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageResponse processValidationError(HttpServletRequest request, MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ValidationErrorFieldsDto validationErrors = processFieldErrors(fieldErrors);
        log.error(getFormattedMessage(request.getRequestURL().toString(),
                HttpStatus.BAD_REQUEST.value(), invalid_parameter_message));

        return ErrorMessageResponse.builder()
                .url(request.getRequestURL().toString())
                .reason(invalid_parameter_message)
                .validationErrors(validationErrors)
                .build();
    }

    /**
     * Process "House not found" exception
     */
    @ExceptionHandler(HouseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponse processDeviceNotSubscribedException(HttpServletRequest request, HouseNotFoundException ex) {
        log.error(getFormattedMessage(request.getRequestURL().toString(), HttpStatus.NOT_FOUND.value(), ex.getMessage()), ex);
        return ErrorMessageResponse.builder()
                .url(request.getRequestURL().toString())
                .reason(ex.getMessage())
                .build();
    }

    /**
     * Process "Validating houseId" exception
     */
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageResponse handleBadRequestExceptions(HttpServletRequest request, Exception exception) {
        log.error(getFormattedMessage(request.getRequestURL().toString(), HttpStatus.BAD_REQUEST.value(), exception.getMessage()), exception);
        return ErrorMessageResponse.builder()
                .url(request.getRequestURL().toString())
                .reason(exception.getMessage())
                .build();
    }


    private ValidationErrorFieldsDto processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorFieldsDto dto = new ValidationErrorFieldsDto();

        for (FieldError fieldError : fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            final Object rejectedValue = fieldError.getRejectedValue();
            String value = rejectedValue != null ? rejectedValue.toString() : null;
            dto.addFieldError(fieldError.getField(), localizedErrorMessage, value);
        }

        return dto;
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        String localizedErrorMessage = messageSource.getMessage(fieldError, null);
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
                String[] fieldErrorCodes = fieldError.getCodes();
                localizedErrorMessage = StringUtils.remove(fieldErrorCodes[0], fieldErrorCodes[fieldErrorCodes.length - 1] + ".") + " " + localizedErrorMessage;
        }
        return localizedErrorMessage;
    }
}
