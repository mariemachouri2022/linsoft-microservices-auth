package com.maghrebia.User.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;


    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;  // Pour l'envoi des emails

    // 🔹 Constructeur unique avec injection de dépendances
    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 🔹 Générer un token et envoyer un email
    public void forgotPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        String token = UUID.randomUUID().toString();  // Générer un token unique
        user.setResetToken(token);
        userRepository.save(user);

        // Envoyer l'email avec le lien contenant le token
        sendResetEmail(user.getEmail(), token);
    }

    // 🔹 Envoyer un email avec le lien de réinitialisation
    private void sendResetEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Réinitialisation de votre mot de passe");
        message.setText("Cliquez sur le lien suivant pour réinitialiser votre mot de passe : " +
                "http://localhost:4200/reset-password?token=" + token);
        mailSender.send(message);
    }

    // 🔹 Vérifier le token et mettre à jour le mot de passe
    public void resetPassword(String token, String newPassword) {
        User user = userRepository.findByResetToken(token)
                .orElseThrow(() -> new RuntimeException("Token invalide"));

        user.setPassword(passwordEncoder.encode(newPassword));  // Hachage du mot de passe
        user.setResetToken(null);  // Supprimer le token après utilisation
        userRepository.save(user);
    }

    // Méthode pour récupérer un utilisateur par son nom d'utilisateur
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username); // Assure-toi que findByUsername existe dans ton repository
    }
    // 🔹 Ajouter un Bean pour le BCryptPasswordEncoder dans une classe de configuration
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }


    // 🔹 Inscription avec un rôle par défaut (CLIENT)
    public User registerUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Collections.singleton(ERole.USER)); // Rôle par défaut

        return userRepository.save(user);
    }

    // 🔹 Mettre à jour les rôles d’un utilisateur
    public User updateUserRole(String username, Set<ERole> roles) {
        User user = userRepository.findByUsername(username);

        user.setRoles(roles); // Mise à jour des rôles
        return userRepository.save(user);
    }

    // 🔹 Vérifier si un utilisateur en base est ADMIN ou MANAGER
    public boolean isAdminOrManager(String username) {
        User user = userRepository.findByUsername(username); // 🔹 Peut retourner null

        // Vérifie si l'utilisateur n'est pas null et si son Set de rôles contient ADMIN ou MANAGER
        return user != null && (user.getRoles().contains(ERole.ADMIN) || user.getRoles().contains(ERole.MANAGER));
    }
    // 🔹 Vérifier le rôle de l'utilisateur connecté
    public boolean isCurrentUserAdminOrManager() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) ||
                authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"));
    }

}
