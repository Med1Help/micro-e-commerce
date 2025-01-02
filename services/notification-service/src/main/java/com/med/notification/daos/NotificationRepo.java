package com.med.notification.daos;

import com.med.notification.enteties.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepo extends MongoRepository<Notification, String> {

}
