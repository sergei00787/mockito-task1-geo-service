package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceTest {
    static GeoService geoService;

    @BeforeAll
    public static void initGeoService() {
        geoService = new GeoServiceImpl();
    }

    @Test
    public void ifByIpArgumentIsMoscowIp_thenReturnMoscowLocation() {
        Assertions.assertEquals(geoService.byIp(GeoServiceImpl.MOSCOW_IP), new Location("Moscow", Country.RUSSIA, "Lenina", 15));
    }

    @Test
    public void ifByIpArgumentIsRussianIp_thenReturnRussianLocation() {
        Assertions.assertEquals(geoService.byIp("172.192.1.30"), new Location("Moscow", Country.RUSSIA, null, 0));
    }

    @Test
    public void ifByIpArgumentIsNewYorkIp_thenReturnNewYorkLocation() {
        Assertions.assertEquals(geoService.byIp(GeoServiceImpl.NEW_YORK_IP), new Location("New York", Country.USA, " 10th Avenue", 32));
    }

    @Test
    public void ifByIpArgumentIsAmericanIp_thenReturnAmericanLocation() {
        Assertions.assertEquals(geoService.byIp("96.88.191.20"), new Location("New York", Country.USA, null, 0));
    }

    @Test
    public void ifByIpArgumentIsLoaclhostIp_thenReturnEmptyLocation() {
        Assertions.assertEquals(geoService.byIp(GeoServiceImpl.LOCALHOST), new Location(null, null, null, 0));
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
