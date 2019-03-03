package demo.task.vehicles.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.task.vehicles.models.Vehicle;
import demo.task.vehicles.services.VehicleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.geo.Point;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringJUnit4ClassRunner.class)
public class VehicleControllerTest {
    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @InjectMocks
    private VehicleController vehiclesController;
    @Mock
    private VehicleService vehicleService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vehiclesController).build();
    }



    @Test
    public void shouldHandleFindByName() throws Exception {
        Vehicle vehicle = new Vehicle("one", new Point(1, 2));
        when(vehicleService.findByName("one")).thenReturn(Optional.of(vehicle));
        mockMvc.perform(
                get("/vehicles/name/one"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("{'id': 'one'," +
                        "'location': {" +
                        "  'x':1," +
                        "  'y':2" +
                        "}}"));
    }

    @Test
    public void shouldHandleFindByNameWithoutExistData() throws Exception {
        Vehicle vehicle = new Vehicle("one", new Point(1, 2));
        when(vehicleService.findByName("one")).thenReturn(Optional.ofNullable(null));
        mockMvc.perform(
                get("/vehicles/name/one"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void shouldHandleFindByPointWithoutExistData() throws Exception {
        Vehicle vehicle = new Vehicle("one", new Point(1, 2));
        when(vehicleService.findInRectangle(0, 0, 10, 10)).thenReturn(Collections.EMPTY_LIST);
        mockMvc.perform(
                get("/vehicles/rectangle/topLeftLat/0/topLeftLng/0/bottomRightLat/10/bottomRightLng/10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void shouldHandleFindByPoint() throws Exception {
        Vehicle vehicle1 = new Vehicle("one", new Point(1, 2));
        Vehicle vehicle2 = new Vehicle("two", new Point(3, 2));

        when(vehicleService.findInRectangle(0, 0, 10, 10)).thenReturn(Arrays.asList(vehicle1, vehicle2));
        mockMvc.perform(
                get("/vehicles/rectangle/topLeftLat/0/topLeftLng/0/bottomRightLat/10/bottomRightLng/10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("[{'id': 'one'," +
                        "'location': {" +
                        "  'x':1," +
                        "  'y':2" +
                        "}}," +
                        "{'id': 'two'," +
                        "'location': {" +
                        "  'x':3," +
                        "  'y':2" +
                        "}}" +
                        "]"));
    }

    private static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }


}
