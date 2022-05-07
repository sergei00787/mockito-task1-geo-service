package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MesegeSenderTest {
    @ParameterizedTest
    @ValueSource(strings = {"172.0.32.11", "172.0.32.1"})
    public void sendMessageWithRussianIp_isReturnRussianHelloString(String ipAddress) {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ipAddress)).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);

        Assertions.assertEquals(messageSender.send(headers), "Добро пожаловать");
    }

    @ParameterizedTest
    @ValueSource(strings = {"96.0.32.11", "96.0.32.1"})
    public void sendMessageWithAmericanIp_isReturnWelcome(String ipAddress) {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ipAddress)).thenReturn(new Location("New York", Country.USA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);

        Assertions.assertEquals(messageSender.send(headers), "Welcome");
    }

    @Test
    public void sendMessageWithLocalHostIp_isReturnNull() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("127.0.0.1")).thenReturn(new Location(null, null, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(null)).thenReturn(null);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "127.0.0.1");

        Assertions.assertNull(messageSender.send(headers));
    }
}
