package proj.been433.cofcapshop.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import proj.been433.cofcapshop.Address;
import proj.been433.cofcapshop.Login;
import proj.been433.cofcapshop.Member;
import proj.been433.cofcapshop.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public List<Member> membersV1() {
        return memberService.findMembers();
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Validated CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        //member.setAddress(request.getAddress());
        member.setLogin(request.getLogin());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);

    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequest request) {
        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }



    @PostMapping("/api/v2/idcheck")
    public IdCheckResponse checkId(@RequestBody @Validated IdCheckRequest request) {
        Boolean result =  memberService.checkLoginId(request.loginId);
        return new IdCheckResponse(result);
    }

    @PostMapping("/api/v2/login")
    public LoginResponse loginMember(@RequestBody @Validated LoginRequest request) {
        String success = memberService.successLogin(request.loginId, request.loginPw);
        return new LoginResponse(success);
    }

    @Data
    static class LoginRequest {
        private String loginId;
        private String loginPw;
    }
    @Data
    @AllArgsConstructor
    static class LoginResponse {
        private String successLoginId;
    }

    @Data
    static class IdCheckRequest {
        private String loginId;
    }
    @Data
    @AllArgsConstructor
    static class IdCheckResponse {
        private Boolean checkResult;
    }

    @Data
    static class UpdateMemberRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Data
    static class CreateMemberRequest {
        private String name;
        private Login login;
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
