package com.market.app_online_market.service.securityService;

import com.market.app_online_market.domain.Users;
import com.market.app_online_market.payload.CustomUserDetail;
import com.market.app_online_market.repositores.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUser_name(username).get();
        if (user != null) {
            return new CustomUserDetail(user);
        }
        throw new UsernameNotFoundException("User not found with this UserName " + username);

    }
}
