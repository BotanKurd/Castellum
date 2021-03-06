package org.castellum.network.handler.database;

import org.castellum.network.CastellumSession;
import org.castellum.network.api.NetworkHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateDatabase implements NetworkHandler {

    @Override
    public void handle(CastellumSession session) {
        if (session.isConnected()) {
            try {
                String database = session.getInputStream().readUTF();

                Path path = Paths.get("database/" + database);

                boolean valid = !Files.exists(path) && !database.isEmpty();

                if (valid)
                    valid = new File(path.toString()).mkdirs();

                session.writeReturnResponse(valid);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
