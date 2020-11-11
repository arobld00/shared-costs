package com.arobld00.autentia.services;

import com.arobld00.autentia.MockObjectFactory;
import com.arobld00.autentia.data_services.FriendService;
import com.arobld00.autentia.domain.Friend;
import com.arobld00.autentia.rest.FriendResource;
import com.arobld00.autentia.rest.dtos.FriendDto;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.apache.commons.collections.ListUtils.EMPTY_LIST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class FriendResourceTest {

    private FriendResource friendResource;

    private FriendService friendService = mock(FriendService.class);

    @Before
    public void before(){
        friendResource = new FriendResource(friendService);
    }

    @Test
    public void shouldCreateNewFriend() {
        //given
        Friend friend = MockObjectFactory.getFriendValid();
        FriendDto expectedFriendDto = MockObjectFactory.getFriendDtoFriendValid();
        when(friendService.create(expectedFriendDto.toFriend())).thenReturn(friend);

        //when
        FriendDto result = friendResource.create(expectedFriendDto);

        //then
        assertThat(result, is(expectedFriendDto));
        verify(friendService).create(friend);
    }

    @Test
    public void shouldReturnEmptyList() {
        //given
        when(friendService.readAll()).thenReturn(EMPTY_LIST);

        //when
        List<FriendDto> out = friendResource.readAll();

        //then
        assertTrue(out.isEmpty());
        verify(friendService, times(1)).readAll();
    }

}