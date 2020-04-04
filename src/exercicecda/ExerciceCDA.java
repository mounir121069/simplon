/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicecda;

import exercicecda.dao.DAO;
import exercicecda.dao.UserDAO;
import exercicecda.entity.User;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Mounir
 */
public class ExerciceCDA {

    private static void error(String message) {
        System.err.println(message);
        System.exit(-1);
    }
 
    private static void selectAll(String message) {
        System.out.println(message);
        try {
            List<User> users = UserDAO.selectAll();
            int i = 1;
            for (User user : users) {
                System.out.println("Utilisateur " + i++ + " : " + user.getEmail() + " " + user.getPassword() + " " + user.getBirthDate());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            error("Impossible de lister les utilisateurs");
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (!DAO.connect()) {
            error("Impossible de se connecter à la base de données");
        }
        
        try {
            User user = new User();
            user.setEmail("bidon0@email.fr");
            user.setPassword("UZFYizr");
            user.setBirthDate(LocalDate.now());
            UserDAO.insert(user);

            user = new User();
            user.setEmail("bidon1@email.fr");
            user.setPassword("qeryezry");
            user.setBirthDate(LocalDate.now());
            UserDAO.insert(user);

            user = new User();
            user.setEmail("bidon2@email.fr");
            user.setPassword("ertuertuetru");
            user.setBirthDate(LocalDate.now());
            UserDAO.insert(user);
       } catch (SQLException ex) {
            ex.printStackTrace();
            error("Impossible d'insérer un utilisateur");
        }
        selectAll("APRES INSERT");
        User lastUser = null;
        try {
            lastUser = UserDAO.selectByEmail("bidon2@email.fr");
            if (lastUser != null) {
                lastUser.setPassword("newPassword");
                UserDAO.update(lastUser);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            error("Impossible de modifier un utilisateur");
        }
        selectAll("APRES UPDATE");
        try {
            if (lastUser != null) {
                UserDAO.delete(lastUser.getId());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            error("Impossible de supprimer un utilisateur");
        }
        selectAll("APRES DELETE");
        
        if (!DAO.disconnect()) {
            error("Impossible de se déconnecter de la base de données");
        }
    }
    
}
