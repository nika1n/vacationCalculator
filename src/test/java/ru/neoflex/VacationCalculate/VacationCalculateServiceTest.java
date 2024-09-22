package ru.neoflex.VacationCalculate;

import org.junit.jupiter.api.Test;
import ru.neoflex.VacationCalculate.service.VacationCalculateService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VacationCalculateServiceTest {


    private final VacationCalculateService vacationCalculateService = new VacationCalculateService();

    @Test
    public void  testCalculateVacationPay() {

        double averageSalary = 60000;
        int vacationDays =  14;

        double expectedVacationPay = (60000/ 29.3) *  14;

        double actualVacationPay = vacationCalculateService.calculateVacationPay(averageSalary, vacationDays);

        assertEquals (expectedVacationPay, actualVacationPay , "Расчёт отпускных выполнился неверно");

    }

    @Test
    public void testCalculateVacationPayByDates() {
        double averageSalary = 60000;
        LocalDate startDate = LocalDate.of(2024,9,1);
        LocalDate endDate =  LocalDate.of(2024,9,14);

        int expectedWorkingDays = 10;

        double expectedVacationPay = (60000/29.3) * expectedWorkingDays;

        double actualVacationPay = vacationCalculateService.calculateVacationPay(averageSalary, startDate, endDate);

        assertEquals(expectedVacationPay, actualVacationPay, "Расчёт отпускных учитывая даты выполнен неверно");




    }



}
