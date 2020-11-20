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

/** This is an auto generated class representing the Client type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Clients")
public final class Client implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField FIRST_NAME = field("firstName");
  public static final QueryField LAST_NAME = field("lastName");
  public static final QueryField PHONE = field("phone");
  public static final QueryField EMAIL = field("email");
  public static final QueryField LAST_SALES_PERSON = field("lastSalesPerson");
  public static final QueryField LICENSE = field("license");
  public static final QueryField LICENSE_IMAGE_URL = field("licenseImageUrl");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String firstName;
  private final @ModelField(targetType="String") String lastName;
  private final @ModelField(targetType="String") String phone;
  private final @ModelField(targetType="String") String email;
  private final @ModelField(targetType="String") String lastSalesPerson;
  private final @ModelField(targetType="String") String license;
  private final @ModelField(targetType="String") String licenseImageUrl;
  public String getId() {
      return id;
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
  
  public String getEmail() {
      return email;
  }
  
  public String getLastSalesPerson() {
      return lastSalesPerson;
  }
  
  public String getLicense() {
      return license;
  }
  
  public String getLicenseImageUrl() {
      return licenseImageUrl;
  }
  
  private Client(String id, String firstName, String lastName, String phone, String email, String lastSalesPerson, String license, String licenseImageUrl) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.email = email;
    this.lastSalesPerson = lastSalesPerson;
    this.license = license;
    this.licenseImageUrl = licenseImageUrl;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Client client = (Client) obj;
      return ObjectsCompat.equals(getId(), client.getId()) &&
              ObjectsCompat.equals(getFirstName(), client.getFirstName()) &&
              ObjectsCompat.equals(getLastName(), client.getLastName()) &&
              ObjectsCompat.equals(getPhone(), client.getPhone()) &&
              ObjectsCompat.equals(getEmail(), client.getEmail()) &&
              ObjectsCompat.equals(getLastSalesPerson(), client.getLastSalesPerson()) &&
              ObjectsCompat.equals(getLicense(), client.getLicense()) &&
              ObjectsCompat.equals(getLicenseImageUrl(), client.getLicenseImageUrl());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getFirstName())
      .append(getLastName())
      .append(getPhone())
      .append(getEmail())
      .append(getLastSalesPerson())
      .append(getLicense())
      .append(getLicenseImageUrl())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Client {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("firstName=" + String.valueOf(getFirstName()) + ", ")
      .append("lastName=" + String.valueOf(getLastName()) + ", ")
      .append("phone=" + String.valueOf(getPhone()) + ", ")
      .append("email=" + String.valueOf(getEmail()) + ", ")
      .append("lastSalesPerson=" + String.valueOf(getLastSalesPerson()) + ", ")
      .append("license=" + String.valueOf(getLicense()) + ", ")
      .append("licenseImageUrl=" + String.valueOf(getLicenseImageUrl()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
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
  public static Client justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Client(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      firstName,
      lastName,
      phone,
      email,
      lastSalesPerson,
      license,
      licenseImageUrl);
  }
  public interface BuildStep {
    Client build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep firstName(String firstName);
    BuildStep lastName(String lastName);
    BuildStep phone(String phone);
    BuildStep email(String email);
    BuildStep lastSalesPerson(String lastSalesPerson);
    BuildStep license(String license);
    BuildStep licenseImageUrl(String licenseImageUrl);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String lastSalesPerson;
    private String license;
    private String licenseImageUrl;
    @Override
     public Client build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Client(
          id,
          firstName,
          lastName,
          phone,
          email,
          lastSalesPerson,
          license,
          licenseImageUrl);
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
    
    @Override
     public BuildStep email(String email) {
        this.email = email;
        return this;
    }
    
    @Override
     public BuildStep lastSalesPerson(String lastSalesPerson) {
        this.lastSalesPerson = lastSalesPerson;
        return this;
    }
    
    @Override
     public BuildStep license(String license) {
        this.license = license;
        return this;
    }
    
    @Override
     public BuildStep licenseImageUrl(String licenseImageUrl) {
        this.licenseImageUrl = licenseImageUrl;
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
    private CopyOfBuilder(String id, String firstName, String lastName, String phone, String email, String lastSalesPerson, String license, String licenseImageUrl) {
      super.id(id);
      super.firstName(firstName)
        .lastName(lastName)
        .phone(phone)
        .email(email)
        .lastSalesPerson(lastSalesPerson)
        .license(license)
        .licenseImageUrl(licenseImageUrl);
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
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
    
    @Override
     public CopyOfBuilder lastSalesPerson(String lastSalesPerson) {
      return (CopyOfBuilder) super.lastSalesPerson(lastSalesPerson);
    }
    
    @Override
     public CopyOfBuilder license(String license) {
      return (CopyOfBuilder) super.license(license);
    }
    
    @Override
     public CopyOfBuilder licenseImageUrl(String licenseImageUrl) {
      return (CopyOfBuilder) super.licenseImageUrl(licenseImageUrl);
    }
  }
  
}
