package com.library.management.services.users;

import com.library.management.factories.UserFactory;
import com.library.management.enums.UserRole;
import com.library.management.models.BorrowedBook;
import com.library.management.models.users.*;

import java.time.LocalDate;
import java.util.*;

public class AdminService {

    private final Map<Integer , Admin> adminMap;
    private final Map<Integer, User> userMap;
    private final Map<Integer, Member> memberMap;
    private final Map<Integer, Librarian> librarianMap;
    private final MemberService memberService;
    private final LibrarianService librarianService;

    public AdminService(Map<Integer, Admin> adminMap,Map<Integer, User> userMap, Map<Integer, Member> memberMap, Map<Integer, Librarian> librarianMap, MemberService memberService ,  LibrarianService librarianService) {
        this.adminMap = adminMap;
        this.userMap = userMap;
        this.memberMap = memberMap;
        this.librarianMap = librarianMap;
        this.memberService = memberService;
        this.librarianService = librarianService;
    }

    @Override
    public String toString() {
        return "AdminService{" +
                "userMap=" + userMap +
                ", memberMap=" + memberMap +
                '}';
    }

    // for members (Student & Teacher)
    public void addUser(UserRole role, int id, String name, String email, boolean isActive,
                        LocalDate membershipDate, List<BorrowedBook> borrowedBooks, double fineBalance) {

        if (userMap.containsKey(id)) {
            throw new IllegalArgumentException("User already exists!");
        }

        User user = UserFactory.createUser(role , id , name , email , isActive , membershipDate , borrowedBooks , fineBalance);
        userMap.put(id, user);

        if (user instanceof Member) {
            memberMap.put ( id , (Member) user);
        }
    }

     // Overloading addUser - for Admin & Librarian
    public void addUser(UserRole role, int id, String name, String email, boolean isActive ) {

        if (userMap.containsKey(id)) {
            throw new IllegalArgumentException("User already exists!");
        }

        User user = UserFactory.createUser(role , id , name , email , isActive , null , null , 0);
        userMap.put(id, user);

        if  (user instanceof Admin) {
            adminMap.put(id, (Admin) user);
        }

        if (user instanceof Librarian) {
            librarianMap.put ( id ,(Librarian) user );
        }
    }

    public void removeUser(int id) {
        User removedUser = userMap.remove(id);
        if (removedUser == null) {
            throw new NoSuchElementException("User with id " + id + " not found");
        }
    }

    public void updateUser(User user) {
        if (!userMap.containsKey(user.getId())){
            throw new NoSuchElementException("User with id " + user.getId() + " not found");
        }
        userMap.put(user.getId(), user);
    }

    public void activateUser(int userId) {
        User user = userMap.get(userId);
        if (user != null) {
            user.setActive(true);
        } else {
            throw new NoSuchElementException("User with id: " + userId + " is not found");
        }
    }

    public void deactivateUser(int userId) {
        User user = userMap.get(userId);
        if (user != null) {
            user.setActive(false);
        } else {
        throw new NoSuchElementException("User with id: " + userId + " is not found");
    }
}

    public User getUserById ( int userId) {
        User user = userMap.get(userId);

        if (user != null) {
            return user;
        } else {
            throw new NoSuchElementException( "User  with id " + userId + " not found" );
        }
    }

    public List <Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    public List <Librarian>  getAllLibrarians() {
        return librarianService.getAllLibrarians();
    }

    public List<User> getAllUsers() {

        return new ArrayList<>(userMap.values());
    }

    public User getUserByName(String userName) {
        for (User user : userMap.values()) {
            if (user.getName().equalsIgnoreCase(userName)) {
                return user;
            }
        }
        throw new NoSuchElementException("User with name " + userName + " not found");
    }

    public List <User> searchUsersByName (String userName) {
        List<User> matchedUsers = new ArrayList<>();

        for (User user : userMap.values()) {
            if (user.getName().toLowerCase().contains(userName.toLowerCase())) {
                matchedUsers.add(user);
            }
        }
        return matchedUsers;
    }



}
