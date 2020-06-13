package net.sia.addon.util;

public interface IReflectionObject {
    <E extends IReflectionObject> E setAccessible(boolean value);

    ReflectionUtil newCall();
}
