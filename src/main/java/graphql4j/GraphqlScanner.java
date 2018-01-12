package graphql4j;

import graphql4j.blueprint.GraphqlSchema;
import graphql4j.stereotype.GraphqlQuery;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class GraphqlScanner {

    private final ClassLoader classLoader;

    public GraphqlScanner() {
        this(Thread.currentThread().getContextClassLoader());
    }

    public GraphqlScanner(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public GraphqlSchema scan(String packageToScan) {
        try {
            for (Class aClass : getClasses(packageToScan)) {
                Annotation schemaAnnot = aClass.getAnnotation(graphql4j.stereotype.GraphqlSchema.class);
                if (schemaAnnot != null) {
                    for (Method method : aClass.getMethods()) {
                        if (method.getDeclaringClass() != Object.class) {
                            for (Type type : method.getGenericParameterTypes()) {
                                System.out.println(type);
                            }
                            System.out.println(method);
                            if (method.isAnnotationPresent(GraphqlQuery.class)) {
                                System.out.println(method);
                            }
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        List<Class> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }


    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : Objects.requireNonNull(files)) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - ".class".length())));
            }
        }
        return classes;
    }

}
