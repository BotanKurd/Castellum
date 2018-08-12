package org.castellum.utils;

import org.castellum.fields.Field;
import org.castellum.logger.Logger;
import org.castellum.network.CastellumSession;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class Utils {

    public static String toString(File file) {
        try {
            return new String(java.nio.file.Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            Logger.writeError(e);
            return "";
        }
    }

    public static File getConfiguration(String database, String table) {
        return new File("database/" + database + "/" + table + "/configuration");
    }

    public static String getStringConfiguration(String database, String table) {
        return toString(new File("database/" + database + "/" + table + "/configuration"));
    }

    public static Field getFieldType(JSONArray fields, String field) {
        int size = fields.length();

        for (int i = 0; i < size; i++) {
            JSONObject object = fields.getJSONObject(i);
            if (object.has(field))
                return Field.values()[object.getInt(field)];
        }

        return null;
    }

    public static Object getObjectFromField(CastellumSession session, Field fieldType) throws IOException {
        switch (fieldType) {
            case BOOLEAN:
                return session.getInputStream().readBoolean();
            case BYTE:
                return session.getInputStream().readByte();
            case CHAR:
                return session.getInputStream().readChar();
            case SHORT:
                return session.getInputStream().readShort();
            case INTEGER:
                return session.getInputStream().readInt();
            case LONG:
                return session.getInputStream().readLong();
            case FLOAT:
                return session.getInputStream().readFloat();
            case DOUBLE:
                return session.getInputStream().readDouble();
            case STRING:
                return session.getInputStream().readUTF();

        }
        return null;
    }

    public static File[] getValues(String database, String table) {
        return new File("database/" + database + "/" + table + "/").listFiles((dir, name) ->
                !name.equals("configuration"));
    }
}
