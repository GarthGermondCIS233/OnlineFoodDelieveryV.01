package observer;

import java.util.ArrayList;
import java.util.List;
import order.Order;
import observer.OrderSubject;
import observer.OrderObserver;

public class OrderTrackingService implements OrderSubject {
   private List<OrderObserver> observers = new ArrayList<>();

   @Override
   public void attach(OrderObserver observer) {
      observers.add(observer);
   }

   @Override
   public void detach(OrderObserver observer) {
      observers.remove(observer);
   }

   @Override
   public void notifyObservers(Order order) {
      for (OrderObserver observer : observers) {
         observer.update(order);
      }
   }
}