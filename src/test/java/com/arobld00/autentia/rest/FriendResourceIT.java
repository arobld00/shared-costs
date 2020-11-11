package com.arobld00.autentia.rest;

import com.arobld00.autentia.Application;
import com.arobld00.autentia.MockObjectFactory;
import com.arobld00.autentia.data_services.FriendService;
import com.arobld00.autentia.domain.Friend;
import com.arobld00.autentia.domain.exceptions.PhoneAlreadyExistException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import java.util.Collections;
import java.util.List;

import static com.arobld00.autentia.rest.FriendResource.FRIENDS;
import static org.apache.commons.collections.ListUtils.EMPTY_LIST;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest(classes = Application.class,
        webEnvironment = WebEnvironment.RANDOM_PORT)
public class FriendResourceIT {

    private MockMvc mockMvc;

    @InjectMocks
    private FriendResource controller;

    @Mock
    private FriendService friendService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    public void shouldReturnNoContentWhenNoFriends() throws Exception {
        //given
        when(friendService.readAll()).thenReturn(EMPTY_LIST);

        //when
        final ResultActions result = mockMvc.perform(
                get(FRIENDS)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        result.andExpect(status().isOk());
        verify(friendService, times(1)).readAll();
    }

    @Test
    public void shouldReturnContentWhenExistsFriends() throws Exception {
        //given
        List<Friend> friends = Collections.singletonList(new Friend());
        when(friendService.readAll()).thenReturn(friends);

        //when
        final ResultActions result = mockMvc.perform(
                get(FRIENDS)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        result.andExpect(status().isOk());
        result.andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(friendService, times(1)).readAll();
    }

    @Test
    public void shouldReturnBadRequestWhenAddFriendWithBadNineDigitsPhone() throws Exception {
        //given
        // TODO
        //when
        final ResultActions result = mockMvc.perform(
                post(FRIENDS)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        result.andExpect(status().isBadRequest());
        verify(friendService, times(0)).create(any());
    }

    @Test
    public void shouldReturnPhoneAlreadyExistWhenAddFriend() throws Exception {
        //given
        Friend friend = MockObjectFactory.getFriendWithRepeatPhone();
        doThrow(new PhoneAlreadyExistException(EMPTY)).when(friendService).create(friend);
        //when
        final ResultActions result = mockMvc.perform(
                post(FRIENDS)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        result.andExpect(status().isBadRequest());
        verify(friendService, times(0)).create(friend);
    }

}
