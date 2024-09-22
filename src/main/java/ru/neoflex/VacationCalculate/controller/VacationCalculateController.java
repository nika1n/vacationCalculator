package ru.neoflex.VacationCalculate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.VacationCalculate.exception.GlobalExceptionHandler;
import ru.neoflex.VacationCalculate.service.VacationCalculateService;

import java.time.LocalDate;

@Controller
public class VacationCalculateController {
    private static final Logger logger = LoggerFactory.getLogger(VacationCalculateController.class);

    private final VacationCalculateService vacationCalculateService;

    public VacationCalculateController(VacationCalculateService vacationCalculateService) {
        this.vacationCalculateService = vacationCalculateService;
    }

    @GetMapping("/calculate")
    @ResponseBody
    public double calculateVacationPay(
            @RequestParam("averageSalary") double averageSalary,
            @RequestParam(value = "vacationDays", required = false) Integer vacationDays,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate) {

        logger.info("Запрос с параметрами: averageSalary={}, vacationDays={}, startDate={}, endDate={}",
                averageSalary, vacationDays, startDate, endDate);
        if (averageSalary <= 0) {
            logger.error("Ошибка средняя зарплата должна быть больше 0.");
            throw new IllegalArgumentException("Средняя зарплата должна быть больше 0.");
        }


        if (startDate != null && endDate != null) {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);


            if (start.isAfter(end)) {
                logger.error("Ошибка:Дата начала отпуска не может быть позже даты окончания.");
                throw new IllegalArgumentException("Дата начала отпуска не может быть позже даты окончания.");
            }

            return vacationCalculateService.calculateVacationPay(averageSalary, start, end);
        }


        if (vacationDays != null) {
            if (vacationDays <= 0) {
                logger.error("Ошибка:Количество дней отпуска должно быть больше 0.");
                throw new IllegalArgumentException("Количество дней отпуска должно быть больше 0.");
            }
            return vacationCalculateService.calculateVacationPay(averageSalary, vacationDays);
        }

        logger.error("Ошибка:Необходимо указать либо количество дней отпуска, либо даты отпуска.");
        throw new IllegalArgumentException("Необходимо указать либо количество дней отпуска, либо даты отпуска.");
    }


    @ExceptionHandler(IllegalAccessError.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("Перехвачено IllegalArgumentException в контроллере : {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);

    }




}



