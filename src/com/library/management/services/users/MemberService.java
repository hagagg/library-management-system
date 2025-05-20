package com.library.management.services.users;

import com.library.management.models.users.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class MemberService {

    Map<Integer, Member> memberMap;

    public MemberService(Map<Integer, Member> memberMap) {
        this.memberMap = memberMap;
    }

    @Override
    public String toString() {
        return "MemberService{" +
                "memberMap=" + memberMap +
                '}';
    }


    public Member getMemberById (int memberId ) {

        Member member = memberMap.get(memberId);
        if (member != null) {
            return member;
        }
        throw new NoSuchElementException("Member with id " + memberId + " does not exist");
    }

    List<Member> getAllMembers() {

      return new ArrayList<>(memberMap.values());
    }


}
