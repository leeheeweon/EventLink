package com.project.eventlink.user.service;

import com.project.eventlink.user.dto.JoinForm;
import com.project.eventlink.user.entity.User;
import com.project.eventlink.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findByUserId(userId);
    }

    public void save(JoinForm joinForm) {
        userRepository.save(User.createUserByJoinForm(joinForm));
    }
}
