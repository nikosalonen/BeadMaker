package beadMaker.arch;

import beadMaker.InterObjectCommunicator;
import beadMaker.InterObjectCommunicator.Subscriber;

public class InterObjectCommunicatorEventBus implements EventBus {
  private final InterObjectCommunicator delegate;

  public InterObjectCommunicatorEventBus(InterObjectCommunicator delegate) {
    this.delegate = delegate;
  }

  @Override
  public void publish(Object payload, Subscriber subscriber) {
    delegate.communicate(payload, subscriber.name());
  }

  @Override
  public void publish(String descriptor, Object payload, Subscriber subscriber) {
    delegate.communicate(descriptor, payload, subscriber.name());
  }

  @Override
  public Object request(Object request, Subscriber subscriber) {
    return delegate.request(request, subscriber.name());
  }

  @Override
  public Object request(String descriptor, Object request, Subscriber subscriber) {
    return delegate.request(descriptor, request, subscriber.name());
  }
}
