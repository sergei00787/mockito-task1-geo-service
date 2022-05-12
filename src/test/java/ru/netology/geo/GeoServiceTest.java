package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceTest {
    static GeoService geoService;

    @BeforeAll
    public static void initGeoService() {
        geoService = new GeoServiceImpl();
    }

    @Test
    public void ifByIpArgumentIsMoscowIp_thenReturnMoscowLocation() {
        Assertions.assertEquals(new Location("Moscow", Country.RUSSIA, "Lenina", 15), geoService.byIp(GeoServiceImpl.MOSCOW_IP));
    }

    @Test
    public void ifByIpArgumentIsRussianIp_thenReturnRussianLocation() {
        Assertions.assertEquals(new Location("Moscow", Country.RUSSIA, null, 0), geoService.byIp("172.192.1.30"));
    }

    @Test
    public void ifByIpArgumentIsNewYorkIp_thenReturnNewYorkLocation() {
        Assertions.assertEquals(new Location("New York", Country.USA, " 10th Avenue", 32), geoService.byIp(GeoServiceImpl.NEW_YORK_IP));
    }

    @Test
    public void ifByIpArgumentIsAmericanIp_thenReturnAmericanLocation() {
        Assertions.assertEquals(new Location("New York", Country.USA, null, 0), geoService.byIp("96.88.191.20"));
    }

    @Test
    public void ifByIpArgumentIsLoaclhostIp_thenReturnEmptyLocation() {
        Assertions.assertEquals(new Location(null, null, null, 0), geoService.byIp(GeoServiceImpl.LOCALHOST));
    }

    @Test
    public void ifByIpArgumentIsAnyOtherIp_thenReturnNull() {
        Assertions.assertNull(geoService.byIp("36.94.22.11"));
    }

    @Test
    public void byCoordinates_shouldThrowRuntimeExceptionNoImplemented() {
        Assertions.assertThrows(RuntimeException.class, () -> geoService.byCoordinates(12.23D, 11.12D));
    }

}
