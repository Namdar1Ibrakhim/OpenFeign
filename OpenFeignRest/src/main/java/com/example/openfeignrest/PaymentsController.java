package com.example.openfeignrest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
public class PaymentsController {

    private static Logger logger =
            Logger.getLogger(PaymentsController.class.getName());

    @PostMapping("/payment")
    public ResponseEntity<Payment> createPayment(@RequestHeader String requestId, @RequestBody Payment payment){
        //Конечная точка получает заголовок и тело
        //запроса от вызывающего ее приложения.
        //Эти данные передаются в метод
        //контроллера в качестве параметров

        logger.info("Received request with ID " + requestId +
                " ;Payment Amount: " + payment.getAmount());

        payment.setId(UUID.randomUUID().toString());
        //Метод присваивает платежу случайный ID

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("requestId", requestId)
                .body(payment);
        //Действие контроллера возвращает
        //HTTP-ответ. У ответа есть заголовок
        //и тело, в котором содержится платеж
        //с присвоенным ему случайным ID
    }
}
