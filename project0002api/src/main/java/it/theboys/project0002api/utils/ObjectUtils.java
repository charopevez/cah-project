package it.theboys.project0002api.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ObjectUtils<T> {

    public T updateObjectFromObject(T updatingObj, T sourceObj, List<String> filter) {
        Method[] methods = updatingObj.getClass().getMethods();

        for (Method fromMethod : methods) {
            if (analyzeMethod(updatingObj, fromMethod)) {

                String toName = getSetterName(fromMethod);


                try {
                    invokeNonNull(updatingObj, sourceObj, fromMethod, toName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return updatingObj;
    }

    private void invokeNonNull(T updatingObj, T sourceObj, Method fromMethod, String toName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method toMethod = updatingObj.getClass().getMethod(toName, fromMethod.getReturnType());
        Object value = fromMethod.invoke(sourceObj, (Object[]) null);
        if (value != null)
        {
            toMethod.invoke(updatingObj, value);
        }
    }


    private String getSetterName(Method fromMethod) {
        String methodName = fromMethod.getName();
        if (methodName.startsWith("get"))
            return methodName.replace("get", "set");
        else return methodName.replace("is", "set");
    }

    private Boolean analyzeMethod(T updatingObj, Method fromMethod) {
        boolean isGetter;
        // check if method name is in object methods list
        if (!fromMethod.getDeclaringClass().equals(updatingObj.getClass())) {
            return false;
        }
        // check if method is Getter
        isGetter = fromMethod.getName().startsWith("get") || fromMethod.getName().startsWith("is");
        return isGetter;
    }

}
