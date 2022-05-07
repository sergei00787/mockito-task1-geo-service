package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceTest {
    static LocalizationService localizationService;


    @BeforeAll
    public static void initLocalizationService() {
        localizationService = new LocalizationServiceImpl();
    }

    @Test
    public void ifByIpIsRussianIp_thenReturnRussianLocation() {
        Assertions.assertEquals(localizationService.locale(Country.RUSSIA), "Добро пожаловать");
    }

    @Test
    public void ifLocaleArgumentIsNotCountryRussia_thenReturnRussianWelcome() {
        Assertions.assertEquals(localizationService.locale(Country.BRAZIL), "Welcome");
    }

    @Test
    public void ifLocaleArgumentIsNull_thenThrowsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> localizationService.locale(null));
    }
}
