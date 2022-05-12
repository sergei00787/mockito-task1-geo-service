package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

public class LocalizationServiceTest {
    static LocalizationService localizationService;


    @BeforeAll
    public static void initLocalizationService() {
        localizationService = new LocalizationServiceImpl();
    }

    @Test
    public void ifByIpIsRussianIp_thenReturnRussianLocation() {
        Assertions.assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
    }

    @Test
    public void ifLocaleArgumentIsNotCountryRussia_thenReturnRussianWelcome() {
        Assertions.assertEquals("Welcome", localizationService.locale(Country.BRAZIL));
    }

    @Test
    public void ifLocaleArgumentIsNull_thenThrowsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> localizationService.locale(null));
    }
}
