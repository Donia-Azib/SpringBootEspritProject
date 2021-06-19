package tn.esprit.auth.user.model;                                        
 
import java.util.Set;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
 
import org.springframework.security.core.GrantedAuthority;
 
@Entity
public class Role  implements GrantedAuthority{
 
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String name;
        
        @ManyToMany(mappedBy = "roles")
        private Set<User> users;
        
        
        
        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
        }
        public Long getId() {
                return id;
        }
        public void setId(Long id) {
            this.id = id;
    }
    public Set<User> getUsers() {
            return users;
    }
    public void setUsers(Set<User> users) {
            this.users = users;
    }
    @Override
    public String getAuthority() {
            // TODO Auto-generated method stub
            return name;
    }

}