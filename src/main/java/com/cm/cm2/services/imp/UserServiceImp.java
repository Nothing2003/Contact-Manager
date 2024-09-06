package com.cm.cm2.services.imp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cm.cm2.entities.User;
import com.cm.cm2.helper.AppCon;
import com.cm.cm2.helper.Helper;
import com.cm.cm2.helper.ResourceNotFoundException;
import com.cm.cm2.repsitories.UserRepo;
import com.cm.cm2.services.EmailService;
import com.cm.cm2.services.UserService;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    EmailService emailService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(AppCon.rUser));
        logger.info(user.getProvider().toString());
        String token = UUID.randomUUID().toString();
        user.setEmailToken(token);
        User saveUser = userRepo.save(user);
        emailService.sendEmail(saveUser.getEmail(), "Verify Account : Email Contact Manager ", Helper.getLinkForEmailVarification(token));
        return saveUser;
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2 = userRepo.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User Not found"));
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(passwordEncoder.encode(user.getPassword()));
        user2.setAbout(user.getAbout());
        user2.setPhoneNo(user.getPhoneNo());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnable(user.isEnabled());
        user2.setEmailVefied(user.isEmailVefied());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user2.getProviderUserId());
        User save = userRepo.save(user2);
        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
        User user2 = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not found"));
        userRepo.delete(user2);
    }

    @Override
    public boolean isUserExist(String Id) {
        User user2 = userRepo.findById(Id).orElse(null);
        return user2 != null;

    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        return user != null;
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    @Override
    public Optional<User> getUserByToken(String token) {
        return userRepo.findByEmailToken(token);
    }

    @Override
    public User updatePasswordByuserId(String id, String password) {
        User user = userRepo.findById(id).orElse(null);
        user.setPassword(password);
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserByForgetToken(String token) {
        return userRepo.findByForgetpasswordToken(token);
    }

}
