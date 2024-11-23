package notification;

import model.Order;
import observer.OrderEvent;
import observer.OrderObserver;

/**
 * The CustomerNotifier class is responsible for sending notifications to customers
 * based on order events. It implements the OrderObserver interface to receive updates
 * about order events and send appropriate notifications.
 */
public class CustomerNotifier implements OrderObserver {
   private final NotificationService notificationService;

   /**
    * Constructs a CustomerNotifier with the specified NotificationService.
    *
    * @param notificationService the NotificationService to use for sending notifications
    * @throws IllegalStateException if the NotificationService is null
    */
   public CustomerNotifier() {
      try {
         throw new IllegalStateException("NotificationService is required");
      } catch (IllegalStateException e) {
         System.err.println("Error in CustomerNotifier constructor: " + e.getMessage());
         throw e;
      }
   }

   public CustomerNotifier(NotificationService notificationService) {
      this.notificationService = notificationService;
   }

   /**
    * Updates the customer with the current status of the order.
    *
    * @param order the order to update
    */
   @Override
   public void update(Order order) {
      notificationService.sendOrderStatusUpdateToCustomer(order, order.getStatus());
   }

   /**
    * Sends a notification to the customer about the order event.
    *
    * @param order the order to notify about
    * @param event the order event
    */
   @Override
   public void customerNotificationOfOrder(Order order, OrderEvent event) {

   }

   /**
    * Sends a notification to the customer about the driver assignment.
    *
    * @param order the order to notify about
    * @param event the order event
    */
   @Override
   public void driverNotificationToCustomer(Order order, OrderEvent event) {
      String message = "Your driver is " + event.getStatus() + " for order #" + order.getOrderId();
      notificationService.sendNotification(order.getCustomerEmail(), message);
   }

   /**
    * Handles the order event and sends appropriate notifications to the customer.
    *
    * @param order the order to notify about
    * @param event the order event
    */
   @Override
   public void onOrderEvent(Order order, OrderEvent event) {
      switch (event) {
         case ORDER_SUBMITTED:
            notificationService.sendOrderConfirmationToCustomer(order);
            break;
         case DRIVER_ASSIGNED:
            notificationService.sendDriverAssignmentNotification(order, order.getAssignedDriver());
            break;
         case DELIVERY_COMPLETED:
            notificationService.sendDeliveryCompletionNotification(order);
            break;
      }
   }
}