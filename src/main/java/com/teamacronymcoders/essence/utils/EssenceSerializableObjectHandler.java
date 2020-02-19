package com.teamacronymcoders.essence.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.teamacronymcoders.essence.api.modifier.core.Modifier;
import com.teamacronymcoders.essence.impl.serializable.recipe.SerializableModifier;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public class EssenceSerializableObjectHandler {

    // SerializableModifier
    public static SerializableModifier readSerializableModifier(PacketBuffer buffer) {
        final Modifier modifier = EssenceRegistration.MODIFIER_REGISTRY.getValue(new ResourceLocation(buffer.readString(0)));
        final int level = buffer.readInt();
        final String operator = buffer.readString(1);
        return new SerializableModifier(modifier, level, SerializableModifier.Operation.valueOf(operator));
    }

    public static void writeSerializableModifier(PacketBuffer buffer, SerializableModifier serializableModifier) {
        buffer.writeString(serializableModifier.getModifier().getRegistryName().toString(), 0);
        buffer.writeInt(serializableModifier.getLevel());
        buffer.writeString(serializableModifier.getOperation().getName(), 1);
    }

    public static JsonObject writeSerializableModifier(SerializableModifier serializableModifier) {
        JsonObject object = new JsonObject();
        object.addProperty("modifier", serializableModifier.getModifier().getRegistryName().toString());
        object.addProperty("level", serializableModifier.getLevel());
        object.addProperty("operation", serializableModifier.getOperation().getName());
        return object;
    }

    public static SerializableModifier readSerializableModifier(JsonElement element) {
        JsonObject object = element.getAsJsonObject();
        Modifier modifier = EssenceRegistration.MODIFIER_REGISTRY.getValue(new ResourceLocation(object.get("modifier").getAsString()));
        int level = object.get("level").getAsInt();
        SerializableModifier.Operation operation = SerializableModifier.Operation.valueOf(object.get("operation").getAsString());
        return new SerializableModifier(modifier, level, operation);
    }

    // SerializableModifier[]
    public static SerializableModifier[] readSerializableModifierArray(PacketBuffer buffer) {
        SerializableModifier[] serializableModifiers = new SerializableModifier[buffer.readInt()];
        for (int i = 0; i < serializableModifiers.length; i++) {
            final Modifier modifier = EssenceRegistration.MODIFIER_REGISTRY.getValue(new ResourceLocation(buffer.readString(0)));
            final int level = buffer.readInt();
            final String operator = buffer.readString(1);
            serializableModifiers[i] = new SerializableModifier(modifier, level, SerializableModifier.Operation.valueOf(operator));
        }
        return serializableModifiers;
    }

    public static void writeSerializableModifierArray(PacketBuffer buffer, SerializableModifier[] serializableModifiers) {
        buffer.writeInt(serializableModifiers.length);
        for (SerializableModifier serializableModifier : serializableModifiers) {
            buffer.writeString(serializableModifier.getModifier().getRegistryName().toString(), 0);
            buffer.writeInt(serializableModifier.getLevel());
            buffer.writeString(serializableModifier.getOperation().getName(), 1);
        }
    }

    public static JsonArray writeSerializableModifierArray(SerializableModifier[] serializableModifiers) {
        JsonArray array = new JsonArray();
        for (SerializableModifier serializableModifier : serializableModifiers) {
            JsonObject object = new JsonObject();
            object.addProperty("modifier", serializableModifier.getModifier().getRegistryName().toString());
            object.addProperty("level", serializableModifier.getLevel());
            object.addProperty("operation", serializableModifier.getOperation().getName());
            array.add(object);
        }
        return array;
    }

    public static SerializableModifier[] readSerializableModifierArray(JsonElement element) {
        JsonArray array = element.getAsJsonArray();
        SerializableModifier[] serializableModifiers = new SerializableModifier[array.size()];
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.get(i).getAsJsonObject();
            Modifier modifier = EssenceRegistration.MODIFIER_REGISTRY.getValue(new ResourceLocation(object.get("modifier").getAsString()));
            int level = object.get("level").getAsInt();
            SerializableModifier.Operation operation = SerializableModifier.Operation.valueOf(object.get("operation").getAsString());
            serializableModifiers[i] = new SerializableModifier(modifier, level, operation);
        }
        return serializableModifiers;
    }
}
