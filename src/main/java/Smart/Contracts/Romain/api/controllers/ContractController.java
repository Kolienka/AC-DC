package Smart.Contracts.Romain.api.controllers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class ContractController {
    private static final String PACKAGE_NAME = "org.web3j";

    public static String[] listParametersOfMethod(String scriptName)
            throws ClassNotFoundException, IOException {
        Class c = getScriptClass(scriptName);
        Method[] methods = c.getMethods();
        Object[] object_methods = Arrays.stream(methods)
                .filter( method -> method.getName().equalsIgnoreCase("sort")).toArray();

        String[] ret = new String[object_methods.length];
        for (int i = 0 ; i < object_methods.length ; i++) {
            String str = getSignature((Method) object_methods[i]);
            String[] arrOfStr = str.split("\\)");
            String[] arrOfStr2 = arrOfStr[0].split("\\(");
            String s = arrOfStr2[1];
            ret[i] = s;
        }
        return ret;
    }

    public static Class getScriptClass(String scriptName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = PACKAGE_NAME.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, PACKAGE_NAME));
        }
        Class[] allClasses = classes.toArray(new Class[classes.size()]);
        Object[] filteredClass = Arrays.stream(allClasses)
                .filter(
                        actualClass -> actualClass.getSimpleName().equalsIgnoreCase(scriptName)).toArray();
        Class ret = null;
        if(filteredClass.length == 1){
            ret = (Class) filteredClass[0];
        }
        else {
            System.out.println("No class found for this script name");
        }
        return ret;
    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    public static String getSignature(Method m){
        String sig;
        try {
            Field gSig = Method.class.getDeclaredField("signature");
            gSig.setAccessible(true);
            sig = (String) gSig.get(m);
            if(sig!=null) return sig;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder("(");
        for(Class<?> c : m.getParameterTypes())
            sb.append((sig = Array.newInstance(c, 0).toString())
                    .substring(1, sig.indexOf('@')));
        return sb.append(')')
                .append(
                        m.getReturnType() == void.class?"V":
                                (sig = Array.newInstance(m.getReturnType(), 0).toString()).substring(1, sig.indexOf('@'))
                )
                .toString();
    }

}
