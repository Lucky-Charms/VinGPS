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

/** This is an auto generated class representing the CarClient type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "CarClients")
public final class CarClient implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField CAR = field("carClientCarId");
  public static final QueryField CLIENT = field("carClientClientId");
  public static final QueryField CAR_CLIENTS_ID = field("carClientsId");
  public static final QueryField CLIENT_CARS_ID = field("clientCarsId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Car", isRequired = true) @BelongsTo(targetName = "carClientCarId", type = Car.class) Car car;
  private final @ModelField(targetType="Client", isRequired = true) @BelongsTo(targetName = "carClientClientId", type = Client.class) Client client;
  private final @ModelField(targetType="ID") String carClientsId;
  private final @ModelField(targetType="ID") String clientCarsId;
  public String getId() {
      return id;
  }
  
  public Car getCar() {
      return car;
  }
  
  public Client getClient() {
      return client;
  }
  
  public String getCarClientsId() {
      return carClientsId;
  }
  
  public String getClientCarsId() {
      return clientCarsId;
  }
  
  private CarClient(String id, Car car, Client client, String carClientsId, String clientCarsId) {
    this.id = id;
    this.car = car;
    this.client = client;
    this.carClientsId = carClientsId;
    this.clientCarsId = clientCarsId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      CarClient carClient = (CarClient) obj;
      return ObjectsCompat.equals(getId(), carClient.getId()) &&
              ObjectsCompat.equals(getCar(), carClient.getCar()) &&
              ObjectsCompat.equals(getClient(), carClient.getClient()) &&
              ObjectsCompat.equals(getCarClientsId(), carClient.getCarClientsId()) &&
              ObjectsCompat.equals(getClientCarsId(), carClient.getClientCarsId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCar())
      .append(getClient())
      .append(getCarClientsId())
      .append(getClientCarsId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("CarClient {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("car=" + String.valueOf(getCar()) + ", ")
      .append("client=" + String.valueOf(getClient()) + ", ")
      .append("carClientsId=" + String.valueOf(getCarClientsId()) + ", ")
      .append("clientCarsId=" + String.valueOf(getClientCarsId()))
      .append("}")
      .toString();
  }
  
  public static CarStep builder() {
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
  public static CarClient justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new CarClient(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      car,
      client,
      carClientsId,
      clientCarsId);
  }
  public interface CarStep {
    ClientStep car(Car car);
  }
  

  public interface ClientStep {
    BuildStep client(Client client);
  }
  

  public interface BuildStep {
    CarClient build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep carClientsId(String carClientsId);
    BuildStep clientCarsId(String clientCarsId);
  }
  

  public static class Builder implements CarStep, ClientStep, BuildStep {
    private String id;
    private Car car;
    private Client client;
    private String carClientsId;
    private String clientCarsId;
    @Override
     public CarClient build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new CarClient(
          id,
          car,
          client,
          carClientsId,
          clientCarsId);
    }
    
    @Override
     public ClientStep car(Car car) {
        Objects.requireNonNull(car);
        this.car = car;
        return this;
    }
    
    @Override
     public BuildStep client(Client client) {
        Objects.requireNonNull(client);
        this.client = client;
        return this;
    }
    
    @Override
     public BuildStep carClientsId(String carClientsId) {
        this.carClientsId = carClientsId;
        return this;
    }
    
    @Override
     public BuildStep clientCarsId(String clientCarsId) {
        this.clientCarsId = clientCarsId;
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
    private CopyOfBuilder(String id, Car car, Client client, String carClientsId, String clientCarsId) {
      super.id(id);
      super.car(car)
        .client(client)
        .carClientsId(carClientsId)
        .clientCarsId(clientCarsId);
    }
    
    @Override
     public CopyOfBuilder car(Car car) {
      return (CopyOfBuilder) super.car(car);
    }
    
    @Override
     public CopyOfBuilder client(Client client) {
      return (CopyOfBuilder) super.client(client);
    }
    
    @Override
     public CopyOfBuilder carClientsId(String carClientsId) {
      return (CopyOfBuilder) super.carClientsId(carClientsId);
    }
    
    @Override
     public CopyOfBuilder clientCarsId(String clientCarsId) {
      return (CopyOfBuilder) super.clientCarsId(clientCarsId);
    }
  }
  
}
