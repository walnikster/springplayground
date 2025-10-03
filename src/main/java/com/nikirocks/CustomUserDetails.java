package com.nikirocks;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
        private final String username;
        private final String password;
        private final Collection<? extends GrantedAuthority> authorities;
        private final String customAttribute;

        public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String customAttribute) {
            this.username = username;
            this.password = password;
            this.authorities = authorities;
            this.customAttribute = customAttribute;
        }

        public String getCustomAttribute() {
            return
customAttribute;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

}
