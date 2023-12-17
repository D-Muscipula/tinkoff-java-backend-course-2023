package edu.hw10.Task2;

import java.lang.reflect.Proxy;

public final class CacheProxy {


    public static <T> T create(T proxiedObject, Class<T> interfaceWeNeed, String path) {
        return interfaceWeNeed.cast(Proxy.newProxyInstance(
            interfaceWeNeed.getClassLoader(),
            new Class[] {interfaceWeNeed},
            new CacheInvocationHandler(proxiedObject, path)
        ));
    }

    private CacheProxy() {

    }
}
