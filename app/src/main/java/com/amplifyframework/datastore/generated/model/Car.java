package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;

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

/** This is an auto generated class representing the Car type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Cars")
public final class Car implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField MAKE = field("make");
  public static final QueryField MODEL = field("model");
  public static final QueryField COLOR = field("color");
  public static final QueryField PRICE = field("price");
  public static final QueryField VIN = field("vin");
  public static final QueryField GPS = field("gps");
  public static final QueryField STATUS = field("status");
  public static final QueryField IMAGE_URL = field("imageUrl");
  public static final QueryField CLIENT = field("carClientId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String make;
  private final @ModelField(targetType="String") String model;
  private final @ModelField(targetType="String") String color;
  private final @ModelField(targetType="String") String price;
  private final @ModelField(targetType="String") String vin;
  private final @ModelField(targetType="String") String gps;
  private final @ModelField(targetType="String") String status;
  private final @ModelField(targetType="String") String imageUrl;
  private final @ModelField(targetType="Client") @BelongsTo(targetName = "carClientId", type = Client.class) Client client;
  public String getId() {
      return id;
  }
  
  public String getMake() {
      return make;
  }
  
  public String getModel() {
      return model;
  }
  
  public String getColor() {
      return color;
  }
  
  public String getPrice() {
      return price;
  }
  
  public String getVin() {
      return vin;
  }
  
  public String getGps() {
      return gps;
  }
  
  public String getStatus() {
      return status;
  }
  
  public String getImageUrl() {
      return imageUrl;
  }
  
  public Client getClient() {
      return client;
  }
  
  private Car(String id, String make, String model, String color, String price, String vin, String gps, String status, String imageUrl, Client client) {
    this.id = id;
    this.make = make;
    this.model = model;
    this.color = color;
    this.price = price;
    this.vin = vin;
    this.gps = gps;
    this.status = status;
    this.imageUrl = imageUrl;
    this.client = client;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Car car = (Car) obj;
      return ObjectsCompat.equals(getId(), car.getId()) &&
              ObjectsCompat.equals(getMake(), car.getMake()) &&
              ObjectsCompat.equals(getModel(), car.getModel()) &&
              ObjectsCompat.equals(getColor(), car.getColor()) &&
              ObjectsCompat.equals(getPrice(), car.getPrice()) &&
              ObjectsCompat.equals(getVin(), car.getVin()) &&
              ObjectsCompat.equals(getGps(), car.getGps()) &&
              ObjectsCompat.equals(getStatus(), car.getStatus()) &&
              ObjectsCompat.equals(getImageUrl(), car.getImageUrl()) &&
              ObjectsCompat.equals(getClient(), car.getClient());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getMake())
      .append(getModel())
      .append(getColor())
      .append(getPrice())
      .append(getVin())
      .append(getGps())
      .append(getStatus())
      .append(getImageUrl())
      .append(getClient())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Car {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("make=" + String.valueOf(getMake()) + ", ")
      .append("model=" + String.valueOf(getModel()) + ", ")
      .append("color=" + String.valueOf(getColor()) + ", ")
      .append("price=" + String.valueOf(getPrice()) + ", ")
      .append("vin=" + String.valueOf(getVin()) + ", ")
      .append("gps=" + String.valueOf(getGps()) + ", ")
      .append("status=" + String.valueOf(getStatus()) + ", ")
      .append("imageUrl=" + String.valueOf(getImageUrl()) + ", ")
      .append("client=" + String.valueOf(getClient()))
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
  public static Car justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Car(
      id,
      null,
      null,
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
      make,
      model,
      color,
      price,
      vin,
      gps,
      status,
      imageUrl,
      client);
  }
  public interface BuildStep {
    Car build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep make(String make);
    BuildStep model(String model);
    BuildStep color(String color);
    BuildStep price(String price);
    BuildStep vin(String vin);
    BuildStep gps(String gps);
    BuildStep status(String status);
    BuildStep imageUrl(String imageUrl);
    BuildStep client(Client client);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String make;
    private String model;
    private String color;
    private String price;
    private String vin;
    private String gps;
    private String status;
    private String imageUrl;
    private Client client;
    @Override
     public Car build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Car(
          id,
          make,
          model,
          color,
          price,
          vin,
          gps,
          status,
          imageUrl,
          client);
    }
    
    @Override
     public BuildStep make(String make) {
        this.make = make;
        return this;
    }
    
    @Override
     public BuildStep model(String model) {
        this.model = model;
        return this;
    }
    
    @Override
     public BuildStep color(String color) {
        this.color = color;
        return this;
    }
    
    @Override
     public BuildStep price(String price) {
        this.price = price;
        return this;
    }
    
    @Override
     public BuildStep vin(String vin) {
        this.vin = vin;
        return this;
    }
    
    @Override
     public BuildStep gps(String gps) {
        this.gps = gps;
        return this;
    }
    
    @Override
     public BuildStep status(String status) {
        this.status = status;
        return this;
    }
    
    @Override
     public BuildStep imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
    
    @Override
     public BuildStep client(Client client) {
        this.client = client;
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
    private CopyOfBuilder(String id, String make, String model, String color, String price, String vin, String gps, String status, String imageUrl, Client client) {
      super.id(id);
      super.make(make)
        .model(model)
        .color(color)
        .price(price)
        .vin(vin)
        .gps(gps)
        .status(status)
        .imageUrl(imageUrl)
        .client(client);
    }
    
    @Override
     public CopyOfBuilder make(String make) {
      return (CopyOfBuilder) super.make(make);
    }
    
    @Override
     public CopyOfBuilder model(String model) {
      return (CopyOfBuilder) super.model(model);
    }
    
    @Override
     public CopyOfBuilder color(String color) {
      return (CopyOfBuilder) super.color(color);
    }
    
    @Override
     public CopyOfBuilder price(String price) {
      return (CopyOfBuilder) super.price(price);
    }
    
    @Override
     public CopyOfBuilder vin(String vin) {
      return (CopyOfBuilder) super.vin(vin);
    }
    
    @Override
     public CopyOfBuilder gps(String gps) {
      return (CopyOfBuilder) super.gps(gps);
    }
    
    @Override
     public CopyOfBuilder status(String status) {
      return (CopyOfBuilder) super.status(status);
    }
    
    @Override
     public CopyOfBuilder imageUrl(String imageUrl) {
      return (CopyOfBuilder) super.imageUrl(imageUrl);
    }
    
    @Override
     public CopyOfBuilder client(Client client) {
      return (CopyOfBuilder) super.client(client);
    }
  }
  
}
