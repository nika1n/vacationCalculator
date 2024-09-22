package ru.neoflex.VacationCalculate.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;



@Service
public class VacationCalculateService {

private static final double WORKING_DAYS_IN_MONTH = 29.3;



private static final Set<LocalDate> HOLIDAYS =Set.of(


        LocalDate.of(2024, 1, 1),
        LocalDate.of(2024, 1, 2),
        LocalDate.of(2024, 1, 3),
        LocalDate.of(2024, 1, 4),
        LocalDate.of(2024, 1, 5),
        LocalDate.of(2024, 1, 6),
        LocalDate.of(2024, 1, 7),
        LocalDate.of(2024, 1, 8),
        LocalDate.of(2024, 2, 23),
        LocalDate.of(2024, 3, 8),
        LocalDate.of(2024, 5, 1),
        LocalDate.of(2024, 5, 9),
        LocalDate.of(2024, 6, 12),
        LocalDate.of(2024, 11, 4)
        );



public double calculateVacationPay(double averageSalary, int vacationDays){
    if (averageSalary <=0 || vacationDays <= 0){
        throw new IllegalArgumentException("Зарплата и количество дней должны быть больше 0");
    }
    return (averageSalary / WORKING_DAYS_IN_MONTH) * vacationDays;
}


public double calculateVacationPay(double averageSalary,LocalDate start, LocalDate end){
        if (averageSalary <=0 ){
        throw new IllegalArgumentException("Зарплата и даты должны быть больше 0");
    }
        int businessDays = calculateBusinessDays(start, end);
        return (averageSalary / WORKING_DAYS_IN_MONTH) * businessDays;


}

private int calculateBusinessDays(LocalDate start,LocalDate end){

    int businessDays = 0;

    for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)){
        if (isBusinessDay(date)){
            businessDays++;
        }
    }
    return businessDays;


}

private boolean isBusinessDay(LocalDate date){
    DayOfWeek dayOfWeek = date.getDayOfWeek();
    return !(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY || HOLIDAYS.contains(date));
}


}
