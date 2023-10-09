package com.mypay.membership.adapter.in.web;

import com.mypay.membership.application.port.in.RegisterMembershipCommand;
import com.mypay.membership.application.port.in.RegisterMembershipUseCase;
import com.mypay.common.WebAdapter;
import com.mypay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterMembershipController {

    private final RegisterMembershipUseCase registerMembershipUseCase;

    @PostMapping(path = "/membership/register")
    public Membership registerMembership(@RequestBody RegisterMembershipRequest request){
        log.info("1. RegisterMembershipRequest : " + request.toString());
        // 1. request
        // 2. request -> command
        // 3. Usecase (request x, command)
        RegisterMembershipCommand command = RegisterMembershipCommand.builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .isValid(true)
                .isCorp(request.isCorp())
                .build();

        log.info("2. RegisterMembershipCommand : " + command.toString());
        return registerMembershipUseCase.registerMembership(command);
    }
}
