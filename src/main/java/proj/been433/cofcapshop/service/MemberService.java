package proj.been433.cofcapshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proj.been433.cofcapshop.Member;
import proj.been433.cofcapshop.repository.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    public Member findOneByLoginId(String memberLoginId) {
        return memberRepository.findOneByLoginIdToLogin(memberLoginId);
    }

    public String successLogin(String memberLoginId, String memberLoginPw) {
        Boolean isSuccess = checkLoginId(memberLoginId);
        if (!isSuccess) {
            Member member = findOneByLoginId(memberLoginId);
            if ((member.getLogin().getLoginId().equals(memberLoginId)) && (member.getLogin().getLoginPw().equals(memberLoginPw))) {
                return memberLoginId;
            }

        }
        return "";
    }

    public Boolean checkLoginId(String loginId) {
        List<Member> findMember = memberRepository.findOneByLoginId(loginId);
        if (!findMember.isEmpty()) {
            //throw new IllegalStateException("이미 존재하는 회원입니다.");
            return false;
        }else{
            return true;
        }
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
