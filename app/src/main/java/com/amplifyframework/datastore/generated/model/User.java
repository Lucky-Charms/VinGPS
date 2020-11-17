package com.amplifyframework.datastore.generated.model;


import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the User type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users")
public final class User implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField PASSWORD = field("password");
  public static final QueryField EMAIL = field("email");
  public static final QueryField FIRST_NAME = field("firstName");
  public static final QueryField LAST_NAME = field("lastName");
  public static final QueryField PHONE = field("phone");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String password;
  private final @ModelField(targetType="String", isRequired = true) String email;
  private final @ModelField(targetType="String") String firstName;
  private final @ModelField(targetType="String") String lastName;
  private final @ModelField(targetType="String") String phone;
  public String getId() {
      return id;
  }
  
  public String getPassword() {
      return password;
  }
  
  public String getEmail() {
      return email;
  }
  
  public String getFirstName() {
      return firstName;
  }
  
  public String getLastName() {
      return lastName;
  }
  
  public String getPhone() {
      return phone;
  }
  
  private User(String id, String password, String email, String firstName, String lastName, String phone) {
    this.id = id;
    this.password = password;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      User user = (User) obj;
      return ObjectsCompat.equals(getId(), user.getId()) &&
              ObjectsCompat.equals(getPassword(), user.getPassword()) &&
              ObjectsCompat.equals(getEmail(), user.getEmail()) &&
              ObjectsCompat.equals(getFirstName(), user.getFirstName()) &&
              ObjectsCompat.equals(getLastName(), user.getLastName()) &&
              ObjectsCompat.equals(getPhone(), user.getPhone());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getPassword())
      .append(getEmail())
      .append(getFirstName())
      .append(getLastName())
      .append(getPhone())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("User {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("password=" + String.valueOf(getPassword()) + ", ")
      .append("email=" + String.valueOf(getEmail()) + ", ")
      .append("firstName=" + String.valueOf(getFirstName()) + ", ")
      .append("lastName=" + String.valueOf(getLastName()) + ", ")
      .append("phone=" + String.valueOf(getPhone()))
      .append("}")
      .toString();
  }
  
  public static PasswordStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static User justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new User(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      password,
      email,
      firstName,
      lastName,
      phone);
  }
  public interface PasswordStep {
    EmailStep password(String password);
  }
  

  public interface EmailStep {
    BuildStep email(String email);
  }
  

  public interface BuildStep {
    User build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep firstName(String firstName);
    BuildStep lastName(String lastName);
    BuildStep phone(String phone);
  }
  

  public static class Builder implements PasswordStep, EmailStep, BuildStep {
    private String id;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    @Override
     public User build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new User(
          id,
          password,
          email,
          firstName,
          lastName,
          phone);
    }
    
    @Override
     public EmailStep password(String password) {
        Objects.requireNonNull(password);
        this.password = password;
        return this;
    }
    
    @Override
     public BuildStep email(String email) {
        Objects.requireNonNull(email);
        this.email = email;
        return this;
    }
    
    @Override
     public BuildStep firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    
    @Override
     public BuildStep lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    
    @Override
     public BuildStep phone(String phone) {
        this.phone = phone;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String password, String email, String firstName, String lastName, String phone) {
      super.id(id);
      super.password(password)
        .email(email)
        .firstName(firstName)
        .lastName(lastName)
        .phone(phone);
    }
    
    @Override
     public CopyOfBuilder password(String password) {
      return (CopyOfBuilder) super.password(password);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
    
    @Override
     public CopyOfBuilder firstName(String firstName) {
      return (CopyOfBuilder) super.firstName(firstName);
    }
    
    @Override
     public CopyOfBuilder lastName(String lastName) {
      return (CopyOfBuilder) super.lastName(lastName);
    }
    
    @Override
     public CopyOfBuilder phone(String phone) {
      return (CopyOfBuilder) super.phone(phone);
    }
  }
  
}
