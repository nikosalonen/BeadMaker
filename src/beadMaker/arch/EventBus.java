package beadMaker.arch;

import beadMaker.InterObjectCommunicator.Subscriber;

public interface EventBus {
    void publish(Object payload, Subscriber subscriber);
    void publish(String descriptor, Object payload, Subscriber subscriber);
    Object request(Object request, Subscriber subscriber);
    Object request(String descriptor, Object request, Subscriber subscriber);
}


