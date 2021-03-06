package org.castellum.network.api;

import org.castellum.network.CastellumSession;

import java.nio.channels.AsynchronousSocketChannel;

public interface NetworkHandler {

    void handle(CastellumSession session);

}
