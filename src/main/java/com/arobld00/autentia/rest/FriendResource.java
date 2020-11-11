package com.arobld00.autentia.rest;

import com.arobld00.autentia.data_services.FriendService;
import com.arobld00.autentia.domain.Friend;
import com.arobld00.autentia.rest.dtos.FriendDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

//@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping(FriendResource.FRIENDS)
public class FriendResource {

    public static final String FRIENDS = "/friends";

    private final FriendService friendService;

    @Autowired
    public FriendResource(FriendService friendService) {
        this.friendService = friendService;
    }

    /*
        {"name": "Alberto","phone":"666666666"}
     */
    @PostMapping
    public FriendDto create(@Valid @RequestBody FriendDto friendDto) {
        Friend friend = this.friendService.create(friendDto.toFriend());
        return new FriendDto(friend);
    }

    @GetMapping
    public List<FriendDto> readAll() {
        return this.friendService.readAll().stream()
                .map(FriendDto::new)
                .collect(Collectors.toList());
    }

}
