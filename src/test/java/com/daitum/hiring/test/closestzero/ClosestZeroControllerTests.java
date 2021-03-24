package com.daitum.hiring.test.closestzero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;

import com.daitum.hiring.test.closestzero.controller.ClosestZeroController;
import com.daitum.hiring.test.closestzero.payload.RequestPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class ClosestZeroControllerTests {
  @Autowired
  ClosestZeroController closestZeroController;

  @Autowired
  private WebApplicationContext webApplicationContext;

  MockMvc mockMvc;

  private String toJson(Object obj) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(obj);
  }

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void contextLoads() throws Exception {
    assertNotNull(closestZeroController);
    assertNotNull(webApplicationContext);
  }

  @Test
  public void testPostRequestWithBadRequestParam() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/closest_zero")
        .accept(MediaType.APPLICATION_JSON).content("").contentType(MediaType.APPLICATION_JSON)).andReturn();
    MockHttpServletResponse response = result.getResponse();
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
  }

  @Test
  public void testPostRequestWithInvalidNParam() throws Exception {
    RequestPayload payload = new RequestPayload();
    payload.setList(Arrays.asList(1, 2, -1, 3));
    payload.setN(-3);

    checkPostRequestWithInvalidParams(payload);
  }

  @Test
  public void testPostRequestWithNullListParam() throws Exception {
    RequestPayload payload = new RequestPayload();
    payload.setList(null);
    payload.setN(5);

    checkPostRequestWithInvalidParams(payload);
  }

  @Test
  public void testPostRequestWithEmptyListParam() throws Exception {
    RequestPayload payload = new RequestPayload();
    payload.setList(new ArrayList<Integer>());
    payload.setN(5);

    checkPostRequestWithInvalidParams(payload);
  }

  @Test
  public void testPostRequestWithNGreaterThanListSize() throws Exception {
    RequestPayload payload = new RequestPayload();
    payload.setList(Arrays.asList(1, 2, -1, 3));
    payload.setN(8);

    checkPostRequestWithInvalidParams(payload);
  }

  private void checkPostRequestWithInvalidParams(RequestPayload payload) throws Exception {
    MockHttpServletResponse response = sendPostRequest(payload);
    assertEquals(HttpStatus.OK.value(), response.getStatus());
    assertEquals(-1, new Integer(response.getContentAsString()));
  }

  private MockHttpServletResponse sendPostRequest(RequestPayload payload) throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/closest_zero")
        .accept(MediaType.APPLICATION_JSON).content(toJson(payload)).contentType(MediaType.APPLICATION_JSON))
        .andReturn();
    return result.getResponse();
  }

  @Test
  public void testPostRequestWithValidParams() throws Exception {
    RequestPayload payload = new RequestPayload();
    payload.setList(Arrays.asList(2, 2, -3, 0, 2));
    payload.setN(2);

    MockHttpServletResponse response = sendPostRequest(payload);
    assertEquals(HttpStatus.OK.value(), response.getStatus());
    assertEquals(1, new Integer(response.getContentAsString()));
  }

  @Test
  public void testPostRequestWithSubListSumIsZero() throws Exception {
    RequestPayload payload = new RequestPayload();
    payload.setList(Arrays.asList(-4, 2, 2, -3, 3, 0, 2, 5, 0, 1, -1));
    payload.setN(3);

    MockHttpServletResponse response = sendPostRequest(payload);
    assertEquals(HttpStatus.OK.value(), response.getStatus());
    assertEquals(0, new Integer(response.getContentAsString()));
  }

  @Test
  public void testPostRequestWithMultipleClosestZeroSubList() throws Exception {
    RequestPayload payload = new RequestPayload();
    payload.setList(Arrays.asList(-4, 2, 2, -3, 0, 2, 5, 0, 1));
    payload.setN(2);

    MockHttpServletResponse response = sendPostRequest(payload);
    assertEquals(HttpStatus.OK.value(), response.getStatus());
    assertEquals(2, new Integer(response.getContentAsString()));
  }
}
