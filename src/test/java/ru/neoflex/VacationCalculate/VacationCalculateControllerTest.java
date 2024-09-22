package ru.neoflex.VacationCalculate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.neoflex.VacationCalculate.controller.VacationCalculateController;
import ru.neoflex.VacationCalculate.service.VacationCalculateService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VacationCalculateControllerTest {

    @Test
    public void testCalculateVacationPayByDays_Success() {

        VacationCalculateService vacationCalculateService = Mockito.mock(VacationCalculateService.class);

        double expectedVacationPay = 28650;

        Mockito.when(vacationCalculateService.calculateVacationPay(60000 , 14)).thenReturn(expectedVacationPay);

        VacationCalculateController controller = new VacationCalculateController(vacationCalculateService);

        double actualVacationPay = controller.calculateVacationPay(60000,14, null,null);

        assertEquals(expectedVacationPay, actualVacationPay, "Контроллер вернул неверный результат для количества дней");

    }

    @Test
    public void testCalculateVacationPayByDates_Success(){
        VacationCalculateService vacationCalculateService = Mockito.mock(VacationCalculateService.class);

        double expectedVacationPay = 20510;

        LocalDate startDate = LocalDate.of(2024,9,1);
        LocalDate endDate = LocalDate.of(2024,9,14);

        Mockito.when(vacationCalculateService.calculateVacationPay(60000,startDate,endDate)).thenReturn(expectedVacationPay);

        VacationCalculateController controller = new VacationCalculateController(vacationCalculateService);

        double actualVacationPay = controller.calculateVacationPay(60000, null, "2024-09-01", "2024-09-14");

        assertEquals(expectedVacationPay, actualVacationPay, "Контроллер вернул неверный результат для дат");

    }

@Test

    public void testInvalidSalary(){

        VacationCalculateService vacationCalculateService = Mockito.mock(VacationCalculateService.class);

        VacationCalculateController controller = new VacationCalculateController(vacationCalculateService);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {

        controller.calculateVacationPay(0, 10, null, null);
    });
        assertEquals("Средняя зарплата должна быть больше 0.",exception.getMessage());

    }
    @Test
    public void testInvalidDates(){

        VacationCalculateService vacationCalculateService = Mockito.mock(VacationCalculateService.class);
        VacationCalculateController controller = new VacationCalculateController(vacationCalculateService);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.calculateVacationPay(60000, null, "2024-09-10", "2024-09-01");

        });

        assertEquals("Дата начала отпуска не может быть позже даты окончания.",exception.getMessage());
    }

@Test
    public void  testMissingParams(){
    VacationCalculateService vacationCalculateService = Mockito.mock(VacationCalculateService.class);
    VacationCalculateController controller = new VacationCalculateController(vacationCalculateService);
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
        controller.calculateVacationPay(60000, null, null,null);
            });
        assertEquals("Необходимо указать либо количество дней отпуска, либо даты отпуска.",exception.getMessage());
}


















}
